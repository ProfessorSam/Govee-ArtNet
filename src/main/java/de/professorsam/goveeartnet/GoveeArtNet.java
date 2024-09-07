package de.professorsam.goveeartnet;

import de.professorsam.goveeartnet.packet.BasePacket;
import de.professorsam.goveeartnet.packet.DMXDataPacket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoveeArtNet {

    private static GoveeSender goveeSender;
    private static final List<LEDStrip> strips = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        ArtNetReceiver receiver = new ArtNetReceiver("127.0.0.1");
        strips.add(new LEDStrip("192.168.100.231", 1));
        strips.add(new LEDStrip("192.168.100.182", 5));
        strips.add(new LEDStrip("192.168.100.236", 9));
        goveeSender = new GoveeSender();
        receiver.listen();
    }

    public static void processPacket(BasePacket packet){
        if(!(packet instanceof DMXDataPacket dmxDataPacket)){
            return;
        }
        for(LEDStrip strip : strips){
            int dimmer = dmxDataPacket.getDMXData()[(0 + (strip.getStartDMXChannel() - 1)) % dmxDataPacket.getDMXData().length];
            int red =    dmxDataPacket.getDMXData()[(1 + (strip.getStartDMXChannel() - 1)) % dmxDataPacket.getDMXData().length];
            int green =  dmxDataPacket.getDMXData()[(2 + (strip.getStartDMXChannel() - 1)) % dmxDataPacket.getDMXData().length];
            int blue =   dmxDataPacket.getDMXData()[(3 + (strip.getStartDMXChannel() - 1)) % dmxDataPacket.getDMXData().length];
            strip.updateColor(dimmer, red, green, blue);
            goveeSender.updateStrip(strip);
        }
    }
}
