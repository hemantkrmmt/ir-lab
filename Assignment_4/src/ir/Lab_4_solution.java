package ir;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Lab_4_solution extends exe_indexer{

	public static void main(String[] args )  throws IOException{
		
		exe_indexer.index_up();
		
	ArrayList<String>query_words = new ArrayList<String>();
	
	System.out.println("enter the position of query");	
	Scanner sc= new Scanner(System.in);
	 int pos = sc.nextInt();
	
	
	String query=query_filter.filter_from_doc(pos);
	// String query="आईपीएल नई फ्रेंचाइजी के लिए करने के लिए फिर से जारी निविदा";
	 
	 query= query.replaceAll("[<>()?:!।/.,'-;॥{}a-zA-Z0-9]", "");
	String[] str1=query.split(" ");     
	
	for(int i=0;i<str1.length;i++){
		if(!check_stop_words.check_word(str1[i])){
				
			if(stem_y_n=="y"){
			      str1[i]=stemmer.stem(str1[i]);
	          }
				if(!query_words.contains(str1[i])){
					if(str1[i]!=""){
					query_words.add(str1[i]);
					}}
				}
	}
	TfIdfCalculator cti=new	TfIdfCalculator(hm,query_words,docname.size());
	 HashMap<Integer, Double> backup = cti.tfidf();
	
	 //sorting the documents according to the tf_idf_weights
		
		Sort_map sort_map= new Sort_map();
		HashMap Sorted_tf_idf = sort_map.get_sorted_map(backup);
	System.out.println("sorted"+Sorted_tf_idf);
		
		ArrayList<String>top_doc_names= new ArrayList<String>();
	int rank=0;
	System.out.print("rank\t\t doc_name\t\t tf-idf\t\t title\n\n");
	
		for(Object t:Sorted_tf_idf.keySet()){  
			int j= (int)t;
			System.out.println(rank+1+"\t\t "+docname.get(j)+"\t\t "+Sorted_tf_idf.get(t)+"\t\t "+titlename.get(j));
				top_doc_names.add(docname.get(j));
		rank++;
	}
		
		//getting the relevent documents from the relevent documents file for the given query
		String query_name;
	
		if(pos<10){ query_name="english-document-0000"+pos+".txt";}
		else{
			 query_name="english-document-000"+pos+".txt";
			
		}
		File rel_docs_path;
		
		 boolean tf;
	do{
		System.out.println("enter the correct path of relevent document names file");
	String	f1= sc.nextLine();
	rel_docs_path = new File(f1);
           tf= rel_docs_path.isFile();
		}while(tf==false);   

	String[] data=null;
		Scanner sc1= new Scanner(rel_docs_path);
		while( sc1.hasNext()) {
			String content = sc1.useDelimiter("\\Z").next();
	//System.out.println(content);
			data = content.split("\\s+");  
	 	
		HashMap<String, Integer> relevent_docs_set= new HashMap<String, Integer>();
		for(int i=0;i<data.length;i++){
			if(i%4==0){
			
				System.out.print(query_name);
				System.out.println(":"+data[i]);
				
				if(data[i].equalsIgnoreCase(query_name)){
					System.out.println("hello");
					relevent_docs_set.put(data[i+2],1);
				System.out.println(data[i+2]);
				}
			}
		}
		int retrieved_relevent_docs=0;
		for(int i=0;i<top_doc_names.size();i++ ){
			String doc_name=top_doc_names.get(i);
			if(relevent_docs_set.containsKey(doc_name)){
				retrieved_relevent_docs++;
System.out.println(relevent_docs_set.size());
			}
		}
	
		System.out.println();
		System.out.println("Retrieved Relevent documents are: "+retrieved_relevent_docs);
		System.out.println("total relevent documents are: "+relevent_docs_set.size());
		System.out.println("precision of retrieved relevent docs is: "+retrieved_relevent_docs/10 );
		System.out.println("recall is: "+retrieved_relevent_docs/relevent_docs_set.size());
		}
}
}