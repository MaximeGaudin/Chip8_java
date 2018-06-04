package com.mgaudin.chip8.instructions;

import com.mgaudin.chip8.*;
import org.springframework.stereotype.Component;

import static com.mgaudin.chip8.HexUtils.toHex;

@Component
public class SetRegisterToShiftedLeftRegister extends PrioritizedInstructionExecutor {

    @Override
    public boolean matches(byte[] opcode) {
        return opcode[0] == 0x8 && opcode[3] == 0xE;
    }

    @Override
    public String prettyPrince(byte[] opcode) {
        return "V" + toHex(opcode[1], 1) + " = V" + toHex(opcode[2], 1) + " << 1";
    }

    /*
     * Store the value of register VY shifted left one bit in register VX
     * Set register VF to the most significant bit prior to the shift
     */
    @Override
    public void execute(byte[] opcode, CPU cpu, Screen screen, Memory memory, Keyboard keyboard, Timers timers) {
        byte vY = memory.getRegister(opcode[2]);

        memory.setRegister((byte) 0x0F, (byte) (vY & 0b10000000));
        memory.setRegister(opcode[1], (byte) (vY << 1));
    }
}
