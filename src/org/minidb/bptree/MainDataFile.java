package org.minidb.bptree;

import org.minidb.exception.MiniDBException;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Predicate;

public class MainDataFile {
    MainDataConfiguration conf;
    private RandomAccessFile file;
    private TreeSet<Long> freeSlots; // each element reflects a free slot

    public long getElementCount() {
        return elementCount;
    }

    private long elementCount; // number of elements
    private long totalPages; // number of pages in the file, can be counted from file length
    public BPlusTree rowID2position;

    public MainDataFile(MainDataConfiguration conf, String mode,
                     String filePath, BPlusTree rowID2position)
            throws IOException, MiniDBException {
        this.conf = conf;
        this.elementCount = 0L;
        this.totalPages = 1L;
        this.freeSlots = new TreeSet<>();
        this.rowID2position = rowID2position;

        File f = new File(filePath);
        String stmode = mode.substring(0, 2);
        file = new RandomAccessFile(filePath, stmode);
        if(f.exists() && !mode.contains("+")) {
            file.seek(0);
            totalPages = file.length() / conf.pageSize;
            elementCount = file.readLong();
            long pindex = file.readLong();
            byte[] buffer = new byte[conf.pageSize];
            // read the free pages
            while (pindex != -1L) {
                freeSlots.add(pindex);
                file.seek(pindex);
                file.read(buffer, 0, buffer.length);
                ByteBuffer bbuffer = ByteBuffer.wrap(buffer);bbuffer.order(ByteOrder.BIG_ENDIAN);
                pindex = bbuffer.getLong();
                for (int i = 0; i < conf.nValidPointerInFreePage; i++) {
                    long index = bbuffer.getLong();
                    if(index != -1L)
                    {
                        freeSlots.add(index);
                    }else {
                        break;
                    }
                }
            }
        }
        else {
            file.setLength(conf.pageSize); // initial tree have 2 pages. head page and root page
            file.seek(0);
            file.writeLong(elementCount);
            file.writeLong(-1L);
        }
    }

    private long getFirstAvailablePageIndex() throws IOException {
        // check if we have unused pages
        if(freeSlots.size() == 0)
        {// file length == conf.pageSize * totalPages, allocate new pages
            long ALLOCATE_NEW_PAGES = 10L;
            long tmp = totalPages;
            totalPages += ALLOCATE_NEW_PAGES;
            file.setLength(conf.pageSize * totalPages);
            for (long i = tmp; i < tmp + ALLOCATE_NEW_PAGES; ++i)
            {
                freeSlots.add(i * conf.pageSize);
            }
        }
        return freeSlots.pollFirst();
    }

    public void insertRow(ArrayList<Object> key, long rowID) throws IOException, MiniDBException {
        long position = getFirstAvailablePageIndex();
        file.seek(position);
        byte[] buffer = new byte[conf.pageSize];
        ByteBuffer bbuffer = ByteBuffer.wrap(buffer);bbuffer.order(ByteOrder.BIG_ENDIAN);
        bbuffer.putLong(rowID);
        conf.writeKey(bbuffer, key);
        rowID2position.insertPair(new ArrayList<Object>(Arrays.asList(rowID)), position);
        elementCount += 1;
        file.write(buffer);
    }

    public void deleteRow(long rowID) throws IOException, MiniDBException {
        long position = rowID2position.search(new ArrayList<Object>(Arrays.asList(rowID))).get(0);
        if(freeSlots.contains(position))
        {
            throw new MiniDBException(String.format("The row %d to delete does not exist!", rowID));
        }
        freeSlots.add(position);
        rowID2position.deletePair(new ArrayList<Object>(Arrays.asList(rowID)), position);
        elementCount -= 1;
    }

    public ArrayList<Object> readRow(long rowID) throws IOException, MiniDBException {
        long position = rowID2position.search(new ArrayList<Object>(Arrays.asList(rowID))).get(0);
        if(freeSlots.contains(position))
        {
            throw new MiniDBException(String.format("The row %d does not exist!", rowID));
        }
        byte[] buffer = new byte[conf.pageSize];
        file.read(buffer);
        ByteBuffer bbuffer = ByteBuffer.wrap(buffer);bbuffer.order(ByteOrder.BIG_ENDIAN);
        bbuffer.getLong();
        return conf.readKey(bbuffer);
    }

