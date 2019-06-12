package pl.lodz.p.pkck.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "price")
@XmlAccessorType(XmlAccessType.NONE)
public class Price {

    @XmlAttribute(name = "id_currency", required = true)
    private String currency;

    @XmlValue
    private double value;

    public Price() {
    }

    public Price(String valueAndCurrency) {
        String[] array = valueAndCurrency.split(" ");
        this.value = Double.parseDouble(array[0]);
        this.currency = array[1];
    }

    public Price(String currency, double value) {
        this.currency = currency;
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Double.toString(value) + " " + currency;
    }
}
