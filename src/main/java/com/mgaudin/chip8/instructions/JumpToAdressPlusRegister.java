package com.mgaudin.chip8.instructions;

import com.mgaudin.chip8.*;
import org.springframework.stereotype.Component;

import static com.mgaudin.chip8.HexUtils.toHex;
import static com.mgaudin.chip8.Memory.constant;

@Component
public class JumpToAdressPlusRegister extends PrioritizedInstructionExecutor {

    @Override
    public boolean matches(byte[] opcode) {
        return opcode[0] == 0xB;
    }

    @Override
    public String prettyPrince(byte[] opcode) {
        return "JMP " + toHex(constant(opcode[1], opcode[2], opcode[3]), 3) + " + V0";
    }

    /*
     * Jump to address NNN + V0
     */
    @Override
    public void execute(byte[] opcode, CPU cpu, Screen screen, Memory memory, Keyboard keyboard, Timers timers) {
        cpu.jump(
                (byte) (
                        constant(opcode[1], opcode[2], opcode[3])
                                + memory.getRegister((byte) 0)
                )
        );
    }
}
