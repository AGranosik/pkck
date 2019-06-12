package pl.lodz.p.pkck.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "binding")
@XmlAccessorType(XmlAccessType.NONE)
public class Binding {

    @XmlAttribute(name = "id_binding", required = true)
    private String bindingName;

    public Binding() {
    }

    public Binding(String bindingName) {
        this.bindingName = bindingName;
    }

    public String getBindingName() {
        return bindingName;
    }

    public void setBindingName(String bindingName) {
        this.bindingName = bindingName;
    }
}
