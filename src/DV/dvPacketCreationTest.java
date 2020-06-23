package DV;

import tp.packetReader;

import java.net.DatagramPacket;
import java.net.UnknownHostException;

public class dvPacketCreationTest {

    public static void main(String[] args) throws UnknownHostException {
        DatagramPacket packetTest = PacketFactory.createDvPacket(4, 1020);

        packetReader pr = new packetReader(packetTest);

        System.out.println();
        System.out.println("Weight value should be: " + 4);
        System.out.println("Weight value is       : " + pr.getMessage());

        System.out.println();
        System.out.println("Type value should be: " + "DISTV");
        System.out.println("Type value is       : " + pr.getType());
    }

}
