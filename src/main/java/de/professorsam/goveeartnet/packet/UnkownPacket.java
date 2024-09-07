package de.professorsam.goveeartnet.packet;

public class UnkownPacket extends BasePacket {

    private final byte[] data;

    public UnkownPacket(int code, byte[] bytes){
        super(code, bytes);
        data = bytes;
    }

    public byte[] getData(){
        return data;
    }
}
