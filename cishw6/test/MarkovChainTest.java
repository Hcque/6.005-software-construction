/* Tests for MarkovChain */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class MarkovChainTest {

    /* Writing tests for Markov Chain can be a little tricky.
     * We provide a few tests below to help you out, but you still need
     * to write your own.
     */
    
    @Test
    public void testAddBigram() {
        MarkovChain mc = new MarkovChain();
        mc.addBigram("1", "2");
        assertTrue(mc.chain.containsKey("1"));
        ProbabilityDistribution<String> pd = mc.chain.get("1");
        assertTrue(pd.getRecords().containsKey("2"));
        assertEquals(1, pd.count("2"));
    }

    @Test
    public void testTrain() {
        MarkovChain mc = new MarkovChain();
        String sentence = "1 2 3";
        mc.train(Arrays.stream(sentence.split(" ")).iterator());
        assertEquals(3, mc.chain.size());
        ProbabilityDistribution<String> pd1 = mc.chain.get("1");
        assertTrue(pd1.getRecords().containsKey("2"));
        assertEquals(1, pd1.count("2"));
        ProbabilityDistribution<String> pd2 = mc.chain.get("2");
        assertTrue(pd2.getRecords().containsKey("3"));
        assertEquals(1, pd2.count("3"));
        ProbabilityDistribution<String> pd3 = mc.chain.get("3");
        assertTrue(pd3.getRecords().containsKey(null));
        assertEquals(1, pd3.count(null));
    }

    @Test
    public void testWalk() {
        /*
         * Using the sentences "CIS 120 rocks" and "CIS 120 beats CIS 160",
         * we're going to put some bigrams into the Markov Chain.
         * If you look at the above sentences, you can generate the 
         * following bigrams and frequencies.
         * 
         * Markov Chain model:
         * "CIS"   -> 2 occurrences of "120", 1 occurrences of "160"
         * "120"   -> 1 occurrence of "beats", 1 occurrence of "rocks"
         * "beats" -> 1 occurrence of "CIS"
         * "rocks" -> 1 occurrence of null (the ending token)
         * "160"   -> 1 occurrence of null (the ending token)
         * 
         * You can take these mappings and use them to generate a walk.
         * To create a walk, start at a word, and then go from one word to another 
         * until you hit a null, or you just don't want to walk any more.
         * 
         * We want to produce the following walk:
         * "CIS" -> "120" -> "beats" -> "CIS" -> "120" -> "rocks" -> null
         * 
         * So how do we do that walk?
         * 
         * Another way of viewing the Markov Chain model from above (which 
         * is also closer to how it's stored in the computer) is by representing 
         * the set of words that come after a word in an alphabetically sorted array:
         * "CIS"   -> ["120", "120", "160"]
         * "120"   -> ["beats", "rocks"]
         * "beats" -> ["CIS"]
         * "rocks" -> [null]
         * "160"   -> [null]
         * 
         * You can then take this model, and use it to generate indices 
         * of how to walk from one word to another (remember arrays are 0 indexed):
         * Indices:        0 or 1  ->    0    ->   0   ->  0 or 1  ->    1    ->  0
         * Words:  "CIS" -> "120"  -> "beats" -> "CIS" ->   "120"  -> "rocks" -> null
         * 
         * We can use the words and the indices to test our MarkovChain as seen below:
         */
         
        Integer[] walkIndices = {0, 0, 0, 1, 1, 0};
        String[] words = {"CIS", "120", "beats", "CIS", "120", "rocks"};
        NumberGenerator r = new ListNumberGenerator(walkIndices);
        MarkovChain mc = new MarkovChain(r);
        
        String sentence1 = "CIS 120 rocks";
        String sentence2 = "CIS 120 beats CIS 160";
        mc.train(Arrays.stream(sentence1.split(" ")).iterator());
        mc.train(Arrays.stream(sentence2.split(" ")).iterator());
        
        mc.reset("CIS"); // we start with "CIS" since that's the word our desired walk starts with
        for (int i = 0; i < words.length; i++) {
            assertTrue(mc.hasNext());
//            System.out.println(mc.next());
            assertEquals(words[i], mc.next());
        }
    }

    /* **** ****** **** WRITE YOUR TESTS BELOW THIS LINE **** ****** **** */


}
