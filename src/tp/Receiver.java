package tp;
import DV.DVMessageSender;
import DV.DistanceVector;
import DV.PacketFactory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Receiver implements Runnable{

	private static final String Arraylist = null;
	Router router;
	byte[] receive = new byte[65535];
	DatagramPacket receivedPacket = new DatagramPacket(receive, receive.length);
	public ArrayList<Link> neighbor;
	public ArrayList<Link>  links;

	public Receiver(Router router)
	{
		this.router = router;
		Thread thread = new Thread(this,"Receiver");
		thread.start();
		this.neighbor = (ArrayList<Link>) this.router.links.clone();
		this.links = (ArrayList<Link>) this.router.links.clone();
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
			DatagramPacket packetToSend;
			Boolean sent = false;
			Socket.setSoTimeout(100);
			while(true) 	
			{

				try {
					receivedPacket = new DatagramPacket(receive, receive.length);
					
					System.out.println(this.router.name + " " + count);
					count++;
					Socket.receive(receivedPacket);

					Socket.send(PacketFactory.createAckPacket(receivedPacket));	//Send ACK
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
						
					}else if(this.router.lastReceive.type.equals("DISTV")) {
						// Si on recoit un vecteur de distance
						// transmet son vecteur de distance a tout ses voisins
						DistanceVector.updateTable(this.router.lastReceive.port, this.router.lastReceive.msg, this.router.edgeTable);
						int weight = DistanceVector.computeDistanceVector(this.router.links, this.router.edgeTable);
						DistanceVector.transmitDistanceVector(this.router.edgeTable, this.router.receivePort, weight);

					}else if(this.router.lastReceive.type.equals("DVP")) {
						// Quand on recoit un packet du protocole DV avec des donnees,
						// On le transmet au chemin le plus court du "edgeTable"

						//Affiche les infos du routeur quand le paquet passe
						System.out.println("hit");

						//Trouve la plus petite valeur du edgeTable
						int closestPort = -1;
						int lowestDistance = -1;

						Iterator it = this.router.edgeTable.entrySet().iterator();
						while (it.hasNext()) {
							Map.Entry pair = (Map.Entry)it.next();

							if((Integer)pair.getValue() < lowestDistance || lowestDistance == -1){
								closestPort = (Integer) pair.getValue();
							}
							it.remove();
						}

						//Envoie le packet recu au prochain router
						DVMessageSender dvs = new DVMessageSender(receivedPacket, this.router.receivePort, closestPort);

					}else if(!this.router.neighborLinkCheck()){
						//si un link est deconnectee
						// transmet son vecteur de distance a tout ses voisins
						//TODO : Comment on fait pour verifier qu'un link est out?
						//TODO : Implementer un truc pour trouver le fautif.
						//TODO : Cancel ca srx.
						int faultyPort = -1;
						DistanceVector.removeFromTable(this.router.edgeTable, faultyPort);
						int weight = DistanceVector.computeDistanceVector(this.router.links, this.router.edgeTable);
						DistanceVector.transmitDistanceVector(this.router.edgeTable, this.router.receivePort, weight);
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


