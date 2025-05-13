import java.util.ArrayList;

class Reader extends Person {
    private ArrayList<Book> borrowedBooks;

    public Reader (int libraryCard, int yearOfBirth, String firstName, String lastName, String middleName,
                   String address, String phoneNumber) {
        super(libraryCard, yearOfBirth, firstName, lastName, middleName, address, phoneNumber);
        this.borrowedBooks = new ArrayList<>();
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}
