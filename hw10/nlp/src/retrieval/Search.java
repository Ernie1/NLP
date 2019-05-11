package retrieval;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.IOException;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.spell.Dictionary;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Search {
	
   String indexDir = "data/index";
   String spellDir = "data/spell";
   
   Searcher searcher;

   public static void main(String[] args) {
      try {
    	 new Search().search();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ParseException e) {
         e.printStackTrace();
      }
   }
   
   private String[] check(String text) throws IOException{
	  Directory directory = FSDirectory.open((new File(spellDir)).toPath());
	  SpellChecker spellChecker = new SpellChecker(directory);
	  IndexReader reader = DirectoryReader.open(FSDirectory.open((new File(indexDir)).toPath()));
	  Dictionary dictionary = new LuceneDictionary(reader, LuceneConstants.CONTENTS);
	  IndexWriterConfig config = new IndexWriterConfig(new SmartChineseAnalyzer());
	  spellChecker.indexDictionary(dictionary, config, true);
	  String[] suggestions = spellChecker.suggestSimilar(text, 5);
	  reader.close();
	  spellChecker.close();
	  directory.close();
	  return suggestions;
   }

   private void search()
      throws IOException, ParseException{
	  searcher = new Searcher(indexDir);
	  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	  while(true){
		 System.out.println("Search:");
	     long startTime = System.currentTimeMillis();
	     String text = br.readLine();
	     if(text.length() == 0)
	    	 continue;
	     String[] suggestions = check(text);
	     if(suggestions.length > 0){
	    	 System.out.println("Did you mean:");
	    	 for(int i = 0; i < suggestions.length; ++i)
	    		 System.out.println("[" + i + "] " + suggestions[i]);
	    	 System.out.println("Input digit AND/OR press enter to continue:");
	    	 String in = br.readLine();
	    	 if (in.length() > 0 && in.charAt(0) - '0' < suggestions.length)
	    		 text = suggestions[in.charAt(0) - '0'];
	     }
	     System.out.println("Search " + text);
	     TopDocs hits = searcher.search(text);
	     long endTime = System.currentTimeMillis();
	  
	     System.out.println(hits.totalHits +
         " documents found. Time :" + (endTime - startTime));
	     for(ScoreDoc scoreDoc : hits.scoreDocs) {
	        Document doc = searcher.getDocument(scoreDoc);
	           System.out.println("File: "
	           + doc.get("filepath"));
	     }
	  }
   }
}