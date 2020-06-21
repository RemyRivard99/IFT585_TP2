package tp;

public class Link {
	public int cost;
	public int from_port;
	public int to_port;
	
	public Link(int cost, int from_port, int to_port) {
		this.cost = cost;
		this.from_port = from_port;
		this.to_port = to_port;
		
	}
	
	public Boolean equals(Link link) {
		return (this.cost == link.cost && this.from_port == link.from_port && this.to_port == link.to_port);
	}
	
	public String toString() {
		return "Cost: " + this.cost + " From: " + this.from_port + " To: " + this.to_port + "\n";
	}
}
