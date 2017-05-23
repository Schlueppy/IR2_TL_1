package LocationSearch;
import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

public class Lucene {
    public static void main(String[] args) throws IOException, ParseException {
        // 0. Specify the analyzer for tokenizing text.
        //    The same analyzer should be used for indexing and searching
        StandardAnalyzer analyzer = new StandardAnalyzer();

        // 1. create the index
        Directory index = new RAMDirectory();

        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter w = new IndexWriter(index, config);
        
        // Die Top Ten als String hinzufügen
        addDoc(w, "Lucene in Action", "bamberg teasdst");
        addDoc(w, "Lucene for Dummies", " bamberg Lindau");
        addDoc(w, "Managing Gigabytes", "bamberg");
        addDoc(w, "The Art of Computer Science", "test 9900333X");
        w.close();
        
        
        CSVReader readerCsv = new CSVReader();
        ArrayList <String> list = readerCsv.CsvReader();
       
        
        
        
        IndexReader reader = DirectoryReader.open(index);
        
        for(int x=0;x<list.size();x++){
        	
        // 2. query
        String querystr = list.get(x);
        
        Query q = new QueryParser("content", analyzer).parse(querystr);

        // 3. search
        int hitsPerPage = 99999;
       
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(q, hitsPerPage);
        ScoreDoc[] hits = docs.scoreDocs;
        
        if(hits.length == 0){
        	
        } else {
        
        System.out.println(list.get(x)+ " found " +hits.length + " times.");
        }
        }
        reader.close();
        
    }

    private static void addDoc(IndexWriter w, String title, String content) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("title", title, Field.Store.YES));

        // use a string field for isbn because we don't want it tokenized
        doc.add(new TextField("content", content, Field.Store.YES));
        w.addDocument(doc);
    }
}