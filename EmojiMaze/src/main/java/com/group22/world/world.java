package com.group22.world;

import java.io.IOException;

public class world {
    public int width;
    private int height;
    private int[][] tiles;
    private int spawnX;
    private int spawnY;
    private int EspawnX;
    private int EspawnY;

    public world(String path) throws IOException {

        // loads world into array split by number
        String[] tokens = path.split("\\s+");

        // set first 2 numbers to width and height of world
        width = Integer.parseInt(tokens[0]);
        height = Integer.parseInt(tokens[1]);

        // set second 2 numbers to starting position
        spawnX = Integer.parseInt(tokens[2]);
        spawnY = Integer.parseInt(tokens[3]);

        // set third 2 numbers to starting enemy position
        EspawnX = Integer.parseInt(tokens[4]);
        EspawnY = Integer.parseInt(tokens[5]);

        // set the rest of the numbers into a 2d array called tiles
        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Integer.parseInt(tokens[(x + y * width) + 6]);
            }
        }
    }

    // Getters/Setters
    public void setTile(int x, int y, int n) {
        tiles[x][y] = n;
    }

    public int getTile(int x, int y) {
        return tiles[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSpawnX() {
        return spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }

    public int getEspawnX() {
        return EspawnX;
    }

    public int getEspawnY() {
        return EspawnY;
    }
}
