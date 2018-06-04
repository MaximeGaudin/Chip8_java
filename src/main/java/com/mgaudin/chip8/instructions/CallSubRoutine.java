package com.mgaudin.chip8.instructions;

import com.mgaudin.chip8.*;
import org.springframework.stereotype.Component;

import static com.mgaudin.chip8.HexUtils.toHex;
import static com.mgaudin.chip8.Memory.constant;

@Component
public class CallSubRoutine extends PrioritizedInstructionExecutor {

    @Override
    public boolean matches(byte[] opcode) {
        return opcode[0] == 0x2;
    }

    @Override
    public String prettyPrince(byte[] opcode) {
        return "CALL " + toHex(constant(opcode[1], opcode[2], opcode[3]), 3);
    }

    /*
     * Execute subroutine starting at address NNN
     */
    @Override
    public void execute(byte[] opcode, CPU cpu, Screen screen, Memory memory, Keyboard keyboard, Timers timers) {
        cpu.callSubRoutine(constant(opcode[1], opcode[2], opcode[3]));
    }
}
