package com.mgaudin.chip8.instructions;

import com.mgaudin.chip8.*;

public interface InstructionExecutor {
    boolean matches(byte[] opcode);

    String prettyPrince(byte[] opcode);

    void execute(byte[] opcode, CPU cpu, Screen screen, Memory memory, Keyboard keyboard, Timers timers);
}
