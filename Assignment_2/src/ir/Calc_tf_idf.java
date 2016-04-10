package ir;

import java.util.ArrayList;
import java.util.HashMap;



public class Calc_tf_idf extends Tf_idf {

	private ArrayList<String> str1;
	private HashMap<String, HashMap<Integer, Integer>> hm;

	public Calc_tf_idf(ArrayList<String> words_list,HashMap<String, HashMap<Integer, Integer>> hm) {  //constructor for calculate tf-idf
		this.str1 = words_list;
		this.hm= hm;
		
	}
	
	
	public HashMap<Integer, Double> get_tf_idf() {
		
		
	    HashMap<Integer, Double>idf= new HashMap<Integer, Double>();
		
		HashMap<Integer,Double>flist= new HashMap<Integer, Double>();  
	   
		for(int j=0;j<docname.size();j++){
	    	flist.put(j,0.0);

		}
		
		
		HashMap<Integer, Double> backup = new HashMap<Integer,Double>();
	  HashMap<Integer, HashMap<Integer, Double>>tfidf= new HashMap<Integer, HashMap<Integer,Double>>();
	    HashMap<Integer, String> docs_list =new HashMap<Integer, String>();
	    int id=0,did=0;
		
		for(String s:str1)   			
		{
			if(hm.get(s)!=null)
			{
				 HashMap<Integer, Double> backup1 = new HashMap<Integer,Double>();
				  	
				idf.put(id,Math.log10(docname.size()/(double)hm.get(s).size()));
				for(int t:hm.get(s).keySet())
				{
					docs_list.put(t, docname.get(t));
		 
					backup1.put(t, (double)(hm.get(s).get(t))*idf.get(id));
				}
				tfidf.put(id, backup1);
				
				id++;
			}
		}
		int c=0;
		for(int t:docs_list.keySet())		 // adding all tf-idf according to the particular documents
		{
			int r=0;
			for(String s:str1)
			{
				
			if(tfidf.get(r).get(t)!=null){
				flist.put(c,flist.get(c)+tfidf.get(r).get(t));
			}r++;
			}
			backup.put(t, flist.get(c));
			c++;
		}
	
		 
	
		return backup;
	}
}