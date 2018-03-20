import java.util.*;

public class Excel {
    private final static int BASE = 'A';
    private final Expression[][] spreadsheet;

    public Excel(int H, char W) {
        this.spreadsheet = new Expression[H+1][W - BASE+1];
    }

    public void set(int r, char c, int v) {
        spreadsheet[r][c - BASE] = new Value(v);
    }

    public int get(int r, char c) {
        Expression cell = spreadsheet[r][c - BASE];
        if (cell != null) {
            return cell.evaluate(this);
        } else {
            return 0;
        }
    }

    public int sum(int r, char c, String[] strs) {
        Expression[] expressions = new Expression[strs.length];

        for (int i = 0; i < strs.length; i++) {
            String[] split = strs[i].split(":");
            if (split.length == 1) {
                expressions[i] = new Reference(split[0]);
            } else {
                String topLeft = split[0];
                String bottomRight = split[1];
                expressions[i] = new Rectangle(topLeft, bottomRight);
            }
        }

        spreadsheet[r][c - BASE] = new Formula(expressions);
        return spreadsheet[r][c - BASE].evaluate(this);
    }

    private int getRow(String cell) {
        return Integer.valueOf(cell.substring(1));
    }

    private int getColumn(String cell) {
        return cell.charAt(0) - BASE;
    }

    private interface Expression {
        int evaluate(Excel excel);
    }

    private class Value implements Expression {
        private final int value;

        Value(int value) {
            this.value = value;
        }

        public int evaluate(Excel excel) {
            return value;
        }
    }

    private class Formula implements Expression {
        private final Expression[] expressions;

        Formula(Expression[] expressions) {
            this.expressions = expressions;
        }

        public int evaluate(Excel excel) {
            int value = 0;

            for (Expression expression : expressions) {
                value += expression.evaluate(excel);
            }

            return value;
        }
    }

    private class Reference implements Expression {
        private final int row;
        private final int column;

        Reference(String cell) {
            this.row = getRow(cell);
            this.column = getColumn(cell);
        }

        public int evaluate(Excel excel) {
            Expression cell = excel.spreadsheet[row][column];
            if (cell != null) {
                return cell.evaluate(excel);
            } else {
                return 0;
            }
        }
    }

    private class Rectangle implements Expression {
        private final int rowStart;
        private final int rowEnd;
        private final int columnStart;
        private final int columnEnd;

        Rectangle(String topLeft, String bottomRight) {
            this.rowStart = getRow(topLeft);
            this.rowEnd = getRow(bottomRight);
            this.columnStart = getColumn(topLeft);
            this.columnEnd = getColumn(bottomRight);
        }

        public int evaluate(Excel excel) {
            int value = 0;

            for (int i = rowStart; i <= rowEnd; i++) {
                for (int j = columnStart; j <= columnEnd; j++) {
                    Expression cell = excel.spreadsheet[i][j];
                    if (cell != null) {
                        value += cell.evaluate(excel);
                    }
                }
            }

            return value;
        }
    }
}
