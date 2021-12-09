package calendar.utils.converter;

import calendar.components.base.date.Year;
import calendar.components.composite.Calendar;
import calendar.utils.converter.tables.dates.DayConversionTable;
import calendar.utils.converter.tables.dates.MonthConversionTable;
import calendar.utils.converter.tables.dates.YearConversionTable;
import calendar.utils.converter.tables.times.HourConversionTable;
import calendar.utils.converter.tables.times.MilliConversionTable;
import calendar.utils.converter.tables.times.MinuteConversionTable;
import calendar.utils.converter.tables.times.SecondConversionTable;

public class ComponentsConverter {

    public static double toMillisPrecise(Calendar calendar) {
        return dateToMillisPrecise(calendar) + timeToMillisPrecise(calendar);
    }

    public static double toSecondsPrecise(Calendar calendar) {
        return dateToSecondsPrecise(calendar) + timeToSecondsPrecise(calendar);
    }

    public static double toSecondsRaw(Calendar calendar) {
        return dateToSecondsPrecise(calendar) + timeToSecondsRaw(calendar);
    }

    public static double toMinutesPrecise(Calendar calendar) {
        return dateToMinutesPrecise(calendar) + timeToMinutesPrecise(calendar);
    }

    public static double toMinutesRaw(Calendar calendar) {
        return dateToMinutesPrecise(calendar) + timeToMinutesRaw(calendar);
    }

    public static double toHoursPrecise(Calendar calendar) {
        return dateToHoursPrecise(calendar) + timeToHoursPrecise(calendar);
    }

    public static double toHoursRaw(Calendar calendar) {
        return dateToHoursPrecise(calendar) + timeToHoursRaw(calendar);
    }

    public static double toDaysPrecise(Calendar calendar) {
        return dateToDaysPrecise(calendar) + timeToDaysPrecise(calendar);
    }

    public static double toDaysRaw(Calendar calendar) {
        return dateToDaysPrecise(calendar);
    }

    public static double toYearsPrecise(Calendar calendar) {
        return dateToYearsPrecise(calendar) + timeToYearsPrecise(calendar);
    }

    public static double toYearsRaw(Calendar calendar) {
        return dateToYearsRaw(calendar);
    }

    public static double dateToMillisPrecise(Calendar calendar) {
        return daysToMillis(calendar) + yearsToMillis(calendar);
    }

    public static double dateToSecondsPrecise(Calendar calendar) {
        return daysToSeconds(calendar) + yearsToSeconds(calendar);
    }

    public static double dateToMinutesPrecise(Calendar calendar) {
        return daysToMinutes(calendar) + yearsToMinutes(calendar);
    }

    public static double dateToHoursPrecise(Calendar calendar) {
        return daysToHours(calendar) + yearsToHours(calendar);
    }

    public static double dateToDaysPrecise(Calendar calendar) {
        return daysPassed(calendar) + yearsToDays(calendar);
    }

    public static double dateToYearsPrecise(Calendar calendar) {
        return daysToYears(calendar) + calendar.getDate().getYear().getValue();
    }

    public static double dateToYearsRaw(Calendar calendar) {
        return calendar.getDate().getYear().getValue();
    }

    public static double timeToMillisPrecise(Calendar calendar) {
        return calendar.getTime().getMilli().getValue() + secondsToMillis(calendar) +
                minutesToMillis(calendar) + hoursToMillis(calendar);
    }

    public static double timeToSecondsPrecise(Calendar calendar) {
        return calendar.getTime().getSecond().getValue() + millisToSeconds(calendar) +
                minutesToSeconds(calendar) + hoursToSeconds(calendar);
    }

    public static double timeToSecondsRaw(Calendar calendar) {
        return calendar.getTime().getSecond().getValue() +
                minutesToSeconds(calendar) + hoursToSeconds(calendar);
    }

    public static double timeToMinutesPrecise(Calendar calendar) {
        return calendar.getTime().getMinute().getValue() + millisToMinutes(calendar) +
                secondsToMinutes(calendar) + hoursToMinutes(calendar);
    }

    public static double timeToMinutesRaw(Calendar calendar) {
        return calendar.getTime().getMinute().getValue() + hoursToMinutes(calendar);
    }

    public static double timeToHoursPrecise(Calendar calendar) {
        return calendar.getTime().getHour().getValue() + millisToHours(calendar) +
                secondsToHours(calendar) + minutesToHours(calendar);
    }

    public static double timeToHoursRaw(Calendar calendar) {
        return calendar.getTime().getHour().getValue();
    }

    public static double timeToDaysPrecise(Calendar calendar) {
        return hoursToDays(calendar) + minutesToDays(calendar) + secondsToDays(calendar) + millisToDays(calendar);
    }

