package pl.lodz.p.pkck.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "author")
@XmlAccessorType(XmlAccessType.NONE)
public class Author {

    @XmlElement(name = "name", required = true)
    private String name;
    @XmlElement(name = "surname", required = true)
    private String surname;
    @XmlElement(name = "indeks", required = true)
    private String indexNumber;

    public Author() {
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

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public Author(String name, String surname, String indexNumber) {
        this.name = name;
        this.surname = surname;
        this.indexNumber = indexNumber;
    }

    @Override
    public String toString() {
        return name + ' ' + surname + ' ' + indexNumber;
    }
}
