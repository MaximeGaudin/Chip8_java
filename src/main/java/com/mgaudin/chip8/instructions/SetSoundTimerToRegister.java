package com.mgaudin.chip8.instructions;

import com.mgaudin.chip8.*;
import org.springframework.stereotype.Component;

import static com.mgaudin.chip8.HexUtils.toHex;

@Component
public class SetSoundTimerToRegister extends PrioritizedInstructionExecutor {

    @Override
    public boolean matches(byte[] opcode) {
        return opcode[0] == 0xF && opcode[2] == 0x1 && opcode[3] == 0x8;
    }

    @Override
    public String prettyPrince(byte[] opcode) {
        return "SOUND = V" + toHex(opcode[1], 1);
    }

    /*
     * Set the delay timer to the value of register VX
     */
    @Override
    public void execute(byte[] opcode, CPU cpu, Screen screen, Memory memory, Keyboard keyboard, Timers timers) {
        timers.setSoundTimer(memory.getRegister(opcode[1]));
    }
}
