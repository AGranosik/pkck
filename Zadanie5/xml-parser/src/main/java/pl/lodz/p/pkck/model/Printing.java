package pl.lodz.p.pkck.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "printing")
@XmlAccessorType(XmlAccessType.NONE)
public class Printing {

    @XmlAttribute(name = "id_printing_type", required = true)
    private String printingType;

    public Printing() {
    }

    public Printing(String printingType) {
        this.printingType = printingType;
    }

    public String getPrintingType() {
        return printingType;
    }

    public void setPrintingType(String printingType) {
        this.printingType = printingType;
    }
}
