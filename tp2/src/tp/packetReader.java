package tp;

import java.net.DatagramPacket;
import java.util.ArrayList;

public class packetReader {
	
	String type;
	ArrayList<Link> links;
	String msg = null;
	int port;
	
	// Receive packet data exemple: LSP;19020,19040,5;
	public packetReader(DatagramPacket packet) {
		String data = new String(packet.getData(), 0, packet.getLength());
		
		String[] dataList = data.split(";;;");
		
		if(dataList.length > 1) {
			this.msg = dataList[1];
		}
		
		dataList = dataList[0].split("&");
		
		this.type = dataList[0];
		
		try {
		//Get links
		String[] linksString = dataList[1].split(";");
		
		
		Link link;
		ArrayList<Link> temp = new ArrayList<Link>();
		
		for(int i = 0; i < linksString.length; i++) {
			String[] c = linksString[i].split(",");

			link = new Link(Integer.parseInt(c[2]), Integer.parseInt(c[0]), Integer.parseInt(c[1]));
			
			temp.add(link);
		}
		
		this.links = temp;
		
		this.port =  packet.getPort();
		
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}