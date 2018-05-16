package XMLconvert;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.zip.GZIPOutputStream;
import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import Connection.connect;
import Player.music_player;
import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class Song {
    int blockCount;
    int block_size=24056;
    byte[] range = null;


    public static void main(String argv[]){
        Song s = new Song();
        String song="/home/toshiba/Música/test2.mp3";
        music_player player = new music_player();
        byte[] b=player.build_song(song);
        String xml="&"+s.buildxml(b,"test");
        connect x = new connect();
        x.connect(xml);

        /**/

    }

    public static void main3(String argv[]){
        Song s = new Song();
        String song="/home/toshiba/Música/test2.mp3";
        music_player player = new music_player();
        byte[] buffer=player.build_song(song);
        byte[] v;
        int readBytes=buffer.length;
        String str=bytesToHex(buffer);

        /*v=hexStringToByteArray(str);
        System.out.println(str);
        System.out.println(buffer);
        System.out.println(v);*/

    }
    public static String bytesToHex(byte[] bytes) {
        String x = HexBin.encode(bytes);
        return x;
    }
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }



    public static byte[] compress(final String str) throws IOException {
        if ((str == null) || (str.length() == 0)) {
            return null;
        }
        ByteArrayOutputStream obj = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(obj);
        gzip.write(str.getBytes("UTF-8"));
        gzip.flush();
        gzip.close();
        return obj.toByteArray();
    }


    public String buildxml(byte[] byte_song, String name){
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("song");
            doc.appendChild(rootElement);

            Element name_song = doc.createElement("Name");
            name_song.appendChild(doc.createTextNode(name));
            rootElement.appendChild(name_song);

            blockCount=(byte_song.length+block_size-1)/block_size;
            for(int i=1;i<blockCount;i++){
                int idx = (i-1)*block_size;
                range=Arrays.copyOfRange(byte_song,idx,idx+block_size);
                ////////////////////////////////////////////////////////////////////////////////
                // chunks elements
                Element chunk = doc.createElement("Chunk");
                rootElement.appendChild(chunk);

                // set attribute to staff element
                Attr attr = doc.createAttribute("id");
                attr.setValue(""+i);
                chunk.setAttributeNode(attr);

                // shorten way
                // staff.setAttribute("id", "1");

                // firstname elements
                Element byte_array = doc.createElement("byte_array");
                byte_array.appendChild(doc.createTextNode(/*bytesToHex(range)*/""+range));
                chunk.appendChild(byte_array);


            }



            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("/home/toshiba/Escritorio/Proyecto2/file.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer2 = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter xml = new StringWriter();
            transformer2.transform(new DOMSource(doc),new StreamResult(xml));
            String xmlString = xml.getBuffer().toString();
            System.out.println(xmlString);
            return xmlString;

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
            return null;
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
            return null;
        }
    }

}
