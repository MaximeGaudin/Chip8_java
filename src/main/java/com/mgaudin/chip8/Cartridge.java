package com.mgaudin.chip8;

class Cartridge {
    private final byte[] content;

    public Cartridge(byte[] content) {
        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }
}
