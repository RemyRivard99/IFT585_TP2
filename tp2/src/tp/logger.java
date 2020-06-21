package tp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class logger implements Runnable{
	
	Router router;
	byte[] receive = new byte[65535];
	DatagramPacket receivedPacket = new DatagramPacket(receive, receive.length);
	
	public logger(Router router) 
	{
		this.router = router;
		Thread thread = new Thread(this,"Receiver");
		thread.start();
	}

	@Override
	public void run() 
	{	
		System.out.println("asR2");
		DatagramSocket Socket;
		try {

			while(true) 	
			{
				Thread.sleep(1000);
				
//				System.out.println("====================ROUTER_" + this.router.name + "============================ (" + this.router.state +")");
//				System.out.println("ROUTER_" + this.router.name + " syncronizing: " + this.router.syncronizing);
//				System.out.println("ROUTER_" + this.router.name + " lastReceive: " + this.router.lastReceive);
				if(this.router.sync) {
					System.out.println("ROUTER_" + this.router.name + " sync: " + this.router.sync);
				}
				if(!(this.router.optimalRoute == null)) {
					System.out.println(this.router.name + " - " + this.router.optimalRoute);
				}
				System.out.println(this.router.links.size());

				
			} 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		}
		
	}


