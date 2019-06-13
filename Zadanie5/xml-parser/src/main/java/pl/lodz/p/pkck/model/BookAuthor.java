package pl.lodz.p.pkck.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "book_author")
@XmlAccessorType(XmlAccessType.NONE)
public class BookAuthor {

    @XmlIDREF
    @XmlAttribute(name = "author_id", required = true)
    private BookAuthorInfo bookAuthorInfo;

    public BookAuthor() {
    }

    public BookAuthor(BookAuthorInfo bookAuthorInfo) {
        this.bookAuthorInfo = bookAuthorInfo;
    }

    public BookAuthorInfo getBookAuthorInfo() {
        return bookAuthorInfo;
    }

    public void setBookAuthorInfo(BookAuthorInfo bookAuthorInfo) {
        this.bookAuthorInfo = bookAuthorInfo;
    }


}