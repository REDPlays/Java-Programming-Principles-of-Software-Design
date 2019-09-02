
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter 
{
    private String where;//Any, Ends, Starts with...
    private String phrase;//What word you are looking for?
    private String Name;
    
    public PhraseFilter(String location, String word, String name)
    {
        where = location;
        phrase = word;
        Name = name;
    }
    
    public String getName()
    {
        return Name;
    }
    
    public boolean satisfies(QuakeEntry qe)
    {
        if(where == "Any")
        {
            return qe.getInfo().contains(phrase);
        }else if(where == "Starts")
        {
            return qe.getInfo().startsWith(phrase); 
        }else if(where == "Ends")
        {
            return qe.getInfo().endsWith(phrase);
        }
        else 
            return false;
    }
}
