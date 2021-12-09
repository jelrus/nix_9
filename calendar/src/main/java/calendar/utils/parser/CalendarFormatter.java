package calendar.utils.parser;

import calendar.components.base.date.Day;
import calendar.components.base.date.Month;
import calendar.components.base.date.Year;
import calendar.components.base.time.Hour;
import calendar.components.base.time.Milli;
import calendar.components.base.time.Minute;
import calendar.components.base.time.Second;
import calendar.components.composite.Calendar;
import calendar.utils.parser.styles.MonthStyles;

public class CalendarFormatter {

    private final String calendar;

    public CalendarFormatter(String calendar) {
        this.calendar = calendar;
    }

    public String getCalendar() {
        return calendar;
    }

    public Calendar parse(String input) {
        Calendar target = new Calendar();
        if (!input.isEmpty() && !input.isBlank()) {
            parseYear(input, target);
            parseMonth(input, target);
            parseDay(input, target);
            parseHour(input, target);
            parseMinute(input, target);
            parseSecond(input, target);
            parseMilli(input, target);
            return target;
        } else {
            return new Calendar();
        }
    }

    public void parseYear(String input, Calendar calendar) {
        if (this.calendar.contains("yyyy") | this.calendar.contains("yyy") |
                this.calendar.contains("yy") | this.calendar.contains("y")) {
            int firstIndex = this.calendar.indexOf("y");
            String sub = input.substring(firstIndex);
            sub = sub.replaceAll("[\\D]+[\\d+\\D]+", "")
                    .replaceAll("[\\D]+", "");
            if (sub.length() == 1) {
                sub = "000" + sub;
            } else if (sub.length() == 2) {
                sub = "00" + sub;
            } else if (sub.length() == 3) {
                sub = "0" + sub;
            } else if (sub.length() == 0) {
                sub = "0000";
            } else if (sub.length() > 4) {
                sub = String.valueOf(sub.charAt(0)) + sub.charAt(1) + sub.charAt(2) + sub.charAt(3);
            }
            if (checkPositionForPositiveNumber(sub)) {
                int parseYears = Integer.parseInt(sub);
                calendar.getDate().getYear().setValue(parseYears);
            } else {
                calendar.getDate().getYear().setValue(0);
            }
        }
        yearsCorrection(calendar);
    }

    public void parseMonth(String input, Calendar calendar) {
        if (this.calendar.contains("mm") | this.calendar.contains("m")) {
            int firstIndex = this.calendar.indexOf("m");
            String sub = input.substring(firstIndex);
            sub = sub.replaceAll("[\\D]+[\\d+\\D]+", "")
                    .replaceAll("[\\D]+", "");
            if (sub.length() == 1) {
                sub = "0" + sub;
            } else if (sub.length() == 0) {
                sub = "01";
            } else if (sub.length() > 2) {
                sub = String.valueOf(sub.charAt(0)) + sub.charAt(1);
            }
            if (checkPositionForPositiveNumber(sub)) {
                int parseMonths = Integer.parseInt(sub);
                calendar.getDate().getMonth().setValue(parseMonths);
                if (calendar.getDate().getMonth().getValue() == 0) {
                    calendar.getDate().getMonth().setValue(1);
                }
            } else {
                calendar.getDate().getMonth().setValue(1);
            }
        }
        monthsCorrection(calendar);
    }

    public void parseDay(String input, Calendar calendar) {
        if (this.calendar.contains("dd") | this.calendar.contains("d")) {
            int firstIndex = this.calendar.indexOf("d");
            String sub = input.substring(firstIndex);
            sub = sub.replaceAll("[\\D]+[\\d+\\D]+", "")
                    .replaceAll("[\\D]+", "");
            if (sub.length() == 1) {
                sub = "0" + sub;
            } else if (sub.length() == 0) {
                sub = "01";
            } else if (sub.length() > 2) {
                sub = String.valueOf(sub.charAt(0)) + sub.charAt(1);
            }
            if (checkPositionForPositiveNumber(sub)) {
                int parseDays = Integer.parseInt(sub);
                calendar.getDate().getDay().setValue(parseDays);
                if (calendar.getDate().getDay().getValue() == 0) {
                    calendar.getDate().getDay().setValue(1);
                }
            } else {
                calendar.getDate().getDay().setValue(1);
            }
        }
        daysCorrection(calendar);
    }

    public void parseHour(String input, Calendar calendar) {
        if (this.calendar.contains("00")) {
            int firstIndex = this.calendar.indexOf("0");
            String sub = input.substring(firstIndex);
            sub = sub.replaceAll("[\\D]+[\\d+\\D]+", "")
                    .replaceAll("[\\D]+", "");
            if (sub.length() == 1) {
                sub = "0" + sub;
            } else if (sub.length() == 0) {
                sub = "00";
            } else if (sub.length() > 2) {
                sub = String.valueOf(sub.charAt(0)) + sub.charAt(1);
            }
            if (checkPositionForPositiveNumber(sub)) {
                int parseHours = Integer.parseInt(sub);
                calendar.getTime().getHour().setValue(parseHours);
                if (calendar.getTime().getHour().getValue() == 0) {
                    calendar.getTime().getHour().setValue(0);
                }
            } else {
                calendar.getTime().getHour().setValue(0);
            }
        }
        hoursCorrection(calendar);
    }

