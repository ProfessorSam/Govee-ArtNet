package de.professorsam.goveeartnet;

import de.professorsam.goveeartnet.packet.BasePacket;
import de.professorsam.goveeartnet.packet.DMXDataPacket;
import de.professorsam.goveeartnet.packet.OpCode;
import de.professorsam.goveeartnet.packet.UnkownPacket;

import java.io.IOException;
import java.net.*;

public class ArtNetReceiver {

    private static final int PORT = 6454;
    private final DatagramSocket socket;

    public ArtNetReceiver(String address) throws IOException {
        socket = new DatagramSocket(PORT, InetAddress.getByName(address));
    }

    public void listen() throws IOException {
        byte[] buffer = new byte[1024];
        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            System.out.println(packet.getAddress() + " - " + packet.getPort());
            byte[] data = packet.getData();
            if(data.length < 10){
                System.err.println("Invalid ArtNet received (Packet too short)");
                continue;
            }
            for(int i = 0; i < 8; i++){
                if(data[i] != BasePacket.ID[i]){
                    System.err.println("Invalid ArtNet received (Missing ArtNet ID)");
                }
            }
            BasePacket basePacket = createPacketInstance(data);
            GoveeArtNet.processPacket(basePacket);
        }
    }

    private BasePacket createPacketInstance(byte[] data){
        int id = decodeLittleEndian(new byte[]{data[8], data[9]});
        return switch (id){
            case OpCode.OP_OUTPUT:
                yield new DMXDataPacket(id, data);
            default:
                yield new UnkownPacket(id, data);
        };
    }

    private int decodeLittleEndian(byte[] bytes) {
        return (bytes[1] << 8) | (bytes[0] & 0xFF);
    }
}
