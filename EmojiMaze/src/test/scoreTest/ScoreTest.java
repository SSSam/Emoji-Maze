package scoreTest;

import org.junit.Test;
import com.group22.score.Score;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// Tests the Score class
public class ScoreTest {
    @Test
    public void getScore() {
        Score s = new Score();
        assertEquals(s.getScore(), 0);
    }

    @Test
    public void punishment() {
        Score s = new Score();
        s.punishment();
        assertEquals(s.getScore(), -5);
    }

    @Test
    public void regular() {
        Score s = new Score();
        s.regular();
        assertEquals(3, s.getScore());
    }

    @Test
    public void bonus() {
        Score s = new Score();
        s.bonus();
        assertEquals(5, s.getScore());
    }

    @Test
    public void setScore() {
        Score s = new Score();
        s.setScore(10);
        assertEquals(10, s.getScore());
    }

    @Test
    public void belowZero() {
        Score s = new Score();
        s.setScore(-10);
        assertTrue(s.belowZero());
    }

    @Test
    public void getAllReward() {
        Score s = new Score();
        s.setRewardNumber(30);
        assertTrue(s.getAllReward());
    }
}