import java.net.*;
import java.io.*;

public class ServidorUDP {
    public static void main(String[] args ) throws IOException{
        juegoBarcos();
    }

    public static void servidorUDP() throws IOException{
        DatagramSocket s = new DatagramSocket(55555);
        DatagramPacket p = new DatagramPacket(new byte[256], 256);
        s.receive(p);
        p.setAddress(p.getAddress());
        p.setPort(p.getPort());
        s.send(p);
        s.close();
    }

    public static void servidorMayusculas() throws IOException {
        DatagramSocket s = new DatagramSocket(55555);
        DatagramPacket p = new DatagramPacket(new byte[256], 256);
        s.receive(p);
        p.setAddress(p.getAddress());
        p.setPort(p.getPort());
        String mensaje = new String(p.getData());
        mensaje = mensaje.toUpperCase();
        byte[] buf = new byte[256];
        buf = mensaje.getBytes();
        p.setData(buf);
        s.send(p);
        s.close();
    }

    public static void tablaMultiplicar() throws IOException {
        DatagramSocket s = new DatagramSocket(55555);
        DatagramPacket p = new DatagramPacket(new byte[256], 256);
        s.receive(p);
        p.setAddress(p.getAddress());
        p.setPort(p.getPort());
        int numero = Integer.parseInt(new String(p.getData(),0,1));
        String cadena = "";
        for(int i=1; i<= 10; i++){
            cadena = cadena + numero+ "*" + i + "= " + i*numero + '\n';
        }
        byte[] buf = new byte[256];
        buf = cadena.getBytes();
        p.setData(buf);
        s.send(p);
        s.close();
    }

    public static void tableroAleatorio(byte[][] tablero){
        int fila;
        int columna;
        for (int i=0;i<10;i++){
            fila = (int)(Math.random()*10);
            columna = (int)(Math.random()*10);
            tablero[fila][columna] = 1;
        }
    }

    public static void juegoBarcos() throws IOException{
        byte [][] tablero = new byte[10][10];
        /*
        for(int i=0; i<10;i++){
            tablero[i][i] = 1;
        }
        */
        tableroAleatorio(tablero);

        for(int i=0; i<10;i++){
            for(int k=0;k<10;k++){
                System.out.print(tablero[i][k]);
            }
            System.out.print('\n');
        }

        int barcosHundidos = 0;

        while(barcosHundidos != 10){
            DatagramSocket s = new DatagramSocket(55555);
            DatagramPacket p = new DatagramPacket(new byte[256], 256);

            s.receive(p);
            int fila = Integer.parseInt(new String(p.getData(),0,1)); // Solo admite números de una cifra

            s.receive(p);
            int columna = Integer.parseInt(new String(p.getData(),0,1));

            String respuesta = "";

            if(tablero[fila][columna] == 1){
                tablero[fila][columna] = 0;
                respuesta = "Hundido\n";
                barcosHundidos++;
            }
            else {
                respuesta = "Has fallado\n";
            }

            p.setAddress(p.getAddress());
            p.setPort(p.getPort());

            byte[] buf = new byte[256];
            buf = respuesta.getBytes();
            p.setData(buf);
            s.send(p);
            s.close();
        }

    }

}
