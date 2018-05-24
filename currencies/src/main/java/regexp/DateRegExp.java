package regexp;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateRegExp {
    public static void main(String[] args) {
        String pattern = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.([12][0-9]{3})$";
        Scanner scanner = new Scanner(System.in);
        Pattern pattern1 = Pattern.compile(pattern);
        String string = scanner.nextLine();
        Matcher matcher = pattern1.matcher(string);

        if (matcher.find()) {
            int day = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(2));
            int year = Integer.parseInt(matcher.group(3));
            System.out.println("Day is: " + day + ", month is: " + month + ", year is: " + year);
        }


    }


}
