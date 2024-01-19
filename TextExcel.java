import java.util.*;
public class TextExcel
{
    public static void main(String[] args)
    {
        Grid sheet = new Spreadsheet(); // Keep this as the first statement in main
        String input;
        Scanner console = new Scanner(System.in);
        System.out.println(sheet.getGridText());
        input = console.nextLine();
        while (!input.equals("quit")) { //process what the user inputs
            System.out.println(sheet.processCommand(input));
            input = console.nextLine();
        }
    }
}
