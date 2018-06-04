package com.mgaudin.chip8;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CartridgeReader {
    private final String romPath;

    @Inject
    public CartridgeReader(@Value("${rom.path}") String romPath) {
        this.romPath = romPath;
    }

    Cartridge read(String romName) throws IOException {
        Path fileLocation = Paths.get(romPath + "/" + romName);
        return new Cartridge(Files.readAllBytes(fileLocation));
    }
}
