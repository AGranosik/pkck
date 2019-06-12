package pl.lodz.p.pkck.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.ZonedDateTime;

public class ZonedDateTimeAdapter extends XmlAdapter<String, ZonedDateTime> {

    public ZonedDateTime unmarshal(String v) {
        return ZonedDateTime.parse(v);
    }

    public String marshal(ZonedDateTime v) {
        return v.toString();
    }
}
