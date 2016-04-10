package ir;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;



public class Tf_idf extends exe_indexer{
	static ArrayList<String>words_list=new ArrayList<String>() ;
	
	static HashMap<Object,Object> sortedHmap = new LinkedHashMap<Object,Object>();
	static HashMap<Integer, Integer> rank_id_pair= new HashMap<Integer, Integer>();
	public static void main(String[] args) throws IOException {
	
		Scanner sc2 = new Scanner(System.in);

		index_up();
		Scanner sc3 = new Scanner(System.in);
	  	System.out.println("---------------------------------------------");
	    
	    System.out.println("do you want to get tf-idf ?" +
	    		"\n press 1 for yes" +
	    		"\n press 0 for no");
	    System.out.println("---------------------------------------");

	    int num =sc3.nextInt();
	    
	    if(num==1){
	    	System.out.println("enter the query");
	    	String str = sc2.nextLine();
	    	String[] str1=str.split(" ");     
	    	
	    	for(int i=0;i<str1.length;i++){
	    		if(!check_stop_words.check_word(str1[i])){
	    				
	    			if(stem_y_n=="y"){
	    			      str1[i]=stemmer.stem(str1[i]);
	    	          
	    				}
	    					words_list.add(str1[i]);
	    		        
	    		}
	    	   
	        }
	    	Calc_tf_idf cti=new	Calc_tf_idf(words_list,hm);
	 HashMap<Integer, Double> backup = cti.get_tf_idf();
	 System.out.println(backup);
	 
	
	

	
	//sorting the documents according to the tf_idf_weights
	
	Sort_map sort_map= new Sort_map();
	sortedHmap=sort_map.get_sorted_map(backup);
	

//printing the result
		
		System.out.print("rank\t\t doc_name\t\t tf-idf\t\t title\n\n");
	
		int rank=1;
	for(Object i:sortedHmap.keySet()){
		int j=(int) (i);
		
		rank_id_pair.put(rank,(int)i);
		System.out.println(rank+"\t\t "+docname.get(j)+"\t\t "+sortedHmap.get(i)+"\t\t "+titlename.get(j));
		rank++;
	}
		
	    
	    
	    }
	    
	}
}