
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class HiloCliente extends Thread{

  private DatagramSocket socket;
  private DatagramPacket datagrama;

  //Constructor
  public HiloCliente (DatagramSocket socket, DatagramPacket datagrama){
    this.socket = socket;
    this.datagrama = datagrama;
  }

  @Override
  public void run(){
    //Extraer informacion del datagrama recibido
    String mensaje = new String(datagrama.getData(), 0, datagrama.getLength());
    System.out.println("Mensaje recibido: "+mensaje);


    //Obtener la direccion IP del cliente
    InetAddress ip_cliente = datagrama.getAddress();
    int puerto_cliente = datagrama.getPort();

    //Mensaje de respuesta
    String respuesta = "Hola, desde el servidor";
    //Arreglo de bytes para enviar datos
    byte[] bufferSalida = respuesta.getBytes();
    //Crear datagrama para enviar el mensaje
    DatagramPacket datagrama_out = new DatagramPacket(bufferSalida, bufferSalida.length, ip_cliente,puerto_cliente);
    try {
      socket.send(datagrama_out);
      
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      
    }
  }
  
}
