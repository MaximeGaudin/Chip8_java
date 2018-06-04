package com.mgaudin.chip8.instructions;

import com.mgaudin.chip8.*;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.mgaudin.chip8.HexUtils.toHex;

@Component
public class SkipIfKeyPressed extends PrioritizedInstructionExecutor {

    @Override
    public boolean matches(byte[] opcode) {
        return opcode[0] == 0xE && opcode[2] == 0x9 && opcode[3] == 0xE;
    }

    @Override
    public String prettyPrince(byte[] opcode) {
        return "SKIP IF KEY[V" + toHex(opcode[1], 1) + "] PRESSED";
    }

    /*
     *  Skip the following instruction if the key corresponding to the hex value currently stored in register VX is pressed
     */
    @Override
    public void execute(byte[] opcode, CPU cpu, Screen screen, Memory memory, Keyboard keyboard, Timers timers) {
        if (keyboard.isPressed(memory.getRegister(opcode[1]))) {
            cpu.skipNextInstruction();
        }
    }
}
