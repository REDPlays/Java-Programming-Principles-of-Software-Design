
/**
 * Write a description of MarkovFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.ArrayList;

public class MarkovFour 
{
    private String myText;
    private Random myRandom;
    private int n;
    public MarkovFour(int num) {
        myRandom = new Random();
        n = num;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public ArrayList<String> getFollows(String key)
    {
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
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - 4);
        String key = myText.substring(index, index + 4);
        sb.append(key);
        
        for(int k=0; k < numChars - 4; k++){
            ArrayList<String> follows = getFollows(key);
            //System.out.println("Key "+key + " " + follows);
            if(follows.size() == 0)
            {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
}
