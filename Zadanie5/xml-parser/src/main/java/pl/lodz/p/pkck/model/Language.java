package pl.lodz.p.pkck.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "language")
@XmlAccessorType(XmlAccessType.NONE)
public class Language {

    @XmlAttribute(name = "id_language", required = true)
    private String idLanguage;

    public Language() {
    }

    public Language(String idLanguage) {
        this.idLanguage = idLanguage;
    }

    public String getIdLanguage() {
        return idLanguage;
    }

    public void setIdLanguage(String idLanguage) {
        this.idLanguage = idLanguage;
    }

}
