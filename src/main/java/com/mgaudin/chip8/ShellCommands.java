package com.mgaudin.chip8;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.inject.Inject;
import java.io.IOException;

@ShellComponent
public class ShellCommands {
    private final CartridgeReader cartridgeReader;
    private final Memory memory;
    private final CPU cpu;

    private final Disassembler disassembler;

    @Inject
    public ShellCommands(
            CartridgeReader cartridgeReader,
            Memory memory,
            CPU cpu,
            Disassembler disassembler
    ) {
        this.cartridgeReader = cartridgeReader;
        this.memory = memory;
        this.cpu = cpu;
        this.disassembler = disassembler;
    }

    @ShellMethod("Disassemble the provided ROM")
    public String disassemble(String romPath) throws IOException {
        return disassembler.disassemble(
                cartridgeReader
                        .read(romPath)
        );
    }

    @ShellMethod("Run the provided ROM")
    public String run(String romPath) throws IOException {
        memory.loadCartridge(cartridgeReader.read(romPath));
        cpu.start();

        return "Running...";
    }
}
