package pl.lodz.p.pkck.model;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.NONE)
public class Book {
    @XmlAttribute(name = "ISBN", required = true)
    private Long ISBN;

//    @XmlIDREF
//    @XmlAttribute(name = "sklep", required = true)
//    private Shop shop;

    @XmlElement(name = "title", required = true)
    private String title;


    @XmlElement(name = "book_author", required = true)
    private BookAuthor bookAuthor;

    @XmlElement(name = "language", required = true)
    private Language languages;

    @XmlElement(name = "realise_day", required = true)
    private String realiseDay;

    @XmlElement(name = "number_of_pages", required = true)
    private Long numberOfPages;

    @XmlElement(name = "binding", required = true)
    private Binding binding;

    @XmlElement(name = "dimensions", required = true)
    private Dimensions dimensions;

    @XmlElement(name = "price", required = true)
    private Price price;

    @XmlElement(name = "printing", required = true)
    private Printing printing;

    @XmlElement(name = "age_category", required = true)
    private AgeCategory ageCategory;

    public Book() {
    }

    public Book(Long ISBN, String title, BookAuthor bookAuthor, Language languages, String realiseDay, Long numberOfPages,
                Binding binding, Dimensions dimensions, Price price, Printing printing, AgeCategory ageCategory) {
        this.ISBN = ISBN;
        this.title = title;
        this.bookAuthor = bookAuthor;
        this.languages = languages;
        this.realiseDay = realiseDay;
        this.numberOfPages = numberOfPages;
        this.binding = binding;
        this.dimensions = dimensions;
        this.price = price;
        this.printing = printing;
        this.ageCategory = ageCategory;
    }

    public Long getISBN() {
        return ISBN;
    }

    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BookAuthor getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(BookAuthor bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Language getLanguages() {
        return languages;
    }

    public void setLanguages(Language languages) {
        this.languages = languages;
    }

    public String getRealiseDay() {
        return realiseDay;
    }

    public void setRealiseDay(String realiseDay) {
        this.realiseDay = realiseDay;
    }

    public Long getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Long numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Binding getBinding() {
        return binding;
    }

    public void setBinding(Binding binding) {
        this.binding = binding;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Printing getPrinting() {
        return printing;
    }

    public void setPrinting(Printing printing) {
        this.printing = printing;
    }

    public AgeCategory getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(AgeCategory ageCategory) {
        this.ageCategory = ageCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(ISBN, book.ISBN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ISBN);
    }



    @Override
    public String toString() {
        return title + ", Autor: " + bookAuthor.getBookAuthorInfo().getName()
                + " " + bookAuthor.getBookAuthorInfo().getSurname() + ", ISBN: " + ISBN;
    }
}
