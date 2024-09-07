package de.professorsam.goveeartnet;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LEDStrip {
    private final int startDMXChannel;

    private final InetAddress address;
    private int red;
    private int green;
    private int blue;
    public LEDStrip(String address, int dmxChannel) {
        try {
            this.address = InetAddress.getByName(address);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        this.startDMXChannel = dmxChannel;
        red = 0;
        green = 0;
        blue = 0;
    }

    public void updateColor(int dimmer, int red, int green, int blue){
        this.red = (int) (red * (dimmer/255F));
        this.green = (int) (green  * (dimmer/255F));
        this.blue = (int) (blue  * (dimmer/255F));
    }

    public int getStartDMXChannel() {
        return startDMXChannel;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }
}
