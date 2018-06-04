package com.mgaudin.chip8.instructions;

import com.mgaudin.chip8.*;
import com.mgaudin.chip8.utils.BCD;
import org.springframework.stereotype.Component;

import static com.mgaudin.chip8.HexUtils.toHex;

@Component
public class SetMemoryToBCD extends PrioritizedInstructionExecutor {

    @Override
    public boolean matches(byte[] opcode) {
        return opcode[0] == 0xF && opcode[2] == 0x3 && opcode[3] == 0x3;
    }

    @Override
    public String prettyPrince(byte[] opcode) {
        return "MEM[I,I+2] = BCD(V" + toHex(opcode[1], 1) + ")";
    }

    /*
     * Store the binary-coded decimal equivalent of the value stored in register VX at addresses I, I+1, and I+2
     */
    @Override
    public void execute(byte[] opcode, CPU cpu, Screen screen, Memory memory, Keyboard keyboard, Timers timers) {
        byte[] bcdEquivalent = BCD.getBCDEquivalent(memory.getRegister(opcode[1]));

        memory.set(memory.getAddressRegister(), bcdEquivalent[0]);
        memory.set((short) (memory.getAddressRegister() + 1), bcdEquivalent[1]);
        memory.set((short) (memory.getAddressRegister() + 2), bcdEquivalent[2]);
    }
}
