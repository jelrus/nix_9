package ua.com.alevel.controller.messages;

import calendar.components.composite.Calendar;

public class MenuMessages {

    public static void mainMenuMessage() {
        System.out.println("""
                --------------------- Unit 6 - Exception ---------------------
                Choose option by entering following numbers:
                -------------------------- Operations -------------------------
                1 - Base operations calendar menu
                2 - Appending operations menu
                3 - Subtraction operations menu
                4 - Distinction operations menu
                5 - Sorting (list) operations menu
                ----------------------- Confirm action -----------------------
                Choose option by entering number into command line
                Exit enter 'e'.
                ______________________________________________________________
                """);
    }

    public static void baseMenuMessage() {
        System.out.println("""
                --------------------- Base Operations Menu --------------------
                Choose option by entering following numbers:
                -------------------------- Operations -------------------------
                1 - Create or change initial calendar
                2 - Change input format
                3 - Change output format
                ----------------------- Confirm action -----------------------
                Choose option by entering number into command line
                Exit enter 'e'.
                ______________________________________________________________
                """);
    }

    public static void appendingMenuMessage() {
        System.out.println("""
                ------------------- Appending Operations Menu -----------------
                Choose option by entering following numbers:
                -------------------------- Operations -------------------------
                1 - Append millis
                2 - Append seconds
                3 - Append minutes
                4 - Append hours
                5 - Append days
                6 - Append years
                ----------------------- Confirm action -----------------------
                Choose option by entering number into command line
                Exit enter 'e'.
                ______________________________________________________________
                """);
    }

    public static void subtractionMenuMessage() {
        System.out.println("""
                ------------------ Subtraction Operations Menu ----------------
                Choose option by entering following numbers:
                -------------------------- Operations -------------------------
                1 - Subtract millis
                2 - Subtract seconds
                3 - Subtract minutes
                4 - Subtract hours
                5 - Subtract days
                6 - Subtract years
                ----------------------- Confirm action -----------------------
                Choose option by entering number into command line
                Exit enter 'e'.
                ______________________________________________________________
                """);
    }

    public static void distinctionMenuMessage() {
        System.out.println("""
                ------------------ Distinction Operations Menu ----------------
                Choose option by entering following numbers:
                -------------------------- Operations -------------------------
                1 - Distinct millis
                2 - Distinct seconds
                3 - Distinct minutes
                4 - Distinct hours
                5 - Distinct days
                6 - Distinct years
                7 - Distinct calendars
                ----------------------- Confirm action -----------------------
                Choose option by entering number into command line
                Exit enter 'e'.
                ______________________________________________________________
                """);
    }

    public static void sortingMenuMessage() {
        System.out.println("""
                ------------------ Distinction Operations Menu ----------------
                Choose option by entering following numbers:
                -------------------------- Operations -------------------------
                1 - Create or change list
                2 - Sort list ascending
                3 - Sort list descending
                4 - Show list
                ----------------------- Confirm action -----------------------
                Choose option by entering number into command line
                Exit enter 'e'.
                ______________________________________________________________
                """);
    }

    public static void goBack() {
        System.out.println("""
                ------------------------ Selection Menu -----------------------
                Go back to submenu? (Y/n)
                ______________________________________________________________
                """);
    }

    public static void calendarInput() {
        System.out.println("""
                ------------------------- Input Menu -------------------------
                -------------------------- WARNING ---------------------------
                Input string should be equal by length to input format,
                less or more symbols will cause input error!
                Each letter represents component of date:
                d - days
                m - months
                y - years
                Additional letters in component represents length of component
                For example - y - stands for 1 digit year from 0 to 9;
                             yy - stands for 2 digit year from 10 to 99;
                            yyy - stands for 3 digit year from 100 to 1000;
                           yyyy - stands for 4 digit year from 1000 to 9999;
                Calendar input components are limited by this rule!
                To change input components go to:
                Base Operations Menu => Change input format
                --------------------------------------------------------------
                Enter calendar according to input format:
                --------------------------------------------------------------
                Example for default input format:
                29/02/1993 05:20:30.300
                ______________________________________________________________
                """);
    }

    public static void viewInputFormat(String inputFormat) {
        System.out.printf("""
                ---------------------- Input Format --------------------------
                Current input format: %s
                ______________________________________________________________
                """, inputFormat);
    }

    public static void calendarOutput(Calendar calendar) {
        System.out.printf("""
                ------------------------- Result -----------------------------
                Calendar has been created successfully!
                %s
                ______________________________________________________________
                """, calendar);
    }

