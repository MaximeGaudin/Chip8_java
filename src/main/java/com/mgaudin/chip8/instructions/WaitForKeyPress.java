package com.mgaudin.chip8.instructions;

import com.mgaudin.chip8.*;
import org.springframework.stereotype.Component;

import static com.mgaudin.chip8.HexUtils.toHex;

@Component
public class WaitForKeyPress extends PrioritizedInstructionExecutor {

    @Override
    public boolean matches(byte[] opcode) {
        return opcode[0] == 0xF && opcode[2] == 0x0 && opcode[3] == 0xA;
    }

    @Override
    public String prettyPrince(byte[] opcode) {
        return "NEXT KEYPRESS IN V" + toHex(opcode[1], 1);
    }

    /*
     * Wait for a keypress and store the result in register VX
     */
    @Override
    public void execute(byte[] opcode, CPU cpu, Screen screen, Memory memory, Keyboard keyboard, Timers timers) {
        memory.setRegister(opcode[1], keyboard.blockUntilKeyPress());
    }
}
