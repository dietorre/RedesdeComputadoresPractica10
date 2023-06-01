import java.net.*;
import java.io.*;
public class ClienteUDP{
        public static void main(String[] args ) throws IOException{
        DatagramSocket s = new DatagramSocket();
        InetAddress dir = InetAddress.getByName ("10.11.69.4");
        String msg = "Hola, esto es un mensaje \n";
        byte[] buf = new byte[256];
        buf = msg.getBytes();
        DatagramPacket p = new DatagramPacket (buf , buf.length , dir ,55555);
        s.send(p);
        s.receive(p); // se bloquea hasta que recibe un datagrama
        System.out.write(p.getData());
        s.close();
        }
}
