import java.util.ArrayList;
import java.util.Scanner;

class LibrarySystem {
    private static ArrayList<Manager> managers = new ArrayList<>(); // Список всех менеджеров

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nLibrary System Menu:");
            System.out.println("1. Register a manager");
            System.out.println("2. Remove a manager");
            System.out.println("3. Register a reader");
            System.out.println("4. Remove a reader");
            System.out.println("5. Register a librarian");
            System.out.println("6. Remove a librarian");
            System.out.println("7. Add a book");
            System.out.println("8. Remove a book");
            System.out.println("9. View all books");
            System.out.println("10. Library statistics");
            System.out.println("11. Books by category");
            System.out.println("12. View books borrowed by reader");
            System.out.println("13. Reader borrows a book");
            System.out.println("14. Reader returns a book");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerManager(scanner);
                    break;
                case 2:
                    removeManager(scanner);
                    break;
                case 3:
                    if (isManagerAvailable()) {
                        Manager manager = managers.get(0);
                            manager.registerReader(scanner);
                    } else {
                        System.out.println("Sorry, there are no managers in our library to register a reader.");
                    }
                    break;
                case 4:
                    if (isManagerAvailable()) {
                        Manager manager = managers.get(0);
                            manager.removeReader(scanner);
                    } else {
                        System.out.println("Sorry, there are no managers in our library to remove a reader.");
                    }
                    break;
                case 5:
                    if (isManagerAvailable()) {
                        Manager manager = managers.get(0);
                            manager.registerLibrarian(scanner);
                    } else {
                        System.out.println("Sorry, there are no managers in our library to register a librarian.");
                    }
                    break;
                case 6:
                    if (isManagerAvailable()) {
                        Manager manager = managers.get(0);
                            manager.removeLibrarian(scanner);
                    } else {
                        System.out.println("Sorry, there are no managers in our library to remove a librarian.");
                    }
                    break;
                case 7:
                    if (isManagerAvailable()) {
                        Manager manager = managers.get(0);
                            manager.addBook(scanner);
                    } else {
                        System.out.println("Sorry, there are no managers in our library to add a book.");
                    }
                    break;
                case 8:
                    if (isManagerAvailable()) {
                        Manager manager = managers.get(0);
                            manager.removeBook(scanner);
                    } else {
                        System.out.println("Sorry, there are no managers in our library to remove a book.");
                    }
                    break;
                case 9:
                    if (isManagerAvailable()) {
                        Manager manager = managers.get(0);
                            manager.displayAllBooks();
                    } else {
                        System.out.println("Sorry, there are no managers in our library to view books.");
                    }
                    break;
                case 10:
                    if (isManagerAvailable()) {
                        Manager manager = managers.get(0);
                            manager.libraryStatistics();

                    } else {
                        System.out.println("Sorry, there are no managers in our library to view statistics.");
                    }
                    break;
                case 11:
                    if (isManagerAvailable()) {
                        Manager manager = managers.get(0);
                            System.out.print("Enter category: ");
                            String category = scanner.nextLine();
                            manager.booksByCategory(category);
                    } else {
                        System.out.println("Sorry, there are no managers in our library to view books by category.");
                    }
                    break;
                case 12:
                    if (isManagerAvailable()) {
                        Manager manager = managers.get(0);
                            System.out.print("Enter reader ID: ");
                            int readerId = scanner.nextInt();
                            Reader reader = manager.findReaderById(readerId);
                            if (reader != null) {
                                manager.booksBorrowedByReader(reader);
                            } else {
                                System.out.println("Reader not found.");
                            }
                    } else {
                        System.out.println("Sorry, there are no managers in our library to view borrowed books.");
                    }
                    break;
                case 13: // Читатель берёт книгу
                    if (isManagerAvailable()) {
                        Manager manager = managers.get(0);
                        if (Manager.isLibrarianAvailable()) {
                            System.out.print("Enter reader ID: ");
                            int readerId = scanner.nextInt();
                            Reader reader = manager.findReaderById(readerId);
                            if (reader != null) {
                                System.out.print("Enter book ID: ");
                                int bookId = scanner.nextInt();
                                Book ourBook = Librarian.findBookById(bookId);
                                if (ourBook != null) {
                                    Librarian.lendBook(ourBook, reader);
                                } else {
                                    System.out.println("Book not found.");
                                }
                            } else {
                                System.out.println("Reader not found.");
                            }
                        } else {
                            System.out.println("Sorry, there are no librarians in our library to borrow a book.");
                        }
                    } else {
                        System.out.println("Sorry, there are no managers in our library to borrow a books");
                    }
                    break;
                case 14: // Читатель возвращает книгу
                    if (isManagerAvailable()) {
                        Manager manager = managers.get(0);
                        if (Manager.isLibrarianAvailable()) {
                            System.out.print("Enter reader ID: ");
                            int readerId = scanner.nextInt();
                            Reader reader = manager.findReaderById(readerId);
                            if (reader != null) {
                                System.out.print("Enter book ID: ");
                                int bookId = scanner.nextInt();
                                Book ourBook = Librarian.findBookById(bookId);
                                if (ourBook != null) {
                                    Librarian.acceptReturnedBook(ourBook, reader);
                                } else {
                                    System.out.println("Book not found.");
                                }
                            } else {
                                System.out.println("Reader not found.");
                            }
                        } else {
                            System.out.println("Sorry, there are no librarians in our library to accept a book.");
                        }
                    } else {
                        System.out.println("Sorry, there are no librarians in our library to accept a books");
                    }
                    break;
                case 0:
                    exit = true;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    // Регистрация менеджера
    private static void registerManager(Scanner scanner) {
        System.out.println("Enter manager ID:");
        int managerId = scanner.nextInt();
        scanner.nextLine();
        if (findManagerById(managerId) == null) {
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

            Manager newManager = new Manager(managerId, yearOfBirth, firstName, lastName, middleName, address, phoneNumber);
            managers.add(newManager);
            System.out.println("Manager " + newManager.getFullName() + " has been registered.");
        } else {
            System.out.println("Manager with same ID already exists.");
        }
    }

    // Поиск менеджера по ID
    public static Manager findManagerById(int managerId) {
        for (Manager manager : managers) {
            if (manager.getPersonId() == managerId) {
                return manager;
            }
        }
        return null;
    }
    // Удаление менеджера
    private static void removeManager(Scanner scanner) {
        if (managers.isEmpty()) {
            System.out.println("No managers available to remove.");
            return;
        }
        System.out.println("Enter manager ID to remove:");
        int managerId = scanner.nextInt();
        scanner.nextLine();
        Manager removeManager = findManagerById(managerId);
        if (removeManager != null) {
            managers.remove(removeManager);
            System.out.println("The manager" + removeManager.getFullName() + " has been removed.");
        } else {
            System.out.println("Manager not found.");
        }
    }

    // Проверка наличия менеджеров
    private static boolean isManagerAvailable() {
        return !managers.isEmpty();
    }
}
