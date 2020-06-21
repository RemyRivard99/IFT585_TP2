package tp;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class main {

	public static void main(String[] args) throws UnknownHostException, InterruptedException 
	{
		int HOST_PORT = 57020;
		int ROUTER_A_PORT = 56010;
		int ROUTER_B_PORT = 56020;
		int ROUTER_C_PORT = 56030;
		int ROUTER_D_PORT = 56040;
		int ROUTER_E_PORT = 56050;
		int ROUTER_F_PORT = 56060;
		int FINAL_PORT = 33333;
		
		ArrayList<Router> routerList = new ArrayList<Router>();
		
		String msg = "Hello world";
		
		//Starting all the routers
		ArrayList<Link> ROUTER_A_Links = new ArrayList<Link>();
		ROUTER_A_Links.add(new Link(5,ROUTER_A_PORT,ROUTER_B_PORT)); 
		ROUTER_A_Links.add(new Link(45,ROUTER_A_PORT,ROUTER_D_PORT));
		Router ROUTER_A = new Router("A", ROUTER_A_PORT, ROUTER_A_PORT + 1, ROUTER_A_Links);

		routerList.add(ROUTER_A);
		new Receiver(ROUTER_A);
		new Sender(ROUTER_A);
		//new logger(ROUTER_A);
		
		ArrayList<Link> ROUTER_B_Links = new ArrayList<Link>();
		ROUTER_B_Links.add(new Link(5,ROUTER_B_PORT, ROUTER_A_PORT));
		ROUTER_B_Links.add(new Link(70, ROUTER_B_PORT, ROUTER_C_PORT));		
		ROUTER_B_Links.add(new Link(3, ROUTER_B_PORT, ROUTER_E_PORT));
		Router ROUTER_B = new Router("B", ROUTER_B_PORT, ROUTER_B_PORT + 1, ROUTER_B_Links);

		routerList.add(ROUTER_B);
		new Receiver(ROUTER_B);
		new Sender(ROUTER_B);
		//new logger(ROUTER_B);
		
		ArrayList<Link> ROUTER_C_Links = new ArrayList<Link>();
		ROUTER_C_Links.add(new Link(70,ROUTER_C_PORT, ROUTER_B_PORT));
		ROUTER_C_Links.add(new Link(78,ROUTER_C_PORT, ROUTER_F_PORT));
		ROUTER_C_Links.add(new Link(50,ROUTER_C_PORT, ROUTER_D_PORT));
		Router ROUTER_C = new Router("C", ROUTER_C_PORT, ROUTER_C_PORT + 1, ROUTER_C_Links);

		routerList.add(ROUTER_C);
		new Receiver(ROUTER_C);
		new Sender(ROUTER_C);
		//new logger(ROUTER_C);
		
		ArrayList<Link> ROUTER_D_Links = new ArrayList<Link>();
		ROUTER_D_Links.add(new Link(45,ROUTER_D_PORT, ROUTER_A_PORT));
		ROUTER_D_Links.add(new Link(8,ROUTER_D_PORT, ROUTER_E_PORT));
		ROUTER_D_Links.add(new Link(50,ROUTER_D_PORT, ROUTER_C_PORT));
		Router ROUTER_D = new Router("D", ROUTER_D_PORT, ROUTER_D_PORT + 1, ROUTER_D_Links);

		routerList.add(ROUTER_D);
		new Receiver(ROUTER_D);
		new Sender(ROUTER_D);
		//new logger(ROUTER_D);
		
		ArrayList<Link> ROUTER_E_Links = new ArrayList<Link>();
		ROUTER_E_Links.add(new Link(8,ROUTER_E_PORT, ROUTER_D_PORT));
		ROUTER_E_Links.add(new Link(3,ROUTER_E_PORT, ROUTER_B_PORT));
		ROUTER_E_Links.add(new Link(7,ROUTER_E_PORT, ROUTER_F_PORT));
		Router ROUTER_E = new Router("E", ROUTER_E_PORT, ROUTER_E_PORT + 1, ROUTER_E_Links);

		routerList.add(ROUTER_E);
		new Receiver(ROUTER_E);
		new Sender(ROUTER_E);
		//new logger(ROUTER_E);
		
		ArrayList<Link> ROUTER_F_Links = new ArrayList<Link>();
		ROUTER_F_Links.add(new Link(78,ROUTER_F_PORT, ROUTER_C_PORT));
		ROUTER_F_Links.add(new Link(7,ROUTER_F_PORT, ROUTER_E_PORT));
		//ROUTER_F_Links.add(new Link(1,ROUTER_F_PORT, FINAL_PORT));
		Router ROUTER_F = new Router("F", ROUTER_F_PORT, ROUTER_F_PORT + 1, ROUTER_F_Links);

		routerList.add(ROUTER_F);
		new Receiver(ROUTER_F);
		new Sender(ROUTER_F);
		//new logger(ROUTER_F);

		//Starting the host(sender)
		HostSender hostSender = new HostSender(msg, HOST_PORT, ROUTER_A_PORT);
		
		while(true) {
			Thread.sleep(999);
			
			for(Router router : routerList) {
				System.out.println("=====================================" + router.name + "=====================================");
				
				System.out.println("Synchronizing: " + router.syncronizing);
				System.out.println("Lsp sent: " + router.LspSent);
				if(router.lastReceive != null ) {
					System.out.println("Last packet type: " + router.lastReceive.type);
				}
				try {
					System.out.println(router.getLinks().size());
				}catch(Exception e) {
					System.out.println(e);
				}
				//System.out.println(router.getLinks().size());
				System.out.println(router.getNeigbor().size());
//				for(Link l : router.links) {
//					System.out.println("from: " + l.from_port + " to: " + l.to_port);
//				}
			}
			
		}
		
	}
	
	public static void clearScreen() {  
		try {
			Runtime.getRuntime().exec("clear");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	   }

}
