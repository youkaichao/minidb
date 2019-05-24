package org.minidb.grammar;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class AccumulateErrorListener extends BaseErrorListener {

    private StringBuilder builder;
    private int count;

    public AccumulateErrorListener() {
        builder = new StringBuilder();
        count = 0;
    }

    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        builder.append("line " + line + ":" + charPositionInLine + " " + msg);
        count += 1;
    }

    public String getAllMessage()
    {
        return builder.toString();
    }

    public boolean hasError()
    {
        return count != 0;
    }
}
