package com.mgaudin.chip8;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Timers {
    private byte delayTimer = 0;
    private byte soundTimer = 0;

    public byte getDelayTimer() {
        return delayTimer;
    }

    public void setDelayTimer(byte value) {
        delayTimer = value;
    }

    public void setSoundTimer(byte value) {
        soundTimer = value;
    }

    @Scheduled(fixedDelay = 17L)
    public void decrementeTimers() {
        if (delayTimer > 0) {
            delayTimer--;
        }

        if (soundTimer > 0) {
            soundTimer--;
        }
    }
}
