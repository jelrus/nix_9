package ua.com.alevel.console.messages;

public class MenuMessages {

    public static void mainMenuText() {
        System.out.print("""
                -------------------- Module 2 ----------------------
                Choose option by entering following numbers
                Generators:
                1 - Generate random dates
                2 - Generate random names
                3 - Generate random cities
                Writers:
                4 - Get valid dates
                5 - Get unique name
                6 - Get shortest path
                 
                ------------------ Confirm action ------------------
                Choose option by entering 1-6 into command line
                Exit enter 'e'.
                ____________________________________________________
                """
        );
    }

    public static void datesGenerationSuccess() {
        System.out.print("""
                -------------------- Success ----------------------
                Dates has been successfully generated in directory!
                Dir: text_files/input_files/dates.txt
                ____________________________________________________
                """
        );
    }

    public static void namesGenerationSuccess() {
        System.out.print("""
                -------------------- Success ----------------------
                Names has been successfully generated in directory!
                Dir: text_files/input_files/names.txt
                ____________________________________________________
                """
        );
    }

    public static void citiesGenerationSuccess() {
        System.out.print("""
                -------------------- Success ----------------------
                Cities has been successfully generated in directory!
                Dir: text_files/input_files/cities.txt
                ____________________________________________________
                """
        );
    }

    public static void datesResolvingSuccess() {
        System.out.print("""
                -------------------- Success ----------------------
                Dates has been successfully resolved.
                Check output directory!
                Dir: text_files/output_files/dates.txt
                ____________________________________________________
                """
        );
    }

    public static void namesResolvingSuccess() {
        System.out.print("""
                -------------------- Success ----------------------
                Names has been successfully resolved.
                Check output directory!
                Dir: text_files/output_files/names.txt
                ____________________________________________________
                """
        );
    }

    public static void citiesResolvingSuccess() {
        System.out.print("""
                -------------------- Success ----------------------
                Cities has been successfully resolved.
                Check output directory!
                Dir: text_files/output_files/cities.txt
                ____________________________________________________
                """
        );
    }

    public static void error() {
        System.out.print("""
                ------------------ Error Message -------------------
                Error. Incorrect input! Try again.
                ____________________________________________________
                """);
    }
}