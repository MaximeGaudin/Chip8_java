package com.mgaudin.chip8.instructions;

public abstract class PrioritizedInstructionExecutor implements InstructionExecutor {
    private long matchCounter = 0;

    public void incrementMatchCounter() {
        matchCounter++;
    }

    public long getPriority() {
        return matchCounter;
    }
}
