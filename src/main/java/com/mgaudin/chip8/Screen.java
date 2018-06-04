package com.mgaudin.chip8;

import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;

import static com.mgaudin.chip8.HexUtils.toBinary;


@Service
public class Screen {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private final Keyboard keyboard;

    private JFrame window;
    private Canvas canvas;

    @Inject
    public Screen(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public void clear() {
        canvas.clear();
    }

    public boolean draw(byte X, byte Y, byte[] data) {
        canvas.draw(X, Y, data);
        window.getContentPane().repaint();

        return false;
    }

    public void scrollDown(byte numberOfLines) {
        throw new RuntimeException("Not implemented");
    }

    public void turnOn() {
        System.setProperty("java.awt.headless", "false");

        window = new JFrame();

        window.setTitle("Chip8");
        window.setSize(WIDTH, HEIGHT);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new Canvas(WIDTH, HEIGHT);
        window.getContentPane().add(canvas);

        window.setVisible(true);
    }

    public void turnOff() {

    }

    private static class Canvas extends JPanel {
        private static final int REAL_WIDTH = 64;
        private static final int REAL_HEIGHT = 32;

        private final int windowWidth;
        private final int windowHeight;

        private final int xStep;
        private final int yStep;

        private final boolean[][] pixels;

        Canvas(int windowWidth, int windowHeight) {
            this.windowWidth = windowWidth;
            this.windowHeight = windowHeight;
            this.xStep = windowWidth / REAL_WIDTH;
            this.yStep = windowHeight / REAL_HEIGHT;

            pixels = new boolean[REAL_WIDTH][REAL_HEIGHT];
        }

        public boolean draw(byte X, byte Y, byte[] data) {
            for (int y = 0; y < data.length; ++y) {
                boolean[] binaryValue = toBinary(data[y]);

                for (int x = 0; x < 8; ++x) {
                    if (X + x < REAL_WIDTH && Y + y < REAL_HEIGHT) {
                        pixels[X + x][Y + y] ^= binaryValue[x];
                    }
                }
            }

            return false;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (int X = 0; X < REAL_WIDTH; ++X) {
                for (int Y = 0; Y < REAL_HEIGHT; ++Y) {
                    if (pixels[X][Y]) {
                        g.fillRect(X * xStep, Y * yStep, xStep, yStep);
                    }
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(windowWidth, windowHeight);
        }

        public void clear() {
        }
    }
}
