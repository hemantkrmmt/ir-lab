package ir;

import java.util.ArrayList;
import java.util.HashMap;

public class TfIdfCalculator  {
	
	private ArrayList<String> query_word;
	private HashMap<String, HashMap<Integer, Integer>> hm;
	private int docs_size;

	public TfIdfCalculator(HashMap<String, HashMap<Integer, Integer>> hm,
			ArrayList<String> words_list, int size) {
   this.query_word= words_list;
   this.hm=hm;
   this.docs_size= size;
		
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("rawtypes")
	public HashMap<Integer, Double> tfidf(){
		
		HashMap<Integer, Double> ranked_hashmap = new HashMap<Integer, Double>();
                
		for(String query_w : query_word)
		{
			if(hm.containsKey(query_w)){
				int df = hm.get(query_w).size();
				double a = (double)docs_size/(double) df;
				double idf;
				idf = Math.log10(a);
				for(int t : hm.get(query_w).keySet()){
					double tfidf = (hm.get(query_w).get(t))*idf;
					if(ranked_hashmap.isEmpty()){
						ranked_hashmap.put(t, tfidf);
					}
					else{
						if(ranked_hashmap.containsKey(t)){
							double updated_tfidf = ranked_hashmap.get(t)+ tfidf;
							ranked_hashmap.put(t, updated_tfidf);
						}
						else
							ranked_hashmap.put(t, tfidf);
					}
				}
			}
		}
		return ranked_hashmap;
	}
}