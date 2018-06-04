package com.mgaudin.chip8.instructions;

import com.mgaudin.chip8.*;

import static com.mgaudin.chip8.HexUtils.toHex;
import static com.mgaudin.chip8.Memory.constant;

public class Missing extends PrioritizedInstructionExecutor {
    @Override
    public boolean matches(byte[] opcode) {
        return false;
    }

    @Override
    public String prettyPrince(byte[] opcode) {
        return "Missing : " + toHex(constant(opcode[0], opcode[1], opcode[2], opcode[3]), 4);
    }

    @Override
    public void execute(byte[] opcode, CPU cpu, Screen screen, Memory memory, Keyboard keyboard, Timers timers) {

    }
}
