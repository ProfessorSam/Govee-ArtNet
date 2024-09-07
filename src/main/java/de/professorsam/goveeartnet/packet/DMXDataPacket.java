package de.professorsam.goveeartnet.packet;

public class DMXDataPacket extends BasePacket {

    private final int[] dmxData;

    public DMXDataPacket(int opCode, byte[] bytes) {
        super(opCode, bytes);
        // Index | description
        // 0-7 = id
        // 8-9 = opCode
        // 10 = ProtverHi
        // 11 = ProtverLow
        // 12 = Sequence
        // 13 = Physical
        // 14 = SubUni
        // 15 = Net
        // 16 = LengthHi
        // 17 = LengthLow
        // 18-(length-1) = DMX data
        int length = ((bytes[16] & 0xFF) << 8) | (bytes[17] & 0xFF);
        dmxData = new int[length];
        for(int i = 18; i < dmxData.length; i++){
            dmxData[i - 18] = Byte.toUnsignedInt(bytes[i]);
        }
    }

    public int[] getDMXData() {
        return dmxData;
    }
}
