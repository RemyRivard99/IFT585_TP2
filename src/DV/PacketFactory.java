package DV;

import tp.Link;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class PacketFactory {
    /**
     *  Quand on veut un paquet de n'importe quel type, on appel la factory pour nous le faire.
     */
    public static synchronized DatagramPacket createDvPacket(int weight, int receiverPort) throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();
        String msg = "DISTV" + "&";         //header
        msg += ";;;";                       //separator
        msg += Integer.toString(weight);    //body

        byte buf[] = msg.getBytes();

        return new DatagramPacket(buf, buf.length, ip, receiverPort);
    }

    public static synchronized DatagramPacket createLinksPacket(int receiverPort, ArrayList<Link> neighbor) throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();

        //CREATE THE HEADER
        String header = "LSP" + "&";

        for(int i = 0; i < neighbor.size(); i++) {
            header += neighbor.get(i).from_port + "," + neighbor.get(i).to_port + "," + neighbor.get(i).cost + ";";

        }

        header += ";;";

        byte buf[] = header.getBytes();

        return new DatagramPacket(buf, buf.length, ip, receiverPort);
    }

    public static synchronized DatagramPacket createAckPacket(DatagramPacket packetToACK) throws UnknownHostException {
        String ACKstring = "ACK";


        byte buf[] = ACKstring.getBytes();
        InetAddress ip = InetAddress.getLocalHost();

        return new DatagramPacket(buf, buf.length, ip, packetToACK.getPort());
    }

    /** TODO : Est-ce qu'on l'utilise elle?  */
    public static synchronized  ArrayList<DatagramPacket> createLinksPacketList(int receiverPort, ArrayList<Link> neighbor) throws UnknownHostException {

        ArrayList<DatagramPacket> packetList = new ArrayList<DatagramPacket>();

        for(Link link : neighbor) {
            packetList.add(createLinksPacket(receiverPort, neighbor));
        }

        return packetList;
    }
}

