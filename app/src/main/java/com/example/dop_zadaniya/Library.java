package com.example.dop_zadaniya;
import java.util.*;

public class Library {
    private ArrayList<Book> books;
    private HashSet<Author> authors;
    private HashMap<String, List<Book>> booksByGenre;

    public Library() {
        books = new ArrayList<>();
        authors = new HashSet<>();
        booksByGenre = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
        authors.add(new Author(book.getAuthor()));

        booksByGenre.putIfAbsent(book.getGenre(), new ArrayList<>());
        booksByGenre.get(book.getGenre()).add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public Map<String, List<Book>> getBooksByGenre() {
        return booksByGenre;
    }
}
