package com.example.BooksREST.controller;

import com.example.BooksREST.pojos.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    List<Book> books = new ArrayList<Book>();

    public BookController(){
        this.books.add(new Book(1, "The Art of Programming", "John Smith", "023-01-15", 49.99F, "Computer Science", "9780123456789", 10));
        this.books.add(new Book(2, "The Power of Words", "Emily Johnson", "2022-11-30", 29.99F, "Self-Help", "9789876543210", 5));
        this.books.add(new Book(3, "A Journey Through Time", "David Thompson", "2023-03-22", 19.99F, "Fantasy", "9786543210987", 8));
        this.books.add(new Book(4, "The Hidden Secrets", "Sarah Roberts", "2023-02-10", 14.99F, "Mystery", "9783210987654", 12));
        this.books.add(new Book(5, "The Science of Nature", "Michael Anderson", "2023-04-18", 24.99F, "Science", "9785432109876", 3));
    }

    @GetMapping("/books")
    public List<Book> getBooks() { return books; }

    @PostMapping("/add-book")
    public void addBook(@RequestBody Book book) { books.add(book); }

    @GetMapping("/book-by-id")
    public Book getBookById(@RequestParam long id) {
        Book res = books.stream().filter(book -> book.getId() == id).findFirst().orElse(null);
        return res;
    }
    @GetMapping("/delete-by-id")
    public List<Book> deleteBookById(@RequestParam long id) {
        Book del = books.stream().filter(book -> book.getId() == id).findFirst().orElse(null);
        if (del != null) {
            books.remove(del);
        }
        return getBooks();
    }

    @GetMapping("/pattern-in-title")
    public List<Book> getBookById(@RequestParam String pattern) {
        List<Book> res = books.stream().filter(book -> book.getTitle().contains(pattern)).toList();
        //res = books.stream().filter(book -> book.getTitle().contains(pattern) ).findAny().orElse(null);
        return res;
    }

    @PutMapping("/book-update")
    public List<Book> updateBook(@RequestBody Book body) {
        Book upd = books.stream().filter(book -> book.getId() == body.getId()).findFirst().orElse(null);
        if(upd != null) {
            //int idx = books.get(books.indexOf(upd));
            books.set(books.indexOf(upd), body);
        }
        return  getBooks();
    }
}
