package pl.lodz.p.pkck.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "bookshelf")
@XmlAccessorType(XmlAccessType.NONE)
public class Bookshelf {

    @XmlElement(name = "information", required = true)
    private Information information;

    @XmlElementWrapper(name = "books", required = true)
    @XmlElement(name = "book", required = true)
    private List<Book> books;

    @XmlElementWrapper(name = "book_authors_list", required = true)
    @XmlElement(name = "book_author_info", required = true)
    private List<BookAuthorInfo> bookAuthors;

    public Bookshelf() {
    }

    public Bookshelf(Information information, List<Book> books, List<BookAuthorInfo> bookAuthors) {
        this.information = information;
        this.books = books;
        this.bookAuthors = bookAuthors;
    }

    public Information getInformation() { return information; }

    public void setInformation(Information information) { this.information = information; }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<BookAuthorInfo> getBookAuthorsList() {
        return bookAuthors;
    }

    public void setBookAuthorsList(List<BookAuthorInfo> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }
}
