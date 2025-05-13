import java.util.ArrayList;
import java.util.Scanner;

class Manager extends Person {

    private static ArrayList<Book> libraryBooks = new ArrayList<>(); // Список всех книг в библиотеке
    private static ArrayList<Reader> readers = new ArrayList<>(); // Список всех зарегистрированных читателей
    private static ArrayList<Librarian> librarians = new ArrayList<>(); // Список всех библиотекарей

    public Manager(int employeeId, int yearOfBirth, String firstName, String lastName, String middleName,
                   String address, String phoneNumber) {
        super(employeeId, yearOfBirth, firstName, lastName, middleName, address, phoneNumber);
    }

    public static ArrayList<Book> getLibraryBooks() {
        return libraryBooks;
    }

    // Метод для добавления книги
    public void addBook(Scanner scanner) {
        System.out.println("Enter book id:");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter book title:");
        String title = scanner.nextLine();
        System.out.println("Enter author:");
        String author = scanner.nextLine();
        System.out.println("Enter edition:");
        String edition = scanner.nextLine();
        System.out.println("Enter publisher:");
        String publisher = scanner.nextLine();
        System.out.println("Enter publication year:");
        int year = scanner.nextInt();
        System.out.println("Enter copies count:");
        int copies = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter category:");
        String category = scanner.nextLine();

        Book book = new Book(bookId, title, author, edition, publisher, year, copies, category);
        libraryBooks.add(book);
        System.out.println("The book " + book.getBookTitle() + " has been added to the library.");
    }

    // Метод для удаления книги
    public void removeBook(Scanner scanner) {
        System.out.println("Enter book ID to remove:");
        int bookIdToRemove = scanner.nextInt();
        scanner.nextLine();
        Book removeBook = null;
        for (Book bookInStock : libraryBooks) {
            if (bookInStock.getBookId() == bookIdToRemove) {
                removeBook = bookInStock;
                libraryBooks.remove(bookInStock);
                System.out.println("The book " + removeBook.getBookTitle() + " has been removed from the library.");
                break;
            }
        }
        if (removeBook != null) {
            System.out.println("The book " + removeBook.getBookTitle() + " is not found in the library.");
        }
    }

    // Статистика: сколько книг было взято и сколько осталось
    public void libraryStatistics() {
        int borrowedBooksCount = 0;
        for (Reader reader : readers) {
            borrowedBooksCount += reader.getBorrowedBooks().size();
        }
        System.out.println("\nBooks borrowed: " + borrowedBooksCount);
        System.out.println("Books available in library: " + (libraryBooks.size() - borrowedBooksCount));
    }

    // Статистика: сколько книг по конкретной категории
    public void booksByCategory(String category) {
        int count = 0;
        for (Book book : libraryBooks) {
            if (book.getCategory().equals(category)) {
                count++;
            }
        }
        System.out.println("Books in category '" + category + "': " + count);
    }

    // Статистика: какие книги брал конкретный читатель
    public void booksBorrowedByReader(Reader reader) {
        ArrayList<Book> borrowedBooks = reader.getBorrowedBooks();
        if (borrowedBooks.isEmpty()) {
            System.out.println("Reader " + reader.getFullName() + " has not borrowed any books.");
        } else {
            System.out.println("Books borrowed by " + reader.getFullName() + ":");
            for (Book book : borrowedBooks) {
                book.displayInfo();
            }
        }
    }

    // Отображение всех книг в библиотеке
    public void displayAllBooks() {
        if (libraryBooks.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            for (Book book : libraryBooks) {
                book.displayInfo();
            }
        }
    }

    // Регистрация нового читателя
    public void registerReader(Scanner scanner) {
        System.out.println("Enter reader ID:");
        int readerId = scanner.nextInt();
        scanner.nextLine();
        if (findReaderById(readerId) == null) {
            System.out.println("Enter first name:");
            String firstName = scanner.nextLine();
            System.out.println("Enter last name:");
            String lastName = scanner.nextLine();
            System.out.println("Enter middle name:");
            String middleName = scanner.nextLine();
            System.out.println("Enter address:");
            String address = scanner.nextLine();
            System.out.println("Enter phone number:");
            String phoneNumber = scanner.nextLine();
            System.out.println("Enter year of birth:");
            int yearOfBirth = scanner.nextInt();

            Reader newReader = new Reader(readerId, yearOfBirth, firstName, lastName, middleName, address, phoneNumber);
            readers.add(newReader);
            System.out.println("Reader " + newReader.getFullName() + " has been registered.");
        } else {
            System.out.println("Reader with same ID already exists.");
        }
    }

    // Метод для удаления читателя
    public void removeReader(Scanner scanner) {
        System.out.println("Enter reader ID to remove:");
        int readerId = scanner.nextInt();
        scanner.nextLine();
        Reader removeReader = findReaderById(readerId);
        if (removeReader != null) {
            readers.remove(removeReader);
            System.out.println("The reader " + removeReader.getFullName() + " has been removed.");
        } else {
            System.out.println("Reader not found.");
        }
    }


    // Поиск читателя по libraryCard
    public static Reader findReaderById(int libraryCard) {
        for (Reader reader : readers) {
            if (reader.getPersonId() == libraryCard) {
                return reader;
            }
        }
        return null;
    }

    // Регистрация нового библиотекаря
    public void registerLibrarian(Scanner scanner) {
        System.out.println("Enter librarian ID:");
        int librarianId = scanner.nextInt();
        scanner.nextLine();
        if (findLibrarianById(librarianId) == null) {
            System.out.println("Enter first name:");
            String firstName = scanner.nextLine();
            System.out.println("Enter last name:");
            String lastName = scanner.nextLine();
            System.out.println("Enter middle name:");
            String middleName = scanner.nextLine();
            System.out.println("Enter address:");
            String address = scanner.nextLine();
            System.out.println("Enter phone number:");
            String phoneNumber = scanner.nextLine();
            System.out.println("Enter year of birth:");
            int yearOfBirth = scanner.nextInt();

            Librarian newLibrarian = new Librarian(librarianId, yearOfBirth, firstName, lastName, middleName, address,
                    phoneNumber);
            librarians.add(newLibrarian);
            System.out.println("Librarian " + newLibrarian.getFullName() + " has been registered.");
        } else {
            System.out.println("Librarian with same ID already exists.");
        }

    }

    // Поиск библиотекаря по ID
    public static Librarian findLibrarianById(int librarianId) {
        for (Librarian librarian : librarians) {
            if (librarian.getPersonId() == librarianId) {
                return librarian;
            }
        }
        return null;
    }

    // Метод для удаления читателя
    public void removeLibrarian(Scanner scanner) {
        System.out.println("Enter librian ID to remove:");
        int librianId = scanner.nextInt();
        scanner.nextLine();
        Librarian removeLibrian = findLibrarianById(librianId);
        if (removeLibrian != null) {
            readers.remove(removeLibrian);
            System.out.println("The librarian " + removeLibrian.getFullName() + " has been removed.");
        } else {
            System.out.println("Librarian not found.");
        }
    }

    // Проверка наличия библиотекарей
    public static boolean isLibrarianAvailable() {
        return !Manager.librarians.isEmpty();
    }
}
