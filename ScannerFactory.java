import java.util.Scanner;

public class ScannerFactory {
    static private Scanner keyboardScanner = new Scanner(System.in);

    static public Scanner getKeyboardScanner() {
        return keyboardScanner;
    }


}
