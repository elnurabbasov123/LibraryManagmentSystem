package utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateChanger {
    public static LocalDateTime change(String stringDate){
        String[] arr = stringDate.split("-");
        int day=Integer.valueOf(arr[0]);
        int month=Integer.valueOf(arr[1]);
        int year=Integer.valueOf(arr[2]);

        String hoursMinutes=arr[3];
        String[] arr2 = hoursMinutes.split(":");

        int hours = Integer.valueOf(arr2[0]);
        int minutes = Integer.valueOf(arr2[1]);

        LocalDateTime dateTime = LocalDateTime.of(year, month, day, hours, minutes);

        return dateTime;
    }
}
