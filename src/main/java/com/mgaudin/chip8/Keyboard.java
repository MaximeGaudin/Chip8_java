package com.mgaudin.chip8;

import org.springframework.stereotype.Service;

@Service
public class Keyboard {
    public boolean isPressed(byte keyCode) {
        return false;
    }

    public byte blockUntilKeyPress() {
        throw new RuntimeException("Not implemented");
    }
}
