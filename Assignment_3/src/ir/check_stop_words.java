package ir;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicScrollPaneUI.HSBChangeListener;

public class  check_stop_words{
static HashMap<String,Integer> stop_words = new HashMap<String,Integer>();
	
public static void get_stop_words(File  s) throws FileNotFoundException{
		Scanner input1 = new Scanner(s); 
		while(input1.hasNext()) {
		   String word = input1.next();
	       stop_words.put(word,1);
	      
	}
}

public static boolean check_word(String str){
	if(stop_words.containsKey(str)){
		return true;
		}
	
	else {
		return false;
	}
}	
}
