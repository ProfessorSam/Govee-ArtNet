package de.professorsam.goveeartnet.packet;

public abstract class BasePacket {
    public static final byte[] ID = {'A', 'r', 't', '-', 'N', 'e', 't', 0x00};
    private final int opCode;

    public BasePacket(int opCode, byte[] bytes){
        this.opCode = opCode;
    }

    public int getOpCode() {
        return opCode;
    }

    public byte[] getId() {
        return ID;
    }
}