    public void parseMinute(String input, Calendar calendar) {
        if (this.calendar.contains("00:00")) {
            int firstIndex = this.calendar.indexOf(":00");
            String sub = input.substring(firstIndex + 1);
            sub = sub.replaceAll("[\\D]+[\\d+\\D]+", "")
                    .replaceAll("[\\D]+", "");
            if (sub.length() == 1) {
                sub = "0" + sub;
            } else if (sub.length() == 0) {
                sub = "00";
            } else if (sub.length() > 2) {
                sub = String.valueOf(sub.charAt(0)) + sub.charAt(1);
            }
            if (checkPositionForPositiveNumber(sub)) {
                int parseHours = Integer.parseInt(sub);
                calendar.getTime().getMinute().setValue(parseHours);
                if (calendar.getTime().getMinute().getValue() == 0) {
                    calendar.getTime().getMinute().setValue(0);
                }
            } else {
                calendar.getTime().getHour().setValue(0);
            }
        }
        minutesCorrection(calendar);
    }

    public void parseSecond(String input, Calendar calendar) {
        if (this.calendar.contains("00:00:00")) {
            int firstIndex = this.calendar.lastIndexOf(":00");
            String sub = input.substring(firstIndex + 1);
            sub = sub.replaceAll("[\\D]+[\\d+\\D]+", "")
                    .replaceAll("[\\D]+", "");
            if (sub.length() == 1) {
                sub = "0" + sub;
            } else if (sub.length() == 0) {
                sub = "00";
            } else if (sub.length() > 2) {
                sub = String.valueOf(sub.charAt(0)) + sub.charAt(1);
            }
            if (checkPositionForPositiveNumber(sub)) {
                int parseSeconds = Integer.parseInt(sub);
                calendar.getTime().getSecond().setValue(parseSeconds);
                if (calendar.getTime().getSecond().getValue() == 0) {
                    calendar.getTime().getSecond().setValue(0);
                }
            } else {
                calendar.getTime().getSecond().setValue(0);
            }
        }
        secondsCorrection(calendar);
    }

    public void parseMilli(String input, Calendar calendar) {
        if (this.calendar.contains("00:00:00.000")) {
            int firstIndex = this.calendar.indexOf(".000");
            String sub = input.substring(firstIndex + 1);
            sub = sub.replaceAll("[\\D]+[\\d+\\D]+", "")
                    .replaceAll("[\\D]+", "");
            if (sub.length() == 1) {
                sub = "00" + sub;
            } else if (sub.length() == 2) {
                sub = "0" + sub;
            } else if (sub.length() == 0) {
                sub = "000";
            } else if (sub.length() > 3) {
                sub = String.valueOf(sub.charAt(0)) + sub.charAt(1) + sub.charAt(2);
            }
            if (checkPositionForPositiveNumber(sub)) {
                int parseMillis = Integer.parseInt(sub);
                calendar.getTime().getMilli().setValue(parseMillis);
                if (calendar.getTime().getMilli().getValue() == 0) {
                    calendar.getTime().getMilli().setValue(0);
                }
            } else {
                calendar.getTime().getMilli().setValue(0);
            }
        }
        millisCorrection(calendar);
    }

