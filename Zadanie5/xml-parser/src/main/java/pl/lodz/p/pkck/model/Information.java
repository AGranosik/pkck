package pl.lodz.p.pkck.model;

import pl.lodz.p.pkck.utils.ZonedDateTimeAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.ZonedDateTime;
import java.util.List;

@XmlRootElement(name = "information")
@XmlAccessorType(XmlAccessType.NONE)
public class Information {

    @XmlElement(name = "description", required = true)
    private String description;

    @XmlElementWrapper(name = "authors", required = true)
    @XmlElement(name = "author", required = true)
    private List<Author> authors;

    //@XmlJavaTypeAdapter(value = ZonedDateTimeAdapter.class)
    @XmlElement(name = "modification_date", required = true)
    private String generationTime;

    public Information() {
    }

    public Information(String description, List<Author> authors, String generationTime) {
        this.description = description;
        this.authors = authors;
        this.generationTime = generationTime;
    }

    public  String getDescription() { return  description; }

    public void setDescription(String description) { this.description = description; }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String getGenerationTime() {
        return generationTime;
    }

    public void setGenerationTime(String generationTime) {
        this.generationTime = generationTime;
    }
}
