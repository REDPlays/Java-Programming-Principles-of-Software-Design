
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter 
{
    private Location location;
    private double maxDist;
    private String Name;
    
    public DistanceFilter(Location from, double DisMax, String name)
    {
        location = from;
        maxDist = DisMax;
        Name = name;
    }
    
    public String getName()
    {
        return Name;
    }
    
    public boolean satisfies(QuakeEntry qe)
    {
        double dist = location.distanceTo(qe.getLocation());
        
        return dist<maxDist;
    }
}
