package tp;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

import algo.NodeWeighted;

public class Router{
	
	//router config
	//TODO: ID
	String name;
	int sendPort;
	int receivePort;
	public ArrayList<Link> links;
	public ArrayList<Link> neighbor;
	
	public boolean LspSent = false;
	public boolean lastRouter = false;
	public ArrayList<Integer> sentToList = new ArrayList<Integer>();

	//Pour DV
	//HashMap<routerID, (int)cost>
	public HashMap<Integer, Integer> edgeTable;
	
	//LoggerInfo
	
	public DatagramPacket PacketToSend;
	public packetReader lastReceive = null;
	
	public Boolean LSPSent = false;
	public Boolean sync = false;
	public Boolean syncronizing = false;
	public String optimalRoute = null;
	
	int num;
	boolean valueSet = false;
	
	public Router(String name, int receivePort, int sendPort, ArrayList<Link> links) {
		this.name = name;
		this.receivePort = receivePort;
		this.sendPort = sendPort;
		this.neighbor = links;
		this.links = links;
	}
	
	public synchronized void put(int num) {
		
		while(this.valueSet) {
			try {
				wait();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//System.out.println("ROUTER_" + this.name + " Receiving: " + num);
		this.num = num;
		
		this.valueSet = true;
		notify(); //Notify Sender that data is ready for it to send
	}
	//Send will have to get the data it has to sends
	public synchronized void get() {
		while(!valueSet) {
			try {
				//System.out.println("send wait");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//System.out.println("ROUTER_" + this.name + " is Sending: " + this.lastReceive);
		
		this.valueSet = false;

		notify(); //Notify Receiver that we got the data
		
	}
	
	public synchronized ArrayList<Link> getLinkList(){
		return this.links;
	}
	
	public synchronized HashMap<String, NodeWeighted> createMap() {
		HashMap<String, NodeWeighted> map = new HashMap<String,NodeWeighted>();
		
		ArrayList<Integer> to = new ArrayList<Integer>();
		int count = 0;
		for(int i = 0; i < this.links.size(); i++) {

			if(!to.contains(this.links.get(i).to_port)) {
				to.add(this.links.get(i).to_port);
				map.put(Integer.toString(i), new NodeWeighted(i, Integer.toString(links.get(i).to_port)));
			}
		}

		
		return map;
	}
	
	public synchronized ArrayList<Link> getLinks() {
		return this.links;
	}
	
	public synchronized void addLinks() {

			for(int i = 0; i < this.lastReceive.links.size(); i ++) 
			{							

				this.links.add(this.lastReceive.links.get(i));
			}
		
	}
	
	public synchronized ArrayList<Link> getNeigbor() {
		return this.neighbor;
	}


	//Pour DV
	public void initEdgeTable(){
		edgeTable = new HashMap<Integer, Integer>();

		for(int e = 0; e < links.size(); e++){
			//-1 == infinite
			edgeTable.put(links.get(0).to_port, -1);
		}
	}

	public void sendMessageByDV(String message){
		
	}

	public boolean neighborLinkCheck(){
		for(int e = 0; e < edgeTable.size() ; e++) {
			if(!links.contains(edgeTable.get(e))){
				return false;
			}
		}
		return true;
	}
}
