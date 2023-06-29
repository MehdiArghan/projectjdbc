package service;

import entity.Book;
import repository.book.BookRepository;

public class BookService {
    private BookRepository bookRepository = new BookRepository();

    public void addBook(String title, int publishedYear, int authorId) {
        Book book = new Book();
        book.setBookId(bookIdGenerator());
        book.setTitle(title);
        book.setPublishedYear(publishedYear);
        book.setAuthorId(authorId);
        bookRepository.save(book);
    }

    public Book[] authorBookList(int authorId) {
        Book[] books = bookRepository.loadAll();
        Book[] authorBooks = new Book[books.length];
        int count = 0;
        for (int i = 0; i < books.length; i++) {
            if (books[i].getAuthorId() == authorId) {
                authorBooks[count] = books[i];
                count++;
            }
        }
        return authorBooks;
    }

    private int bookIdGenerator() {
        int generator = (bookRepository.loadId()) + 1;
        if (generator == 0) generator = 1;
        return generator;
    }


}
