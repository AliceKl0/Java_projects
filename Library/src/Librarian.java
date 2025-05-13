import java.util.ArrayList;

class Librarian extends Person {

    public Librarian(int employeeId, int yearOfBirth, String firstName, String lastName, String middleName,
                     String address, String phoneNumber) {
        super(employeeId, yearOfBirth, firstName, lastName, middleName, address, phoneNumber);
    }

    // Поиск книги по bookId
    public static Book findBookById(int bookId) {
        ArrayList<Book> libraryBooks = Manager.getLibraryBooks();
        for (Book book : libraryBooks) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }

    // Выдача книги читателю
    public static void lendBook(Book book, Reader reader) {
        boolean isAvaliableChoice = true;
        if (book.getCopiesCount() > 0) {
            for (Book bookInReaderList : reader.getBorrowedBooks()) {
                if (bookInReaderList.getBookId() == book.getBookId()) {
                    System.out.println("The book " + book.getBookTitle() + " is already borrowed by this reader.");
                    isAvaliableChoice = false;
                    break;
                }
            }
            if (isAvaliableChoice) {
                addBookToReader(book, reader);
                book.setCopiesCount(book.getCopiesCount() - 1);
                System.out.println("The book " + book.getBookTitle() + " is borrowed by " + reader.getPersonId() +
                        " " + reader.getFullName());
            }

        } else {
            System.out.println("The book " + book.getBookTitle() + " is not in stock.");
        }
    }


    // Принятие книги обратно от читателя
    public static void acceptReturnedBook(Book book, Reader reader) {
        removeBookFromReader(book, reader);
        book.setCopiesCount(book.getCopiesCount() + 1);
        System.out.println("The book " + book.getBookTitle() + " was returned by " + reader.getPersonId() + " " +
                reader.getFullName());
    }

    // Вспомогательный метод для добавления книги читателю
    private static void addBookToReader(Book book, Reader reader) {
        reader.getBorrowedBooks().add(book);
    }

    // Вспомогательный метод для удаления книги у читателя
    private static void removeBookFromReader(Book book, Reader reader) {
        reader.getBorrowedBooks().remove(book);
    }
}
