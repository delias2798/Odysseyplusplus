package Connection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.Serializable;
import java.io.StringWriter;
import javax.xml.stream.*;
import java.io.*;

public class JAXBObjectToXml {
    public static void main(String[] args) {
        connect connection=new connect();
        JAXBObjectToXml xml = new JAXBObjectToXml();
        Track track = new Track();

        track.setId(1);
        track.setTitle("Hey Jude");
        track.setGenre("Rock?");
        track.setAlbum("No me recuerod");
        track.setArtist("The Beatles");
        track.setYear("19##");
        track.setLetter("Hey jude, nananananan, he");

        String xml_string=xml.ConvertToXMLSong(track);
        //connection.connect3(xml_string);
        System.out.println(xml_string);

        User user = new User();
        user.setUsername("edd");
        user.setName("Eduardo");
        user.setLastname("Solano");
        user.setAge("18");
        user.setLike("Any");
        user.setPass("123456");
        user.setFriends("No tengo");
        xml_string=xml.ConvertToXMLUser(user);
        System.out.println(xml_string);
    }

    public String ConvertToXMLSong(Object object){
        try {
            JAXBContext context = JAXBContext.newInstance(Track.class);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            marshaller.marshal(object,sw);
            String xmlString = sw.toString();//transforma xml en xmlString
            return xmlString;


        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String ConvertToXMLUser(Object object){
        try {
            JAXBContext context = JAXBContext.newInstance(User.class);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            marshaller.marshal(object,sw);
            String xmlString = sw.toString();//transforma xml en xmlString
            return xmlString;


        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Track ConvertToTrack(String xml_string){
        /*try {
            // Unmarshallers are not thread-safe.  Create a new one every time.
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(new StringReader(xmlString));
            return unmarshaller.unmarshal(reader, YourRootObject.class);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }*/
        return null;
    }
}
