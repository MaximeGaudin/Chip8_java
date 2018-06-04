package com.mgaudin.chip8.instructions;

import com.mgaudin.chip8.*;
import org.springframework.stereotype.Component;

import static com.mgaudin.chip8.Memory.constant;

@Component
public class NotImplemented extends PrioritizedInstructionExecutor {
    @Override
    public boolean matches(byte[] opcode) {
        return opcode[0] == 0x0
                && opcode[2] != 0xC
                && constant(opcode[0], opcode[1], opcode[2], opcode[3]) != 0x00FB
                && constant(opcode[0], opcode[1], opcode[2], opcode[3]) != 0x00FC
                && constant(opcode[0], opcode[1], opcode[2], opcode[3]) != 0x00FD
                && constant(opcode[0], opcode[1], opcode[2], opcode[3]) != 0x00FE
                && constant(opcode[0], opcode[1], opcode[2], opcode[3]) != 0x00FF
                && constant(opcode[0], opcode[1], opcode[2], opcode[3]) != 0x00E0
                && constant(opcode[0], opcode[1], opcode[2], opcode[3]) != 0x00EE;
    }

    @Override
    public String prettyPrince(byte[] opcode) {
        return "GRAPHIC [" + constant(opcode[0], opcode[1]) + "," + constant(opcode[2], opcode[3]) + "]";
    }

    @Override
    public void execute(byte[] opcode, CPU cpu, Screen screen, Memory memory, Keyboard keyboard, Timers timers) {
        // Nothing
    }
}
