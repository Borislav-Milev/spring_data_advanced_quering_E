package spring.app.constants;

public final class Messages {

    private Messages() {
    }

    //Exception
    public static final String NO_SUCH_EXERCISE = "There is no such exercise.";
    public static final String NO_SUCH_AGE_RESTRICTION = "No such age restriction.";
    public static final String PROCEDURE_ALREADY_CREATED = "\nProcedure already created.";

    //Message
    public static final String INSTRUCTIONS = "\nPlease select a query from 1 to 14 you wish to test.\n" +
            "If you want to end testing, press 0.\n" +
            "Create procedure by selecting 15 for exercise 14.";

    //Database filled messages
    public static final String FILLED_AUTHORS = "Authors are already filled.";
    public static final String FILLED_BOOKS = "Books are already filled.";
    public static final String FILLED_CATEGORIES = "Categories are already filled.";

    //EX1
    public static final String INPUT_BOOKS_AGE_RESTRICTION = "Please enter age restriction.";

    //EX4
    public static final String INPUT_YEAR = "Please enter an year.";

    //EX5
    public static final String INPUT_DATE = "Please enter date in format dd-MM-yyyy.";

    //EX6 //EX7 //EX8
    public static final String INPUT_STRING = "Please enter string.";

    //EX9
    public static final String INPUT_TITLE_LENGTH = "Please input title length.";

    public static final String OUTPUT_STRING = "There are %d books with longer title than %d symbols.%n";

    //EX11
    public static final String INPUT_TITLE = "Please input title.";

    //EX12
    public static final String INPUT_DATE_AFTER = "Please input date in format dd MMM yyyy";

    public static final String INPUT_COPIES_TO_ADD = "Please input amount of copies to add.";

    public static final String OUTPUT_INFORMATION =
            "%d books are released after %s, so total of %d book copies were added.%n";
    
    //EX13
    public static final String INPUT_AMOUNT_OF_COPIES_FOR_REMOVAL = "Please input amount of copies.";

    public static final String RESULT_BOOKS_REMOVED = "%d books removed%n";

    public static final String EXIT_APP = "Table needs to update...";

    //EX14
    public static final String INPUT_AUTHOR_FIRST_NAME = "Please input author's first name.";

    public static final String INPUT_AUTHOR_LAST_NAME = "Please input author's last name.";

    public static final String CREATED_PROCEDURE = "Procedure created.";

    public static final String NO_AUTHOR_FOUND = "No author with name %s %s.%n";

    public static final String AUTHOR_BOOKS_COUNT = "%s %s has written %d books.%n";
}
