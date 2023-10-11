package service;

import entity.Author;
import repository.author.AuthorRepository;
import view.CreateList;

import java.util.Arrays;

public class AuthorService {
    private AuthorRepository authorRepository = new AuthorRepository();
    private BookService bookService = new BookService();

    public void register(String firstName, String lastName, int age) {
        Author author = new Author();
        author.setAuthorId(authorIdGenerator());
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setAge(age);
        authorRepository.save(author);
    }

    public void printAuthor() {
        Author[] authors = authorRepository.loadAll();
        for (int i = 0; i < authors.length; i++) {
            authors[i].setBooks(bookService.authorBookList(i + 1));
        }
        for (Author author : authors) {
            System.out.println("AuthorId: " + author.getAuthorId() + " FirstName: " + author.getFirstName() + " LastName: " + author.getLastName() + " Age: " + author.getAge() + " Book:" + Arrays.toString(author.getBooks()));
        }
    }

    private int authorIdGenerator() {
        int generator = (authorRepository.loadId()) + 1;
        if (generator == 0) generator = 1;
        return generator;
    }


    public void sortAuthorFamily() throws Exception {
        CreateList createList = new CreateList();
        createList.sortAuthor(authorRepository.loadAll());
    }
}
