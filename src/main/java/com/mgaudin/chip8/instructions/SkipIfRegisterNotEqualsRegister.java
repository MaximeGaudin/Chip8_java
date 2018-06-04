package com.mgaudin.chip8.instructions;

import com.mgaudin.chip8.*;
import org.springframework.stereotype.Component;

import static com.mgaudin.chip8.HexUtils.toHex;

@Component
public class SkipIfRegisterNotEqualsRegister extends PrioritizedInstructionExecutor {

    @Override
    public boolean matches(byte[] opcode) {
        return opcode[0] == 0x9 && opcode[3] == 0x0;
    }

    @Override
    public String prettyPrince(byte[] opcode) {
        return "SKIP IF V" + toHex(opcode[1], 1) + " != V" + toHex(opcode[2], 1);
    }

    /*
     * Skip the following instruction if the value of register VX is not equal to the value of register VY
     */
    @Override
    public void execute(byte[] opcode, CPU cpu, Screen screen, Memory memory, Keyboard keyboard, Timers timers) {
        if (memory.getRegister(opcode[1]) != memory.getRegister(opcode[2])) {
            cpu.skipNextInstruction();
        }
    }
}
