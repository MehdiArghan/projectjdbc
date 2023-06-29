package repository.author;

public class AuthorQuery {
    public static final String SAVE = "insert into author values (?,?,?,?)";
    public static final String LOAD_BY_ID = "select * from author where author_id=?";
    public static final String LOAD_ALL = "select * from author";
}
