package pl.lodz.p.pkck.converter;

import net.sf.saxon.TransformerFactoryImpl;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.pkck.exception.PdfConversionException;
import pl.lodz.p.pkck.exception.XmlConversionException;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class XsltConverter {

    private Logger log = LoggerFactory.getLogger(XsltConverter.class);

    public void convertXmlToPdf(String xmlPath, String xsltPath, String outputPdfPath) throws PdfConversionException {
        try (OutputStream out = new java.io.FileOutputStream("xml/" + outputPdfPath)) {
            File xsltFile = new File("xml/" + xsltPath);
            StreamSource xmlSource = new StreamSource(new File("xml/" + xmlPath));
            FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
            TransformerFactory factory = new TransformerFactoryImpl();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));
            Result res = new SAXResult(fop.getDefaultHandler());
            transformer.transform(xmlSource, res);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new PdfConversionException(e.getMessage(), e);
        }
    }

    public void convertXmlToXml(String xmlPath, String xsltPath, String outputXmlPath) throws XmlConversionException {
        try {
            TransformerFactory factory = new TransformerFactoryImpl();
            Templates template = factory.newTemplates(new StreamSource(new FileInputStream("xml/" + xsltPath)));
            Transformer xformer = template.newTransformer();
            Source source = new StreamSource(new FileInputStream("xml/" + xmlPath));
            Result result = new StreamResult(new FileOutputStream("xml/" + outputXmlPath));
            xformer.transform(source, result);
        } catch (FileNotFoundException | TransformerException e) {
            log.error(e.getMessage(), e);
            throw new XmlConversionException(e.getMessage(), e);
        }
    }
}
