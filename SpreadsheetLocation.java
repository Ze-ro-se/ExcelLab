public class SpreadsheetLocation implements Location {
    private int row;
    private int col;
    public SpreadsheetLocation(String str) { //convert row and column to integers
        str = str.trim();
        if (str.length() == 3) {
            this.row = Integer.parseInt(str.substring(1)) - 1;
        } else if (str.length() == 2) {
            this.row = Integer.parseInt(str.substring(1)) - 1;
        }
        this.col = str.charAt(0) - 'A';
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getCol() {
        return col;
    }
}
