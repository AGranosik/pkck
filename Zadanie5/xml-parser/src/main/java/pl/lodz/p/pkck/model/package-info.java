@XmlSchema(
        namespace = "http://example.com/my",
        xmlns = {
                @XmlNs(prefix = "", namespaceURI = "http://example.com/my")
        },
        elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED)
package pl.lodz.p.pkck.model;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlSchema;