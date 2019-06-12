package pl.lodz.p.pkck.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.pkck.exception.DaoException;
import pl.lodz.p.pkck.model.Bookshelf;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class DocumentDao implements Dao<Bookshelf> {

    private Logger log = LoggerFactory.getLogger(DocumentDao.class);

    private String xsdPath;

    public DocumentDao(String xsdPath) {
        this.xsdPath = xsdPath;
    }

    @Override
    public Bookshelf read(String path) throws DaoException {
        Bookshelf document = null;
        try {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File("xml/" + xsdPath));
            JAXBContext jaxbContext = JAXBContext.newInstance(Bookshelf.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            jaxbUnmarshaller.setSchema(schema);
            document = (Bookshelf) jaxbUnmarshaller.unmarshal(new File("xml/" + path));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        }
        return document;
    }

    @Override
    public void write(Bookshelf obj, String path) throws DaoException {
        try {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File("xml/" + xsdPath));
            JAXBContext jaxbContext = JAXBContext.newInstance(Bookshelf.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setSchema(schema);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(obj, new File("xml/" + path));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        }
    }
}
