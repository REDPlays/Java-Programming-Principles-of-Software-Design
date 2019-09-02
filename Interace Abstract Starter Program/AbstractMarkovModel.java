
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    public void setRandom(int seed)
    {
        myRandom = new Random(seed);
    }
    
    public ArrayList<String> getFollows(String key)
    {
       int n = key.length();
       
       ArrayList<String> follow = new ArrayList<String>();
       
       for(int i=0; i < myText.length() - n; i++)
       {
           if(key.equals(myText.substring(i, i+n)))
           {
               follow.add(myText.substring(i+n, i+n+1));
           }
       }
       
       return follow; 
    }
 
    abstract public String getRandomText(int numChars);

}
