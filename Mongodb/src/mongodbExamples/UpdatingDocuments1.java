package mongodbExamples;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bson.Document;
import com.mongodb.MongoClient;
public class UpdatingDocuments1 {
   public static void main( String args[] ) {
      // Creating a Mongo client
      MongoClient mongo = new MongoClient( "localhost" , 27017 );
      //Connecting to the database
      MongoDatabase database = mongo.getDatabase("myDatabase");
      //Creating a collection object
      MongoCollection<Document>collection = database.getCollection("myCollection");
      //Preparing documents
      Document document1 = new Document("name", "Ram").append("age", 26).append("city", "Hyderabad");
      Document document2 = new Document("name", "Robert").append("age", 27).append("city", "Vishakhapatnam");
      Document document3 = new Document("name", "Rhim").append("age", 30).append("city", "Delhi");
      Document document4 = new Document("name", "Robert").append("age", 30).append("city", "Delhi");//Inserting the created documents
      List<Document> list = new ArrayList<Document>();
      list.add(document1);
      list.add(document2);
      list.add(document3);
      list.add(document4);
      collection.insertMany(list);
      //Updating a document
      collection.updateMany(Filters.eq("name", "Robert"), Updates.set("city", "Delhi"));
      System.out.println("Document update successfully...");
      FindIterable<Document> iterDoc = collection.find();
      Iterator it = iterDoc.iterator();
      while (it.hasNext()) {
         System.out.println(it.next());
      }
   }
}