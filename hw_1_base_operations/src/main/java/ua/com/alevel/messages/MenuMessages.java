package ua.com.alevel.messages;

public class MenuMessages {
    public static void returnMainMenuText() {
        System.out.print(
                """
                        ------------- Unit 1 - Base Operations -------------
                        Choose option by entering following numbers
                         1 - Count sum of digits in line
                         2 - Filter and count quantity of characters in line
                         3 - Calculate when the lesson ends
                         
                        ------------------ Confirm action ------------------
                        Choose option by entering 1, 2 or 3 into command line
                        Exit enter 'e'.
                        Check Log enter 'l'.
                        Check Error Log enter 'er'.
                        Check Unified Log enter 'ul'.
                        ____________________________________________________
                        """
        );
    }

    public static void returnSubMenuText() {
        System.out.print(
                """
                        ------------------ Confirm action ------------------
                        Do you want to return back? (y/N)
                        If you want to exit enter 'e'.
                        ____________________________________________________
                        """
        );
    }

    public static void returnLogsSubMenuText() {
        System.out.print(
                """
                        ------------------ Confirm action ------------------
                        Do you want to return back? (y)
                        If you want to exit enter 'e'.
                        ____________________________________________________
                        """
        );
    }

    public static void returnResult(String input, String output) {
        System.out.printf(
                """
                        -----------------------Result-----------------------
                        Input:  %s
                        Output: %s
                        ____________________________________________________
                        """, input, output
        );
    }

    public static void getInputMessage() {
        System.out.println(
                """
                        --------------------Input window--------------------
                        Enter input data here:
                        ____________________________________________________
                        """
        );
    }
}