    public static double timeToYearsPrecise(Calendar calendar) {
        return hoursToYears(calendar) + minutesToYears(calendar) + secondsToYears(calendar) + millisToYears(calendar);
    }

    public static double yearsToMillis(Calendar calendar) {
        double leapMillis = collectLeapYears(calendar) * MilliConversionTable.LEAP_YEAR_TO_MILLI.getValue();
        double commonMillis = collectCommonYears(calendar) * MilliConversionTable.COMMON_YEAR_TO_MILLI.getValue();
        return leapMillis + commonMillis;
    }

    public static double yearsToSeconds(Calendar calendar) {
        double leapMillis = collectLeapYears(calendar) * SecondConversionTable.LEAP_YEAR_TO_SECOND.getValue();
        double commonMillis = collectCommonYears(calendar) * SecondConversionTable.COMMON_YEAR_TO_SECOND.getValue();
        return leapMillis + commonMillis;
    }

    public static double yearsToMinutes(Calendar calendar) {
        double leapMillis = collectLeapYears(calendar) * MinuteConversionTable.LEAP_YEAR_TO_MINUTE.getValue();
        double commonMillis = collectCommonYears(calendar) * MinuteConversionTable.COMMON_YEAR_TO_MINUTE.getValue();
        return leapMillis + commonMillis;
    }

    public static double yearsToHours(Calendar calendar) {
        double leapMillis = collectLeapYears(calendar) * HourConversionTable.LEAP_YEAR_TO_HOUR.getValue();
        double commonMillis = collectCommonYears(calendar) * HourConversionTable.COMMON_YEAR_TO_HOUR.getValue();
        return leapMillis + commonMillis;
    }

    public static double yearsToDays(Calendar calendar) {
        double leapMillis = collectLeapYears(calendar) * DayConversionTable.LEAP_YEAR_TO_DAY.getValue();
        double commonMillis = collectCommonYears(calendar) * DayConversionTable.COMMON_YEAR_TO_DAY.getValue();
        return leapMillis + commonMillis;
    }

    public static double daysToMillis(Calendar calendar) {
        double currentMonthDays = calendar.getDate().getDay().getValue() * MilliConversionTable.DAY_TO_MILLI.getValue();
        double daysBeforeCurrentMonth;
        if (!calendar.getDate().getYear().isLeap()) {
            daysBeforeCurrentMonth = predefineDaysPassedBeforeCommon(calendar) * MilliConversionTable.DAY_TO_MILLI.getValue();
        } else {
            daysBeforeCurrentMonth = predefineDaysPassedBeforeLeap(calendar) * MilliConversionTable.DAY_TO_MILLI.getValue();
        }
        return currentMonthDays + daysBeforeCurrentMonth;
    }

    public static double daysToSeconds(Calendar calendar) {
        double currentMonthDays = calendar.getDate().getDay().getValue() * SecondConversionTable.DAY_TO_SECOND.getValue();
        double daysBeforeCurrentMonth;
        if (!calendar.getDate().getYear().isLeap()) {
            daysBeforeCurrentMonth = predefineDaysPassedBeforeCommon(calendar) * SecondConversionTable.DAY_TO_SECOND.getValue();
        } else {
            daysBeforeCurrentMonth = predefineDaysPassedBeforeLeap(calendar) * SecondConversionTable.DAY_TO_SECOND.getValue();
        }
        return currentMonthDays + daysBeforeCurrentMonth;
    }

    public static double daysToMinutes(Calendar calendar) {
        double currentMonthDays = calendar.getDate().getDay().getValue() * MinuteConversionTable.DAY_TO_MINUTE.getValue();
        double daysBeforeCurrentMonth;
        if (!calendar.getDate().getYear().isLeap()) {
            daysBeforeCurrentMonth = predefineDaysPassedBeforeCommon(calendar) * MinuteConversionTable.DAY_TO_MINUTE.getValue();
        } else {
            daysBeforeCurrentMonth = predefineDaysPassedBeforeLeap(calendar) * MinuteConversionTable.DAY_TO_MINUTE.getValue();
        }
        return currentMonthDays + daysBeforeCurrentMonth;
    }

    public static double daysToHours(Calendar calendar) {
        double currentMonthDays = calendar.getDate().getDay().getValue() * HourConversionTable.DAY_TO_HOUR.getValue();
        double daysBeforeCurrentMonth;
        if (!calendar.getDate().getYear().isLeap()) {
            daysBeforeCurrentMonth = predefineDaysPassedBeforeCommon(calendar) * HourConversionTable.DAY_TO_HOUR.getValue();
        } else {
            daysBeforeCurrentMonth = predefineDaysPassedBeforeLeap(calendar) * HourConversionTable.DAY_TO_HOUR.getValue();
        }
        return currentMonthDays + daysBeforeCurrentMonth;
    }

