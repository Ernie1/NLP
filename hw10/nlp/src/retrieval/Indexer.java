package retrieval;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Indexer {

   private IndexWriter writer;

   public Indexer(String indexDirectoryPath) throws IOException{
      //this directory will contain the indexes
      Directory indexDirectory = 
    		  FSDirectory.open(FileSystems.getDefault().getPath(indexDirectoryPath));

      //create the indexer
      writer = new IndexWriter(indexDirectory, 
         new IndexWriterConfig(new SmartChineseAnalyzer()));
   }

   public void close() throws CorruptIndexException, IOException{
      writer.close();
   }

   private Document getDocument(File file) throws IOException{
	   Document document = new Document(); 
	   FieldType analyzed = new FieldType();
	   analyzed.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS); 
	   analyzed.setStored(true); 
	   analyzed.setStoreTermVectors(true);
	   analyzed.setStoreTermVectorOffsets(true);
	   analyzed.setStoreTermVectorPayloads(true);
	   analyzed.setStoreTermVectorPositions(true);
	   analyzed.setTokenized(true);
	   String text = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
      
      //index file contents
      Field contentField = new Field(LuceneConstants.CONTENTS,
    		  text,
    		  analyzed);
      //index file name
      Field fileNameField = new Field(LuceneConstants.FILE_NAME,
    		  file.getName(),
    		  analyzed);
      //index file path
      Field filePathField = new Field(LuceneConstants.FILE_PATH,
    		  file.getCanonicalPath(),
    		  analyzed);

      document.add(contentField);
      document.add(fileNameField);
      document.add(filePathField);

      return document;
   }   

   private void indexFile(File file) throws IOException{
      System.out.println("Indexing "+file.getCanonicalPath());
      Document document = getDocument(file);
      writer.addDocument(document);
   }

   public int createIndex(String dataDirPath, FileFilter filter) 
      throws IOException{
      //get all files in the data directory
      File[] files = new File(dataDirPath).listFiles();

      for (File file : files) {
         if(!file.isDirectory()
            && !file.isHidden()
            && file.exists()
            && file.canRead()
            && filter.accept(file)
         ){
            indexFile(file);
         }
      }
      return writer.getDocStats().numDocs;
   }
}