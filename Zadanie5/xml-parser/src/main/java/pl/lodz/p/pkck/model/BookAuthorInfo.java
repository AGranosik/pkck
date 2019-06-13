package pl.lodz.p.pkck.model;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlRootElement(name = "book_author_info")
@XmlAccessorType(XmlAccessType.NONE)
public class BookAuthorInfo {

    @XmlID
    @XmlAttribute(name = "author_id", required = true)
    private String id;

    @XmlElement(name = "name", required = true)
    private String name;

    @XmlElement(name = "surname", required = true)
    private String surname;

    @XmlElement(name = "author_nation", required = true)
    private AuthorNation authorNation;

    public BookAuthorInfo() {
    }

    public BookAuthorInfo(String id, String name, String surname, AuthorNation authorNation) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.authorNation = authorNation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public AuthorNation getAuthorNation() {
        return authorNation;
    }

    public void setAuthorNation(AuthorNation authorNation) {
        this.authorNation = authorNation;
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Drive drive = (Drive) o;
//        return Objects.equals(id, drive.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
//
    @Override
    public String toString() {
        return (name + ' ' + surname + ", " + authorNation);
    }
}