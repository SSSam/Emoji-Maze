package CharacterTest;

import com.group22.characters.MainCharacter;
import org.junit.Test;

import com.group22.world.world;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class characterMoveTest {
    world mainWorld = new world("8 6" + "\n" + "1 2" + "\n" + "2 1" + "\n" + "\n" + "1 1 1 1 1 1 1 1" + "\n" + "1 0 0 3 0 0 0 1" + "\n" + "1 4 1 0 1 1 0 1" + "\n" + "1 0 1 0 1 1 0 1" + "\n" + "1 0 0 0 0 0 2 1" + "\n" + "1 1 1 1 1 1 1 1");

    MainCharacter mainChar = new MainCharacter(mainWorld.getSpawnX(),mainWorld.getSpawnY(),25,25);

    public characterMoveTest() throws IOException {
    }

    //Test the character's spawn
    @Test
    public void characterSpawn() {
        assertEquals(1, mainChar.getX());
        assertEquals(2, mainChar.getY());

    }
    //Check the character's recognition between barriers and rewards
    @Test
    public void characterMove() {

    }

}
