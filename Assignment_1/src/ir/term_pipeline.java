package ir;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;


public class term_pipeline {


	
		
		static String query;
		int count=1;
	
		public term_pipeline(String content) {
			
			this.query=content;
			
			
		}
		// method for geting terms removing Stopwords
		public HashMap<String, Integer> getterms(String stem_y_n) throws IOException
		{
			HindiStemmerLight stemmer = new HindiStemmerLight();
				HashMap<String, Integer> words =new HashMap<String, Integer>();
				
				// tokenizing the string
				
				StringTokenizer st = new StringTokenizer(query);
			     while (st.hasMoreTokens()) {
			         String token= st.nextToken();
			    
				if(!check_stop_words.check_word(token))
					  {
					 if(stem_y_n.equalsIgnoreCase("y")){
			        	  token=stemmer.stem(token);
			        	  }
		            
						  if(words.containsKey(token))
						  {
							  int fr=words.get(token)+1;
							  words.put(token, fr);
						  }else
						  {
							  words.put(token,count);
					  
						  }
					  }
				   }
				 
			
			return words;
		}
		
			
	}

