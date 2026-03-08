package bank.util;

public class BankUtils {
    public static boolean isNumeric(String str) {
        if(str == null) return false;
        try { Double.parseDouble(str); }
        catch(NumberFormatException nfe) { return false; }
        return true;
    }
}
