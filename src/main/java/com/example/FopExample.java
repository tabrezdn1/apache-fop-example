package com.example;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.MimeConstants;
import org.xml.sax.SAXException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;


public class FopExample {

    public static void main(String[] args) {
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        OutputStream out = null;

        // XSL-FO Stylesheet as a String with image
        // String xslFoImage = "<fo:root xmlns:fo='http://www.w3.org/1999/XSL/Format'>"
        //        + "<fo:layout-master-set>"
        //        + "<fo:simple-page-master master-name='simple'>"
        //        + "<fo:region-body margin='1in'/>"
        //        + "</fo:simple-page-master>"
        //        + "</fo:layout-master-set>"
        //        + "<fo:page-sequence master-reference='simple'>"
        //        + "<fo:flow flow-name='xsl-region-body'>"
        //        + "<fo:block font-size='24pt' color='blue'>Hello, World from XSL-FO embedded in Java!</fo:block>"
        //        + "<fo:block font-size='12pt'>Here is some <fo:inline color='red'>colored text</fo:inline>.</fo:block>"
        //        + "<fo:block text-align='center'>"
        //        + "<fo:external-graphic src='https://www.w3.org/People/mimasa/test/imgformat/img/w3c_home.jpg' width='50%' content-width='scale-to-fit'/>"
        //        + "</fo:block>"
        //        + "<fo:block>"
        //        + "Visit our "
        //        + "<fo:basic-link color='green' external-destination='url(http://www.example.com)'>website</fo:basic-link>."
        //        + "</fo:block>"
        //        + "</fo:flow>"
        //        + "</fo:page-sequence>"
        //        + "</fo:root>";

        

        // // XSL-FO Stylesheet as a String basic
        // String xslFo = "<fo:root xmlns:fo='http://www.w3.org/1999/XSL/Format' version='1.0'>" +
        //                "<fo:layout-master-set>" +
        //                "<fo:simple-page-master master-name='simple'>" +
        //                "<fo:region-body margin='1in'/>" +
        //                "</fo:simple-page-master>" +
        //                "</fo:layout-master-set>" +
        //                "<fo:page-sequence master-reference='simple'>" +
        //                "<fo:flow flow-name='xsl-region-body'>" +
        //                "<fo:block>Hello, World from XSL-FO embedded in Java!</fo:block>" +
        //                "</fo:flow>" +
        //                "</fo:page-sequence>" +
        //                "</fo:root>";

        String xslFo = "<fo:root xmlns:fo='http://www.w3.org/1999/XSL/Format'>"
        + "<fo:layout-master-set>"
        + "<fo:simple-page-master master-name='simple'>"
        + "<fo:region-body margin='1in'/>"
        + "</fo:simple-page-master>"
        + "</fo:layout-master-set>"
        + "<fo:page-sequence master-reference='simple'>"
        + "<fo:flow flow-name='xsl-region-body'>"
        + "<fo:block font-size='24pt' color='blue'>Hello, World from XSL-FO embedded in Java!</fo:block>"
        + "<fo:block font-size='12pt'>Here is some <fo:inline color='red'>colored text</fo:inline>.</fo:block>"
        + "<fo:block>"
        + "Visit our "
        + "<fo:basic-link color='green' external-destination='url(http://www.example.com)'>website</fo:basic-link>."
        + "</fo:block>"
        + "</fo:flow>"
        + "</fo:page-sequence>"
        + "</fo:root>";


        // XML data as a String
        String xmlData = "<document><title>Sample Title</title><content>Sample content.</content></document>";


        try {
            out = new BufferedOutputStream(new FileOutputStream(new File("result.pdf")));
            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(new StringReader(xslFo)));

            StreamSource src = new StreamSource(new StringReader(xmlData));
            SAXResult res = new SAXResult(fop.getDefaultHandler());

            // If you want to use file path below is the code
            // TransformerFactory factory = TransformerFactory.newInstance();
            // Transformer transformer = factory.newTransformer(new StreamSource(new File("src/main/resources/template.fo")));

            // StreamSource src = new StreamSource(new File("src/main/resources/data.xml"));
            // SAXResult res = new SAXResult(fop.getDefaultHandler());

            transformer.transform(src, res);
        } catch (SAXException | IOException | javax.xml.transform.TransformerException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
