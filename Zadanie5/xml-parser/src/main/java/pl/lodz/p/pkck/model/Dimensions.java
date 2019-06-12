package pl.lodz.p.pkck.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "dimensions")
@XmlAccessorType(XmlAccessType.NONE)
public class Dimensions {

    @XmlAttribute(name = "id_dimension", required = true)
    private String dimensionType;

    @XmlElement(name = "width", required = true)
    private Integer width;

    @XmlElement(name = "height", required = true)
    private Integer height;

    public String getDimensionType() {
        return dimensionType;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setDimensionType(String dimensionType) {
        this.dimensionType = dimensionType;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

}