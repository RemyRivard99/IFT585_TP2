package DV;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class DVVerticeSender implements Runnable {

    int HOST_PORT;
    int RECEIVER_PORT;
    DatagramPacket packet;

    public DVVerticeSender(int weight, int hostPort, int receiverPort) throws UnknownHostException {
        packet = PacketFactory.createDvPacket(weight, receiverPort);
        HOST_PORT = hostPort;
        RECEIVER_PORT = receiverPort;

        Thread thread = new Thread(this, "DVSender");
        thread.start();
    }

    @Override
    public void run() {
        try
        {
            DatagramSocket socket = new DatagramSocket(HOST_PORT);
            socket.send(packet);
            socket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
