package retrieval;

import java.io.IOException;

public class CreateIndex {
	
   String indexDir = "data/index";
   String dataDir = "data/page";
   Indexer indexer;

   public static void main(String[] args) {
      try {
    	new CreateIndex().createInvertedIndex();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   private void createInvertedIndex() throws IOException{
	   indexer = new Indexer(indexDir);
	   int numIndexed;
	   long startTime = System.currentTimeMillis();	
	   numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
	   long endTime = System.currentTimeMillis();
	   indexer.close();
	   System.out.println(numIndexed+" File indexed, time taken: "
	      +(endTime-startTime)+" ms");
   }

}