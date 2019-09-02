
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.util.Random;
import java.util.ArrayList;

public class EfficientMarkovModel extends AbstractMarkovModel
{
    private int n;
    private HashMap<String, ArrayList<String>> map;
    
    public EfficientMarkovModel(int num) {
        myRandom = new Random();
        n = num;
        map = new HashMap<String, ArrayList<String>>();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
        buildMap();
        printHashMapInfo();
    }
    
    public void buildMap()
    {
        // Build HashMap of the characters that follow each substring of order length
        for (int pos = 0; pos <= myText.length() - n; pos++) {
            int subEnd = pos + n;
            // Identify the current substring
            String sub = myText.substring(pos, subEnd);
            // If HashMap doesn't yet contain substring as key
            if (!map.containsKey(sub)) {
                map.put(sub, new ArrayList<String>());
            }
            // Add to HashMap the character that follows current substring, if there is one
            if (subEnd < myText.length()) {
                String follower = myText.substring(subEnd, subEnd + 1);
                //System.out.println(sub + ": " + follower);
                ArrayList<String> followers = map.get(sub);
                followers.add(follower);
                map.put(sub, followers);
            }
        }
    }
    
    public void printHashMapInfo()
    {
        // Print the HashMap (all the keys and their corresponding values)
        // System.out.println("Hashmap: " + "\n" + map);
        
        // Print the number of keys in the HashMap
        System.out.println("Number of keys: " + map.size());
        
        // Print the size of the largest largest ArrayList of characters in the HashMap
        int largestSize = 0; 
        for (String key : map.keySet()) {
            int keySize = map.get(key).size();
            if (keySize > largestSize) {
                largestSize = keySize;
            }
        }
        System.out.println("The size of the largest ArrayList of characters: " + largestSize);
        
        // Print the keys that have the maximum size value
        System.out.println("The keys that have the maximum size value:");
        for (String key : map.keySet()) {
            if (map.get(key).size() == largestSize) {
                System.out.println(key);
            }
        }
        
        System.out.println("\n");
    }
    
    public ArrayList<String> getFollows(String key) {
        return map.get(key);
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - n);
        String key = myText.substring(index, index + n);
        sb.append(key);
        
        for(int k=0; k < numChars - n; k++){
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
    
    public String toString()
    {
        return "EfficientMarkovModel Class of Order " + n;
    }
}
