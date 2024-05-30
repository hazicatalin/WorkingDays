import java.util.Set;

public class Main {

    //Calculate if year is year is leap or not
    public static boolean isLeap(int year){
        if(year % 4 == 0){
            if(year % 100 == 0 && year % 400 != 0){
                return false;
            }
            return true;
        }
        return false;
    }

    //Calculate the working days in a month
    public static int workingDaysInMonth(int dayOfWeek, int month, int year){
        Set<Integer> longMonths = Set.of(1, 3, 5, 7, 8, 10, 12);

        if(longMonths.contains(month)){
            switch (dayOfWeek){
                case 4:
                    return 22;
                case 5, 6, 7:
                    return 21;
                default:
                    return 23;
            }
        }else {
            if (month == 2) {
                if (isLeap(year)) {
                    switch (dayOfWeek) {
                        case 6, 7:
                            return 20;
                        default:
                            return 21;

                    }
                } else {
                    return 20;
                }
            }
            else{
                switch (dayOfWeek){
                    case 4, 7:
                        return 21;
                    case 5, 6:
                        return 20;
                    default:
                        return 22;
                }
            }
        }

    }

    //calculcate number of days from seconds
    public static long secondsToDays(long seconds){
        return seconds/86400;
    }

    //calculate the working days after a period of seconds
    public static void workingDaysAfterSeconds(Long seconds){
        int year = 1980;
        int month = 1;
        int dayOfWeek = 2;
        long daysToPass = secondsToDays(seconds);
        int daysInMonth[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int daysInYear = 365;
        if(isLeap(year)){
            daysInMonth[1] = 29;
            daysInYear = 366;
        }

        while(daysInYear <= daysToPass){
            year ++;
            daysToPass -= daysInYear;

            dayOfWeek += daysInYear % 7;

            if(dayOfWeek > 7){
                dayOfWeek -= 7;
            }
            daysInYear = 365;
            if(isLeap(year)){
                daysInMonth[1] = 29;
                daysInYear = 366;
            }
        }

        while(daysInMonth[month-1] <= daysToPass){
            daysToPass -= daysInMonth[month-1];
            month++;

            dayOfWeek += daysInMonth[month-1]% 7;

            if(dayOfWeek > 7){
                dayOfWeek -= 7;
            }
        }

        System.out.println("In luna a " + month + "-a sunt " + workingDaysInMonth(dayOfWeek, month, year) + " de zile lucratoare");

    }

    public static void main(String[] args) {
        workingDaysAfterSeconds(10454400L);
    }
}