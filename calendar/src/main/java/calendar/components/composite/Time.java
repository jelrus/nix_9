package calendar.components.composite;

import calendar.components.base.time.Hour;
import calendar.components.base.time.Milli;
import calendar.components.base.time.Minute;
import calendar.components.base.time.Second;

import java.util.Comparator;

public class Time implements Comparable<Time> {

    private Milli milli;
    private Second second;
    private Minute minute;
    private Hour hour;
    private int daysPassed;

    public Time() {
        this.milli = new Milli();
        this.second = new Second();
        this.minute = new Minute();
        this.hour = new Hour();
    }

    public Time(int hour, int minute, int second, int milli) {
        if (hour >= Hour.MIN_VALUE && hour < Hour.MAX_VALUE) {
            this.hour = new Hour(hour);
        } else {
            this.hour = new Hour();
        }
        if (minute >= Minute.MIN_VALUE && minute < Minute.MAX_VALUE) {
            this.minute = new Minute(minute);
        } else {
            this.minute = new Minute();
        }
        if (second >= Second.MIN_VALUE && second < Second.MAX_VALUE) {
            this.second = new Second(second);
        } else {
            this.second = new Second();
        }
        if (milli >= Milli.MIN_VALUE && milli < Milli.MAX_VALUE) {
            this.milli = new Milli(milli);
        } else {
            this.milli = new Milli();
        }
    }

    public void appendTime(int hours, int minutes, int seconds, int millis) {
        appendHours(hours);
        appendMinutes(minutes);
        appendSeconds(seconds);
        appendMillis(millis);
    }

    public void subtractTime(int hours, int minutes, int seconds, int millis) {
        subtractHours(hours);
        subtractMinutes(minutes);
        subtractSeconds(seconds);
        subtractMillis(millis);
    }

    public void appendMillis(int millis) {
        this.milli.setValue(revaluateMillisForward(millis));
    }

    public void subtractMillis(int millis) {
        this.milli.setValue(revaluateMillisBackward(millis));
    }

    private int revaluateMillisForward(int millis) {
        if (millis > 0) {
            for (int i = 0; i < millis; i++) {
                this.milli = nextMilli();
            }
        }
        return this.milli.getValue();
    }

    private int revaluateMillisBackward(int millis) {
        if (millis > 0) {
            for (int i = 0; i < millis; i++) {
                this.milli = previousMilli();
            }
        }
        return this.milli.getValue();
    }

    private Milli previousMilli() {
        if (this.milli.getValue() == Milli.MIN_VALUE) {
            this.second = previousSecond();
            this.milli.setValue(Milli.MAX_VALUE - 1);
        } else {
            this.milli.setValue(this.milli.getValue() - 1);
        }
        return this.milli;
    }

    public Milli getMilli() {
        return milli;
    }

    private Milli nextMilli() {
        if (this.milli.getValue() == Milli.MAX_VALUE - 1) {
            this.second = nextSecond();
            this.milli.setValue(Milli.MIN_VALUE);
        } else {
            this.milli.setValue(this.milli.getValue() + 1);
        }
        return this.milli;
    }

    public void appendSeconds(int seconds) {
        this.second.setValue(revaluateSecondsForward(seconds));
    }

    public void subtractSeconds(int seconds) {
        this.second.setValue(revaluateSecondsBackward(seconds));
    }

    private int revaluateSecondsForward(int seconds) {
        if (seconds > 0) {
            for (int i = 0; i < seconds; i++) {
                this.second = nextSecond();
            }
        }
        return this.second.getValue();
    }

    private int revaluateSecondsBackward(int seconds) {
        if (seconds > 0) {
            for (int i = 0; i < seconds; i++) {
                this.second = previousSecond();
            }
        }
        return this.second.getValue();
    }

    private Second previousSecond() {
        if (this.second.getValue() == Second.MIN_VALUE) {
            this.minute = previousMinute();
            return new Second(Second.MAX_VALUE - 1);
        } else {
            return new Second(this.second.getValue() - 1);
        }
    }

    public Second getSecond() {
        return second;
    }

    private Second nextSecond() {
        if (this.second.getValue() == Second.MAX_VALUE - 1) {
            this.minute = nextMinute();
            return new Second(Second.MIN_VALUE);
        } else {
            return new Second(this.second.getValue() + 1);
        }
    }

    public void appendMinutes(int minutes) {
        this.minute.setValue(revaluateMinutesForward(minutes));
    }

    public void subtractMinutes(int minutes) {
        this.minute.setValue(revaluateMinutesBackward(minutes));
    }

    private int revaluateMinutesForward(int minutes) {
        if (minutes > 0) {
            for (int i = 0; i < minutes; i++) {
                this.minute = nextMinute();
            }
        }
        return this.minute.getValue();
    }

    private int revaluateMinutesBackward(int minutes) {
        if (minutes > 0) {
            for (int i = 0; i < minutes; i++) {
                this.minute = previousMinute();
            }
        }
        return this.minute.getValue();
    }

    private Minute previousMinute() {
        if (this.minute.getValue() == Minute.MIN_VALUE) {
            this.hour = previousHour();
            return new Minute(Minute.MAX_VALUE - 1);
        } else {
            return new Minute(this.minute.getValue() - 1);
        }
    }

    public Minute getMinute() {
        return minute;
    }

    private Minute nextMinute() {
        if (this.minute.getValue() == Minute.MAX_VALUE - 1) {
            this.hour = nextHour();
            return new Minute(Minute.MIN_VALUE);
        } else {
            return new Minute(this.minute.getValue() + 1);
        }
    }

    public void appendHours(int hours) {
        this.hour.setValue(revaluateHoursForward(hours));
    }

    public void subtractHours(int hours) {
        this.hour.setValue(revaluateHoursBackward(hours));
    }

    private int revaluateHoursForward(int hours) {
        if (hours > 0) {
            for (int i = 0; i < hours; i++) {
                this.hour = nextHour();
            }
        }
        return this.hour.getValue();
    }

    private int revaluateHoursBackward(int hours) {
        if (hours > 0) {
            for (int i = 0; i < hours; i++) {
                this.hour = previousHour();
            }
        }
        return this.hour.getValue();
    }

    private Hour previousHour() {
        if (this.hour.getValue() == Hour.MIN_VALUE) {
            this.daysPassed--;
            return new Hour(Hour.MAX_VALUE - 1);
        } else {
            return new Hour(this.hour.getValue() - 1);
        }
    }

    public Hour getHour() {
        return hour;
    }

    private Hour nextHour() {
        if (this.hour.getValue() == Hour.MAX_VALUE - 1) {
            this.daysPassed++;
            return new Hour(Hour.MIN_VALUE);
        } else {
            return new Hour(this.hour.getValue() + 1);
        }
    }

    public int getDaysPassed() {
        return Math.abs(this.daysPassed);
    }

    public void resetDaysPassed() {
        daysPassed = 0;
    }

    @Override
    public int compareTo(Time target) {
        return Comparator.comparing(Time::getHour)
                .thenComparing(Time::getMinute)
                .thenComparing(Time::getSecond)
                .thenComparing(Time::getMilli)
                .compare(this, target);
    }

    @Override
    public String toString() {
        return hour + ":" + minute + ":" + second + "." + milli;
    }
}