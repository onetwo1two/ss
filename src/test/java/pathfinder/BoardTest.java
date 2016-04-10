package pathfinder;


import org.junit.Assert;
import org.junit.Test;

public class BoardTest {

    @Test
    public void testFindPath() {
        int[][] matrix = {{-2, 0, 0,  0,  0, 0,  0},
                           {0, 0, 0, -1, -1, 0,  0},
                           {0, 0, 0, -1, -1, 0,  0},
                           {0, 0, 0, -1, -1, 0,  0},
                           {0, 0, 0, -1, -1, 0, -3}};
        int[][] path = new Board().findPath(matrix);
        Assert.assertEquals(5, path.length);
    }
}
