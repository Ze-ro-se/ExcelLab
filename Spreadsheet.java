import java.util.*;
public class Spreadsheet implements Grid
{
    Cell[][] data;
    public final int x = 4;
    public final int y = 6;
    ArrayList<String> histArray = new ArrayList<>();
    private boolean history;
    private int histSize;
    public Spreadsheet() { //create a spreadsheet full of empty cells
        data = new Cell[x][y];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                data[j][i] = new EmptyCell();
            }
        }
    }
    @Override
    public String processCommand(String command) {
        // TODO Auto-generated method stub
        String[] split = command.split(" ", 3);
        try {
            if (history) { //add values to history array
                if (!command.contains("history")) {
                    if (histArray.size() < histSize) {
                        histArray.add(0, command);
                    } else {
                        histArray.remove(histSize - 1);
                        histArray.add(0, command);
                    }
                }
            }
            if (split.length == 1) {
                if (split[0].equals("clear")) { //clear whole grid
                    for (int i = 0; i < y; i++) {
                        for (int j = 0; j < x; j++) {
                            data[i][j] = new EmptyCell();
                        }
                    }
                    return getGridText();
                } else { //Cell inspection
                    SpreadsheetLocation loc = new SpreadsheetLocation(command);
                    return data[loc.getRow()][loc.getCol()].fullCellText();
                }
            } else if (split.length == 2) { //clear specific cell
                SpreadsheetLocation loc = new SpreadsheetLocation(split[1]);
                if (split[0].contains("clear")) {
                    data[loc.getRow()][loc.getCol()] = new EmptyCell();
                    return getGridText();
                } else if (split[0].contains("type")) { //get the type of cell
                    if (data[loc.getRow()][loc.getCol()] instanceof RealCell) { //test
                        if (data[loc.getRow()][loc.getCol()] instanceof ValueCell) {
                            return "Real Value";
                        } else if (data[loc.getRow()][loc.getCol()] instanceof PercentCell) {
                            return "Real Percent";
                        } else if (data[loc.getRow()][loc.getCol()] instanceof FormulaCell) {
                            return "Real Formula";
                        }
                    } else if (data[loc.getRow()][loc.getCol()] instanceof TextCell) { //test
                        return "Text";
                    }
                } else if (split[0].equals("history")) { //split 2 history commands
                    if (split[1].equals("stop")) { //stop history
                        history = false;
                        histArray.clear();
                    } else if (split[1].equals("display")) { //display history
                        String display = "";
                        for (String s : histArray) {
                            display += s + "\n";
                        }
                        return display;
                    }
                }
            } else if (split.length == 3) {
                if (split[1].equals("=")) { //assignment of cells
                    SpreadsheetLocation loc = new SpreadsheetLocation(split[0]);
                    try {
                        if (split[2].contains("%")) { //real cells
                            RealCell text = new PercentCell(split[2]);
                            data[loc.getRow()][loc.getCol()] = text;
                        } else if (split[2].contains("( ")){
                            String[] temp = split[2].split(" ");
                            String[][] s = new String[2][temp.length];
                            for(int i = 0; i < s.length; i++) {
                                for (int j = 0; j < temp.length; j++) {
                                    s[i][j] = "";
                                }
                            }
                            for (int i = 0; i < temp.length; i++) { //get 2D array of all the locations and their values
                                if (temp[i].matches("\\d+") || temp[i].equals("+") || temp[i].equals("-") || temp[i].equals("*") || temp[i].equals("/") || temp[i].equals("(") || temp[i].equals(")")) {
                                    s[0][i] = "null";
                                    s[1][i] = "null";
                                } else {
                                    s[0][i] = temp[i];
                                    SpreadsheetLocation l = new SpreadsheetLocation(s[0][i]);
                                    s[1][i] = Double.toString(((RealCell)data[l.getRow()][l.getCol()]).getDoubleValue());
                                }
                            }
                            //return Arrays.deepToString(s);
                            RealCell text = new FormulaCell(split[2], s);
                            data[loc.getRow()][loc.getCol()] = text;
                        } else {
                            RealCell text = new ValueCell(split[2]);
                            data[loc.getRow()][loc.getCol()] = text;
                        }
                    } catch (Exception e) { //text cells
                        TextCell text = new TextCell(split[2]);
                        data[loc.getRow()][loc.getCol()] = text;
                    }
                    return getGridText();
                } else if (split[0].equals("history")) { //split 3 history commands
                    if (split[1].equals("start")) { //start history
                        history = true;
                        histSize = Integer.parseInt(split[2]);
                    } else if (split[1].equals("clear")) { //clear certain amount of history
                        if (histSize <= Integer.parseInt(split[2])) {
                            histArray.clear();

                        } else {
                            for (int i = histSize - 1; i >= histSize - Integer.parseInt(split[2]); i--) {
                                histArray.remove(i);
                            }
                        }
                        histSize = histArray.size();
                    }
                }
            }
        } catch (Exception e) { //if an error occurs, return the statement, wait for next command
            return "ERROR: Invalid command.";
        }
        return "";
    }

    @Override
    public int getRows()
    {
        // TODO Auto-generated method stub
        return x;
    }

    @Override
    public int getCols()
    {
        // TODO Auto-generated method stub
        return y;
    }

    @Override
    public Cell getCell(Location loc)
    {
        // TODO Auto-generated method stub
        return data[loc.getRow()][loc.getCol()];
    }

    @Override
    public String getGridText() //print out the spreadsheet
    {
        // TODO Auto-generated method stub
        String grid = "   ";
        for (int i = 0; i < data[0].length; i++) {
            grid += "|" + (char)(i + 'A') + "         ";
        }
        grid += "\n";
        for (int i = 0; i < data.length; i++) {
            if (i >= 9) {
                grid += i + 1 + " ";
            } else {
                grid += i + 1 + "  ";
            }
            for (int j = 0; j < data[i].length; j++) {
                grid += "|" + data[i][j].abbreviatedCellText();
            }
            grid += "\n";
        }

        return grid;
    }

}