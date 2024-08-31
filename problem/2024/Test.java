import java.util.*;

public class Test
{
    // ANSI escape code
    public static final String RESET = "\033[0m";  // Reset the color
    public static final String RED = "\033[0;31m";  // Red color
    public static final String GREEN = "\033[0;32m";  // Green color
    public static final String YELLOW = "\033[0;33m";  // Yellow color
    public static final String PURPLE = "\033[0;34m";  // Blue color    

    public Scanner sc = new Scanner(System.in);

    public static void one()
    {
        System.out.println(YELLOW + "\n-------- Testing simulateOneDay --------" + RESET);
        boolean bearVisit = false;
        
        for(int i = 0; i < 1000; i++)
        {
            Feeder f1 = new Feeder(5000);
            f1.simulateRandomSeed(12345); // Set a seed that will trigger a bear visit
            f1.simulateOneDay(2);
            if(f1.getFood() == 0)
            {
                bearVisit = true;
            }
        }
        System.out.println("Test 1 (Bear visit): " + (bearVisit ? GREEN + "PASS" : RED + "FAIL") + RESET);

        // Test 2: Normal day
        Feeder f2 = new Feeder(5000);
        f2.simulateRandomSeed(54321); // Set a seed for a normal day
        int initialFood = f2.getFood();
        f2.simulateOneDay(10);
        System.out.println("Test 2 (Normal day): " + (f2.getFood() < initialFood && f2.getFood() >= 0 ? GREEN + "PASS" : RED + "FAIL") + RESET);

        // Test 3: Edge case - 1 bird
        Feeder f3 = new Feeder(100);
        f3.simulateRandomSeed(11111);
        f3.simulateOneDay(1);
        System.out.println("Test 3 (1 bird): " + (f3.getFood() <= 100 && f3.getFood() >= 50 ? GREEN + "PASS" : RED + "FAIL") + RESET);
    
        System.out.println(YELLOW + "\n-------- Testing simulateManyDays --------" + RESET);
        
        // Test 4: Food runs out
        Feeder f4 = new Feeder(500);
        f4.simulateRandomSeed(12345);
        int days1 = f4.simulateManyDays(20, 30);
        System.out.println("Test 1 (Food runs out): " + (days1 > 0 && days1 <= 30 ? GREEN + "PASS" : RED + "FAIL") + RESET);

        // Test 5: Food doesn't run out
        Feeder f5 = new Feeder(10000);
        f5.simulateRandomSeed(54321);
        int days2 = f5.simulateManyDays(3, 10);
        System.out.println("Test 2 (Food doesn't run out): " + (days2 == 0 ? GREEN + "PASS" : RED + "FAIL") + RESET);

        // Test 6: Edge case - 1 day
        Feeder f6 = new Feeder(100);
        f6.simulateRandomSeed(11111);
        int days3 = f6.simulateManyDays(50, 1);
        System.out.println("Test 3 (1 day): " + (days3 == 0 || days3 == 1 ? GREEN + "PASS" : RED + "FAIL") + RESET);

        System.out.print(RESET);
    }

    public static void two()
    {
        System.out.println(YELLOW + "---------- Problem 2 ----------");
        Scoreboard game = new Scoreboard("Red", "Blue");
        System.out.println(RESET + "1 : " + GREEN + game.getScore());
        game.recordPlay(1);
        System.out.println(RESET + "2 : " + GREEN + game.getScore());
        game.recordPlay(0); 
        System.out.println(RESET + "3 : " + GREEN + game.getScore());
        System.out.println(RESET + "4 : " + GREEN + game.getScore());
        game.recordPlay(3);
        System.out.println(RESET + "5 : " + GREEN + game.getScore());
        game.recordPlay(1);
        game.recordPlay(0);
        System.out.println(RESET + "6 : " + GREEN + game.getScore());
        game.recordPlay(0);
        game.recordPlay(4);
        game.recordPlay(0);
        System.out.println(RESET + "7 : " + GREEN + game.getScore());
        Scoreboard match = new Scoreboard("Lions", "Tiger");
        System.out.println(RESET + "8 : " + GREEN + match.getScore());
        System.out.println(RESET + "9 : " + GREEN + game.getScore());

        System.out.print(RESET);

    }


    public static void three()
    {
        System.out.println(YELLOW + "---------- Part(a) ----------");

        ArrayList<String> example1 = new ArrayList<>();
        ArrayList<String> example2 = new ArrayList<>();
        
        example1.add("an");
        example1.add("band");
        example1.add("band");
        example1.add("abandon");

        example2.add("to");
        example2.add("too");
        example2.add("stool");
        example2.add("tools");
        WordChecker a1 = new WordChecker(example1);
        System.out.println(RESET + "ex1 : " + GREEN + (a1.isWordChain() ? "True" : "False"));
        WordChecker a2 = new WordChecker(example2);
        System.out.println(RESET + "ex2 : " + GREEN + (a2.isWordChain() ? "True" : "False"));

        System.out.println(YELLOW + "---------- Problem 2 ----------");
        ArrayList<String> example = new ArrayList<>();
        example.add("catch");
        example.add("bobcat");
        example.add("catchacat");
        example.add("cat");
        example.add("at");
        WordChecker b = new WordChecker(example);
        System.out.println(RESET + "1.b : " + GREEN + b.createList("cat"));
        System.out.println(RESET + "2.b : " + GREEN + b.createList("catch"));
        System.out.println(RESET + "3.b : " + GREEN + b.createList("dog"));



        System.out.print(RESET);

    }


