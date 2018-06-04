package com.mgaudin.chip8.instructions;

import com.mgaudin.chip8.*;
import org.springframework.stereotype.Component;

import static com.mgaudin.chip8.HexUtils.toHex;

@Component
public class DrawSprite extends PrioritizedInstructionExecutor {

    @Override
    public boolean matches(byte[] opcode) {
        return opcode[0] == 0xD;
    }

    @Override
    public String prettyPrince(byte[] opcode) {
        return "DRAW (V" + toHex(opcode[1], 1) + ", V" + toHex(opcode[2], 1) + ")[" + toHex(opcode[3], 1) + "]";
    }

    /*
     * Draw a sprite at position VX, VY with N bytes of sprite data starting at the address stored in I
     * Set VF to 01 if any set pixels are changed to unset, and 00 otherwise
     */
    @Override
    public void execute(byte[] opcode, CPU cpu, Screen screen, Memory memory, Keyboard keyboard, Timers timers) {
        boolean pixelsHasBeenUnset = screen.draw(
                memory.getRegister(opcode[1]), memory.getRegister(opcode[2]),
                memory.get(memory.getAddressRegister(), opcode[3])
        );
        if (pixelsHasBeenUnset) {
            memory.setRegister((byte) 0xF, (byte) 1);
        } else {
            memory.setRegister((byte) 0xF, (byte) 0);
        }
    }
}
