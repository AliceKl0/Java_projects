public class Book {
    private int bookId;
    private String bookTitle;
    private String author;
    private String edition;
    private String publisher;
    private int pubYear;
    private String category;
    private int copiesCount;

    public Book(int bookId, String bookTitle, String author, String edition, String publisher, int pubYear,
                int copiesCount, String category) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.author = author;
        this.edition = edition;
        this.publisher = publisher;
        this.pubYear = pubYear;
        this.category = category;
        this.copiesCount = copiesCount;
    }

    // Геттеры и сеттеры. Нет смысла делать их для всех атрибутов, т.к. рациональнее будет просто добавлять новую книгу.

    public int getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public int getPubYear() {
        return pubYear;
    }

    public String getCategory() {
        return category;
    }

    public int getCopiesCount() {
        return copiesCount;
    }

    public void setCopiesCount(int copiesCount) {
        this.copiesCount = copiesCount;
    }

    public void displayInfo() {
        System.out.println("\n" + "Book id: " + bookId + "\n" + "Book title: " + bookTitle + "\n" + "Author: "
                + author + "\n" + "Edition: " + edition + "\n" + "Publisher: " + publisher + "\n" + "Publication year: "
                + pubYear +  "\n" + "Category: " + category + "\n" +  "Count of copies: " + copiesCount);
    }

}