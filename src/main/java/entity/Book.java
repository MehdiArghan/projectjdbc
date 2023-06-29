package entity;
public class Book {
    private int bookId;
    private String title;
    private int publishedYear;
    private int authorId;

    public Book() {
    }

    public Book(int bookId, String title, int publishedYear, int authorId) {
        this.bookId = bookId;
        this.title = title;
        this.publishedYear = publishedYear;
        this.authorId = authorId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", publishedYear=" + publishedYear +
                ", authorId=" + authorId +
                '}';
    }
}
