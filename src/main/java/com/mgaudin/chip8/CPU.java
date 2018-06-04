package com.mgaudin.chip8;

import com.mgaudin.chip8.instructions.Instruction;
import com.mgaudin.chip8.instructions.InstructionBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Stack;

@Slf4j
@Component
public class CPU {
    private static final int OPCODE_LENGTH = 2;

    private final Screen screen;
    private final Memory memory;
    private final Keyboard keyboard;
    private final Timers timers;

    private final InstructionBuilder instructionBuilder;

    private short programCounter = Memory.PROGRAM_SPACE_ADDRESS;
    private Stack<Short> stack = new Stack<>();
    private boolean started = false;

    @Inject
    public CPU(
            Screen screen,
            Memory memory,
            Keyboard keyboard,
            Timers timers,
            InstructionBuilder instructionBuilder
    ) {
        this.screen = screen;
        this.memory = memory;
        this.keyboard = keyboard;
        this.timers = timers;
        this.instructionBuilder = instructionBuilder;
    }

    @Scheduled(fixedDelay = 2)
    void tick() {
        if (!started) {
            return;
        }

        try {
            executeCurrentInstruction();
        } catch (Exception e) {
            log.error("The system encountered and error. Program execution aborded", e);
            stop();
        }
    }

    public void start() {
        programCounter = Memory.PROGRAM_SPACE_ADDRESS;
        stack = new Stack<>();

        screen.turnOn();

        started = true;
    }

    public void stop() {
        started = false;

        screen.turnOff();
    }

    private void executeCurrentInstruction() {
        byte[] opcode = memory.get(programCounter, (byte) 2);
        Instruction instruction = instructionBuilder.decodeInstruction(programCounter, opcode);
        programCounter += OPCODE_LENGTH;

        instruction
                .execute(
                        this,
                        screen,
                        memory,
                        keyboard,
                        timers
                );
    }

    public void jump(short address) {
        programCounter = address;
    }

    public void skipNextInstruction() {
        programCounter += OPCODE_LENGTH;
    }

    public void callSubRoutine(short address) {
        stack.push(programCounter);
        programCounter = address;
    }

    public void returnFromSubRoutine() {
        programCounter = stack.pop();
    }
}
