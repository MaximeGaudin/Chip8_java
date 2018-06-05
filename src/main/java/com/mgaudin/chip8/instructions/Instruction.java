package com.mgaudin.chip8.instructions;

import com.mgaudin.chip8.*;

import static com.mgaudin.chip8.HexUtils.toHex;

public class Instruction {
    private final InstructionExecutor executor;

    private final int position;
    private final byte[] opcode;

    public Instruction(InstructionExecutor executor, int position, byte[] opcode) {
        this.executor = executor;
        this.position = position;
        this.opcode = opcode;
    }

    public String prettyPrint() {
        return "[" + toHex(position + 0x200, 4) + "] " + executor.prettyPrince(opcode);
    }

    public void execute(
            CPU cpu,
            Screen screen,
            Memory memory,
            Keyboard keyboard,
            Timers timers
    ) {
        executor.execute(opcode, cpu, screen, memory, keyboard, timers);
    }
}
