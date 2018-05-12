package Connection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.Serializable;
import java.io.StringWriter;

public class JAXBObjectToXml {
    public static void main(String[] args) {
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
}
