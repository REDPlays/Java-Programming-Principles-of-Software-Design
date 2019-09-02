import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

       //Location Japan = new Location(35.42, 139.43);
       //Filter f = new MinMagFilter(4.0,""); 
       //Filter f = new MagnitudeFilter(4.0, 5.0,"");
       //f = new DepthFilter(-35000.0, -12000.0,"");
       //Filter f = new DistanceFilter(Japan,10000000,"");
       //f = new PhraseFilter("Ends","Japan","");
       Location Colorado = new Location(39.7392, -104.9903);
       //Filter f = new MagnitudeFilter(3.5,4.5,"");
       //f = new DepthFilter(-55000.0,-20000.0,"");
       Filter f = new PhraseFilter("Ends","a","");
       f = new DistanceFilter(Colorado,1000000.0,"");
        
        ArrayList<QuakeEntry> m7  = filter(list, f); 
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
        System.out.println("Quakes found: "+m7.size());
    }
    
    public void testMatchAllFilter()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(1.0,4.0,""));
        maf.addFilter(new DepthFilter(-180000.0,-30000.0,""));
        maf.addFilter(new PhraseFilter("Any","o",""));
        
        ArrayList<QuakeEntry> quakes = filter(list,maf);
        
        for(QuakeEntry qe : quakes)
        {
            System.out.println(qe);
        }
        System.out.println(quakes.size() + " earth quakes read.");
        
    }
    
    public void testMatchAllFilter2()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        
        Location Denmark = new Location(55.7308, 9.1153);
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0,5.0,"Magnitude"));
        maf.addFilter(new DistanceFilter(Denmark, 3000000,"Distance"));
        maf.addFilter(new PhraseFilter("Any","e","Phrase"));
        
        ArrayList<QuakeEntry> quakes = filter(list,maf);
        
        for(QuakeEntry qe : quakes)
        {
            System.out.println(qe);
        }
        System.out.println(quakes.size() + " earth quakes read.");
        System.out.println("Filters Used are : "+ maf.getName());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
        
    }

}
