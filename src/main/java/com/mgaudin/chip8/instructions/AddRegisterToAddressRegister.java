package com.mgaudin.chip8.instructions;

import com.mgaudin.chip8.*;
import org.springframework.stereotype.Component;

import static com.mgaudin.chip8.HexUtils.toHex;

@Component
public class AddRegisterToAddressRegister extends PrioritizedInstructionExecutor {

    @Override
    public boolean matches(byte[] opcode) {
        return opcode[0] == 0xF && opcode[2] == 0x1 && opcode[3] == 0xE;
    }

    @Override
    public String prettyPrince(byte[] opcode) {
        return "I += V" + toHex(opcode[1], 1);
    }

    /*
     * Add the value stored in register VX to register I
     */
    @Override
    public void execute(byte[] opcode, CPU cpu, Screen screen, Memory memory, Keyboard keyboard, Timers timers) {
        memory.setAddressRegister(
                (short) (memory.getAddressRegister() + memory.getRegister(opcode[1]))
        );
    }
}
