package tp;

import DV.PacketFactory;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Sender implements Runnable{
	
	Router router;
	byte[] receive = new byte[65535];
	DatagramPacket receivedPacket = new DatagramPacket(receive, receive.length);
	
	public Sender(Router router) 
	{
		this.router = router;
		Thread thread = new Thread(this,"Sender");
		thread.start();
	}

	@Override
	public synchronized void  run() 
	{
		
		byte[] send = new byte[65535];
		DatagramPacket packetToSend;
		DatagramPacket LSPdataPacket;
		DatagramSocket Socket;
		Boolean sent = false;
		try {
			Thread.sleep(999);
			//this.router.get();  //wait
			Socket = new DatagramSocket(this.router.sendPort);
			
			
			while(true) {
				Thread.sleep(100);
				//System.out.println(this.router.name + "sending port");
				
				if(true) {

					for(int i = 0; i < this.router.getNeigbor().size(); i++) {

						if(this.router.getNeigbor().get(i).to_port != this.router.lastReceive.port - 1 ) {
							packetToSend = PacketFactory.createLinksPacket(this.router.getNeigbor().get(i).to_port, this.router.getNeigbor() );
							Socket.setSoTimeout(999);
							sent = false;
							while(!sent) {
								try {
									receivedPacket = new DatagramPacket(receive, receive.length);
									//SEND ACK

									Socket.send(packetToSend);
									//WAIT FOR ACK
									Socket.receive(receivedPacket);
									if(this.router.name.contentEquals("F")) {
										System.out.println("F: " + this.router.getNeigbor().get(i).to_port);
									}
									this.router.sentToList.add(this.router.getNeigbor().get(i).to_port);
									
									sent = true;
								} catch (IOException e) {
									//ON TIMEOUT RESEND
									continue;
								}
							}
						}
						
					}
					this.router.sentToList = new ArrayList<Integer>();
					this.router.LspSent = true;
					Thread.sleep(999);
				}
				
			}
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		
	}

}
