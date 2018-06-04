package com.mgaudin.chip8.instructions;

import com.mgaudin.chip8.*;
import org.springframework.stereotype.Component;

import static com.mgaudin.chip8.HexUtils.toHex;

@Component
public class SubstractRegisterToRegister extends PrioritizedInstructionExecutor {

    @Override
    public boolean matches(byte[] opcode) {
        return opcode[0] == 0x8 && opcode[3] == 0x5;
    }

    @Override
    public String prettyPrince(byte[] opcode) {
        return "V" + toHex(opcode[1], 1) + " -= V" + toHex(opcode[2], 1);
    }

    /*
     * Subtract the value of register VY from register VX
     * Set VF to 00 if a borrow occurs
     * Set VF to 01 if a borrow does not occur
     */
    @Override
    public void execute(byte[] opcode, CPU cpu, Screen screen, Memory memory, Keyboard keyboard, Timers timers) {
        byte vX = memory.getRegister(opcode[1]);
        byte vY = memory.getRegister(opcode[2]);

        memory.setRegister(opcode[1], (byte) (vX - vY));

        boolean borrow = vY > vX;
        if (borrow) {
            memory.setRegister((byte) 0x0F, (byte) 0x00);
        } else {
            memory.setRegister((byte) 0x0F, (byte) 0x01);
        }
    }
}
