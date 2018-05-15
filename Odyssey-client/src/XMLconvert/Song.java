package XMLconvert;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Song {
    String name;
    int chunk;
    byte[] byte_song;

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public int getChunk() {
        return chunk;
    }

    @XmlElement
    public void setChunk(int chunk) {
        this.chunk = chunk;
    }

    public byte[] getByte_song() {
        return byte_song;
    }

    @XmlElement
    public void setByte_song(byte[] byte_song) {
        this.byte_song = byte_song;
    }

    @Override
    public String toString() {
        return "Track{" +
                "name=" + name +
                ", chunk=" + chunk + "'" +
                ", array=" + byte_song + "'"+
                "}";
    }
}
