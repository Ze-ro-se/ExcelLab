public abstract class RealCell implements Cell{
    public String num;
    public double d;
    @Override
    public String abbreviatedCellText() { //print first 10 characters
        return (num += "          ").substring(0, 10);
    }

    @Override
    public String fullCellText() {
        return num;
    }
    public double getDoubleValue() {
        return d;
    }
}
