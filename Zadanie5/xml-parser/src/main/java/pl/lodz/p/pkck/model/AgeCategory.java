package pl.lodz.p.pkck.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "age_category")
@XmlAccessorType(XmlAccessType.NONE)
public class AgeCategory {

    @XmlAttribute(name = "id_age_category", required = true)
    private Integer minAge;

    public AgeCategory() {
    }

    public AgeCategory(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }
}
