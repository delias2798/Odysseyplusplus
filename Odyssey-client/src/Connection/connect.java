package Connection;
import javax.xml.bind.annotation.*;
import java.net.*;
import java.io.*;
public class connect {
    public static void main(String[] args){
        connect x = new connect();
        //x.initClient();
        //x.connect2();
        x.connect3("hola111111");
    }

    final String Host="192.168.1.109";
    final int PORT=8080;
    Socket sc,clientSocket;
    DataOutputStream out;
    DataInputStream in;
    public void initClient(){
        try{
            sc=new Socket(Host,PORT);

            out=new DataOutputStream(sc.getOutputStream());

            out.writeUTF("test1111111111");

            sc.close();
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    public void connect2(){
        try{
            String sentence="hi";
            String modifiedSentence;
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            clientSocket=new Socket(Host,PORT);

            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            sentence = inFromUser.readLine();
            outToServer.writeUTF(sentence);
            //outToServer.writeBytes(sentence + '\n');
            modifiedSentence = inFromServer.readLine();
            System.out.println(modifiedSentence);
            clientSocket.close();
        }catch (Exception e){}

    }

    public void connect3(String msg){
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
            /*InputStream is = sc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String message = br.readLine();
            System.out.println("Message received from the server : " +message);*/

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}