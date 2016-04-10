package ir;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Relevance_feedback extends Tf_idf {

public static void sf() throws IOException{
	
	Tf_Idf_calculation();
	HashMap<Object, Object> sortedHmap1 = new HashMap<Object, Object>();

   	ArrayList<Integer> ranks = new ArrayList<Integer>();
	ArrayList<Integer> nr_ranks = new ArrayList<Integer>();  
  	
	System.out.println("__________________________________________________\n");
	   
   	Scanner sc3=new Scanner(System.in);
   	System.out.println("do you want to give some feedback?\n" +
   			"press 'y' to say yes\n press 'n' to say no");
   	String var = sc3.nextLine();
   	if(var.equalsIgnoreCase("y")){
 
	System.out.println("please help us to get relevent documents \n" +
			"enter the rank of the document/s which is/are relevent to you\n" +
			"press 00  to 'quit' when you are done");
  	System.out.println("__________________________________________________");
   	
	int rank;
  	do{
		System.out.println("enter the rank or 00 to quit");
		
		rank=sc3.nextInt();
		if(rank!=0){
	    if(!ranks.contains(rank)){
		ranks.add(rank);
			}
		}
	}while(rank!=00);
     
  	for(int i=0;i<10;i++){
          if(!nr_ranks.contains(i)){
        	  nr_ranks.add(i);
          }
     }
  	
	System.out.println("__________________________________________________\n");
  	
	System.out.println("you entered the ranks");
	System.out.println("__________________________________________________\n");
 System.out.println("rank \t relevent documents\n");
	for(int i=0;i<ranks.size();i++){
		
		System.out.println(ranks.get(i)+"\t"+docname.get(rank_id_pair.get(ranks.get(i))));
	}
}
   	else{
   		
   	   	int rank=0;
   	  
   			ranks.add(rank);
   				
   			while(rank!=00);
   	     
   	  	for(int i=0;i<10;i++){
   	          if(!nr_ranks.contains(i)){
   	        	  nr_ranks.add(i);
   	          }
   	     }
   	  	
   	 System.out.println("rank \t relevent documents\n");
   		for(int i=0;i<ranks.size();i++){
   			
   			System.out.println(ranks.get(i)+"\t"+docname.get(rank_id_pair.get(ranks.get(i))));
   		}

   	}
	//making word_position pair
	HashMap<String,Integer> word_position_pair = new HashMap<String, Integer>();
	int position=0;
	for(String words:hm.keySet()){
	word_position_pair.put(words,position );
	position++;
	}
	
	//making Q0 
	
	HashMap<Integer,Double> q0_map = new HashMap< Integer,Double>();
int index=0;
	for(String i:hm.keySet()){
	q0_map.put( index, 0.0);
	index++;
	
	}
	for(String s:words_list){
	int pos=word_position_pair.get(s);	
	
	double val= q0_map.get(pos);
	val=val+1;
	System.out.println(val);
	q0_map.put(pos,val);
	}
	
	
	double alpha=0.25;
	double beta= 0.75;
	
	
	
	
	//making sum of relevent documents
	int index1=0;
	HashMap<Integer, Double > rel_vector= new HashMap<Integer, Double >();
	for(String i:hm.keySet()){
		rel_vector.put( index1, 0.0);
	index1++;
	}
	
for(int i=0;i<ranks.size();i++){
	for(String word:hm.keySet()){
	for(int did:hm.get(word).keySet() ){
		
	
		if(did==rank_id_pair.get(ranks.get(i))){
			
			int pos=word_position_pair.get(word);	
			System.out.println(pos);
			double val= rel_vector.get(pos);
			val=val+(alpha*(hm.get(word).get(did))/ranks.size());
		rel_vector.put(pos,val);
		}
	}
	}
	}


   // making sum of non-relevent documents
int index2=0;
HashMap<Integer, Double > non_rel_vector= new HashMap<Integer, Double >();
for(String i:hm.keySet()){
	non_rel_vector.put( index2, 0.0);
index2++;
}

for(int i=0;i<ranks.size();i++){
for(String word:hm.keySet()){
for(int did:hm.get(word).keySet() ){
	

	if(did==rank_id_pair.get(ranks.get(i))){
		
		int pos=word_position_pair.get(word);	
		double val= non_rel_vector.get(pos);
		val=val+(beta*hm.get(word).get(did)/nr_ranks.size());
	non_rel_vector.put(pos,val);
	}
}
}
}


// getting the new result Q-modified vector
int index3 = 0;
HashMap<Integer, Double >resultant_vector= new HashMap<Integer, Double >();
for(String i:hm.keySet()){
	resultant_vector.put( index3, 0.0);
index3++;
}

for(int i:resultant_vector.keySet()){
	
	double sum= q0_map.get(i)+rel_vector.get(i)-non_rel_vector.get(i);
	resultant_vector.put(i,sum);
}

Sort_map sort_map = new Sort_map();
HashMap<Object, Object> sorted_map = sort_map.get_sorted_map(resultant_vector);

ArrayList<String>sorted_words = new ArrayList<String>();

for(Object i:sorted_map.keySet()){
int j= (int)i;
for(String word:word_position_pair.keySet()){
	if(j==word_position_pair.get(word)){
		sorted_words.add(word);	}
	
}
}
ArrayList<String>query = new ArrayList<String>();
for(int ind=0;ind<20;ind++){
	query.add(sorted_words.get(ind));
}

TfIdfCalculator cti=new	TfIdfCalculator(hm,query,docname.size());
HashMap<Integer, Double> backup = cti.tfidf();
System.out.println(backup);

//sorting the documents according to the tf_idf_weights

	
               sortedHmap1 = sort_map.get_sorted_map(backup);
	

	//printing the result
			
			System.out.print("rank\t\t doc_name\t\t tf-idf\t\t title\n\n");
		
			int rank1=1;
		for(Object i:sortedHmap1.keySet()){
			int j=(int) (i);
			
			rank_id_pair.put(rank1,(int)i);
			System.out.println(rank1+"\t\t "+docname.get(j)+"\t\t "+sortedHmap1.get(i)+"\t\t "+titlename.get(j));
			rank1++;
		}
			
		
}

}
  


