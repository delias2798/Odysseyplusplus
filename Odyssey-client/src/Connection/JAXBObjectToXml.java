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
        Track track2 = new Track();

        track.setId(1);
        track.setTitle("Hey Jude");
        track.setGenre("Rock?");
        track.setAlbum("No me recuerod");
        track.setArtist("The Beatles");
        track.setYear("19##");
        track.setLetter("Hey jude, nananananan, he");

        String xml_string=xml.ConvertToXML(track,Track.class);
        //connection.connect3(xml_string);
        System.out.println(xml_string);
        track2=xml.ConvertToTrack(xml_string);
        System.out.println(track2.getAlbum());
        User user = new User();
        User user2 = new User();
        user.setUsername("edd");
        user.setName("Eduardo");
        user.setLastname("Solano");
        user.setAge("22");
        user.setLike("Any");
        user.setPass("123456");
        user.setFriends("No tengo");
        xml_string=xml.ConvertToXML(user,User.class);
        System.out.println(xml_string);

        user2=xml.ConvertToUser(xml_string);
        System.out.println(user2.getFriends());
    }

    public String ConvertToXML(Object object, Class class_){
        try {
            JAXBContext context = JAXBContext.newInstance(class_);

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


    public User ConvertToUser(String xml_string){

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xml_string);
            User user = (User) unmarshaller.unmarshal(reader);
            return user;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Track ConvertToTrack(String xml_string){

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Track.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xml_string);
            Track track = (Track) unmarshaller.unmarshal(reader);
            return track;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
