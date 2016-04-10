package ir;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class indexer {
static	HashMap<Integer, String> docname = new HashMap<Integer, String>();
static HashMap<Integer, String> titlename = new HashMap<Integer, String>();
	
	public static  HashMap<String, HashMap<Integer, Integer>> index_words(String folder_files, String stem_y_n) throws IOException {
		// TODO Auto-generated method stub
		 HashMap<String, HashMap<Integer, Integer>>hm =new HashMap<String, HashMap<Integer,Integer>>();
			
		File Directory=new File(folder_files);
		 File[] list = Directory.listFiles();
		 int docid=1,count=0;
			int n=0;
			for(File files: list){
                docname.put(docid, files.getName());
				Scanner cs1 = new Scanner(files);
					String content = cs1.useDelimiter("\\Z").next();
					final Pattern pattern = Pattern.compile("<title>(.+?)</title>");
					final Matcher matcher = pattern.matcher(content);
					matcher.find();
					titlename.put(docid,matcher.group(1));
					n++;
		        System.out.println(n);
					//removing regular expressions
					content= content.replaceAll("[<>()?:!।/.,'-;॥{}a-zA-Z0-9]", "");
			
					
				     term_pipeline tp = new term_pipeline(content);
				     HashMap<String, Integer> word=  tp.getterms(stem_y_n);
				  
				     
				     for(String s:word.keySet())
					    {
					    
					    	HashMap<Integer, Integer> hmap=new HashMap<Integer,Integer>();
					    	if(hm.containsKey(s))
					    	{
					    		int m=word.get(s);
					    		hm.get(s).put(docid,m);
					    	
					    	}else
					    	{
					    		hmap.put(docid,word.get(s));
					    		hm.put(s, hmap);
					    	}
					    	
					    }
					    docid++;
					}
				
			return hm ;
				     }
				    
			}
		
	