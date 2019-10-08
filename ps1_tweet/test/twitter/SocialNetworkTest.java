/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class SocialNetworkTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T12:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it @cous reasonable @bbitdiddle to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest @alyssa talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "cous", "rivest talk in 30 seconds #hype", d3);
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // Test GuessFollowsGraph
    // Testing strategy:
    //
    // partition input and output space:
    // tweets: 0 tweet, >1 tweets
    // No people follows any other people, one people follows another, two people follows each other
    
    // this test case covers 0 tweet, no people
    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        
        assertTrue("expected empty graph", followsGraph.isEmpty());
    }
  
    // this test case covers 1 tweet, no people
    @Test
    public void testGuessFollowsGraph1tweet() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1));        
        assertTrue(followsGraph.containsKey("cous") && followsGraph.containsKey("bbitdiddle"));
    }
    
    // this test case covers >1 tweet, one follows another
    @Test
    public void testGuessFollowsGraph2tweets() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet3));        
        assertTrue(followsGraph.containsKey("cous") && followsGraph.containsKey("bbitdiddle"));

        assertEquals(followsGraph.get("bbitdiddle"), new HashSet<String>(Arrays.asList("alyssa")));
        assertEquals(followsGraph.get("cous"), new HashSet<String>(Arrays.asList("alyssa")));
    }
    
    // this test case covers >1 tweet, follow each other
    @Test
    public void testGuessFollowsGraphundirected() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet2, tweet3));        
        Map<String, Set<String>> expected = new HashMap<String, Set<String>>();
        
        expected.put("alyssa", new HashSet<String>(Arrays.asList("bbitdiddle", "cous")));
        expected.put("bbitdiddle", new HashSet<String>(Arrays.asList("alyssa")));

        assertTrue(followsGraph.containsKey("alyssa")); 
        assertTrue(followsGraph.containsKey("bbitdiddle")); 
        assertEquals(followsGraph.get("alyssa"), new HashSet<String>(Arrays.asList("bbitdiddle")));
        assertEquals(followsGraph.get("bbitdiddle"), new HashSet<String>(Arrays.asList("alyssa")));
    }
    
    
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("expected empty list", influencers.isEmpty());
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}