    public static void formatInput() {
        System.out.println("""
                ------------------------- Input Menu -------------------------
                -------------------------- WARNING ---------------------------
                Input format can be constructed from letters and constructions
                below, any delimiters can be used, even date out of them will
                be parsed if inputted correctly.
                Each letter represents component of date/time:
                d - days
                m - months
                y - years
                00 - hours
                00:00 - hours and minutes
                00:00:00 - hours, minutes and seconds
                00:00:00.000 - hours, minutes, seconds and millis
                Additional letters in date component represents length of component
                For example - y - stands for 1 digit year from 0 to 9;
                             yy - stands for 2 digit year from 10 to 99;
                            yyy - stands for 3 digit year from 100 to 1000;
                           yyyy - stands for 4 digit year from 1000 to 9999;
                Calendar input components are limited by this rule!
                --------------------------------------------------------------
                Default input format dd/mm/yyyy 00:00:00.000.
                Construct input format:
                --------------------------------------------------------------
                Examples: dd/mm yy (assumed input [a.i] 10/12/20)
                          yyy, mm, dd (a.i. 132, 04, 23)
                          m/d/yyyy 00 (a.i. 2/9/1345 20)
                          00:00:00.000 mm/dd/yyyy (a.i. 20:30:45.546 05/11/1450)
                ______________________________________________________________
                """);
    }

    public static void formatInputResult(String oldFormat, String newFormat) {
        System.out.printf("""
                ------------------------- Result Menu -------------------------
                Input format has been changed!
                --------------------------------------------------------------
                From %s to %s
                ______________________________________________________________
                """, oldFormat, newFormat);
    }

    public static void formatOutput() {
        System.out.println("""
                ------------------------- Input Menu -------------------------
                -------------------------- WARNING ---------------------------
                Output format can be constructed from letters and constructions
                below, any delimiters can be used, even date out them will be
                formatted according to format pattern.
                Each letter represents component of date/time:
                d - days
                m - months
                (mmm construction is allowed here, will cause to display month name)
                y - years
                00 - hours
                00:00 - hours and minutes
                00:00:00 - hours, minutes and seconds
                00:00:00.000 - hours, minutes, seconds and millis
                Additional letters in date component represents length of component
                For example - y - stands for 1 digit year from 0 to 9;
                             yy - stands for 2 digit year from 10 to 99;
                            yyy - stands for 3 digit year from 100 to 1000;
                           yyyy - stands for 4 digit year from 1000 to 9999;
                Calendar format components are limited by this rule!
                --------------------------------------------------------------
                Default output format dd/mm/yyyy 00:00:00.000.
                Construct output format:
                --------------------------------------------------------------
                Examples: dd/mm yy (assumed output [a.o] 10/12/20)
                          Year: yyy, Month: mm, Day: dd (a.o. Year: 132, Month: 04, Day: 23)
                          mmm, dd yyyy 00 (a.i. 5, 9 1345 12 => a.o. May, 9 1345 20)
                          00:00:00.000 mm/dd/yyyy (a.o. 20:30:45.546 05/11/1450)
                ______________________________________________________________
                """);
    }

    public static void formatOutputResult(String oldFormat, String newFormat) {
        System.out.printf("""
                ------------------------- Result Menu -------------------------
                Output format has been changed!
                --------------------------------------------------------------
                From %s to %s
                ______________________________________________________________
                """, oldFormat, newFormat);
    }

    public static void currentCalendar(Calendar calendar) {
        System.out.printf("""
                ------------------------- Help Menu -------------------------
                Current date is %s
                ______________________________________________________________
                """, calendar);
    }

    public static void millisAppendInput() {
        System.out.println("""
                ------------------------- Input Menu -------------------------
                Enter millis to append:
                ______________________________________________________________
                """);
    }

    public static void millisSubtractInput() {
        System.out.println("""
                ------------------------- Input Menu -------------------------
                Enter millis to subtract:
                ______________________________________________________________
                """);
    }

    public static void secondsAppendInput() {
        System.out.println("""
                ------------------------- Input Menu -------------------------
                Enter seconds to append:
                ______________________________________________________________
                """);
    }

    public static void secondsSubtractInput() {
        System.out.println("""
                ------------------------- Input Menu -------------------------
                Enter seconds to subtract:
                ______________________________________________________________
                """);
    }

    public static void minutesAppendInput() {
        System.out.println("""
                ------------------------- Input Menu -------------------------
                Enter minutes to append:
                ______________________________________________________________
                """);
    }

    public static void minutesSubtractInput() {
        System.out.println("""
                ------------------------- Input Menu -------------------------
                Enter minutes to subtract:
                ______________________________________________________________
                """);
    }

