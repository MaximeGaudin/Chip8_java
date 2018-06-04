package com.mgaudin.chip8.instructions;

import com.mgaudin.chip8.*;
import org.springframework.stereotype.Component;

import static com.mgaudin.chip8.HexUtils.toHex;

@Component
public class SetRegisterToRegister extends PrioritizedInstructionExecutor {

    @Override
    public boolean matches(byte[] opcode) {
        return opcode[0] == 0x8 && opcode[3] == 0x0;
    }

    @Override
    public String prettyPrince(byte[] opcode) {
        return "V" + toHex(opcode[1], 1) + " = V" + toHex(opcode[2], 1);
    }

    /*
     * Store the value of register VY in register VX
     */
    @Override
    public void execute(byte[] opcode, CPU cpu, Screen screen, Memory memory, Keyboard keyboard, Timers timers) {
        memory.setRegister(opcode[1], memory.getRegister(opcode[2]));
    }
}
