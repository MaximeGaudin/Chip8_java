package com.mgaudin.chip8.instructions;

import com.mgaudin.chip8.*;
import org.springframework.stereotype.Component;

import static com.mgaudin.chip8.HexUtils.toHex;

@Component
public class AddRegisterToRegister extends PrioritizedInstructionExecutor {

    @Override
    public boolean matches(byte[] opcode) {
        return opcode[0] == 0x8 && opcode[3] == 0x4;
    }

    @Override
    public String prettyPrince(byte[] opcode) {
        return "V" + toHex(opcode[1], 1) + " += V" + toHex(opcode[2], 1);
    }

    /*
     * "Add the value of register VY to register VX
     * Set VF to 01 if a carry occurs
     * Set VF to 00 if a carry does not occur"
     */
    @Override
    public void execute(byte[] opcode, CPU cpu, Screen screen, Memory memory, Keyboard keyboard, Timers timers) {
        byte vX = memory.getRegister(opcode[1]);
        byte vY = memory.getRegister(opcode[2]);
        boolean carry = vX + vY > 255;

        memory.setRegister(
                opcode[1],
                (byte) (vX + vY)
        );

        if (carry) {
            memory.setRegister((byte) 0x0F, (byte) 0x01);
        } else {
            memory.setRegister((byte) 0x0F, (byte) 0x00);
        }
    }
}
