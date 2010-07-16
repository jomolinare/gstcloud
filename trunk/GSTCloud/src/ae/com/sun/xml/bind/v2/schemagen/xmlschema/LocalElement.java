
package ae.com.sun.xml.bind.v2.schemagen.xmlschema;

import javax.xml.namespace.QName;
import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;

@XmlElement("element")
public interface LocalElement
    extends Element, Occurs, TypedXmlWriter
{


    @XmlAttribute
    public LocalElement form(String value);

    @XmlAttribute
    public LocalElement name(String value);

    @XmlAttribute
    public LocalElement ref(QName value);

}