    public void updateRow(long rowID, ArrayList<Object> newKey) throws IOException, MiniDBException {
        long position = rowID2position.search(new ArrayList<Object>(Arrays.asList(rowID))).get(0);
        byte[] buffer = new byte[conf.pageSize];
        ByteBuffer bbuffer = ByteBuffer.wrap(buffer);bbuffer.order(ByteOrder.BIG_ENDIAN);
        bbuffer.putLong(rowID);
        conf.writeKey(bbuffer, newKey);
        file.seek(position);
        file.write(buffer);
    }

    public static class SearchResult{
        public ArrayList<Object> key;
        public long rowID;

        public SearchResult(ArrayList<Object> key, long rowID) {
            this.key = key;
            this.rowID = rowID;
        }

        public SearchResult() {
            key = null;
            rowID = 0;
        }
    }

    // linear scan
    public LinkedList<SearchResult> searchRows(Function<SearchResult, Boolean> pred) throws IOException {
        long length = file.length();
        long[] positions = new long[(int)length / conf.pageSize];
        int index = 0;
        for (long i = conf.pageSize; i < length; i += conf.pageSize) {
            if(!freeSlots.contains(i))
            {
                positions[index++] = i;
            }
        }
        LinkedList<SearchResult> ans = new LinkedList<>();
        for (int i = 0; i < index; i++) {
            long position = positions[i];
            file.seek(position);
            byte[] buffer = new byte[conf.pageSize];
            file.read(buffer);
            ByteBuffer bbuffer = ByteBuffer.wrap(buffer);bbuffer.order(ByteOrder.BIG_ENDIAN);
            SearchResult each = new SearchResult();
            each.rowID = bbuffer.getLong();
            each.key = conf.readKey(bbuffer);
            if(pred.apply(each))
            {
                ans.add(each);
            }
        }
        return ans;
    }

    public void close() throws IOException, MiniDBException {
        // allocate space when there are no free slots
        long position = getFirstAvailablePageIndex();
        freeSlots.add(position);

        // trim file
        TreeSet<Long> tailFreePages = new TreeSet<>();
        long lastPos = file.length() - conf.pageSize;
        while (!freeSlots.isEmpty())
        {
            long last = freeSlots.pollLast();
            if(lastPos == last)
            {
                tailFreePages.add(last);
                lastPos -= conf.pageSize;
            }else{
                break;
            }
        }
        if(tailFreePages.size() < 20)
        {// waste some pages, ok
            freeSlots.addAll(tailFreePages);
        }else {// too many free pages at the last, trim the file
            for (int i = 0; i < 20; i++) {
                freeSlots.add(tailFreePages.pollFirst());
            }
            file.setLength(file.length() - conf.pageSize * tailFreePages.size());
        }

        Long[] positions = new Long[freeSlots.size()];
        freeSlots.toArray(positions);

        int pages = freeSlots.size() / conf.nValidPointerInFreePage;
        if(freeSlots.size() % conf.nValidPointerInFreePage != 0)
        {
            pages += 1;
        }

        file.seek(0);
        file.writeLong(elementCount);
        file.writeLong(pages == 0 ? -1L : positions[0]);

        byte[] buffer = new byte[conf.pageSize];

        int ipage = 0, ifree = 1; // the first position is written in the file header
        while (ipage < pages && ifree < positions.length)
        {
            file.seek(positions[ipage++]);
            ByteBuffer bbuffer = ByteBuffer.wrap(buffer);bbuffer.order(ByteOrder.BIG_ENDIAN);
            if(ipage == pages)
            {// end
                bbuffer.putLong(-1L);
            }else {
                bbuffer.putLong(positions[ipage]);
            }
            for (int i = 0; i < conf.nValidPointerInFreePage; i++) {
                if(ifree >= positions.length)
                {
                    bbuffer.putLong(-1L);
                    break;
                }
                bbuffer.putLong(positions[ifree++]);
            }
            file.write(buffer);
        }
        file.close();
        rowID2position.commitTree();
    }
}
