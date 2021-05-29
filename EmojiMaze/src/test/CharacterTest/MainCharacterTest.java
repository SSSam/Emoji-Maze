package CharacterTest;

import com.group22.characters.MainCharacter;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

// Tests MainCharacterEntity class
public class MainCharacterTest {
    MainCharacter character = new MainCharacter(2,5,7,12);

    public MainCharacterTest() throws IOException {
    }

    //Testing the getters and setters
    @Test
    public void getX() {
        assertEquals(2, character.getX());
    }

    @Test
    public void getY() {
        assertEquals(5, character.getY());
    }

    @Test
    public void setX() {
        character.setX(10);
        assertEquals(10, character.getX());
    }

    @Test
    public void setY() {
        character.setY(22);
        assertEquals(22, character.getY());
    }

    @Test
    public void getWidth() {
        assertEquals(7, character.getWidth());
    }

    @Test
    public void getHeight() {
        assertEquals(12, character.getHeight());
    }
}