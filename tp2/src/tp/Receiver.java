package tp;
import algo.*;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Receiver implements Runnable{
	
	Router router;
	byte[] receive = new byte[65535];
	DatagramPacket receivedPacket = new DatagramPacket(receive, receive.length);
	
	public Receiver(Router router) 
	{
		this.router = router;
		Thread thread = new Thread(this,"Receiver");
		thread.start();
	}

	@Override
	public synchronized void run() 
	{
		DatagramSocket Socket;
		try {
			Socket = new DatagramSocket(this.router.receivePort);
			packetReader pReader;
			int count = 0;
			int duplicate = 0;
			ArrayList<Link> toAdd;
			while(true) 	
			{


				try {
					receivedPacket = new DatagramPacket(receive, receive.length);
					
					System.out.println(this.router.name + " " + count);
					count++;
					Socket.receive(receivedPacket);

					Socket.send(this.router.createAckPacket(receivedPacket));	//Send ACK
					//System.out.println(this.router.name + " is sending ack to: " + receivedPacket.getPort());
					this.router.lastReceive = new packetReader(receivedPacket);
					
					if(this.router.lastReceive.type.equals("LSP")) {
						this.router.syncronizing = true;
						
						//ADD RECEIVED LINKS TO THIS ROUTER LINK
					try {
							if(!this.router.lastReceive.links.isEmpty()) {
								
									for(int i = 0; i < this.router.lastReceive.links.size(); i ++) 
									{							

										this.router.links.add(this.router.lastReceive.links.get(i));
									}
								
							
							}
					}catch(Exception e) {
						System.out.println(e);
					}
						
						
					}else if(this.router.lastReceive.type.equals("LAST")){
						
					}
					
				} catch (IOException e) {
					System.out.println(this.router.name +  " receiving timeout at port: " + this.router.receivePort);
				}
				
				
				Thread.sleep(100);
				
				//this.router.put(1);
			} 
		} catch (SocketException e) {
			e.printStackTrace();
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		
	}


