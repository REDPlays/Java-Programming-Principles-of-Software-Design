
/**
 * Write a description of MarkovWordTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWordTwo implements IMarkovModel 
{
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index + 1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++)
        {
            ArrayList<String> follows = getFollows(key1, key2);
            //System.out.println(key + ": " + follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        
        return sb.toString().trim();
    }
    
    /*&public void testIndexOf()
    {
         String[] words = "this is just a test yes this is a simple test".split(" ");
         int index = indexOf(words, "this", 0);
         System.out.println(index);
         index = indexOf(words,"this", 3);
         System.out.println(index);
         index = indexOf(words,"frog", 0);
         System.out.println(index);
         index = indexOf(words,"frog", 5);
         System.out.println(index);
         index = indexOf(words,"simple", 2);
         System.out.println(index);
         index = indexOf(words,"test", 5);
         System.out.println(index);
    }*/
    
    private int indexOf(String[] words, String target1, String target2, int start)
    {
        for(int k=start; k<words.length; k++)
        {
            if(words[k].equals(target1) && words[k+1].equals(target2))
            {
                return k + 1;
            }
        }
        return -1;
    }
    
    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        
        /*for(int i=0; i<myText.length; i++)
        {
            if(key.equals(myText[i]))
            {
                follows.add(myText[i+1]);
            }
        }*/
        int index = 0;
        while (true) {
            index = indexOf(myText, key1, key2, index);
            
            if (index == -1 || (index + 1) >= myText.length) {
                break;
            }


            follows.add(myText[index + 1]);

            
            index++;
        }
        
        return follows;
    }
}
