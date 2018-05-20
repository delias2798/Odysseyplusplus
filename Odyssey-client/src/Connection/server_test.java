package Connection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class server_test {
    public static void main(String[] args){
        server_test server = new server_test();
        server.initServer();

    }
    final int PORT=8080;
    //final String HOST="LocalHost";
    ServerSocket sc;
    Socket so;

    DataOutputStream salida;
    String mensajeResivido;
    public void initServer(){
        BufferedReader entrada;
        try{
            sc=new ServerSocket(PORT);
            so=new Socket();
            System.out.println("Esperando connexion...");
            so=sc.accept();
            System.out.println("Cliente conectado...");

            //canales
            entrada = new BufferedReader(new InputStreamReader(so.getInputStream()));
            salida =new DataOutputStream(so.getOutputStream());
            System.out.println("Confirmando conexion al cliente...");

            //reciviendo mensaje
            mensajeResivido=entrada.readLine();
            System.out.println(mensajeResivido);

            //envia de salida
            salida.writeUTF("hola amigo");

            sc.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
