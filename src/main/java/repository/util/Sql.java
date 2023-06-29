package repository.util;

public class Sql {
    public static final String createAuthor = "create table author\n" +
            "(\n" +
            "    author_id        int primary key,\n" +
            "    firstName varchar not null,\n" +
            "    lastName  varchar not null,\n" +
            "    age       integer not null\n" +
            ");";

    public static final String createBook = "create table book\n" +
            "(\n" +
            "    book_id       int     primary key,\n" +
            "    title         varchar not null,\n" +
            "    publishedYear int    not null,\n" +
            "    author_id     int     not null,\n" +
            "    CONSTRAINT fk_author_id FOREIGN KEY (author_id) REFERENCES author (author_id) on delete cascade on update cascade\n" +
            ");\n";
}
