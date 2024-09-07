package de.professorsam.goveeartnet;

import java.io.IOException;
import java.net.*;

public class GoveeSender {

    private static final int PORT = 4003;
    private final DatagramSocket socket;
    public GoveeSender() {
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStrip(LEDStrip strip) {
        byte[] data = ("{\"msg\":{\"cmd\":\"colorwc\",\"data\":{\"color\":{\"r\":" + strip.getRed() +
                ",\"g\":" + strip.getGreen() +
                ",\"b\":" + strip.getBlue() +
                "},\"colorTemInKelvin\":0}}}").getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, strip.getAddress(), PORT);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
