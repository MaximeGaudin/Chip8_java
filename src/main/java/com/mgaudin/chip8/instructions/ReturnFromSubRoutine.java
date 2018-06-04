package com.mgaudin.chip8.instructions;

import com.mgaudin.chip8.*;
import org.springframework.stereotype.Component;

import static com.mgaudin.chip8.Memory.constant;

@Component
public class ReturnFromSubRoutine extends PrioritizedInstructionExecutor {

    @Override
    public boolean matches(byte[] opcode) {
        return constant(opcode[0], opcode[1], opcode[2], opcode[3]) == 0x00EE;
    }

    @Override
    public String prettyPrince(byte[] opcode) {
        return "RET";
    }

    /*
     * Return from a subroutine
     */
    @Override
    public void execute(byte[] opcode, CPU cpu, Screen screen, Memory memory, Keyboard keyboard, Timers timers) {
        cpu.returnFromSubRoutine();
    }
}
