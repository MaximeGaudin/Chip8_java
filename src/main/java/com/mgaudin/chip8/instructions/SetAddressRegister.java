package com.mgaudin.chip8.instructions;

import com.mgaudin.chip8.*;
import org.springframework.stereotype.Component;

import static com.mgaudin.chip8.HexUtils.toHex;
import static com.mgaudin.chip8.Memory.constant;

@Component
public class SetAddressRegister extends PrioritizedInstructionExecutor {

    @Override
    public boolean matches(byte[] opcode) {
        return opcode[0] == 0xA;
    }

    @Override
    public String prettyPrince(byte[] opcode) {
        return "I = " + toHex(constant(opcode[1], opcode[2], opcode[3]), 3);
    }

    /*
     * Store memory address NNN in register I
     */
    @Override
    public void execute(byte[] opcode, CPU cpu, Screen screen, Memory memory, Keyboard keyboard, Timers timers) {
        memory.setAddressRegister(constant(opcode[1], opcode[2], opcode[3]));
    }
}
