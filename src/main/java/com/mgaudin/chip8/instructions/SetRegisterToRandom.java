package com.mgaudin.chip8.instructions;

import com.mgaudin.chip8.*;
import org.springframework.stereotype.Component;

import java.util.Random;

import static com.mgaudin.chip8.HexUtils.toHex;
import static com.mgaudin.chip8.Memory.constant;

@Component
public class SetRegisterToRandom extends PrioritizedInstructionExecutor {
    private static final Random randomGenerator = new Random();

    @Override
    public boolean matches(byte[] opcode) {
        return opcode[0] == 0xC;
    }

    @Override
    public String prettyPrince(byte[] opcode) {
        return "V" + toHex(opcode[1], 1) + " = RND & " + toHex(constant(opcode[2], opcode[3]), 2);
    }

    /*
     * Set VX to a random number with a mask of NN
     */
    @Override
    public void execute(byte[] opcode, CPU cpu, Screen screen, Memory memory, Keyboard keyboard, Timers timers) {
        int randomValue = randomGenerator.nextInt(255);

        memory.setRegister(
                opcode[1],
                (byte) (randomValue & constant(opcode[2], opcode[3]))
        );
    }
}
