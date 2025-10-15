
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class servidor{
  public static void main(String[] args) {
    int puerto = 5000;
    //Crear socket
    try {
      DatagramSocket socket = new DatagramSocket(puerto);
      System.out.println("Esperando conexiones..................");

      //Arreglo de bytes para recibir los datos
      byte[] bufferEntrada = new byte[1024];
      //Crear datagrama para recibir datos
      DatagramPacket datagrama = new DatagramPacket(bufferEntrada, bufferEntrada.length);

      //Esperando recibir el mensaje del cliente
      socket.receive(datagrama);

      HiloCliente hilo = new HiloCliente(socket, datagrama);
      hilo.start();

          
    } catch (Exception e) {
      e.printStackTrace();
    }
      
  }
}

