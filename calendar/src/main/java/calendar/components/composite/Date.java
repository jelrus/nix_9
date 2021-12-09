package calendar.components.composite;

import calendar.components.base.date.Day;
import calendar.components.base.date.Month;
import calendar.components.base.date.Year;

import java.util.Comparator;

public class Date implements Comparable<Date> {

    private Day day;
    private Month month;
    private Year year;

    public Date() {
        this.day = new Day();
        this.month = new Month();
        this.year = new Year();
    }

    public Date(int day, int month, int year) {
        if (month >= Month.MIN_VALUE && month <= Month.MAX_VALUE) {
            this.month = new Month(month);
        } else {
            this.month = new Month();
        }
        if (year >= Year.MIN_VALUE) {
            this.year = new Year(year);
        } else {
            this.year = new Year();
        }
        if (this.month.isFebruary() && this.year.isLeap() && day >= Day.MIN_VALUE && day <= Day.MAX_VALUE_LEAP_YEAR) {
            this.day = new Day(day);
        } else if (this.month.isFebruary() && !this.year.isLeap() && day >= Day.MIN_VALUE && day <= Day.MAX_VALUE_COMMON_YEAR) {
            this.day = new Day(day);
        } else if (this.month.isOdd() && day >= Day.MIN_VALUE && day <= Day.MAX_VALUE_ODD) {
            this.day = new Day(day);
        } else if (this.month.isEven() && day >= Day.MIN_VALUE && day <= Day.MAX_VALUE_EVEN) {
            this.day = new Day(day);
        } else {
            this.day = new Day();
        }
    }

    public void appendDate(int days, int months, int years) {
        appendYears(years);
        appendMonths(months);
        appendDays(days);
    }

    public void subtractDate(int days, int months, int years) {
        subtractYears(years);
        subtractMonths(months);
        subtractDays(days);
    }

    public void appendDays(int days) {
        this.day.setValue(revaluateDaysForward(days));
    }

    public void subtractDays(int days) {
        this.day.setValue(revaluateDaysBackward(days));
    }

    private int revaluateDaysForward(int days) {
        if (days > 0) {
            for (int i = 0; i < days; i++) {
                this.day = nextDay();
            }
        }
        return this.day.getValue();
    }

    public int revaluateDaysBackward(int days) {
        if (days > 0) {
            for (int i = 0; i < days; i++) {
                this.day = previousDay();
            }
        }
        return this.day.getValue();
    }

    public void appendMonths(int months) {
        this.month.setValue(revaluateMonthsForward(months));
    }

    public void subtractMonths(int months) {
        this.month.setValue(revaluateMonthsBackward(months));
    }

    private int revaluateMonthsForward(int months) {
        if (months > 0) {
            for (int i = 0; i < months; i++) {
                this.month = nextMonth();
            }
            if (this.day.getValue() > getCurrentMonthCapacity()) {
                this.day.setValue(getCurrentMonthCapacity());
            }
        }
        return this.month.getValue();
    }

    private int revaluateMonthsBackward(int months) {
        if (months > 0) {
            for (int i = 0; i < months; i++) {
                this.month = previousMonth();
            }
            if (this.day.getValue() > getCurrentMonthCapacity()) {
                this.day.setValue(getCurrentMonthCapacity());
            }
        }
        return this.month.getValue();
    }

    public void appendYears(int years) {
        this.year.setValue(revaluateYearsForward(years));
    }

    public void subtractYears(int years) {
        this.year.setValue(revaluateYearsBackward(years));
    }

    private int revaluateYearsForward(int years) {
        if (years > 0) {
            revaluateMonthsForward(years * Month.MAX_VALUE);
        }
        return this.year.getValue();
    }

    private int revaluateYearsBackward(int years) {
        if (years > 0) {
            revaluateMonthsBackward(years * Month.MAX_VALUE);
        }
        return this.year.getValue();
    }

    public Day previousDay() {
        if (this.day.getValue() == Day.MIN_VALUE) {
            this.month = previousMonth();
            return new Day(getCurrentMonthCapacity());
        } else {
            return new Day(this.day.getValue() - Day.MIN_VALUE);
        }
    }

    public Day getDay() {
        return day;
    }

    public Day nextDay() {
        if (this.day.getValue() == getCurrentMonthCapacity()) {
            this.month = nextMonth();
            return new Day(Day.MIN_VALUE);
        } else {
            return new Day(this.day.getValue() + Day.MIN_VALUE);
        }
    }

    public Month previousMonth() {
        if (this.month.getValue() == Month.MIN_VALUE) {
            this.year = previousYear();
            return new Month(Month.MAX_VALUE);
        } else {
            return new Month(this.month.getValue() - Day.MIN_VALUE);
        }
    }

    public Month getMonth() {
        return month;
    }

    public Month nextMonth() {
        if (this.month.getValue() == Month.MAX_VALUE) {
            this.year = nextYear();
            return new Month(Month.MIN_VALUE);
        } else {
            return new Month(this.month.getValue() + Month.MIN_VALUE);
        }
    }

    public Year previousYear() {
        if (this.year.getValue() == Year.MIN_VALUE) {
            return new Year(Year.MIN_VALUE);
        } else {
            return new Year(this.year.getValue() - 1);
        }
    }

    public Year getYear() {
        return year;
    }

    public Year nextYear() {
        return new Year(this.year.getValue() + 1);
    }

    public int getPreviousMonthCapacity() {
        switch (previousMonth().getValue()) {
            case 1, 3, 5, 7, 8, 10, 12 -> {
                return Day.MAX_VALUE_ODD;
            }
            case 4, 6, 9, 11 -> {
                return Day.MAX_VALUE_EVEN;
            }
            case 2 -> {
                if (this.year.isLeap()) {
                    return Day.MAX_VALUE_LEAP_YEAR;
                } else {
                    return Day.MAX_VALUE_COMMON_YEAR;
                }
            }
            default -> {
                return 0;
            }
        }
    }

    public int getCurrentMonthCapacity() {
        switch (this.month.getValue()) {
            case 1, 3, 5, 7, 8, 10, 12 -> {
                return Day.MAX_VALUE_ODD;
            }
            case 4, 6, 9, 11 -> {
                return Day.MAX_VALUE_EVEN;
            }
            case 2 -> {
                if (this.year.isLeap()) {
                    return Day.MAX_VALUE_LEAP_YEAR;
                } else {
                    return Day.MAX_VALUE_COMMON_YEAR;
                }
            }
            default -> {
                return 0;
            }
        }
    }

    public int getNextMonthCapacity() {
        switch (nextMonth().getValue()) {
            case 1, 3, 5, 7, 8, 10, 12 -> {
                return Day.MAX_VALUE_ODD;
            }
            case 4, 6, 9, 11 -> {
                return Day.MAX_VALUE_EVEN;
            }
            case 2 -> {
                if (this.year.isLeap()) {
                    return Day.MAX_VALUE_LEAP_YEAR;
                } else {
                    return Day.MAX_VALUE_COMMON_YEAR;
                }
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int compareTo(Date target) {
        return Comparator.comparing(Date::getYear)
                .thenComparing(Date::getMonth)
                .thenComparing(Date::getDay)
                .compare(this, target);
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }
}