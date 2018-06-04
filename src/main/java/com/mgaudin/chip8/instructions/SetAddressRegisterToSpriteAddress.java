package com.mgaudin.chip8.instructions;

import com.mgaudin.chip8.*;
import org.springframework.stereotype.Component;

import static com.mgaudin.chip8.HexUtils.toHex;

@Component
public class SetAddressRegisterToSpriteAddress extends PrioritizedInstructionExecutor {

    @Override
    public boolean matches(byte[] opcode) {
        return opcode[0] == 0xF && opcode[2] == 0x2 && opcode[3] == 0x9;
    }

    @Override
    public String prettyPrince(byte[] opcode) {
        return "I = &SPRITE [" + toHex(opcode[1], 1) + "]";
    }

    /*
     * Set I to the memory address of the sprite data corresponding to the hexadecimal digit stored in register VX
     */
    @Override
    public void execute(byte[] opcode, CPU cpu, Screen screen, Memory memory, Keyboard keyboard, Timers timers) {
        memory.setAddressRegister(
                memory.getSpriteAddress(memory.getRegister(opcode[1]))
        );
    }
}
