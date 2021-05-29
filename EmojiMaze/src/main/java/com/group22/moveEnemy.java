package com.group22;

import com.group22.characters.CharacterEntity;
import com.group22.characters.MainCharacter;
import com.group22.world.world;
import javafx.application.Platform;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TimerTask;

// Runs enemy movement every period of time
// Uses breadth first search to find shortest route to player
public class moveEnemy extends TimerTask {
    private final MainCharacter mainCharacter;
    private final world mainWorld;
    private final CharacterEntity enemy;
    private final int TILE_SIZE;

    public moveEnemy(MainCharacter mainCharacter, world mainWorld, CharacterEntity enemy, int TILE_SIZE) {
        this.mainCharacter = mainCharacter;
        this.mainWorld = mainWorld;
        this.enemy = enemy;
        this.TILE_SIZE = TILE_SIZE;
    }

    public void run() {
        // Save main characters current position
        int x = mainCharacter.getX() / TILE_SIZE;
        int y = mainCharacter.getY() / TILE_SIZE;

        // Create array to see what tiles have been explored by the algorithm and initialize starting position
        boolean[][] explored = new boolean[mainWorld.getWidth()][mainWorld.getHeight()];
        explored[x][y] = true;

        // Create queue for holding explored tiles in coordinates
        class coordinate {
            int x;
            int y;

            coordinate(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        // Add first starting tile
        Queue<coordinate> breadthFirst = new LinkedList<>();
        breadthFirst.add(new coordinate(x, y));

        // While final enemy position is not found search (duh)
        while (true) {
            if (mainCharacter.getX() == enemy.getX() && mainCharacter.getY() == enemy.getY()) {
                System.out.println("Array Empty");
                break;
            }

            // explore the four directions from the element at the start of queue (if not already explored)
            if ((mainWorld.getTile(breadthFirst.peek().x-1,breadthFirst.peek().y) != 1) && (!explored[breadthFirst.peek().x-1][breadthFirst.peek().y])) {
                if (breadthFirst.peek().x-1 == enemy.getX()/ TILE_SIZE && breadthFirst.peek().y == enemy.getY()/ TILE_SIZE) {
                    enemy.setX(breadthFirst.peek().x* TILE_SIZE);
                    enemy.setY(breadthFirst.peek().y* TILE_SIZE);
                    break;
                }
                explored[x-1][y] = true;
                breadthFirst.add(new coordinate(x-1, y));
            }
            if (mainWorld.getTile(breadthFirst.peek().x+1,breadthFirst.peek().y) != 1 && (!explored[breadthFirst.peek().x+1][breadthFirst.peek().y])) {
                if (breadthFirst.peek().x+1 == enemy.getX()/ TILE_SIZE && breadthFirst.peek().y == enemy.getY()/ TILE_SIZE) {
                    enemy.setX(breadthFirst.peek().x* TILE_SIZE);
                    enemy.setY(breadthFirst.peek().y* TILE_SIZE);
                    break;
                }
                explored[x+1][y] = true;
                breadthFirst.add(new coordinate(x+1, y));
            }
            if (mainWorld.getTile(breadthFirst.peek().x,breadthFirst.peek().y-1) != 1 && (!explored[breadthFirst.peek().x][breadthFirst.peek().y-1])) {
                if (breadthFirst.peek().x == enemy.getX()/ TILE_SIZE && breadthFirst.peek().y-1 == enemy.getY()/ TILE_SIZE) {
                    enemy.setX(breadthFirst.peek().x* TILE_SIZE);
                    enemy.setY(breadthFirst.peek().y* TILE_SIZE);
                    break;
                }
                explored[x][y-1] = true;
                breadthFirst.add(new coordinate(x, y-1));
            }
            if (mainWorld.getTile(breadthFirst.peek().x,breadthFirst.peek().y+1) != 1 && (!explored[breadthFirst.peek().x][breadthFirst.peek().y+1])) {
                if (breadthFirst.peek().x == enemy.getX()/ TILE_SIZE && breadthFirst.peek().y+1 == enemy.getY()/ TILE_SIZE) {
                    enemy.setX(breadthFirst.peek().x* TILE_SIZE);
                    enemy.setY(breadthFirst.peek().y* TILE_SIZE);
                    break;
                }
                explored[x][y+1] = true;
                breadthFirst.add(new coordinate(x, y+1));
            }

            breadthFirst.remove();

            x = breadthFirst.peek().x;
            y = breadthFirst.peek().y;
        }

        if (mainCharacter.getX() == enemy.getX() && mainCharacter.getY() == enemy.getY()) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    JavaFX.gameOver();
                }
            });
        }
    }
}
