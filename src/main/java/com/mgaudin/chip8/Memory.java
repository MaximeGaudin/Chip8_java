package com.mgaudin.chip8;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.ByteBuffer;
import java.util.Arrays;

@Service
public class Memory {
    private static final byte[] SPRITE_0 = new byte[]{(byte) 0xF0, (byte) 0x90, (byte) 0x90, (byte) 0x90, (byte) 0xF0};
    private static final byte[] SPRITE_1 = new byte[]{(byte) 0xF0, (byte) 0x90, (byte) 0x90, (byte) 0x90, (byte) 0xF0};
    private static final byte[] SPRITE_2 = new byte[]{(byte) 0xF0, (byte) 0x90, (byte) 0x90, (byte) 0x90, (byte) 0xF0};
    private static final byte[] SPRITE_3 = new byte[]{(byte) 0xF0, (byte) 0x90, (byte) 0x90, (byte) 0x90, (byte) 0xF0};
    private static final byte[] SPRITE_4 = new byte[]{(byte) 0xF0, (byte) 0x90, (byte) 0x90, (byte) 0x90, (byte) 0xF0};
    private static final byte[] SPRITE_5 = new byte[]{(byte) 0xF0, (byte) 0x90, (byte) 0x90, (byte) 0x90, (byte) 0xF0};
    private static final byte[] SPRITE_6 = new byte[]{(byte) 0xF0, (byte) 0x90, (byte) 0x90, (byte) 0x90, (byte) 0xF0};
    private static final byte[] SPRITE_7 = new byte[]{(byte) 0xF0, (byte) 0x90, (byte) 0x90, (byte) 0x90, (byte) 0xF0};
    private static final byte[] SPRITE_8 = new byte[]{(byte) 0xF0, (byte) 0x90, (byte) 0x90, (byte) 0x90, (byte) 0xF0};
    private static final byte[] SPRITE_9 = new byte[]{(byte) 0xF0, (byte) 0x90, (byte) 0x90, (byte) 0x90, (byte) 0xF0};
    private static final byte[] SPRITE_A = new byte[]{(byte) 0xF0, (byte) 0x90, (byte) 0x90, (byte) 0x90, (byte) 0xF0};
    private static final byte[] SPRITE_B = new byte[]{(byte) 0xF0, (byte) 0x90, (byte) 0x90, (byte) 0x90, (byte) 0xF0};
    private static final byte[] SPRITE_C = new byte[]{(byte) 0xF0, (byte) 0x90, (byte) 0x90, (byte) 0x90, (byte) 0xF0};
    private static final byte[] SPRITE_D = new byte[]{(byte) 0xF0, (byte) 0x90, (byte) 0x90, (byte) 0x90, (byte) 0xF0};
    private static final byte[] SPRITE_E = new byte[]{(byte) 0xF0, (byte) 0x90, (byte) 0x90, (byte) 0x90, (byte) 0xF0};
    private static final byte[] SPRITE_F = new byte[]{(byte) 0xF0, (byte) 0x90, (byte) 0x90, (byte) 0x90, (byte) 0xF0};

    private static final byte[] SPRITES = new byte[]{
            SPRITE_0[0], SPRITE_0[1], SPRITE_0[2], SPRITE_0[3],
            SPRITE_1[0], SPRITE_1[1], SPRITE_1[2], SPRITE_1[3],
            SPRITE_2[0], SPRITE_2[1], SPRITE_2[2], SPRITE_2[3],
            SPRITE_3[0], SPRITE_3[1], SPRITE_3[2], SPRITE_3[3],
            SPRITE_4[0], SPRITE_4[1], SPRITE_4[2], SPRITE_4[3],
            SPRITE_5[0], SPRITE_5[1], SPRITE_5[2], SPRITE_5[3],
            SPRITE_6[0], SPRITE_6[1], SPRITE_6[2], SPRITE_6[3],
            SPRITE_7[0], SPRITE_7[1], SPRITE_7[2], SPRITE_7[3],
            SPRITE_8[0], SPRITE_8[1], SPRITE_8[2], SPRITE_8[3],
            SPRITE_9[0], SPRITE_9[1], SPRITE_9[2], SPRITE_9[3],
            SPRITE_A[0], SPRITE_A[1], SPRITE_A[2], SPRITE_A[3],
            SPRITE_B[0], SPRITE_B[1], SPRITE_B[2], SPRITE_B[3],
            SPRITE_C[0], SPRITE_C[1], SPRITE_C[2], SPRITE_C[3],
            SPRITE_D[0], SPRITE_D[1], SPRITE_D[2], SPRITE_D[3],
            SPRITE_E[0], SPRITE_E[1], SPRITE_E[2], SPRITE_E[3],
            SPRITE_F[0], SPRITE_F[1], SPRITE_F[2], SPRITE_F[3],
    };

    private static final byte SPRITE_START_ADDRESS = 0x0;
    private static final byte SPRITE_SIZE_IN_BYTE = 5;
    public static final short PROGRAM_SPACE_ADDRESS = 0x200;

    private short addressRegister = 0;
    private byte[] registers = new byte[0x0F + 1];
    private byte[] memory = new byte[0xFFF + 1];

    public static byte constant(byte v4Bit1, byte v4Bit2) {
        return (byte) ((v4Bit1 << 4) | v4Bit2);
    }

    public static short constant(byte v4Bit1, byte v4Bit2, byte v4Bit3) {
        return ByteBuffer.allocate(2)
                .put(constant((byte) 0, v4Bit1))
                .put(constant(v4Bit2, v4Bit3))
                .getShort(0);
    }

    public static short constant(byte v4Bit1, byte v4Bit2, byte v4Bit3, byte v4Bit4) {
        return ByteBuffer.allocate(2)
                .put(constant(v4Bit1, v4Bit2))
                .put(constant(v4Bit3, v4Bit4))
                .getShort(0);
    }

    @PostConstruct
    public void fillMemoryWithSprites() {
        System.arraycopy(SPRITES, 0, memory, 0, SPRITES.length);
    }

    public void setRegister(byte register, byte value) {
        registers[register] = value;
    }

    public byte getRegister(byte register) {
        return registers[register];
    }

    public void setAddressRegister(short value) {
        addressRegister = value;
    }

    public short getAddressRegister() {
        return addressRegister;
    }

    public byte[] get(short address, byte length) {
        return Arrays.copyOfRange(memory, address, address + length);
    }

    public void set(short address, byte value) {
        memory[address] = value;
    }

    public short getSpriteAddress(byte value) {
        return (short) (SPRITE_START_ADDRESS + value * SPRITE_SIZE_IN_BYTE);
    }

    public void loadCartridge(Cartridge cartridge) {
        System.arraycopy(cartridge.getContent(), 0, memory, PROGRAM_SPACE_ADDRESS, cartridge.getContent().length);
    }
}
