package worldTest;

import com.group22.world.world;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

// Tests the worldLoader Class
public class worldLoaderTest {
    @Test
    public void loadFileAsString() throws IOException {
        world testWorld = new world("10 5" + "\n" + "1 2" + "\n" + "2 1" + "\n\n" + "1 1 1 1 1 1 1 1 1 1" + "\n" + "1 0 0 1 2 3 4 0 0 1" + "\n" + "1 0 0 0 0 0 0 0 0 1" + "\n" + "1 0 0 0 0 0 0 0 0 1" + "\n" + "1 1 1 1 1 1 1 1 1 1");
        String properString = "10 5" + "\n" + "1 2" + "\n" + "2 1" + "\n\n" + "1 1 1 1 1 1 1 1 1 1" + "\n" + "1 0 0 1 2 3 4 0 0 1" + "\n" + "1 0 0 0 0 0 0 0 0 1" + "\n" + "1 0 0 0 0 0 0 0 0 1" + "\n" + "1 1 1 1 1 1 1 1 1 1";
        String[] properArray = properString.split("\\s+");
        int[] intArray = new int[testWorld.getWidth() * testWorld.getHeight()];

        for(int i = 0; i < properArray.length - 6; i++) {
            intArray[i] = Integer.parseInt(properArray[i + 6]);
        }

        for (int i = 0; i < testWorld.getHeight(); i++) {
            for (int j = 0; j < testWorld.getWidth(); j++) {
                assertEquals(testWorld.getTile(j, i), intArray[j + i * testWorld.getWidth()]);
            }
        }
    }
}
