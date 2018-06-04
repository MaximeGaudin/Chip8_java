package com.mgaudin.chip8;

import com.mgaudin.chip8.instructions.Instruction;
import com.mgaudin.chip8.instructions.InstructionBuilder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Disassembler {
    private final InstructionBuilder instructionBuilder;

    @Inject
    public Disassembler(InstructionBuilder instructionBuilder) {
        this.instructionBuilder = instructionBuilder;
    }

    public String disassemble(Cartridge cartridge) {
        List<Instruction> instructions = new ArrayList<>();
        for (int i = 0; i < cartridge.getContent().length; i += 2) {
            Instruction instruction = instructionBuilder.decodeInstruction(
                    i,
                    new byte[]{cartridge.getContent()[i], cartridge.getContent()[i + 1]}
            );
            instructions.add(instruction);
        }


        return String.join(
                "\n",
                instructions.stream()
                        .map(Instruction::prettyPrint)
                        .collect(Collectors.toList())
        );
    }
}