    public boolean checkPositionForPositiveNumber(String input) {
        if (input == null) {
            return false;
        }
        try {
            int number = Integer.parseInt(input);
            return number >= 0;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public void yearsCorrection(Calendar calendar) {
        if (calendar.getDate().getYear().getValue() < Year.MIN_VALUE) {
            calendar.getDate().getYear().setValue(Year.MIN_VALUE);
        }
    }

    public void monthsCorrection(Calendar calendar) {
        if (calendar.getDate().getMonth().getValue() > Month.MAX_VALUE) {
            calendar.getDate().getMonth().setValue(Month.MAX_VALUE);
        } else if (calendar.getDate().getDay().getValue() < Month.MIN_VALUE) {
            calendar.getDate().getDay().setValue(Month.MIN_VALUE);
        }
    }

    public void daysCorrection(Calendar calendar) {
        if (calendar.getDate().getDay().getValue() > calendar.getDate().getCurrentMonthCapacity()) {
            calendar.getDate().getDay().setValue(calendar.getDate().getCurrentMonthCapacity());
        } else if (calendar.getDate().getDay().getValue() < Day.MIN_VALUE) {
            calendar.getDate().getDay().setValue(Day.MIN_VALUE);
        }
    }

    public void hoursCorrection(Calendar calendar) {
        if (calendar.getTime().getHour().getValue() >= Hour.MAX_VALUE) {
            calendar.getTime().getHour().setValue(Hour.MAX_VALUE - 1);
        } else if (calendar.getTime().getHour().getValue() < Hour.MIN_VALUE) {
            calendar.getTime().getHour().setValue(Hour.MIN_VALUE);
        }
    }

    public void minutesCorrection(Calendar calendar) {
        if (calendar.getTime().getMinute().getValue() >= Minute.MAX_VALUE) {
            calendar.getTime().getMinute().setValue(Minute.MAX_VALUE - 1);
        } else if (calendar.getTime().getMinute().getValue() < Minute.MIN_VALUE) {
            calendar.getTime().getMinute().setValue(Minute.MIN_VALUE);
        }
    }

    public void secondsCorrection(Calendar calendar) {
        if (calendar.getTime().getSecond().getValue() >= Second.MAX_VALUE) {
            calendar.getTime().getSecond().setValue(Second.MAX_VALUE - 1);
        } else if (calendar.getTime().getSecond().getValue() < Second.MIN_VALUE) {
            calendar.getTime().getSecond().setValue(Second.MIN_VALUE);
        }
    }

    public void millisCorrection(Calendar calendar) {
        if (calendar.getTime().getMilli().getValue() >= Milli.MAX_VALUE) {
            calendar.getTime().getMilli().setValue(Milli.MAX_VALUE - 1);
        } else if (calendar.getTime().getMilli().getValue() < Milli.MIN_VALUE) {
            calendar.getTime().getMilli().setValue(Milli.MIN_VALUE);
        }
    }

    public String format(Calendar calendar) {
        String formatter = this.calendar;
        formatter = formatDays(formatter, calendar);
        formatter = formatMonths(formatter, calendar);
        formatter = formatYears(formatter, calendar);
        formatter = formatTime(formatter, calendar);
        return formatter;
    }

    public String formatDays(String formatter, Calendar calendar) {
        if (this.calendar.contains("dd")) {
            formatter = formatter.replaceAll("dd", calendar.getDate().getDay().toString());
        } else if (formatter.contains("d")) {
            if (calendar.getDate().getDay().toString().charAt(0) == '0') {
                String reducedDays = calendar.getDate().getDay().toString().replaceAll("0", "");
                formatter = formatter.replaceAll("d", reducedDays);
            } else {
                formatter = formatter.replaceAll("d", calendar.getDate().getDay().toString());
            }
        }
        return formatter;
    }

    public String formatMonths(String formatter, Calendar calendar) {
        if (this.calendar.contains("mmm")) {
            formatter = formatter.replaceAll("mmm", MonthStyles.getVerbose(calendar.getDate().getMonth().getValue()));
            formatter = formatter.replaceAll("-", " ");
            formatter = formatter.replaceAll("/", " ");
        } else if (this.calendar.contains("mm")) {
            formatter = formatter.replaceAll("mm", calendar.getDate().getMonth().toString());
        } else if (this.calendar.contains("m")) {
            if (calendar.getDate().getMonth().toString().charAt(0) == '0') {
                String reducedMonths = calendar.getDate().getMonth().toString().replaceAll("0", "");
                formatter = formatter.replaceAll("m", reducedMonths);
            } else {
                formatter = formatter.replaceAll("m", calendar.getDate().getMonth().toString());
            }
        }
        return formatter;
    }

    public String formatYears(String formatter, Calendar calendar) {
        if (this.calendar.contains("yyyy")) {
            formatter = formatter.replaceAll("yyyy", calendar.getDate().getYear().toString());
        } else if (this.calendar.contains("yyy")) {
            formatter = formatter.replaceAll("yyy", calendar.getDate().getYear().toString());
        } else if (this.calendar.contains("yy")) {
            formatter = formatter.replaceAll("yy", calendar.getDate().getYear().toString());
        } else if (this.calendar.contains("y")) {
            formatter = formatter.replaceAll("y", calendar.getDate().getYear().toString());
        }
        return formatter;
    }

    public String formatTime(String formatter, Calendar calendar) {
        if (this.calendar.contains("00:00:00.000")) {
            formatter = formatter.replace("00:00:00.000", calendar.getTime().toString());
        } else if (this.calendar.contains("00:00:00")) {
            formatter = formatter.replace("00:00:00",
                    calendar.getTime().getHour().toString() + ":" +
                            calendar.getTime().getMinute().toString() + ":" +
                            calendar.getTime().getSecond().toString());
        } else if (this.calendar.contains("00:00")) {
            formatter = formatter.replace("00:00",
                    calendar.getTime().getHour().toString() + ":" +
                            calendar.getTime().getMinute().toString());
        } else if (this.calendar.contains("00")) {
            formatter = formatter.replaceFirst("00", calendar.getTime().getHour().toString());
        }
        return formatter;
    }
}