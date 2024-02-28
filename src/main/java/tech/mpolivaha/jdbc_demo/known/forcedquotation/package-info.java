/**
 * https://www.postgresql.org/docs/current/sql-syntax-lexical.html
 * <p>
 * CC:
 * There is a second kind of identifier: the delimited identifier or quoted identifier. It is formed by enclosing an arbitrary sequence of characters
 * in double-quotes ("). A delimited identifier is always an identifier, never a key word. So "select" could be used to refer to a column or table
 * named “select”, whereas an unquoted select would be taken as a key word and would therefore provoke a parse error when used where a table or column
 * name is expected.
 */
package tech.mpolivaha.jdbc_demo.known.forcedquotation;