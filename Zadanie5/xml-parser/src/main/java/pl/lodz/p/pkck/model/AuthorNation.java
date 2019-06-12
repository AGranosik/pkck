package pl.lodz.p.pkck.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "author_nation")
@XmlAccessorType(XmlAccessType.NONE)
public class AuthorNation {

    @XmlAttribute(name = "id_author_nation", required = true)
    private String authorNation;

    public AuthorNation() {
    }

    public AuthorNation(String authorNation) {
        this.authorNation = authorNation;
    }

    public String getAuthorNation() {
        return authorNation;
    }

    public void setAuthorNation(String authorNation) {
        this.authorNation = authorNation;
    }

    @Override
    public String toString() {
        return authorNation;
    }
}