    public static double daysPassed(Calendar calendar) {
        double currentMonthDays = calendar.getDate().getDay().getValue();
        double daysBeforeCurrentMonth;
        if (!calendar.getDate().getYear().isLeap()) {
            daysBeforeCurrentMonth = predefineDaysPassedBeforeCommon(calendar);
        } else {
            daysBeforeCurrentMonth = predefineDaysPassedBeforeLeap(calendar);
        }
        return currentMonthDays + daysBeforeCurrentMonth;
    }

    public static double daysToYears(Calendar calendar) {
        double currentMonthDays;
        double daysBeforeCurrentMonth;
        if (!calendar.getDate().getYear().isLeap()) {
            currentMonthDays = calendar.getDate().getDay().getValue() * YearConversionTable.DAY_TO_COMMON_YEAR.getValue();
            daysBeforeCurrentMonth = predefineDaysPassedBeforeCommon(calendar) * YearConversionTable.DAY_TO_COMMON_YEAR.getValue();
        } else {
            currentMonthDays = calendar.getDate().getDay().getValue() * YearConversionTable.DAY_TO_LEAP_YEAR.getValue();
            daysBeforeCurrentMonth = predefineDaysPassedBeforeCommon(calendar) * YearConversionTable.DAY_TO_LEAP_YEAR.getValue();
        }
        return currentMonthDays + daysBeforeCurrentMonth;
    }

    public static double hoursToMillis(Calendar calendar) {
        return calendar.getTime().getHour().getValue() * MilliConversionTable.HOUR_TO_MILLI.getValue();
    }

    public static double hoursToSeconds(Calendar calendar) {
        return calendar.getTime().getHour().getValue() * SecondConversionTable.HOUR_TO_SECOND.getValue();
    }

    public static double hoursToMinutes(Calendar calendar) {
        return calendar.getTime().getHour().getValue() * MinuteConversionTable.HOUR_TO_MINUTE.getValue();
    }

    public static double hoursToDays(Calendar calendar) {
        return calendar.getTime().getHour().getValue() * DayConversionTable.HOUR_TO_DAY.getValue();
    }

    public static double hoursToYears(Calendar calendar) {
        if (!calendar.getDate().getYear().isLeap()) {
            return calendar.getTime().getHour().getValue() * YearConversionTable.HOUR_TO_COMMON_YEAR.getValue();
        } else {
            return calendar.getTime().getHour().getValue() * YearConversionTable.HOUR_TO_LEAP_YEAR.getValue();
        }
    }

    public static double minutesToMillis(Calendar calendar) {
        return calendar.getTime().getMinute().getValue() * MilliConversionTable.MINUTE_TO_MILLI.getValue();
    }

    public static double minutesToSeconds(Calendar calendar) {
        return calendar.getTime().getMinute().getValue() * SecondConversionTable.MINUTE_TO_SECOND.getValue();
    }

    public static double minutesToHours(Calendar calendar) {
        return calendar.getTime().getMinute().getValue() * HourConversionTable.MINUTE_TO_HOUR.getValue();
    }

    public static double minutesToDays(Calendar calendar) {
        return calendar.getTime().getMinute().getValue() * DayConversionTable.MINUTE_TO_DAY.getValue();
    }

    public static double minutesToYears(Calendar calendar) {
        if (!calendar.getDate().getYear().isLeap()) {
            return calendar.getTime().getMinute().getValue() * YearConversionTable.MINUTE_TO_COMMON_YEAR.getValue();
        } else {
            return calendar.getTime().getMinute().getValue() * YearConversionTable.MINUTE_TO_LEAP_YEAR.getValue();
        }
    }

    public static double secondsToMillis(Calendar calendar) {
        return calendar.getTime().getSecond().getValue() * MilliConversionTable.SECOND_TO_MILLI.getValue();
    }

    public static double secondsToMinutes(Calendar calendar) {
        return calendar.getTime().getSecond().getValue() * MinuteConversionTable.SECOND_TO_MINUTE.getValue();
    }

    public static double secondsToHours(Calendar calendar) {
        return calendar.getTime().getSecond().getValue() * HourConversionTable.SECOND_TO_HOUR.getValue();
    }

    public static double secondsToDays(Calendar calendar) {
        return calendar.getTime().getSecond().getValue() * DayConversionTable.SECOND_TO_DAY.getValue();
    }

    public static double secondsToYears(Calendar calendar) {
        if (!calendar.getDate().getYear().isLeap()) {
            return calendar.getTime().getSecond().getValue() * YearConversionTable.SECOND_TO_COMMON_YEAR.getValue();
        } else {
            return calendar.getTime().getSecond().getValue() * YearConversionTable.SECOND_TO_LEAP_YEAR.getValue();
        }
    }

