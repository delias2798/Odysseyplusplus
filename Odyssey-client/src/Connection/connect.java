package Connection;
import javax.xml.bind.annotation.*;
import java.net.*;
import java.io.*;
public class connect {
    public static void main(String[] args){
        connect x = new connect();
        x.initServer2();
    }

    final String Host=/*"LocalHost";//*/"192.168.1.109";
    final int PORT=8080;
    Socket sc;
    DataOutputStream mensaje;
    DataInputStream entrada;

    public void connect(String msg){
        try {
            InetAddress address = InetAddress.getByName(Host);
            sc = new Socket(address, PORT);

            //Send the message to the server
            OutputStream os = sc.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            String number = msg;//"Espero que sirva";

            String sendMessage = number + "\n";
            bw.write(sendMessage);
            bw.flush();
            System.out.println("Message sent to the server : "+sendMessage);

            //Get the return message from the server
           /* InputStream is = sc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String message = br.readLine();
            System.out.println("Message received from the server : " +message);*/

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void initServer2(){
        try{
            sc=new Socket(Host,PORT);

            mensaje = new DataOutputStream(sc.getOutputStream());
            mensaje.writeUTF("Holaaaa");


            //System.out.println(entrada.read());

            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}