    public static void hoursAppendInput() {
        System.out.println("""
                ------------------------- Input Menu -------------------------
                Enter hours to append:
                ______________________________________________________________
                """);
    }

    public static void hoursSubtractInput() {
        System.out.println("""
                ------------------------- Input Menu -------------------------
                Enter hours to subtract:
                ______________________________________________________________
                """);
    }

    public static void daysAppendInput() {
        System.out.println("""
                ------------------------- Input Menu -------------------------
                Enter days to append:
                ______________________________________________________________
                """);
    }

    public static void daysSubtractInput() {
        System.out.println("""
                ------------------------- Input Menu -------------------------
                Enter days to subtract:
                ______________________________________________________________
                """);
    }

    public static void yearsAppendInput() {
        System.out.println("""
                ------------------------- Input Menu -------------------------
                Enter years to append:
                ______________________________________________________________
                """);
    }

    public static void yearsSubtractInput() {
        System.out.println("""
                ------------------------- Input Menu -------------------------
                Enter years to subtract:
                ______________________________________________________________
                """);
    }

    public static void dateResult(String calendar) {
        System.out.printf("""
                ------------------------- Result Menu -------------------------
                Date has been updated to %s
                ______________________________________________________________
                """, calendar);
    }

    public static void newDate() {
        System.out.println("""
                ------------------------- Input Menu -------------------------
                Enter new calendar according to input format:
                --------------------------------------------------------------
                Example for default input format:
                30/05/2010 10:25:53.300
                ______________________________________________________________
                """);
    }

    public static void millisDistinctionResult(Calendar source, Calendar target, String millis) {
        System.out.printf("""
                ------------------------- Result Menu ------------------------
                Millis between current: %s
                and target:             %s
                are                     %s milliseconds
                ______________________________________________________________
                """, source, target, millis);
    }

    public static void secondsDistinctionResult(Calendar source, Calendar target, String seconds) {
        System.out.printf("""
                ------------------------- Result Menu ------------------------
                Seconds between current: %s
                and target:              %s
                are                      %s seconds
                ______________________________________________________________
                """, source, target, seconds);
    }

    public static void minutesDistinctionResult(Calendar source, Calendar target, String minutes) {
        System.out.printf("""
                ------------------------- Result Menu ------------------------
                Minutes between current: %s
                and target:              %s
                are                      %s minutes
                ______________________________________________________________
                """, source, target, minutes);
    }

    public static void hoursDistinctionResult(Calendar source, Calendar target, String hours) {
        System.out.printf("""
                ------------------------- Result Menu ------------------------
                Hours between current: %s
                and target:            %s
                are                    %s hours
                ______________________________________________________________
                """, source, target, hours);
    }

    public static void daysDistinctionResult(Calendar source, Calendar target, String days) {
        System.out.printf("""
                ------------------------- Result Menu ------------------------
                Days between current: %s
                and target:           %s
                are                   %s days
                ______________________________________________________________
                """, source, target, days);
    }

    public static void yearsDistinctionResult(Calendar source, Calendar target, String years) {
        System.out.printf("""
                ------------------------- Result Menu ------------------------
                Years between current: %s
                and target:            %s
                are                    %s years
                ______________________________________________________________
                """, source, target, years);
    }

    public static void dateDistinctionResult(String distinction) {
        System.out.printf("""
                ------------------------- Result Menu ------------------------
                Difference between current and target:
                %s
                ______________________________________________________________
                """, distinction);
    }

    public static void datesNumber() {
        System.out.println("""
                ------------------------- Input Menu -------------------------
                Enter how many dates add to list:
                ______________________________________________________________
                """);
    }

    public static void enterDate(int position) {
        System.out.printf("""
                ------------------------- Input Menu -------------------------
                Enter date %d
                --------------------------------------------------------------
                Examples:
                28/12/2008 10:25:53.300
                31/06/2006 10:23:53.320
                28/07/2012 15:25:53.310
                23/05/2006 11:24:53.300
                14/05/2006 10:25:53.300
                ______________________________________________________________
                """, position);
    }

    public static void sortAscending() {
        System.out.println("""
                ------------------------- Result Menu ------------------------
                List has been sorted ascended.
                To look on changes use "Show list" in submenu.
                ______________________________________________________________
                """);
    }

    public static void sortDescending() {
        System.out.println("""
                ------------------------- Result Menu ------------------------
                List has been sorted descended.
                To look on changes use "Show list" in submenu.
                ______________________________________________________________
                """);
    }

    public static void showAll(String dates) {
        System.out.printf("""
                ------------------------- Result Menu ------------------------
                List of dates:
                %s
                ______________________________________________________________
                """, dates);
    }
}