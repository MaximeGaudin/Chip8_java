package com.mgaudin.chip8.instructions;

import com.mgaudin.chip8.*;
import org.springframework.stereotype.Component;

import static com.mgaudin.chip8.HexUtils.toHex;

@Component
public class CopyMemoryToRegisters extends PrioritizedInstructionExecutor {

    @Override
    public boolean matches(byte[] opcode) {
        return opcode[0] == 0xF && opcode[2] == 0x6 && opcode[3] == 0x5;
    }

    @Override
    public String prettyPrince(byte[] opcode) {
        return "COPY [I], V0 -> " + toHex(opcode[1], 1) + "";
    }

    /*
     * Fill registers V0 to VX inclusive with the values stored in memory starting at address I
     * I is set to I + X + 1 after operation
     */
    @Override
    public void execute(byte[] opcode, CPU cpu, Screen screen, Memory memory, Keyboard keyboard, Timers timers) {
        for (int i = 0; i <= opcode[1]; ++i) {
            memory.setRegister(
                    (byte) i,
                    memory.get((short) (memory.getAddressRegister() + i), (byte) 1)[0]
            );
        }

        memory.setAddressRegister(
                (short) (memory.getAddressRegister() + opcode[1] + 1)
        );
    }
}
