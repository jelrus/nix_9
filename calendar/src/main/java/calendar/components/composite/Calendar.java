package calendar.components.composite;

import calendar.components.base.date.Month;
import calendar.components.base.time.Hour;
import calendar.components.base.time.Milli;
import calendar.components.base.time.Minute;
import calendar.components.base.time.Second;
import calendar.utils.converter.ComponentsConverter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Calendar implements Comparable<Calendar> {

    private final Date date;
    private final Time time;

    public Calendar() {
        this.date = new Date();
        this.time = new Time();
    }

    public Calendar(Date date, Time time) {
        this.date = date;
        this.time = time;
    }

    public Calendar(int days, int months, int years,
                    int hours, int minutes, int seconds, int millis) {
        this.time = new Time(hours, minutes, seconds, millis);
        this.date = new Date(days, months, years);
    }

    private static String getCleanSubtraction(Calendar current, Calendar target) {
        Calendar clone = new Calendar(current.date.getDay().getValue(),
                                      current.date.getMonth().getValue(),
                                      current.date.getYear().getValue(),
                                      current.time.getHour().getValue(),
                                      current.time.getMinute().getValue(),
                                      current.time.getSecond().getValue(),
                                      current.time.getMilli().getValue());
        int currentMillis = clone.time.getMilli().getValue();
        int targetMillis = target.time.getMilli().getValue();
        StringBuilder subtractionBuilder = new StringBuilder();
        String millis;
        if (currentMillis > targetMillis) {
            millis = (currentMillis - targetMillis) + " millis ";
        } else if (currentMillis < targetMillis) {
            clone.subtractSeconds(1);
            millis = (currentMillis + Milli.MAX_VALUE - targetMillis) + " millis ";
        } else {
            millis = 0 + " millis ";
        }

        int currentSeconds = clone.time.getSecond().getValue();
        int targetSeconds = target.time.getSecond().getValue();
        String seconds;
        if (currentSeconds > targetSeconds) {
            seconds = (currentSeconds - targetSeconds) + " seconds, ";
        } else if (currentSeconds < targetSeconds) {
            clone.subtractMinutes(1);
            seconds = (currentSeconds + Second.MAX_VALUE - targetSeconds) + " seconds, ";
        } else {
            seconds = 0 + " seconds, ";
        }

        int currentMinutes = clone.time.getMinute().getValue();
        int targetMinutes = target.time.getMinute().getValue();
        String minutes;
        if (currentMinutes > targetMinutes) {
            minutes = (currentMinutes - targetMinutes) + " minutes, ";
        } else if (currentMinutes < targetMinutes) {
            clone.subtractHours(1);
            minutes = (currentMinutes + Minute.MAX_VALUE - targetMinutes) + " minutes, ";
        } else {
            minutes = 0 + " minutes, ";
        }

        int currentHours = clone.time.getHour().getValue();
        int targetHours = target.time.getHour().getValue();
        String hours;
        if (currentHours > targetHours) {
            hours = (currentHours - targetHours) + " hours, ";
        } else if (currentHours < targetHours) {
            clone.subtractDays(1);
            hours = (currentHours + Hour.MAX_VALUE - targetHours) + " hours, ";
        } else {
            hours = 0 + " hours, ";
        }

        int currentDays = clone.date.getDay().getValue();
        int targetDays = target.date.getDay().getValue();
        int previousMonthsDays = clone.date.getPreviousMonthCapacity();
        String days;
        if (currentDays > targetDays) {
            days = (currentDays - targetDays) + " days, ";
        } else if (currentDays < targetDays) {
            clone.subtractMonths(1);
            days = (currentDays + previousMonthsDays - targetDays) + " days, ";
        } else {
            days = 0 + " days, ";
        }

        int currentMonths = clone.date.getMonth().getValue();
        int targetMonths = target.date.getMonth().getValue();
        String months;
        if (currentMonths > targetMonths) {
            months = (currentMonths - targetMonths) + " months, ";
        } else if (currentMonths < targetMonths) {
            clone.subtractYears(1);
            months = (currentMonths + Month.MAX_VALUE - targetMonths) + " months, ";
        } else {
            months = 0 + " months, ";
        }

        int currentYears = clone.date.getYear().getValue();
        int targetYears = target.date.getYear().getValue();
        String years;
        if (currentYears > targetYears) {
            years = (currentYears - targetYears) + " years, ";
        } else if (currentYears < targetYears) {
            years = (currentYears - targetYears) + " years, ";
        } else {
            years = 0 + " years, ";
        }
        subtractionBuilder.append(years).append(months).append(days)
                          .append(hours).append(minutes).append(seconds).append(millis);
        return subtractionBuilder.toString();
    }

    public static void sortCalendarsAsc(List<Calendar> calendars) {
        Collections.sort(calendars);
    }

    public static void sortCalendarsDesc(List<Calendar> calendars) {
        Collections.reverse(calendars);
    }

    public void appendDate(Date date) {
        this.date.appendDate(date.getDay().getValue(),
                date.getMonth().getValue(),
                date.getYear().getValue());
    }

    public void subtractDate(Date date) {
        this.date.subtractDate(date.getDay().getValue(),
                date.getMonth().getValue(),
                date.getYear().getValue());
    }

    public void appendTime(Time time) {
        this.time.appendTime(time.getHour().getValue(),
                             time.getMinute().getValue(),
                             time.getSecond().getValue(),
                             time.getMilli().getValue());
    }

    public void subtractTime(Time time) {
        this.time.subtractTime(time.getHour().getValue(),
                               time.getMinute().getValue(),
                               time.getSecond().getValue(),
                               time.getMilli().getValue());
    }

    public Calendar appendDateAndTime(Date date, Time time) {
        appendTime(time);
        appendDate(date);
        return this;
    }

    public Calendar subtractDateAndTime(Date date, Time time) {
        subtractTime(time);
        subtractDate(date);
        return this;
    }

    public Calendar appendDateComponents(int days, int months, int years) {
        appendYears(years);
        appendMonths(months);
        appendDays(days);
        return this;
    }

    public Calendar subtractDateComponents(int days, int months, int years) {
        subtractYears(years);
        subtractMonths(months);
        subtractDays(days);
        return this;
    }

    public Calendar appendTimeComponents(int hours, int minutes, int seconds, int millis) {
        appendHours(hours);
        appendMinutes(minutes);
        appendSeconds(seconds);
        appendMillis(millis);
        return this;
    }

    public Calendar subtractTimeComponents(int hours, int minutes, int seconds, int millis) {
        subtractHours(hours);
        subtractMinutes(minutes);
        subtractSeconds(seconds);
        subtractMillis(millis);
        return this;
    }

    public Calendar appendDateAndTimeComponents(int days, int months, int years, int hours,
                                                int minutes, int seconds, int millis) {
        appendHours(hours);
        appendMinutes(minutes);
        appendSeconds(seconds);
        appendMillis(millis);
        appendYears(years);
        appendMonths(months);
        appendDays(days);
        return this;

    }

    public Calendar subtractDateAndTimeComponents(int days, int months, int years, int hours,
                                                  int minutes, int seconds, int millis) {
        subtractHours(hours);
        subtractMinutes(minutes);
        subtractSeconds(seconds);
        subtractMillis(millis);
        subtractYears(years);
        subtractMonths(months);
        subtractDays(days);
        return this;
    }

    public void appendMillis(int millis) {
        this.time.appendMillis(millis);
        this.date.appendDays(this.time.getDaysPassed());
        this.time.resetDaysPassed();
    }

    public void subtractMillis(int millis) {
        this.time.subtractMillis(millis);
        this.date.subtractDays(this.time.getDaysPassed());
        this.time.resetDaysPassed();
    }

    public void appendSeconds(int seconds) {
        this.time.appendSeconds(seconds);
        this.date.appendDays(this.time.getDaysPassed());
        this.time.resetDaysPassed();
    }

    public void subtractSeconds(int seconds) {
        this.time.subtractSeconds(seconds);
        this.date.subtractDays(this.time.getDaysPassed());
        this.time.resetDaysPassed();
    }

    public void appendMinutes(int minutes) {
        this.time.appendMinutes(minutes);
        this.date.appendDays(this.time.getDaysPassed());
        this.time.resetDaysPassed();
    }

    public void subtractMinutes(int minutes) {
        this.time.subtractMinutes(minutes);
        this.date.subtractDays(this.time.getDaysPassed());
        this.time.resetDaysPassed();
    }

    public void appendHours(int hours) {
        this.time.appendHours(hours);
        this.date.appendDays(this.time.getDaysPassed());
        this.time.resetDaysPassed();
    }

    public void subtractHours(int hours) {
        this.time.subtractHours(hours);
        this.date.subtractDays(this.time.getDaysPassed());
        this.time.resetDaysPassed();
    }

    public void appendDays(int days) {
        this.date.appendDays(days);
    }

    public void subtractDays(int days) {
        this.date.subtractDays(days);
    }

    public void appendMonths(int months) {
        this.date.appendMonths(months);
    }

    public void subtractMonths(int months) {
        this.date.subtractMonths(months);
    }

    public void appendYears(int years) {
        this.date.appendYears(years);
    }

    public void subtractYears(int years) {
        this.date.subtractYears(years);
    }

    public String distinct(Calendar target) {
        StringBuilder resultBuilder = new StringBuilder();
        if (this.compareTo(target) == 0) {
            resultBuilder.append("Calendars are exact!");
        } else if (this.compareTo(target) > 0) {
            resultBuilder.append(getCleanSubtraction(this, target)).append("\n");
            resultBuilder.append("or ").append(this.distinctByDays(target).longValue()).append(" calendar days").append("\n");
            resultBuilder.append("Target is before current date: ").append(target).append(" => ").append(this);
        } else {
            resultBuilder.append(getCleanSubtraction(target, this)).append("\n");
            resultBuilder.append("or ").append(target.distinctByDays(this).longValue()).append(" calendar days").append("\n");
            resultBuilder.append("Target is after current date: ").append("\n").append(this).append(" => ").append(target);
        }
        return resultBuilder.toString();
    }

    public Double distinctByMillis(Calendar target) {
        return ComponentsConverter.toMillisPrecise(this) - ComponentsConverter.toMillisPrecise(target);
    }

    public Double distinctBySeconds(Calendar target) {
        return ComponentsConverter.toSecondsRaw(this) - ComponentsConverter.toSecondsRaw(target);
    }

    public Double distinctByMinutes(Calendar target) {
        return ComponentsConverter.toMinutesRaw(this) - ComponentsConverter.toMinutesRaw(target);
    }

    public Double distinctByHours(Calendar target) {
        return ComponentsConverter.toHoursRaw(this) - ComponentsConverter.toHoursRaw(target);
    }

    public Double distinctByDays(Calendar target) {
        return ComponentsConverter.toDaysRaw(this) - ComponentsConverter.toDaysRaw(target);
    }

    public Double distinctByYears(Calendar target) {
        return ComponentsConverter.toYearsRaw(this) - ComponentsConverter.toYearsRaw(target);
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    @Override
    public int compareTo(Calendar target) {
        return Comparator.comparing(Calendar::getDate)
                .thenComparing(Calendar::getTime)
                .compare(this, target);
    }

    @Override
    public String toString() {
        return date + " " + time;
    }
}