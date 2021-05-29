package worldTest;

import com.group22.world.world;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.assertEquals;

// Tests the world class
public class worldTest {
    world mainWorld = new world("10 5" + "\n" + "1 2" + "\n" + "2 1" + "\n\n" + "1 1 1 1 1 1 1 1 1 1" + "\n" + "1 0 0 1 2 3 4 0 0 1" + "\n" + "1 0 0 0 0 0 0 0 0 1" + "\n" + "1 0 0 0 0 0 0 0 0 1" + "\n" + "1 1 1 1 1 1 1 1 1 1");

    public worldTest() throws IOException {
    }

    //Testing the getters and setters
    @Test
    public void getTile() {
        assertEquals(1, mainWorld.getTile(0,0));
    }

    @Test
    public void setTile() {
        mainWorld.setTile(9,0,1);
        assertEquals(1,mainWorld.getTile(9,0));
    }

    @Test
    public void getWidth() {
        assertEquals(10, mainWorld.getWidth());
    }

    @Test
    public void getHeight() {
        assertEquals(5, mainWorld.getHeight());
    }

    @Test
    public void getSpawnX() {
        assertEquals(1, mainWorld.getSpawnX());
    }

    @Test
    public void getSpawnY() {
        assertEquals(2, mainWorld.getSpawnY());
    }

    @Test
    public void getEspawnX() {
        assertEquals(2, mainWorld.getEspawnX());
    }

    @Test
    public void getEspawnY() {
        assertEquals(1, mainWorld.getEspawnY());
    }
}