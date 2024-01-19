public class FormulaCell extends RealCell {
    private String[][] s;
    public FormulaCell(String n, String[][] s) {
        this.num = n;
        this.s = s;
    }
    @Override
    public String abbreviatedCellText() { //do the formula
        String[] formula = num.split(" ");
        for (int i = 0; i < formula.length; i++) {
            for (int j = 0; j < s[0].length; j++) {
                if (formula[i].equals(s[0][j])) {
                    formula[i] = s[1][j];
                }
            }
        }
        for (int i = 0; i < formula.length; i++) { //if * or /
            if (formula[i].equals("*") || formula[i].equals("/")) {
                if (formula[i].equals("*")) {
                    d = Double.parseDouble(formula[i - 1]) * Double.parseDouble(formula[i + 1]);
                } else {
                    d = Double.parseDouble(formula[i - 1]) / Double.parseDouble(formula[i + 1]);
                }
                int j = i - 2;
                int k = i + 2;
                while (formula[j].equals(formula[i - 1])) {
                    formula[j] = Double.toString(d);
                    j -= 1;
                }
                while (formula[k].equals(formula[i + 1])) {
                    formula[k] = Double.toString(d);
                    k += 1;
                }
                formula[i] = Double.toString(d);
                formula[i - 1] = Double.toString(d);
                formula[i + 1] = Double.toString(d);
            }
        }
        for (int i = 0; i < formula.length; i++) { //if + or -
            if (formula[i].equals("+") || formula[i].equals("-")) {
                if (formula[i].equals("+")) {
                    d = Double.parseDouble(formula[i - 1]) + Double.parseDouble(formula[i + 1]);
                } else {
                        d = Double.parseDouble(formula[i - 1]) - Double.parseDouble(formula[i + 1]);
                }
                int j = i - 2;
                int k = i + 2;
                while (formula[j].equals(formula[i - 1])) {
                    formula[j] = Double.toString(d);
                    j -= 1;
                }
                while (formula[k].equals(formula[i + 1])) {
                    formula[k] = Double.toString(d);
                    k += 1;
                }
                formula[i] = Double.toString(d);
                formula[i - 1] = Double.toString(d);
                formula[i + 1] = Double.toString(d);
            }
        }
        String str = Double.toString(d);
        return (str += "          ").substring(0, 10);
    }
}
