package DV;

import java.io.IOException;
import java.net.*;

public class DVMessageSender implements Runnable {

    int HOST_PORT;
    int RECEIVER_PORT;
    DatagramPacket packet;

    public DVMessageSender(String msg, int hostPort, int receiverPort) throws UnknownHostException {
        packet = PacketFactory.createDvMessagePacket(msg, receiverPort);
        HOST_PORT = hostPort;
        RECEIVER_PORT = receiverPort;

        Thread thread = new Thread(this, "DVSender");
        thread.start();
    }

    public DVMessageSender(DatagramPacket packetP, int hostPort, int closestPort) throws UnknownHostException {
        packet = packetP;
        HOST_PORT = hostPort;
        RECEIVER_PORT = closestPort;

        Thread thread = new Thread(this, "DVSender");
        thread.start();
    }

    @Override
    public void run() {
        try
        {
            DatagramSocket Socket = new DatagramSocket(HOST_PORT);
            Socket.send(packet);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
