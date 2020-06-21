package tp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class HostSender implements Runnable{
	
	int HOST_PORT;
	int MAIN_ROUTER_PORT;
	
	String msg;
	String LSP = "LSP&;;;";
	DatagramPacket LinkedStatePacket;
	
	public HostSender(String msg, int HOST_PORT, int MAIN_ROUTER_PORT) throws UnknownHostException {
		this.msg = msg;
		this.HOST_PORT = HOST_PORT;
		this.MAIN_ROUTER_PORT = MAIN_ROUTER_PORT;
		
		byte buf[] = LSP.getBytes();
		InetAddress ip = InetAddress.getLocalHost(); 
		
		LinkedStatePacket = new DatagramPacket(buf, buf.length, ip, MAIN_ROUTER_PORT);
		
		Thread thread = new Thread(this, "HostSender");
		thread.start();
	}

	@Override
	public void run() {
		//System.out.println("HostSender: Start sending: '" + msg + "' host start sending form port: " + HOST_PORT);
		//System.out.println("HostSender: Start creating Linked State packet");
		
		byte[] receive = new byte[65535];
		DatagramPacket receivedPacket = new DatagramPacket(receive, receive.length);
		
		try 
		{
			//DatagramSocket sendingSocket = new DatagramSocket();
			DatagramSocket Socket = new DatagramSocket(HOST_PORT); 
			
			//System.out.println("HostSender: Sending LSP to: " + MAIN_ROUTER_PORT);
			Socket.send(LinkedStatePacket);
			
			//System.out.println("HostSender: Waiting to receive back the LSP");
			Socket.receive(receivedPacket);
			//System.out.println("HostSender: Received LSP back");
			
			
		} catch (SocketException e) 
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}

}
