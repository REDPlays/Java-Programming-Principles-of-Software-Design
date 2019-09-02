
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from)//<==Index Pos
    {
        int maxIndex = from;
        for(int i = from; i<quakeData.size(); i++)//add + 1?
        {
            if(quakeData.get(i).getDepth() > quakeData.get(maxIndex).getDepth())
            {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in)
    {
        for(int i=0; i<in.size(); i++)
        {
            if(i < 70)
            {
                int maxIndex = getLargestDepth(in, i);
                QuakeEntry qMax = in.get(i);
                QuakeEntry qMin = in.get(maxIndex);
                in.set(i, qMin);
                in.set(maxIndex, qMax);
            }else
            {
                break;
            }
        }
        System.out.println("70th pass: " + in.get(70));
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted)
    {
        for(int i=1; i<quakeData.size()-numSorted; i++)
        {
            if(quakeData.get(i-1).getMagnitude() > quakeData.get(i).getMagnitude())
            {
                QuakeEntry qBig = quakeData.get(i-1);
                QuakeEntry qSmall = quakeData.get(i);
                quakeData.set(i-1,qSmall);
                quakeData.set(i,qBig);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in)
    {
        for(int i=0; i<in.size()-1; i++)
        {
            onePassBubbleSort(in, i);
            
            System.out.println("Printing Quakes after pass "+i);
            for(QuakeEntry qe : in)
            {
                System.out.println(qe);
            }
        }
        System.out.println("Earth Quakes in sorted order: ");
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes)
    {
        for (int i=1; i<= quakes.size()-1; i++) {
            if (quakes.get(i-1).getMagnitude() > quakes.get(i).getMagnitude()) 
            {
                return false;
            }
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in)
    {
        for(int i=0; i<in.size()-1; i++)
        {
            if(checkInSortedOrder(in))//Checking through the array to see if its ordered
            {//if so then break the loop
                System.out.println(i + " passes were needed to sort the elements");
                break;
            }
            onePassBubbleSort(in, i);//Passes through the BubbleSort once if its not ordered
        }
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in)
    {
        for(int i=0; i<in.size(); i++)
        {
            if(checkInSortedOrder(in))
            {
                System.out.println(i + " Passes were needed to sort the Array");
                break;
            }
            int minIndex = getSmallestMagnitude(in, i);
            QuakeEntry qMax = in.get(i);
            QuakeEntry qMin = in.get(minIndex);
            in.set(i, qMin);
            in.set(minIndex, qMax);
        }
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        //String source = "data/earthquakeDataSampleSix1.atom";
        //String source = "data/earthQuakeDataDec6sample2.atom";
        //String source = "data/earthQuakeDataWeekDec6sample2.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom.";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
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
