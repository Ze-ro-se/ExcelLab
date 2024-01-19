public class ValueCell extends RealCell {
    public ValueCell(String n) { //constructor
        this.num = n;
        this.d = Double.parseDouble(n);
    }
}
