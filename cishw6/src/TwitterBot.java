import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the class where everything you've worked on thus far comes together!
 * You can see that we've provided a path to a CSV file full of tweets and the
 * column from which they can be extracted. When run as an application, this
 * program builds a Markov Chain from the training data in the CSV file,
 * generates 10 random tweets, and prints them to the terminal.
 * 
 * This class also provides the writeTweetsToFile method, which can be used to
 * create a file containing randomly generated tweets.
 * 
 * Note: All IOExceptions thrown by writers should be caught and handled
 * properly.
 */
public class TwitterBot {

    static final int MAX_TWEET_LENGTH = 280;
    /**
     * This is a path to the CSV file containing the tweets. The main method below
     * uses the tweets in this file when calling Twitterbot. If you want to run the
     * Twitterbot on the other files we provide, change this path to a different
     * file. (You may need to adjust the TWEET_COLUMN too.)
     */
    static final String PATH_TO_TWEETS = "files/dog_feelings_tweets.csv";
    static final int TWEET_COLUMN = 2;
    static final String PATH_TO_OUTPUT_TWEETS = "files/generated_tweets.txt";

    // The MarkovChain you'll be using to generate tweets
    MarkovChain mc;
    NumberGenerator ng;

    /**
     * Given a column and a path to the csvFile, initializes the TwitterBot by
     * training the MarkovChain with sentences sourced from that CSV file. Uses the
     * RandomNumberGenerator().
     *
     * @param csvFile     - a path to a CSV file containing tweet data
     * @param tweetColumn - the column in that CSV where the text of the tweet
     *                    itself is stored
     */
    public TwitterBot(String csvFile, int tweetColumn) {
        this(csvFile, tweetColumn, new RandomNumberGenerator());
    }

    /**
     * Given a column and a path to the csvFile, initializes the TwitterBot by
     * training the MarkovChain with all the sentences obtained as training data
     * from that CSV file.
     *
     * @param csvFile     - a path to a CSV file containing tweet data
     * @param tweetColumn - the column in that CSV where the text of the tweet
     *                    itself is stored
     * @param ng          - A NumberGenerator for the ng field, also to be passed to
     *                    MarkovChain
     */
    public TwitterBot(String csvFile, int tweetColumn, NumberGenerator ng) {
        mc = new MarkovChain(ng);
        this.ng = ng;
        // todo: Complete this method.
    }

    /**
     * Given a List of Strings, prints those Strings to a file (one String per line
     * in the file). This method uses BufferedWriter, the flip side to
     * BufferedReader. Ensure that each tweet you generate is written on its own
     * line in the file produced.
     *
     * You may assume none of the arguments or strings passed in will be null.
     *
     * If the process of writing the data triggers an IOException, you should catch
     * it and stop writing.  (You can also print an error message to the terminal, 
     * but we will not test that behavior.)
     * 
     * @param stringsToWrite - A List of Strings to write to the file
     * @param filePath       - the string containing the path to the file where the
     *                       tweets should be written
     * @param append         - a boolean indicating whether the new tweets should be
     *                       appended to the current file or should overwrite its
     *                       previous contents
     */
    public void writeStringsToFile(List<String> stringsToWrite, String filePath, boolean append) {
        File file = Paths.get(filePath).toFile();
        BufferedWriter br = null;
        // todo: Complete this method.
    }

    /**
     * Generates tweets and writes them to a file.
     * 
     * @param numTweets   - the number of tweets that should be written
     * @param tweetLength - the approximate length (in characters) of each tweet
     * 
     * @param filePath    - the path to a file to write the tweets to
     * @param append      - a boolean indicating whether the new tweets should be
     *                    appended to the current file or should overwrite its
     *                    previous contents
     */
    public void writeTweetsToFile(int numTweets, int tweetLength, String filePath, boolean append) {
        writeStringsToFile(generateTweets(numTweets, tweetLength), filePath, append);
    }

    /**
     * Generates a tweet of a given length by using the populated MarkovChain.
     * Remember in the writeup where we explained how to use MarkovChain to pick a
     * random starting word and then pick each subsequent word based on the
     * probability that it follows the one before? This is where you implement that
     * core logic!
     *
     * Use the (assumed to be trained) MarkovChain as an iterator to build up a
     * String that represents the tweet that's returned.
     * 
     * 1. validate the length argument 2. reset the MarkovChain (to prepare it to
     * generate a new sentence) 3. repeatedly generate new words to add to the
     * tweet:
     * 
     * 3.a If the MarkovChain has no more values in its Iterator but the tweet is
     * not yet at the required length, use randomPunctuation() to end the sentence
     * and then reset() to begin the next sentence with a random start word.
     *
     * If appending a word or punctuation ever makes your tweet's length greater 
     * than the desired length, you should include it and end the tweet with
     * randomPunctuation() (assuming it's not punctuated already). The resulting
     * tweet may be slightly longer than the desired length (and possibly
     * MAX_TWEET_LENGTH), but it should contain no spaces past the input length.
     *
     * Your tweet should be properly formatted with one space between each word and
     * between sentences. It should not contain any leading or trailing whitespace.
     * You should leave the words uncapitalized, just as they are from TweetParser.
     *
     * You should return an empty string if there were no sentences available to
     * train the Markov Chain. You also need to do some input validation to make
     * sure the length is appropriate.
     *
     * @param length - The desired (approximate) length of the tweet (in characters)
     *               to be produced
     * @return a String representing a generated tweet
     * @throws IllegalArgumentException if length is less than 1 or greater than
     *                                  MAX_TWEET_LENGTH
     */
    public String generateTweet(int length) {
        return null; // todo: Complete this method.
    }

    /**
     * Generates a series of tweets using generateTweet().
     *
     * @param numTweets   - the number of tweets to generate
     * @param tweetLength - the length that each generated tweet should be.
     * @return a List of Strings where each element is a tweet
     */
    public List<String> generateTweets(int numTweets, int tweetLength) {
        List<String> tweets = new ArrayList<String>();
        while (numTweets > 0) {
            tweets.add(generateTweet(tweetLength));
            numTweets--;
        }
        return tweets;
    }

    /**
     * A helper function for providing a random punctuation String. Returns '.' 70%
     * of the time and ';', '?', and '!' each 10% of the time.
     * 
     * @return a string containing just one punctuation character
     */
    public String randomPunctuation() {
        char[] puncs = { ';', '?', '!' };
        int m = ng.next(10);
        if (m < puncs.length) {
            return String.valueOf(puncs[m]);
        }
        return ".";
    }

    /**
     * A helper function to determine if a string ends in punctuation.
     *
     * @param s - an input string to check for punctuation
     * @return true if the string s ends in punctuation
     */
    public static boolean isPunctuated(String s) {
        if (s == null || s.equals("")) {
            return false;
        }
        char[] puncs = TweetParser.getPunctuation();
        for (char c : puncs) {
            if (s.charAt(s.length() - 1) == c) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prints ten generated tweets to the console so you can see how your bot is
     * performing!
     */
    public static void main(String[] args) {
        TwitterBot t = new TwitterBot(PATH_TO_TWEETS, TWEET_COLUMN);
        List<String> tweets = t.generateTweets(10, 140);
        for (String tweet : tweets) {
            System.out.println(tweet);
        }
        
        // You can also write randomly genrated tweets to a file by 
        // uncommenting the following code: 
        // t.writeTweetsToFile(10, 140, PATH_TO_OUTPUT_TWEETS, false);
    }

}
