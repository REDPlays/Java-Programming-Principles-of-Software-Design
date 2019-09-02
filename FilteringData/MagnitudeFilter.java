
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter 
{
    private double minMag;
    private double maxMag;
    private String Name;
    
    public MagnitudeFilter(double min, double max, String name)
    {
        minMag = min;
        maxMag = max;
        Name = name;
    }  
    
    public String getName()
    {
        return Name;
    }
      
    public boolean satisfies(QuakeEntry qe)
    {
      return qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag;
    }
}
