package com.mgaudin.chip8.instructions;

import com.mgaudin.chip8.*;
import org.springframework.stereotype.Component;

import static com.mgaudin.chip8.HexUtils.toHex;
import static com.mgaudin.chip8.Memory.constant;

@Component
public class SkipIfRegisterNotEqualsConstant extends PrioritizedInstructionExecutor {

    @Override
    public boolean matches(byte[] opcode) {
        return opcode[0] == 0x4;
    }

    @Override
    public String prettyPrince(byte[] opcode) {
        return "SKIP IF V" + toHex(opcode[1], 1) + " != " + toHex(constant(opcode[2], opcode[3]), 2);
    }

    /*
     * Skip the following instruction if the value of register VX is not equal to NN
     */
    @Override
    public void execute(byte[] opcode, CPU cpu, Screen screen, Memory memory, Keyboard keyboard, Timers timers) {
        if (memory.getRegister(opcode[1]) != constant(opcode[2], opcode[3])) {
            cpu.skipNextInstruction();
        }
    }
}
