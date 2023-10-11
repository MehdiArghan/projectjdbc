package view;

import entity.Author;
import entity.Book;
import service.AuthorService;
import service.BookService;

public class CreateList {
    private AuthorService authorService = new AuthorService();
    private BookService bookService = new BookService();

    public void createAuthor() {
        String[] authorName = {"mehdi", "mohammad", "erfan", "ali"};
        String[] authorFamily = {"arghan", "sedghi", "navab", "malek"};
        for (int i = 0; i < authorName.length; i++) {
            authorService.register(authorName[i], authorFamily[i], i + 20);
        }
        System.out.println("list of author created");
    }

    public void createBook() {

        String[] title = {"math", "history", "english", "culture"};
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int publishedYear = 2016;
                bookService.addBook(title[j], publishedYear + j, i);
            }
        }
    }


    public void printAuthorBook(int authorId) {
        for (Book book : bookService.authorBookList(authorId)) {
            if (book != null) {
                System.out.println(book.getTitle() + " : " + book.getPublishedYear());
            } else {
                System.out.println("book is null");
            }
        }
    }

    public void sortAuthor(Author[] authors) {
        int size = 4;
        String[] lastNames = new String[authors.length];
        for (int i = 0; i < authors.length; i++) {
            lastNames[i] = authors[i].getLastName();
        }
        String temp;
        Author authorTemporary;
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (lastNames[i].compareTo(lastNames[j]) > 0) {
                    authorTemporary = authors[i];
                    temp = lastNames[i];
                    authors[i] = authors[j];
                    lastNames[i] = lastNames[j];
                    authors[j] = authorTemporary;
                    lastNames[j] = temp;
                }
            }
        }
        System.out.println("The author family in alphabetical order are: ");
        for (int i = 0; i < size; i++) {
            System.out.println("ID:" + authors[i].getAuthorId() + "  family of this author is :" + authors[i].getLastName());
        }
    }
}
