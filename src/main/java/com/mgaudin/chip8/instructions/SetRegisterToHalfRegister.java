package com.mgaudin.chip8.instructions;

import com.mgaudin.chip8.*;
import org.springframework.stereotype.Component;

import static com.mgaudin.chip8.HexUtils.toHex;

@Component
public class SetRegisterToHalfRegister extends PrioritizedInstructionExecutor {

    @Override
    public boolean matches(byte[] opcode) {
        return opcode[0] == 0x8 && opcode[3] == 0x7;
    }

    @Override
    public String prettyPrince(byte[] opcode) {
        return "V" + toHex(opcode[1], 1) + " = V" + toHex(opcode[2], 1) + " / 2";
    }

    /*
     * Set register VX to the value of VY minus VX
     * Set VF to 00 if a borrow occurs
     * Set VF to 01 if a borrow does not occur
     */
    @Override
    public void execute(byte[] opcode, CPU cpu, Screen screen, Memory memory, Keyboard keyboard, Timers timers) {
        byte vX = memory.getRegister(opcode[1]);
        byte vY = memory.getRegister(opcode[2]);

        memory.setRegister(opcode[1], (byte) (vY - vX));

        boolean borrow = vX > vY;
        if (borrow) {
            memory.setRegister((byte) 0x0F, (byte) 0);
        } else {
            memory.setRegister((byte) 0x0F, (byte) 1);
        }
    }
}
