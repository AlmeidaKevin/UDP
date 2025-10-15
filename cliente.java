import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class cliente {
    public static void main(String[] args) {
        int puerto_servidor = 5000;
        try {
            //cirección del servidor
            InetAddress ip_servidor = InetAddress.getByName("172.31.115.142"); 

            //crear el socket del cliente
            DatagramSocket socket = new DatagramSocket();

            //////------------------Enviar---------------//////

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Escribe el mensaje, x para cerrar): ");
                String mensaje = scanner.nextLine();

                if (mensaje == null || mensaje.isEmpty()) {
                    System.out.println("Mensaje vacio");
                    continue; 
                }

                if (mensaje.equalsIgnoreCase("x")) {
                    break;
                }

                //convertir el mensaje a bytes
                byte[] bufferSalida = mensaje.getBytes();

                //crear datagrama para enviar el mensaje al servidor
                DatagramPacket datagrama_out = new DatagramPacket(bufferSalida, bufferSalida.length, ip_servidor, puerto_servidor);
                socket.send(datagrama_out);


                //////------------Recibir respuesta--------------------- //////
                //preparar buffer para recibir respuesta
                byte[] bufferEntrada = new byte[1024];
                DatagramPacket datagrama_in = new DatagramPacket(bufferEntrada, bufferEntrada.length);

                //esperar la respuesta del servidor
                socket.receive(datagrama_in);

                //Convertir los datos recibidos en string
                String respuesta = new String(datagrama_in.getData(), 0, datagrama_in.getLength());
                System.out.println("Mensaje recibido del servidor: " + respuesta);
            }

            scanner.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}