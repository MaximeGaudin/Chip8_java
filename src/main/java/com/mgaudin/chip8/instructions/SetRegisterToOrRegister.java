package com.mgaudin.chip8.instructions;

import com.mgaudin.chip8.*;
import org.springframework.stereotype.Component;

import static com.mgaudin.chip8.HexUtils.toHex;

@Component
public class SetRegisterToOrRegister extends PrioritizedInstructionExecutor {

    @Override
    public boolean matches(byte[] opcode) {
        return opcode[0] == 0x8 && opcode[3] == 0x1;
    }

    @Override
    public String prettyPrince(byte[] opcode) {
        return "V" + toHex(opcode[1], 1) + " |= V" + toHex(opcode[2], 1);
    }

    /*
     * Set VX to VX OR VY
     */
    @Override
    public void execute(byte[] opcode, CPU cpu, Screen screen, Memory memory, Keyboard keyboard, Timers timers) {
        memory.setRegister(
                opcode[1],
                (byte) (memory.getRegister(opcode[1]) | memory.getRegister(opcode[2]))
        );
    }
}
