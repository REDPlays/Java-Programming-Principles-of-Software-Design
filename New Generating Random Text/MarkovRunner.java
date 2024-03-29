
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;

public class MarkovRunner 
{
    public void runMarkovZero() 
    {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovZero markov = new MarkovZero();
        markov.setRandom(1024 );
        markov.setTraining(st);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }
    
    public void runMarkovOne() 
    {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setRandom(365);
        markov.setTraining(st);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }
    
    public void runMarkovFour()
    {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //st = "this is a test yes a test";
        MarkovFour markov = new MarkovFour(4);
        markov.setRandom(715);
        markov.setTraining(st);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }
    
    public void runMarkovModel()
    {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //st = "this is a test yes a test";
        MarkovModel markov = new MarkovModel();
        markov.setRandom(953);
        markov.setTraining(st);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500, 7);
            printOut(text);
        }
    }
    
    private void printOut(String s)
    {
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
}
