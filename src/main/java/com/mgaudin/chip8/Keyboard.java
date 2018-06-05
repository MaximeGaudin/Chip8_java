package com.mgaudin.chip8;

import org.springframework.stereotype.Service;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import static org.awaitility.Awaitility.await;

@Service
public class Keyboard implements KeyListener {
    private final Map<Integer, Byte> keycodeMap = new HashMap<>();
    private final boolean[] pressedKeys = new boolean[16];
    private Byte lastKeyPressed;

    private Keyboard() {
        keycodeMap.put(49, (byte) 0x01);
        keycodeMap.put(50, (byte) 0x02);
        keycodeMap.put(51, (byte) 0x03);
        keycodeMap.put(52, (byte) 0x0C);

        keycodeMap.put(81, (byte) 0x04);
        keycodeMap.put(87, (byte) 0x05);
        keycodeMap.put(69, (byte) 0x06);
        keycodeMap.put(82, (byte) 0x0D);

        keycodeMap.put(65, (byte) 0x07);
        keycodeMap.put(83, (byte) 0x08);
        keycodeMap.put(68, (byte) 0x09);
        keycodeMap.put(70, (byte) 0x0E);

        keycodeMap.put(90, (byte) 0x0A);
        keycodeMap.put(88, (byte) 0x00);
        keycodeMap.put(67, (byte) 0x0B);
        keycodeMap.put(86, (byte) 0x0F);
    }

    public boolean isPressed(byte keyCode) {
        return pressedKeys[keyCode];
    }

    public byte blockUntilKeyPress() {
        lastKeyPressed = null;
        await()
                .until(() -> lastKeyPressed != null);
        return lastKeyPressed;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        // NOTHING
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        pressedKeys[keycodeMap.get(keyEvent.getKeyCode())] = true;
        lastKeyPressed = keycodeMap.get(keyEvent.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        pressedKeys[keycodeMap.get(keyEvent.getKeyCode())] = false;
    }
}
