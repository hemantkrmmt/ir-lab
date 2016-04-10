package ir;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class index_print {

	public static void print(HashMap<String,HashMap<Integer,Integer>>  hm ){
		 for(String name: hm.keySet()){
			
				String key=name.toString();
				HashMap<Integer, Integer>value= new HashMap<Integer,Integer>();
			     System.out.print(key+"--->");
				value= hm.get(name);
				
				 for(Integer keys: value.keySet()){
				
					System.out.print("("+ keys+":"+value.get(keys)+")  , ");
				}
			
			System.out.println();   			
			}	
	}
	
	
	
}
