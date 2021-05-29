import com.group22.characters.CharacterEntity;
import com.group22.characters.MainCharacter;
import com.group22.moveEnemy;
import com.group22.world.world;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

// Tests the moveEnemy class
public class moveEnemyTest {
    int TILE_SIZE = 25;
    String world = "10 5" + "\n" + "1 2" + "\n" + "2 1" + "\n\n" + "1 1 1 1 1 1 1 1 1 1" + "\n" + "1 0 0 1 2 3 4 0 0 1" + "\n" + "1 0 0 0 0 0 0 0 0 1" + "\n" + "1 0 0 0 0 0 0 0 0 1" + "\n" + "1 1 1 1 1 1 1 1 1 1";

    @Test
    public void moveLeft() throws IOException {
        world mainWorld = new world(world);
        MainCharacter player = new MainCharacter(TILE_SIZE*2,TILE_SIZE*2,TILE_SIZE,TILE_SIZE);
        CharacterEntity enemy = new CharacterEntity(TILE_SIZE*4,TILE_SIZE*2,TILE_SIZE,TILE_SIZE);
        moveEnemy move = new moveEnemy(player, mainWorld, enemy, TILE_SIZE);
        move.run();
        assertEquals(TILE_SIZE*3, enemy.getX());
    }

    @Test
    public void moveRight() throws IOException {
        world mainWorld = new world(world);
        MainCharacter player = new MainCharacter(TILE_SIZE*4,TILE_SIZE*2,TILE_SIZE,TILE_SIZE);
        CharacterEntity enemy = new CharacterEntity(TILE_SIZE*2,TILE_SIZE*2,TILE_SIZE,TILE_SIZE);
        moveEnemy move = new moveEnemy(player, mainWorld, enemy, TILE_SIZE);
        move.run();
        assertEquals(TILE_SIZE*3, enemy.getX());
    }

    @Test
    public void moveUp() throws IOException {
        world mainWorld = new world(world);
        MainCharacter player = new MainCharacter(TILE_SIZE*2,TILE_SIZE*3,TILE_SIZE,TILE_SIZE);
        CharacterEntity enemy = new CharacterEntity(TILE_SIZE*2,TILE_SIZE,TILE_SIZE,TILE_SIZE);
        moveEnemy move = new moveEnemy(player, mainWorld, enemy, TILE_SIZE);
        move.run();
        assertEquals(TILE_SIZE*2, enemy.getY());
    }

    @Test
    public void moveDown() throws IOException {
        world mainWorld = new world(world);
        MainCharacter player = new MainCharacter(TILE_SIZE*2,TILE_SIZE,TILE_SIZE,TILE_SIZE);
        CharacterEntity enemy = new CharacterEntity(TILE_SIZE*2,TILE_SIZE*3,TILE_SIZE,TILE_SIZE);
        moveEnemy move = new moveEnemy(player, mainWorld, enemy, TILE_SIZE);
        move.run();
        assertEquals(TILE_SIZE*2, enemy.getY());
    }
}
