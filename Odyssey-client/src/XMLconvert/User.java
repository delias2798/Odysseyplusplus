package XMLconvert;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
    private String username;
    private String name;
    private String last_name;
    private String age;
    private String pass;
    private String like;
    private String friends;

    public User(){}

    public String getUsername() {
        return username;
    }

    @XmlElement
    public void setUsername(String username) {
        this.username = username;
    }

    @XmlElement
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getLastname() {
        return last_name;
    }
    public void setLastname(String last_name) {
        this.last_name = last_name;
    }

    @XmlElement
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }

    @XmlElement
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }

    @XmlElement
    public String getLike() {
        return like;
    }
    public void setLike(String like) {
        this.like = like;
    }

    @XmlElement
    public String getFriends() {
        return friends;
    }
    public void setFriends(String friends) {
        this.friends = friends;
    }



    @Override
    public String toString() {
        return "User{" +
                "username=" + username +
                ", name='" + name + "'" +
                ", last_name='" + last_name + "'" +
                ", age='" + age + "'" +
                ", pass='" + pass + "'" +
                ", like='" + like + "'" +
                ", friend='" + friends + "'" +
                "}";
    }
    //out
    /*
    <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    <user username="edd">
        <age>22</age>
        <friends>No tengo</friends>
        <lastname>Solano</lastname>
        <like>Any</like>
        <name>Eduardo</name>
        <pass>123456</pass>
    </user>
     */


}
