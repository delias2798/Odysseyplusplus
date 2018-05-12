package Connection;
import javax.xml.bind.annotation.*;
import java.net.*;
import java.io.*;
public class connect {
    public static void main(String[] args){
        connect x = new connect();
        x.initClient();
    }
    final String Host="localholst";
    final int PORT=5000;
    Socket sc;

    DataOutputStream out;
    DataInputStream in;
    public void initClient(){
        try{
            sc=new Socket(Host,PORT);

            out=new DataOutputStream(sc.getOutputStream());

            out.writeUTF("holi");

            sc.close();
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    public String toXML(Serializable object){

        return null;
    }
}