package EjerciciosRepaso.Tema2.RepasoSAX;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class getSax {
    public static void getSax(String entradaXML) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        
        System.out.println("-----------EJER 1------------");
        Ejercicio1SAX ejer1 = new Ejercicio1SAX();
        // parser.parse(entradaXML, ejer1);
        System.out.println();
        
        System.out.println("-----------EJER 2------------");
        Ejercicio2SAX ejer2 = new Ejercicio2SAX();
        // parser.parse(entradaXML, ejer2);
        System.out.println();

        System.out.println("-----------EJER 3------------");
        Ejercicio3SAX ejer3 = new Ejercicio3SAX();
        // parser.parse(entradaXML, ejer3);
        System.out.println();

        System.out.println("-----------EJER 4------------");
        Ejercicio4SAX ejer4 = new Ejercicio4SAX();
        // parser.parse(entradaXML, ejer4);
        System.out.println();

        System.out.println("-----------EJER 5------------");
        Ejercicio5SAX ejer5 = new Ejercicio5SAX();
        parser.parse(entradaXML, ejer5);
        System.out.println();

        // System.out.println("-----------EJER 3------------");
        // Ejercicio6SAX ejer6 = new Ejercicio6SAX();
        // parser.parse(entradaXML, ejer6);
        // System.out.println();

        // System.out.println("-----------EJER 7------------");
        // Ejercicio7SAX ejer7 = new Ejercicio7SAX();
        // parser.parse(entradaXML, ejer7);
        // System.out.println();
        
    }
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        getSax("EjerciciosRepaso\\Tema2\\liga.xml");
        
    }
}
