package pro.bigyellow.exams;

import android.app.Person;
import android.util.Log;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DomActivityService {
    /**
     * @param inStream
     * @return
     * @throws Exception
     */
    public static double[][] getForms(InputStream inStream)
            throws Exception {

        double[][] path=new double[100][100];
        /**
         * 文檔的解析
         */
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();


        Document document = builder.parse(inStream);

        /**
         * 操作對象樹
         */
        Element root = document.getDocumentElement();//返回文檔的根元素
        NodeList ActivityNode = root.getElementsByTagName("activity");
        int count=0;

        for (int i = 0; i < ActivityNode.getLength(); i++) {
            Element activityElement;
            count++;
             activityElement = (Element) ActivityNode.item(i);
             NodeList childNodes;

                 childNodes = activityElement.getChildNodes();

            for (int y = 0; y < childNodes.getLength(); y++) {

                if("link".equals(childNodes.item(y).getNodeName())){
                    Element linkElement=(Element) childNodes.item(y);


                    int mark=new Integer(linkElement.getAttribute("id"));

                    double pro =new Double(linkElement.getAttribute("probability"));

                    path[count][mark]=pro;
                }
            }


        }

        inStream.close();
        path[99][99]=count;
        return path;
    }

}
