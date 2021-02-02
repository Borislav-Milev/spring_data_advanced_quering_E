package spring.app.constants;

public final class FilePaths {

    private FilePaths(){
    }

    private static final String FILE_PATH =
            System.getProperty("user.dir") + "\\src\\main\\resources\\files\\";

    public static final String AUTHORS_FILE_PATH = FILE_PATH + "authors.txt";
    public static final String BOOKS_FILE_PATH = FILE_PATH + "books.txt";
    public static final String CATEGORIES_FILE_PATH = FILE_PATH + "categories.txt";
}
