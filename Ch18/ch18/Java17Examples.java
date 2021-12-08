package ch18;

import java.time.DayOfWeek;
import java.time.Month;

import static java.time.DayOfWeek.TUESDAY;

public class Java17Examples {
    public static void printMonth(Month month){
        String message = switch(month) {
            case JANUARY, FEBRUARY, DECEMBER  -> "Winter, brrrr";
            case MARCH, APRIL, MAY            -> "Spring has sprung!";
            case JUNE, JULY, AUGUST           -> "Summer is here!";
            case SEPTEMBER, OCTOBER, NOVEMBER -> "Fall has descended";
        };
        System.out.println(message);
    }

    public static boolean isWorkDay2(DayOfWeek day){
        var today = switch(day) {
            case SATURDAY, SUNDAY: yield false;
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY: yield true;
        };

        // Do any further processing to e.g. account for public holidays...

        return today;
    }

    public static String doTextBlock() {
        String query = """
               SELECT "ORDER_ID", "QUANTITY" FROM "ORDERS"
               WHERE "CLIENT_ID" = ?
               ORDER BY "DATE_TIME", "STATUS" LIMIT 100;
               """;
        return query;
    }

    void exampleInstanceOf() {
        Object o = "Cat";
        if (o instanceof String) {
            String s = (String)o;
            System.out.println(s.length());
        } else {
            System.out.println("Not a String");
        }

        if (o instanceof String s) {
            // s is in scope on this branch
            System.out.println(s.length());
        } else {
            // but not on this one
            System.out.println("Not a String");
        }
    }

    void examplePattern() {
        Object o = "Cat";
        if (o instanceof String s) {
            System.out.println("String of length:"+ s.length());
        } else if (o instanceof Integer i) {
            System.out.println("Integer:"+ i);
        } else {
            System.out.println("Not a String or Integer");
        }

        var msg = switch (o) {
            case String s -> "String of length:"+ s.length();
            case Integer i -> "Integer:"+ i;
            case null, default -> "Not a String or Integer";
        };
        System.out.println(msg);
    }

    public static void main(String[] args) {
        printMonth(Month.FEBRUARY);
        System.out.println(doTextBlock());
    }
}
