package ir;

import java.util.ArrayList;
import java.util.HashMap;

public class Print_tf_idf {

	private HashMap<String, HashMap<Integer, Double>> hm;
	private HashMap<Integer, String> docname;

	public Print_tf_idf(HashMap<String, HashMap<Integer, Double>> backup,
			HashMap<Integer, String> docname) {
	this.hm=backup;
	this.docname=docname;
	
	
	}

	public HashMap<Integer, Double>print() {
HashMap<Integer, Double> docs_tf_idf_weight = new HashMap<Integer,Double>(docname.size());
for(int i:docname.keySet()){
	docs_tf_idf_weight.put(i,0.0);
}
		for(String word: hm.keySet()){
			System.out.print(word+"\t\t");
		for(int doc_id:docname.keySet()){
			double weight=0;
			double value=0;
			if(hm.get(word).containsKey(doc_id)){
				value=hm.get(word).get(doc_id);
				System.out.print(value+"\t\t");
				weight=docs_tf_idf_weight.get(doc_id);
				weight= weight+value;
				docs_tf_idf_weight.put(doc_id,weight);	
			
			}
			else{
				value=0;
				System.out.print(value+"\t\t");
			}
			}
	System.out.println();	
		}
	
		for(int i:docname.keySet()){
			if(docs_tf_idf_weight.get(i)==0.0){
				docs_tf_idf_weight.remove(i);
			}
		}
		
	return docs_tf_idf_weight;
	}

}
