package regexp;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelephoneRegExp {
    public static void main(String[] args) {
        String pattern = "^(\\+7|8)[(|\\-|\\s][0-9]{3}[)|\\-|\\s?][0-9]{3}[\\-|\\s?][0-9]{2}[\\-|\\s?][0-9]{2}$";

        Scanner scanner = new Scanner(System.in);
        Pattern pattern1 = Pattern.compile(pattern);
        String string = scanner.nextLine();
        Matcher matcher = pattern1.matcher(string);

        if (matcher.find()) {
            System.out.println(matcher.matches());
        }else {
            System.out.println("хрень");
        }


    }













}
