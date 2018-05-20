package XMLconvert;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

import java.util.zip.DataFormatException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;

import Connection.connect;
import Player.music_player;
import jdk.nashorn.internal.ir.ReturnNode;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Song {
    int blockCount;
    int block_size=24056;
    byte[] range = null;
    CompressionUtils compress = new CompressionUtils();
    music_player play = new music_player();

    public static void main1(String argv[]){
        Song s = new Song();
        String song="/home/toshiba/Música/grito.mp3";
        music_player player = new music_player();
        byte[] b=player.build_song(song);
        //player.play(b);
        String xml="&"+s.buildxml(b,"test");



        /**/

    }

    public static void main(String argv[]) throws IOException, DataFormatException {
        Song s = new Song();
        connect cc = new connect();
        String song="/home/toshiba/Música/grito.mp3";
        music_player player = new music_player();
        byte[] buffer=player.build_song(song);
        //int readBytes=buffer.length;

        String xml=s.buildxml(buffer,"test");
        int incremento=2000;
        for (int i = 0; i < xml.length(); i += incremento) {
            cc.connect("&"+xml.substring(i, Math.min(i + incremento, xml.length())));
        }


        /*System.out.println("buffer");
        String ss_song=s.BArraytoString(buffer);
        System.out.println("str");
        byte[] v=s.converToArray(ss_song);
        System.out.println("array");
        player.play(v);
        System.out.println("ok");//s.BArraytoString(ss));*/

        /*String ss = new String(buffer);
        byte[] vv = ss.getBytes();

        System.out.println(ss);*/




    }






    public byte[] converToArray(String b_str){
        byte[] bval = new BigInteger(b_str, 2).toByteArray();
        return bval;
    }


    public String BArraytoString(byte[] n){
        int length=n.length;
        String b_str="";
        for(int i=0;i<length;i++){
            b_str=b_str+toBinaryString(n[i]);
        }
        return b_str;
    }

    public String toBinaryString(byte n){
        StringBuilder sb = new StringBuilder("00000000");
        for(int bit=0;bit<8;bit++){
            if(((n>>bit)&1)>0){
                sb.setCharAt(7-bit,'1');
            }
        }
        //System.out.println(sb.toString());
        return sb.toString();
    }

    public void convertxml(int id){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;
        try {
            builder=factory.newDocumentBuilder();
            doc=builder.parse("/home/toshiba/Escritorio/Proyecto2/file.xml");

            // Create XPathFactory object
            XPathFactory xpathFactory = XPathFactory.newInstance();

            // Create XPath object
            XPath xpath = xpathFactory.newXPath();

            music_player player = new music_player();
            for(int i=1; i<=6;i++){
                String chunk=getChunk(doc,xpath,i);
                //System.out.println(chunk.toString());
                byte[] b_array=converToArray(chunk);
                player.play(b_array);
            }
            /*List<String>chunk=getChunk(doc,xpath,id);
            System.out.println(Arrays.toString(chunk.toArray()));*/



        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getChunk(Document doc, XPath xpath,int id) {
        try {
            //create XPathExpression object
            XPathExpression expr = xpath.compile("/song/Chunk[@id="+id+"]/byte_array/text()");
            //evaluate expression result on XML document
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            return nodes.item(0).getNodeValue().toString();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return null;
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
                //String ss = new String(range);
                String rangeS=/*IOUtils.toString(range,"UTF-8");//*/BArraytoString(range);
                byte_array.appendChild(doc.createTextNode(rangeS));
                System.out.println("Chunk "+i);
                /*byte[] v = converToArray(rangeS);
                play.play(v);*/
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
            //System.out.println(xmlString);
            return xmlString;

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();

        } catch (TransformerException tfe) {
            tfe.printStackTrace();

        }
        return null;
    }

}
