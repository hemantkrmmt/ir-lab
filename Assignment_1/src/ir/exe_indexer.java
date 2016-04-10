package ir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class exe_indexer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	static HashMap<Integer, String> docname = new HashMap<Integer, String>();
	static HashMap<Integer, String> titlename = new HashMap<Integer, String>();
	
	public static HindiStemmerLight stemmer = new HindiStemmerLight();
	public static HashMap<String, HashMap<Integer, Integer>>hm =new HashMap<String, HashMap<Integer,Integer>>();
	public static String stem_y_n;
	
	
	public static void main(String[] args) throws IOException{
	// TODO Auto-generated method stub
		
		docname= indexer.docname;
		titlename=indexer.titlename;
		
		Scanner sc1 = new Scanner(System.in);
		String stop_words_file;
			
		 String folder_files;
		 File f1;
		  
			 boolean tf;
		do{
			System.out.println("enter the correct path of stopwords file");
			stop_words_file= sc1.nextLine();
			 f1 = new File(stop_words_file);
	            tf= f1.isFile();
			}while(tf==false);   
	       
		
	
	check_stop_words.get_stop_words(f1);
	
	
	do{
		System.out.println("enter the correct path of folder where the files exist");
		 folder_files = sc1.nextLine();
		 File f = new File(folder_files);
         tf= f.isDirectory();
		}while(tf==false) ;  
    
	System.out.println("Do you want to use stemming \n click on 'y' for yes\n click on 'n' for no");
		 stem_y_n=sc1.next();
	
	hm	= indexer.index_words(folder_files,stem_y_n);
	
	
	System.out.println("/n printing the indexed words and\n the file names and frequency of that word in the document");
	index_print.print(hm);	
	
  	System.out.println("---------------------------------------------");
    
	}
	
	}
	
	
	
	
	



	
		


