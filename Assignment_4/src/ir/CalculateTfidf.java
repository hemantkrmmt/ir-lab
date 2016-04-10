package ir;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;


public class CalculateTfidf extends Lab_4_solution {

	private ArrayList<String> str1;
	private HashMap<String, HashMap<Integer, Integer>> hm;

	public CalculateTfidf(ArrayList<String> words_list,HashMap<String, HashMap<Integer, Integer>> hm) {  //constructor for calculate tf-idf
		this.str1 = words_list;
		this.hm= hm;
		
	}
	
	
	public HashMap<Integer, Double> getTfidf() {
		int count=0;
		
	    double[] idf =new double[1000];
	    double[] flist =new double[(int)count];
	//    double[] sortdid = new double[(int)count];
	    HashMap<Integer, Double> backup = new HashMap<Integer,Double>();
	    double[][] tfidf =new double[1000][2*(int)count];
	    HashMap<Integer, String> fnldoc =new HashMap<Integer, String>();
	    int id=0,did=0;
	
		for(String s:str1)
		{
			if(hm.get(s)!=null){
			
		    idf[id++]=Math.log10(count/(double)hm.get(s).size());
		    for(int t:hm.get(s).keySet()){
		    fnldoc.put(t, docname.get(t));
		 //   sortdid[did++]=t;
			tfidf[id-1][t]=(double)(hm.get(s).get(t))*idf[id-1];
		    }
			}
		}
		int c=0;
		
		for(int t:fnldoc.keySet())
		{
			int r=0;
			for(String s:str1){
				flist[c]=flist[c]+tfidf[r][t];
				r++;
			}
			backup.put(t, flist[c]);
			c++;
		}
	
		 
		
			return backup;
	}
	
	

}
