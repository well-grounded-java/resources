package ch18;

import java.time.DayOfWeek;

public class SwitchExamples {
    public static boolean isWorkDay(DayOfWeek day){
        var today = switch(day) {
            case SATURDAY, SUNDAY -> false;
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> true;
        };

        // Do any further processing to e.g. account for public holidays...

        return today;
    }

    public static boolean isWorkDay2(DayOfWeek day){
        var today = switch(day) {
            case SATURDAY, SUNDAY: yield false;
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY: yield true;
        };

        // Do any further processing to e.g. account for public holidays...

        return today;
    }


}
