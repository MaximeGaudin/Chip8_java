package com.mgaudin.chip8.instructions;

import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;

@Service
public class InstructionBuilder {
    private final List<PrioritizedInstructionExecutor> availableInstructionExecutors;
    private final InstructionExecutor defaultInstructionExecutor = new Missing();

    private int decodingCounter = 0;

    @Inject
    public InstructionBuilder(List<PrioritizedInstructionExecutor> availableInstructionExecutors) {
        this.availableInstructionExecutors = availableInstructionExecutors;
    }

    public Instruction decodeInstruction(int position, byte[] memoryOpcode) {
        performJitOptimizations();

        byte[] opcode = extractNibbles(memoryOpcode);

        for (PrioritizedInstructionExecutor availableInstructionExecutor : availableInstructionExecutors) {
            if (availableInstructionExecutor.matches(opcode)) {
                availableInstructionExecutor.incrementMatchCounter();
                return new Instruction(availableInstructionExecutor, position, opcode);
            }
        }

        return new Instruction(defaultInstructionExecutor, position, opcode);
    }

    private void performJitOptimizations() {
        decodingCounter++;
        if (decodingCounter % 50 == 0) {
            availableInstructionExecutors.sort(Comparator.comparing(PrioritizedInstructionExecutor::getPriority).reversed());
            decodingCounter = 0;
        }
    }

    private byte[] extractNibbles(byte[] data) {
        return new byte[]{
                (byte) ((data[0] & 0xF0) >> 4),
                (byte) (data[0] & 0x0F),
                (byte) ((data[1] & 0xF0) >> 4),
                (byte) (data[1] & 0x0F)
        };
    }
}