    public static double millisToSeconds(Calendar calendar) {
        return calendar.getTime().getMilli().getValue() * SecondConversionTable.MILLI_TO_SECOND.getValue();
    }

    public static double millisToMinutes(Calendar calendar) {
        return calendar.getTime().getMilli().getValue() * MinuteConversionTable.MILLI_TO_MINUTE.getValue();
    }

    public static double millisToHours(Calendar calendar) {
        return calendar.getTime().getMilli().getValue() * HourConversionTable.MILLI_TO_HOUR.getValue();
    }

    public static double millisToDays(Calendar calendar) {
        return calendar.getTime().getMilli().getValue() * DayConversionTable.MILLI_TO_DAY.getValue();
    }

    public static double millisToYears(Calendar calendar) {
        if (!calendar.getDate().getYear().isLeap()) {
            return calendar.getTime().getMilli().getValue() * YearConversionTable.MILLI_TO_COMMON_YEAR.getValue();
        } else {
            return calendar.getTime().getMilli().getValue() * YearConversionTable.MILLI_TO_LEAP_YEAR.getValue();
        }
    }

    private static int collectLeapYears(Calendar calendar) {
        int leapsCounter = 0;
        Year year;
        for (int i = 0; i < calendar.getDate().getYear().getValue(); i++) {
            year = new Year(i);
            if (year.isLeap()) {
                leapsCounter++;
            }
        }
        return leapsCounter;
    }

    private static int collectCommonYears(Calendar calendar) {
        if (calendar.getDate().getYear().getValue() == 0) {
            return 0;
        } else {
            return calendar.getDate().getYear().getValue() - ComponentsConverter.collectLeapYears(calendar);
        }
    }

    private static long predefineDaysPassedBeforeCommon(Calendar calendar) {
        switch (calendar.getDate().getMonth().getValue()) {
            case 1 -> {
                return MonthConversionTable.MONTH_START.getValue();
            }
            case 2 -> {
                return MonthConversionTable.JANUARY_DAYS_PASSED.getValue();
            }
            case 3 -> {
                return MonthConversionTable.FEBRUARY_DAYS_PASSED.getValue();
            }
            case 4 -> {
                return MonthConversionTable.MARCH_DAYS_PASSED.getValue();
            }
            case 5 -> {
                return MonthConversionTable.APRIL_DAYS_PASSED.getValue();
            }
            case 6 -> {
                return MonthConversionTable.MAY_DAYS_PASSED.getValue();
            }
            case 7 -> {
                return MonthConversionTable.JUNE_DAYS_PASSED.getValue();
            }
            case 8 -> {
                return MonthConversionTable.JULY_DAYS_PASSED.getValue();
            }
            case 9 -> {
                return MonthConversionTable.AUGUST_DAYS_PASSED.getValue();
            }
            case 10 -> {
                return MonthConversionTable.SEPTEMBER_DAYS_PASSED.getValue();
            }
            case 11 -> {
                return MonthConversionTable.OCTOBER_DAYS_PASSED.getValue();
            }
            case 12 -> {
                return MonthConversionTable.NOVEMBER_DAYS_PASSED.getValue();
            }
            default -> {
                return 0;
            }
        }
    }

    private static long predefineDaysPassedBeforeLeap(Calendar calendar) {
        switch (calendar.getDate().getMonth().getValue()) {
            case 1 -> {
                return MonthConversionTable.MONTH_START.getValue();
            }
            case 2 -> {
                return MonthConversionTable.JANUARY_DAYS_PASSED.getValue();
            }
            case 3 -> {
                return MonthConversionTable.FEBRUARY_DAYS_PASSED_LEAP.getValue();
            }
            case 4 -> {
                return MonthConversionTable.MARCH_DAYS_PASSED_LEAP.getValue();
            }
            case 5 -> {
                return MonthConversionTable.APRIL_DAYS_PASSED_LEAP.getValue();
            }
            case 6 -> {
                return MonthConversionTable.MAY_DAYS_PASSED_LEAP.getValue();
            }
            case 7 -> {
                return MonthConversionTable.JUNE_DAYS_PASSED_LEAP.getValue();
            }
            case 8 -> {
                return MonthConversionTable.JULY_DAYS_PASSED_LEAP.getValue();
            }
            case 9 -> {
                return MonthConversionTable.AUGUST_DAYS_PASSED_LEAP.getValue();
            }
            case 10 -> {
                return MonthConversionTable.SEPTEMBER_DAYS_PASSED_LEAP.getValue();
            }
            case 11 -> {
                return MonthConversionTable.OCTOBER_DAYS_PASSED_LEAP.getValue();
            }
            case 12 -> {
                return MonthConversionTable.NOVEMBER_DAYS_PASSED_LEAP.getValue();
            }
            default -> {
                return 0;
            }
        }
    }
}