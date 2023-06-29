package repository.book;

public class BookQuery {
    public static final String SAVE = "insert into book values (?,?,?,?)";
    public static final String LOAD_BY_ID="select * from book where book_id=?";
    public static final String DELETE="delete from book where book_id=?";
    public static final String LOAD_ALL="select * from book";
}
