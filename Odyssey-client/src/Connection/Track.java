package Connection;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Track {
    private Integer id;
    private String title;
    private String genre;
    private String artist;
    private String album;
    private String year;
    private String letter;

    public Track() {
    }

    public Integer getId() {
        return id;
    }

    @XmlAttribute
    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement
    public String getTitle() {
        return title;
    }

    @XmlElement
    public String getGenre() {
        return genre;
    }

    @XmlElement
    public String getArtist() {
        return artist;
    }

    @XmlElement
    public String getAlbum() {
        return album;
    }

    @XmlElement
    public String getYear() {
        return year;
    }

    @XmlElement
    public String getLetter() {
        return letter;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }




    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", title=" + title + "'" +
                ", genre=" + genre + "'" +
                ", artist=" + artist + "'" +
                ", album=" + album + "'" +
                ", year=" + year + "'" +
                ", letter=" + letter + "'" +
                "}";
    }
    //output
    /*
    <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    <track id="1">
        <album>No me recuerod</album>
        <artist>The Beatles</artist>
        <genre>Rock?</genre>
        <letter>Hey jude, nananananan, he</letter>
        <title>Hey Jude</title>
        <year>19##</year>
    </track>
     */
}
