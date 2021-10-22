package ua.com.alevel.messages;

public class MenuMessages {
    public static void returnMainMenuText() {
        System.out.print(
                """
                        ------------------ Unit 2 - String Operations ------------------
                        Choose option by entering following numbers:
                        ------------------------- String methods ------------------------
                         1  - Reverse full string
                         2  - Reverse with first symbol
                         3  - Reverse after first symbol
                         4  - Reverse from index
                         5  - Reverse from by indexes
                         6  - Reverse char sequence
                         ------------------------ Substring methods ----------------------
                         7  - Reverse substring
                         8  - Reverse substrings with symbol
                         9  - Reverse substring after symbol
                         10 - Reverse substrings from index
                         11 - Reverse substrings by indexes
                        ----------------------- Confirm action -----------------------
                        Choose option by entering number from 1 to 11 into command line
                        Help enter 'h'.
                        Exit enter 'e'.
                        Check Log enter 'l'.
                        Check Error Log enter 'er'.
                        Check Unified Log enter 'ul'.
                        ______________________________________________________________
                        """
        );
    }

    public static void returnHelpText(){
        System.out.print("""
                         ------------------------------------- Help -------------------------------------
                         -------------------------------- String methods --------------------------------
                         1  - Reverse full string
                         input:  hello great world hello -> output: [olleh dlrow taerg olleh]
                         
                         2  - Reverse with first symbol;
                         input:  hello great world hello, l -> output: he[olleh dlrow taerg oll]
                         
                         3  - Reverse after first symbol;
                         input:  hello great world hello, l -> output: hel[olleh dlrow taerg ol]
                         
                         4 - Reverse string from index;
                         input:  hello great world hello, 4 -> output: hell[olleh dlrow taerg o]
                         
                         5  - Reverse string by indexes;
                         input:  hello great world hello, 3, 7 -> output: hel[rg ol]eat world hello
                         
                         6 - Reverse char sequence.
                         input:  hello great world hello, [ell ] -> output: he[ oll]great world hello
                         
                         ------------------------------ Substring methods --------------------------------
                         7  - Reverse subsequence;
                         input:  hello great world hello, ell -> output: h[lle]o great world h[lle]o
                         
                         8  - Reverse sequences with symbol;
                         input:  hello great world hello, l -> output: he[l][row taerg ol][eh dl][l][ol]
                         
                         9  - Reverse sequences after symbol
                         input:  hello great world hello, l -> output: hell[row taerg o]l[eh d]ll[o]
                         
                         10  - Reverse substrings from index
                         input:  hello great world hello, 2 -> output: hello great [dlrow olleh]
                         
                         11  - Reverse substrings by indexes;
                         input:  hello great world hello, 1, 3 -> output: hello [taerg dlrow olleh]
                         ________________________________________________________________________________
                         """);
    }

    public static void returnSubMenuText() {
        System.out.print(
                """
                        ----------------------- Confirm action -----------------------
                        Do you want to return back? (y/N)
                        If you want to exit enter 'e'.
                        ______________________________________________________________
                        """
        );
    }

    public static void returnLogsSubMenuText() {
        System.out.print(
                """
                        ----------------------- Confirm action -----------------------
                        Do you want to return back? (y)
                        If you want to exit enter 'e'.
                        ______________________________________________________________
                        """
        );
    }

    public static void getInputMessage() {
        System.out.print(
                """
                        ------------------------ Input window-------------------------
                        Enter input data here:
                        ______________________________________________________________
                        """
        );
    }

    public static void getInputBeginIndexMessage(){
        System.out.print("""
                         From:
                         ______________________________________________________________
                         """);
    }

    public static void getInputEndIndexMessage(){
        System.out.print("""
                         To:
                         ______________________________________________________________
                         """);
    }

    public static void getInputSubsequenceMessage(){
        System.out.print("""
                           Enter subsequence:
                           ____________________________________________________________
                           """);
    }

    public static void getInputCharMessage(){
        System.out.print("""
                           Enter char:
                           _____________________________________________________________
                           """);
    }

    public static void returnResult(String input, String output) {
        System.out.printf(
                """
                        --------------------------- Result----------------------------
                        Input:  %s
                        Output: %s
                        ______________________________________________________________
                        """, input, output
        );
    }
}
