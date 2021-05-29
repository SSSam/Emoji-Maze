package bonusSpawnTest;

import org.junit.Test;
import com.group22.world.world;
import java.io.IOException;
import static org.junit.Assert.assertEquals;

public class BonusSpawnTest {

    world mainWorld = new world("5 5" + "\n" + "1 2" + "\n" + "2 1" + "\n" + "\n" + "1 1 1 1 1" + "\n" + "1 3 5 3 1" + "\n" + "1 3 3 3 1" + "\n" + "1 5 5 5 1" + "\n" + "1 1 1 1 1");

    public BonusSpawnTest() throws IOException {
    }

    @Test
    public void BonusExist() {
        assertEquals(3, mainWorld.getTile(2,2));
    
    }

    @Test
    public void BonusDisappear() {
        assertEquals(5, mainWorld.getTile(2,3));

    }

    @Test
    public void BonusRender() {
        int i=1;
        int j=3;
        assertEquals(5, mainWorld.getTile(i,j));
        switch(mainWorld.getTile(i,j)) {
            case 3:
                mainWorld.setTile(i, j, 5);
                break;
            case 5:
                mainWorld.setTile(i, j, 3);
                break;
        }
        assertEquals(3, mainWorld.getTile(i,j));

    }


    
}
