public class PercentCell extends RealCell {
    public PercentCell(String n) {
        this.num = n;
        this.d = Double.parseDouble(n.substring(0, n.length() - 1)) * 0.01; //turn percent into decimal
    }
    @Override
    public String abbreviatedCellText() { //print the decimal value
        String str = Double.toString(d);
        return (str += "          ").substring(0, 10);
    }
}
