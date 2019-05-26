grammar minisql;

sql_stmt :
           K_CREATE K_TABLE table_name '(' column_def ( ',' column_def )* ( ',' table_constraint )* ')' EOF # create_table
         | K_INSERT K_INTO table_name ( '(' column_name ( ',' column_name )* ')' )? ( K_VALUES row ( ',' row )* ) EOF # insert_table
         | K_DELETE K_FROM table_name ( K_WHERE logical_expr )? EOF # delete_table
         | K_DROP K_TABLE table_name EOF # drop_table
         | K_UPDATE table_name K_SET column_name '=' literal_value ( ',' column_name '=' literal_value )* ( K_WHERE logical_expr )? EOF # update_table
         | K_SELECT result_column ( ',' result_column )* K_FROM table_name (join_operator table_name join_constraint?)? ( K_WHERE logical_expr )? EOF # select_table
         | K_SHOW K_TABLE IDENTIFIER EOF # show_table
         | K_CREATE K_DATABASE IDENTIFIER EOF # create_db
         | K_DROP K_DATABASE IDENTIFIER EOF # drop_db
         | K_USE K_DATABASE IDENTIFIER EOF # use_db
         | K_SHOW K_DATABASES EOF # show_dbs
         | K_SHOW K_DATABASE IDENTIFIER EOF # show_db
         | K_EXIT EOF # exit
         | ('#' .*?)? EOF # comment
 ;

row : '(' literal_value ( ',' literal_value )* ')';

column_def
 : column_name type_name ( K_PRIMARY K_KEY | K_NOT K_NULL | K_UNIQUE )?
 ;

type_name
 : K_INT | K_LONG | K_FLOAT | K_DOUBLE | (K_VARCHAR '(' NUMERIC_LITERAL ')')
 ;

table_constraint
 : ( K_PRIMARY K_KEY | K_UNIQUE ) '(' column_name ( ',' column_name )* ')'
 ;

logical_expr
 : logical_expr K_AND logical_expr
 | logical_expr K_OR logical_expr
 | '(' logical_expr K_AND logical_expr ')'
 | '(' logical_expr K_OR logical_expr ')'
 | value_expr ( K_LT | K_LE | K_GT | K_GE | K_EQ | K_NEQ ) value_expr
 ;

value_expr
 : literal_value
 | ( table_name '.' )? column_name
 ;

result_column
 : column_name
 | table_name '.' column_name
 ;

join_operator
 : K_CARTESIAN
 | K_NATURAL? K_JOIN
 ;

join_constraint
 : K_ON logical_expr
 | K_USING '(' column_name ( ',' column_name )* ')'
 ;

literal_value
 : ( '+' | '-' )? NUMERIC_LITERAL
 | STRING_LITERAL
 | K_NULL
 ;

table_name 
 : IDENTIFIER
 ;

column_name 
 : IDENTIFIER
 ;

K_AND : A N D;
K_CREATE : C R E A T E;
K_DATABASE : D A T A B A S E;
K_DATABASES : D A T A B A S E S;
K_DELETE : D E L E T E;
K_DISTINCT : D I S T I N C T;
K_DROP : D R O P;
K_EXISTS : E X I S T S;
K_FROM : F R O M;
K_IF : I F;
K_IN : I N;
K_INSERT : I N S E R T;
K_INTO : I N T O;
K_IS : I S;
K_ISNULL : I S N U L L;
K_JOIN : J O I N;
K_KEY : K E Y;
K_NATURAL : N A T U R A L;
K_NO : N O;
K_NOT : N O T;
K_NOTNULL : N O T N U L L;
K_NULL : N U L L;
K_ON : O N;
K_OR : O R;
K_PRIMARY : P R I M A R Y;
K_RECURSIVE : R E C U R S I V E;
K_SELECT : S E L E C T;
K_SET : S E T;
K_TABLE : T A B L E;
K_UNIQUE : U N I Q U E;
K_UPDATE : U P D A T E;
K_USING : U S I N G;
K_VALUES : V A L U E S;
K_WHERE : W H E R E;
K_WITH : W I T H;
K_INT: I N T;
K_LONG: L O N G;
K_FLOAT: F L O A T;
K_DOUBLE: D O U B L E;
K_VARCHAR: V A R C H A R;
K_USE: U S E;
K_SHOW: S H O W;
K_EXIT: E X I T;
K_LT: '<';
K_LE: '<=';
K_GT: '>';
K_GE: '>=';
K_EQ: '=';
K_NEQ: '<>';
K_CARTESIAN: ',';

IDENTIFIER
 : [a-zA-Z_] [a-zA-Z_0-9]*
 ;

NUMERIC_LITERAL
 : DIGIT+ ( '.' DIGIT* )?
 | '.' DIGIT+
 ;

STRING_LITERAL
 : '\'' ( ~'\'' | '\'\'' )* '\''
 ;

SPACES
 : [ \t\r\n] -> channel(HIDDEN)
 ;

fragment DIGIT : [0-9];
fragment NZDIGIT : [1-9];

fragment A : [aA];
fragment B : [bB];
fragment C : [cC];
fragment D : [dD];
fragment E : [eE];
fragment F : [fF];
fragment G : [gG];
fragment H : [hH];
fragment I : [iI];
fragment J : [jJ];
fragment K : [kK];
fragment L : [lL];
fragment M : [mM];
fragment N : [nN];
fragment O : [oO];
fragment P : [pP];
fragment Q : [qQ];
fragment R : [rR];
fragment S : [sS];
fragment T : [tT];
fragment U : [uU];
fragment V : [vV];
fragment W : [wW];
fragment X : [xX];
fragment Y : [yY];
fragment Z : [zZ];
