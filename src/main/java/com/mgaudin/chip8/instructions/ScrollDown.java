package com.mgaudin.chip8.instructions;

import com.mgaudin.chip8.*;
import org.springframework.stereotype.Component;

@Component
public class ScrollDown extends PrioritizedInstructionExecutor {

    @Override
    public boolean matches(byte[] opcode) {
        return opcode[0] == 0x0 && opcode[1] == 0x0 && opcode[2] == 0xC;
    }

    @Override
    public String prettyPrince(byte[] opcode) {
        return "SCROLLDOWN " + opcode[3];
    }

    @Override
    public void execute(byte[] opcode, CPU cpu, Screen screen, Memory memory, Keyboard keyboard, Timers timers) {
        screen.scrollDown(opcode[3]);
    }
}
