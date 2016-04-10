package ir;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class query_filter {
	
	public static String filter_from_doc(int pos) throws IOException{
		Scanner sc = new Scanner(System.in);
		HashMap< Integer,String>query_list = new HashMap< Integer,String>(); 
		File query_path;
		  
		 boolean tf;
	do{
		System.out.println("enter the correct path of query file");
	String	f1= sc.nextLine();
	query_path = new File(f1);
            tf= query_path.isFile();
		}while(tf==false);   
 HashMap<Integer,String> id_num_map=new HashMap<Integer, String>(); 
	int count=1;int num=0;
			int i=0;
	  BufferedReader in = new BufferedReader(new FileReader(query_path));
      String str;
      ArrayList<String> list = new ArrayList<String>();
      while((str = in.readLine()) != null){
          list.add(str);
         String  id_num[] =  list.get(i).split("-", 2);
       String j=id_num[0];
       int k= Integer.parseInt(j);
         id_num_map.put(k, id_num[1]);
         System.out.println("number is"+j); 
         
      System.out.println(list.get(i));
      
      i++;
      }
	    
	   
      for(Integer id:id_num_map.keySet()){
    	  System.out.println(id+":"+id_num_map.get(id));
      }
      
	 
	    
	System.out.println("query is:"+id_num_map.get(pos));
return id_num_map.get(pos);
}
}