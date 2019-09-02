
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.Random;
import java.util.ArrayList;

public class tester 
{
    public void testGetFollows()
    {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        st = "this is a test yes this is a test.";
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        
        ArrayList<String> follows = markov.getFollows("t");
        
        System.out.println(follows);
    }
    
    public void testGetFollowsWithFile()
    {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        
        ArrayList<String> follows = markov.getFollows("he");
        
        System.out.println(follows.size());
    }
}
