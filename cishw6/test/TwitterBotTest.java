/* Tests for TwitterBot class */
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TwitterBotTest {

    String simpleData = "files/simple_test_data.csv";
    String testData = "files/test_data.csv";
    
    // this tests whether your TwitterBot class itself is written correctly
    @Test
    public void simpleTwitterBotTest() {
        
        /* The first sentence starts with the second start word, 
         * then always choose the first option.
         * For a better understanding of how these indices work, 
         * see the provided test in MarkovChainTest.java
         */
        List<Integer> indices = new ArrayList<Integer>(Collections.nCopies(100, 0));
        indices.set(0, 1);
        
        ListNumberGenerator lng = new ListNumberGenerator(indices);
        TwitterBot t = new TwitterBot(simpleData, 1, lng);
        
        String expected = "this comes from data with no duplicate words. the end should come.";
        String actual = TweetParser.replacePunctuation(t.generateTweet(63));
        assertEquals(expected, actual);
    }

    /* **** ****** **** WRITE YOUR TESTS BELOW THIS LINE **** ****** **** */

}
