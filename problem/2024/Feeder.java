import java.util.*;
public class Feeder {
    /**
     * The amount of food, in grams, currently in the bird feeder; initialized in
     * the constructor and
     * always greater than or equal to zero
     */
    private int currentFood;

    Feeder(int currentFood)
    {
        this.currentFood = currentFood;
    }

    /**
     * Simulates one day with numBirds birds or possibly a bear at the bird feeder,
     * as described in part (a)
     * Precondition: numBirds > 0
     */
    public void simulateOneDay(int numBirds) 
    {  
        if(Math.random() < 0.05)
        {
            // bear feeds
            currentFood = 0;
        }
        else
        {
            int grams = (int)(Math.random() * (50-10+1) + 10);

            currentFood -= grams * numBirds;

            if(currentFood < 0)
            {
                currentFood = 0;
            }
        }
    }

    /**
     * Returns the number of days birds or a bear found food to eat at the feeder in
     * this simulation,
     * as described in part (b)
     * Preconditions: numBirds > 0, numDays > 0
     */
    public int simulateManyDays(int numBirds, int numDays) 
    {  

        for(int i = 1; i <= numDays; i++)
        {
            simulateOneDay(numBirds);

            if(currentFood == 0)
            {
                return i;
            }
        }

        return 0;    
    }




    
    
    /**
     * @TestOnly This method is not part of the original AP CSA exam question.
     * It is added for testing purposes only.
     */
    public int getFood() {
        return currentFood;
    }

    /**
     * @TestOnly This method is not part of the original AP CSA exam question.
     * It simulates setting a random seed by skipping a number of random values.
     * This is not a true seed setting, but it can provide similar repeatability for testing.
     */
    public void simulateRandomSeed(long seed) {
        for (long i = 0; i < seed % 1000; i++) {
            Math.random();
        }
    }

}