public class TextCell implements Cell {
    private String text;
    public TextCell(String t) {
        this.text = t;
    }
    @Override
    public String abbreviatedCellText() { //print first 10 characters
        return (text += "          ").substring(0, 10);
    }

    @Override
    public String fullCellText() {
        return text;
    }
}