    public static void four()
    {
        System.out.println(YELLOW + "---------- Part(a) ----------");
        GridPath gp = new GridPath();
        System.out.println(RESET + "ex1 : " + GREEN + "(0, 0) - " + YELLOW + "(" + gp.getNextLoc(0, 0).getRow() + ", " + gp.getNextLoc(0, 0).getCol() + ")");
        System.out.println(RESET + "ex2 : " + GREEN + "(1, 3) - " + YELLOW + "(" + gp.getNextLoc(1, 3).getRow() + ", " + gp.getNextLoc(1, 3).getCol() + ")");
        System.out.println(RESET + "ex3 : " + GREEN + "(2, 4) - " + YELLOW + "(" + gp.getNextLoc(2, 4).getRow() + ", " + gp.getNextLoc(2, 4).getCol() + ")");
        System.out.println(RESET + "ex4 : " + GREEN + "(4, 3) - " + YELLOW + "(" + gp.getNextLoc(4, 3).getRow() + ", " + gp.getNextLoc(4, 3).getCol() + ")");

        System.out.println(YELLOW + "---------- Part(b) ----------");
        GridPath gp2 = new GridPath();
        System.out.println(RESET + "ex4 : " + GREEN + "Start : (1, 1) - sum: " + YELLOW + gp2.sumPath(1, 1));


        System.out.print(RESET);

    }


    public static void testSimulateOneDay() {
        System.out.println(YELLOW + "\n-------- Testing simulateOneDay --------" + RESET);
        
        // Test 1: Bear visit
        boolean bearVisit = false;
        
        for(int i = 0; i < 1000; i++)
        {
            Feeder f1 = new Feeder(5000);
            f1.simulateRandomSeed(12345); // Set a seed that will trigger a bear visit
            f1.simulateOneDay(2);
            if(f1.getFood() == 0)
            {
                bearVisit = true;
            }
        }
        System.out.println("Test 1 (Bear visit): " + (bearVisit ? GREEN + "PASS" : RED + "FAIL") + RESET);

        // Test 2: Normal day
        Feeder f2 = new Feeder(5000);
        f2.simulateRandomSeed(54321); // Set a seed for a normal day
        int initialFood = f2.getFood();
        f2.simulateOneDay(10);
        System.out.println("Test 2 (Normal day): " + (f2.getFood() < initialFood && f2.getFood() >= 0 ? GREEN + "PASS" : RED + "FAIL") + RESET);

        // Test 3: Edge case - 1 bird
        Feeder f3 = new Feeder(100);
        f3.simulateRandomSeed(11111);
        f3.simulateOneDay(1);
        System.out.println("Test 3 (1 bird): " + (f3.getFood() <= 100 && f3.getFood() >= 50 ? GREEN + "PASS" : RED + "FAIL") + RESET);
    }

    public static void testSimulateManyDays() {

    }

    public static void testStatisticalProperties() {
        System.out.println(YELLOW + "\n-------- Testing Statistical Properties --------" + RESET);
        
        int totalRuns = 1000;
        int bearVisits = 0;
        List<Integer> foodConsumptions = new ArrayList<>();

        for (int i = 0; i < totalRuns; i++) {
            Feeder f = new Feeder(5000);
            f.simulateOneDay(10);
            if (f.getFood() == 0) {
                bearVisits++;
            } else {
                foodConsumptions.add(5000 - f.getFood());
            }
        }

        double bearVisitRate = (double) bearVisits / totalRuns;
        double avgFoodConsumption = foodConsumptions.stream().mapToInt(Integer::intValue).average().orElse(0.0);

        System.out.println("Bear visit rate: " + bearVisitRate + " (Expected: ~0.05)");
        System.out.println("Average food consumption: " + avgFoodConsumption + " (Expected: 100-500)");
        
        boolean bearVisitRateCorrect = Math.abs(bearVisitRate - 0.05) < 0.02;
        boolean avgFoodConsumptionCorrect = avgFoodConsumption > 100 && avgFoodConsumption < 500;

        System.out.println("Statistical properties test: " + 
            (bearVisitRateCorrect && avgFoodConsumptionCorrect ? GREEN + "PASS" : RED + "FAIL") + RESET);
    }

    // public static void main(String[] args) {
    //     testSimulateOneDay();
    //     // one();
    //     // two();
    //     // three();
    //     // four();
    // }
}