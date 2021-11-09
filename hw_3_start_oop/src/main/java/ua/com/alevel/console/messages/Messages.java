package ua.com.alevel.console.messages;

public class Messages {

    public static void returnMainMenuText() {
        System.out.print(
                """
                        ------------------ Unit 3 - CRUD Operations -------------------
                        Choose option by entering following numbers:
                        ------------------------- CRUD methods ------------------------
                         1  - Create user
                         2  - Update user
                         3  - Delete user
                         4  - Find user by ID
                         5  - Find all users
                        ----------------------- Confirm action -----------------------
                        Choose option by entering number into command line
                        Exit enter 'e'.
                        Check Log enter 'l'.
                        Check Error Log enter 'er'.
                        Check Unified Log enter 'ul'.
                        ______________________________________________________________
                        """
        );
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

    public static void getInputIdMessage() {
        System.out.print("""
                        ------------------------ Input window-------------------------
                        Enter id:
                        ______________________________________________________________
                        """
        );
    }

    public static void getInputNameMessage() {
        System.out.print("""
                        ------------------------ Input window-------------------------
                        Enter name:
                        ______________________________________________________________
                        """
        );
    }

    public static void getInputNameShortedMessage() {
        System.out.print("""
                        Enter name:
                        ______________________________________________________________
                        """
        );
    }

    public static void getInputLastNameMessage() {
        System.out.print("""
                        Enter last name:
                        ______________________________________________________________
                        """
        );
    }

    public static void getInputAgeMessage() {
        System.out.print("""
                        Enter age:
                        ______________________________________________________________
                        """
        );
    }

    public static void getInputEmailMessage() {
        System.out.print("""
                        Enter email:
                        ______________________________________________________________
                        """
        );
    }

    public static void successCreate(String id, String name, String lastName, String age, String email){
        System.out.printf("""
                          -------------------------- Success ---------------------------
                          User has been successfully created!
                          ID:        %s
                          Name:      %s
                          Last name: %s
                          Age:       %s
                          Email:     %s
                          ______________________________________________________________
                          """, id, name, lastName, age, email);
    }

    public static void successDelete(String id){
        System.out.printf("""
                          -------------------------- Success ---------------------------
                          User with id: "%s" successfully deleted!
                          ______________________________________________________________
                          """, id);
    }

    public static void successUpdate(String id, String name, String lastName, String age, String email){
        System.out.printf("""
                          -------------------------- Success ---------------------------
                          User with id: "%s" has been successfully updated!
                          New name:      %s
                          New last name: %s
                          New age:       %s
                          New email:     %s
                          ______________________________________________________________
                          """, id, name, lastName, age, email);
    }

    public static void successFindById(String id, String name, String lastName, String age, String email){
        System.out.printf("""
                          -------------------------- Success ---------------------------
                          User with id: "%s" has been found!
                          Name:      %s
                          Last name: %s
                          Age:       %s
                          Email:     %s
                          ______________________________________________________________
                          """, id, name, lastName, age, email);
    }

    public static void returnUserList(String users){
        String idHeader = "----------------- ID ----------------";
        String nameHeader = "+----------- NAME -----------+";
        String lastNameHeader = "--------- LAST NAME ---------";
        String ageHeader = "+-- AGE --+";
        String emailHeader = "------------ EMAIL -----------";
        String successLine = "----------------------------------------------------------------";
        String successText = " Success ";
        String filler = "---------";
        System.out.printf("""
                          %s%s%s
                          %s%s%s%s%s
                          %s
                          %s%s%s
                          """,
                successLine,successText,successLine,
                idHeader, nameHeader, lastNameHeader, ageHeader, emailHeader, users,
                successLine,filler,successLine);
    }
}