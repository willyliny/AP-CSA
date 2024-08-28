import java.util.ArrayList;

public class WordChecker {
    /** Initialized in the constructor and contains no null elements */
    private ArrayList<String> wordList;

    WordChecker(ArrayList<String> wordList)
    {
        this.wordList = wordList;
    }
    /**
     * Returns true if each element of wordList (except the first) contains the
     * previous
     * element as a substring and returns false otherwise, as described in part (a)
     * Precondition: wordList contains at least two elements.
     * Postcondition: wordList is unchanged.
     */
    public boolean isWordChain() {
        for(int i = 1; i < wordList.size(); i++)
        {
            String prev = wordList.get(i - 1);
            String curr = wordList.get(i);

            if(curr.indexOf(prev) < 0)
            {
                return false;
            }
        }
        return true;

        /* to be implemented in part (a) */ }

    /**
     * Returns an ArrayList<String> based on strings from wordList that start
     * with target, as described in part (b). Each element of the returned ArrayList
     * has had
     * the initial occurrence of target removed.
     * Postconditions: wordList is unchanged.
     * Items appear in the returned list in the same order as they appear in
     * wordList.
     */
    public ArrayList<String> createList(String target) {
        ArrayList<String> result = new ArrayList<>();

        int len = target.length();

        for(int i = 0; i < wordList.size(); i++)
        {
            String word = wordList.get(i);

            if(word.indexOf(target) == 0)
            {
                String rw = word.substring(len);
                result.add(rw);
            }
        }


        return result;
        /* to be implemented in part (b) */ }
    // There may be instance variables, constructors, and methods that are not
    // shown.
}