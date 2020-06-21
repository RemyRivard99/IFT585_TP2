package tp;

import java.net.DatagramPacket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class testPacketCreation {

	public static void main(String[] args) throws UnknownHostException 
	{
	
	int HOST_PORT 		= 18020;
	int ROUTER_A_PORT 	= 19010;
	int ROUTER_B_PORT 	= 19020;
	int ROUTER_C_PORT 	= 19030;
	int ROUTER_D_PORT 	= 19040;
	int ROUTER_E_PORT 	= 19050;
	int ROUTER_F_PORT 	= 19060;
	
	String msg = "Hello world";
	
	//Starting all the routers
	ArrayList<Link> ROUTER_A_Links = new ArrayList<Link>();
	ROUTER_A_Links.add(new Link(5,ROUTER_A_PORT,ROUTER_B_PORT)); 
	ROUTER_A_Links.add(new Link(45,ROUTER_A_PORT,ROUTER_D_PORT));
	Router ROUTER_A = new Router("A", ROUTER_A_PORT, ROUTER_A_PORT + 1, ROUTER_A_Links);

	
	
	//packetReader pReader = new packetReader(p);
//	
//	for(Link l : pReader.links) {
//		System.out.println(l.toString());
//	}
//	
	

	}
}