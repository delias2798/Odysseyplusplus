package XMLconvert;
import Player.music_player;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.beans.ExceptionListener;
import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;

@XmlRootElement
public class SerializeSong {
    public static void main(String[] args) throws IOException {
        String song="/home/toshiba/Música/grito.mp3";
        JAXBObjectToXml m = new JAXBObjectToXml();
        music_player player = new music_player();
        SerializeSong serialize = new SerializeSong();
        byte[] buffer=player.build_song(song);
        serialize.setName_song("hola");
        serialize.setSong(buffer);
        m.serializeToXML(serialize);


    }
    private String name_song;
    private byte[] song;

    public String getName_song() {
        return name_song;
    }

    @XmlAttribute
    public void setName_song(String name_song) {
        this.name_song = name_song;
    }

    public void setSong(byte[] song) {
        this.song = song;
    }

    @XmlElement
    public byte[] getSong() {
        return song;
    }

    @Override
    public String toString() {
        return "Song{" +
                "Name=" + name_song +
                ", Song=" + song + "'" +
                "}";
    }

}
