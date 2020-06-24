package DV.Tests;

import DV.PacketFactory;
import tp.packetReader;

import java.net.DatagramPacket;
import java.net.UnknownHostException;

public class DvPacketCreationTest {

    public static void main(String[] args) throws UnknownHostException {
        //-------------Test 1 : DV vertice packet
        DatagramPacket packetTest = PacketFactory.createDvPacket(4, 1020);

        packetReader pr = new packetReader(packetTest);

        System.out.println();
        System.out.println("Weight value should be: " + 4);
        System.out.println("Weight value is       : " + pr.getMessage());

        System.out.println();
        System.out.println("Type value should be: " + "DISTV");
        System.out.println("Type value is       : " + pr.getType());

        //-------------Test 2 : DV message packet
        DatagramPacket packetTest2 = PacketFactory.createDvMessagePacket("testHello", 1020);

        packetReader pr2 = new packetReader(packetTest2);

        System.out.println();
        System.out.println("Msg value should be: " + "testHello");
        System.out.println("Msg value is       : " + pr2.getMessage());

        System.out.println();
        System.out.println("Type value should be: " + "DVP");
        System.out.println("Type value is       : " + pr2.getType());
    }

}
