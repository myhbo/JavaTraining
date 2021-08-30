import org.junit.Test;

import static org.junit.Assert.*;

public class ModelTest {
    Model modelInstance = new Model();
    @org.junit.Test
    public void getRandomNumber() {
        int randomInt;
        for (int i = 0; i < 100000; i++) {
            randomInt = modelInstance.getRandomNumber(0,100);
            boolean inRange = (randomInt > 0 && randomInt < 100);
            assertTrue(inRange);
        }
    }

    @Test
    public void boundaryTest() {
        boolean isValidBoundaries = (modelInstance.getMinBoundary() < modelInstance.getMaxBoundary());
        assertTrue(isValidBoundaries);
    }


}