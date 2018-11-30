// Hey guys! It's Frank here, I've completed the "GetProducts()" method
// which you can use as a model for completing the other methods!
//
// Some you can copy/paste with some minor tweaks, while others
// may require you to do some light digging into the documentation.
//
// You should all be able to complete all the required methods
// EXCEPT for MakeOrder(), which I will handle, AND
// SearchProductReviews(), a method that we're NOT
// required to complete.
//
// DISCLAIMER: The GetProducts() method won't work until you
// re-downdload the "Products.json" file from GitHub, and
// use that in your database!
//
// If you have any questions you have my phone #!
import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.*;

import org.bson.Document;
import java.util.*;

import javax.swing.JOptionPane;

public class DatabaseAccess {


   // "localhost" specifies host running in your computer's terminal
   // Change from "localhost" once we get one from Instructor.
   private static final String CLIENT_URL = "mongodb://localhost:27017"; 

   // Change if you want to use another Database
   private static final String DB_NAME = "project2";

   private static MongoClient myClient;
   private static MongoCollection<Document> collection;

	
   // Put any code you want to test into this method, and hit the red "Run" button.
   // THIS SHOULD NOT BE IN THE FINAL VERSION OF THE PROGRAM!
   public static void main(String[] args)
   {

   }
   
   // Establishes connection to MongoDB host, database, and the collection which 
   // is specified by given collectionName (EX: use "Customers" to access Customers collection).
   //
   // USE THIS BEFORE YOU START QUERYING A COLLECTION! TO SWITCH COLLECTIONS, CALL close(),
   // THEN CALL open().
   private static void open(String collectionName)
   {
      if (myClient == null) {
         myClient = MongoClients.create(CLIENT_URL);
      }
      MongoDatabase database = myClient.getDatabase(DB_NAME);
      collection = database.getCollection(collectionName);            
   }
   
   // Closes all connections with MongoDB host. Resets stored collection to null.
   //
   // USE THIS AT THE END OF A METHOD, OR WHEN YOU WANT TO SWITCH COLLECTIONS!
   private static void close()
   {
      myClient.close();
      myClient = null;
      collection = null;
   }
   
	public static Order [] GetPendingOrders()
	{
		// TODO:  Query the database and retrieve the information.
		// resultset.findcolumn(string col)
		
		/* DUMMY DATA!
		Order o = new Order();
		o.OrderID = 1;
		o.Customer = new Customer();
		o.Customer.CustomerID = 1;
		o.Customer.Name = "Kevin";
		o.Customer.Email = "kevin@pathology.washington.edu";
		o.OrderDate = new Date();
		o.Status = "ORDERED";
		o.TotalCost = 520.20;
		o.BillingAddress = "1959 NE Pacific St, Seattle, WA 98195";
		o.BillingInfo	 = "PO 12345";
		o.ShippingAddress= "1959 NE Pacific St, Seattle, WA 98195";
		return new Order [] { o };
      */
      
      return null; // REMOVE THIS ONCE METHOD IS COMPLETE!
	}
	
   // THIS IS A COMPLETE EXAMPLE:
	public static Product[] GetProducts()
	{
      // Before you start, call the open() method, passing in the name
      // of the collection you want to access.
      open("Products");
      
      // You want to modify the .find() method call if you want
      // to query specific records. Check the documentation on how
      // to do that. Otherwise, leave it blank to get all records
      // in the collection.
      FindIterable<Document> contents = collection.find();
      
      MongoCursor<Document> cursor = contents.iterator();
      
      // Since this method returns an array, we want
      // to create one to store all our objects in which
      // we create using our records. If you're not
      // returning an array, leave this out.
      ArrayList<Product> results = new ArrayList<Product>();
      
      // If you're only dealing with a single record,
      // (i.e. returning a single object, NOT an array),
      // remove the "while" loop and just use its contents.
      while (cursor.hasNext()) {
         Document record = cursor.next();
         
         // Create object, and specify values within object by extracting values 
         // from each record in order to create the object. Check the .java file
         // of the object to see what values you need.
         Product p = new Product();
         p.ProductID = record.getInteger("_id");
         p.InStock = record.getInteger("inStock");
         p.Name = record.getString("name");
         
         // Confused on how to get values? 
         // Check: http://api.mongodb.com/java/current/org/bson/Document.html
         
         p.Price = record.getDouble("price"); // TO DO: add ".00" to end of price in Products,
                                              // collection, otherwise this won't work.
         p.Description = record.getString("description");
         
         // Don't worry about these 4 lines of code, you won't have to do this
         // FYI these lines of code took me like 30 minutes to figure out =(
         ArrayList<String> list = new ArrayList<String>(); 
         list = record.get("userComments", list);
         String[] newList = new String[list.size()];
         newList = list.toArray(newList);
         
         p.UserComments = newList;
        
         p.Relavance = record.getDouble("relevance");  // TO DO: make sure "relavance" field 
                                                       // in "Products" collection is spelled
                                                       // correctly.    
         // Adds object to array.
         results.add(p);
      }
      
      // Always close() when you're done, or you want to switch collections.
      // Call close() BEFORE the return statement.
      close();
      
      // We can't just return results, since Java wants an array, NOT
      // an ArrayList. We circumvent this by just converting it to
      // an array before returning it. You don't need to do this
      // if you're returning a single object, just return it.
      Product[] newResults = new Product[results.size()];
      newResults = results.toArray(newResults);
      return newResults;
      
		/* DUMMY VALUES
		Product p = new Product();
		p.Description = "A great monitor";
		p.Name = "Monitor, 19 in";
		p.InStock = 10;
		p.Price = 196;
		p.ProductID = 1;
		return new Product [] { p } ;
      */
	}


   // Refer to this link on how to query specific records using .find()
   // http://mongodb.github.io/mongo-java-driver/3.9/driver/tutorials/perform-read-operations/
   //
   // Note that for methods like these, you are returning a single object, NOT
   // an array!  vvvv 
	public static Order GetOrderDetails(int OrderID)
	{
		// TODO:  Query the database to get the flight information as well as all 
		// the reservations.
      /*      
		// DUMMY DATA FOLLOWS
		Order o = new Order();
		o.OrderID = 1;
		o.Customer = new Customer();
		o.Customer.CustomerID = 1;
		o.Customer.Name = "Kevin";
		o.Customer.Email = "kevin@pathology.washington.edu";
		o.OrderDate = new Date();
		o.Status = "ORDERED";
		o.TotalCost = 520.20;
		o.BillingAddress = "1959 NE Pacific St, Seattle, WA 98195";
		o.BillingInfo	 = "PO 12345";
		o.ShippingAddress= "1959 NE Pacific St, Seattle, WA 98195";

      LineItem li = new LineItem();
		li.Order = o;
		li.PricePaid = 540.00;
		li.Product = new Product();
		li.Product.Description = "A great product.";
		li.Product.Name = "Computer Mouse";
		li.Quantity = 2;
      
		o.LineItems = new LineItem[] {li};
		return o;
      */
      
      return null; // REMOVE THIS ONCE METHOD IS COMPLETE!
	}


   // Refer to this link on how to query specific records using .find()
   // http://mongodb.github.io/mongo-java-driver/3.9/driver/tutorials/perform-read-operations/
	public static Product GetProductDetails (int ProductID)
	{
      /*
		Product p = new Product();
		p.Description = "A great monitor";
		p.Name = "Monitor, 19 in";
		p.InStock = 10;
		p.Price = 196;
		p.ProductID = ProductID;
		p.UserComments = new String [] { "I bought this product last year and it's still the best monitor I've had.", "After 6 months the color started going out, not sure if it was just mine or all of them" };
		
		return p;
		*/
      return null;
	}
	
	public static Customer [] GetCustomers ()
	{
		/*
		// DUMMY VALUES FOLLOW
		Customer c1 = new Customer();
		c1.CustomerID = 1;
		c1.Email = "k@u";
		c1.Name = "Kevin Fleming";
		
		Customer c2 = new Customer();
		c2.CustomerID = 2;
		c2.Email = "k@u";
		c2.Name = "Niki Cassaro";

		Customer c3 = new Customer();
		c3.CustomerID = 3;
		c3.Email = "k@u";
		c3.Name = "Ava Fleming";
		
		return new Customer [] { c1, c2, c3 };
      */
      
      return null;
	}
	
   // Refer to this link on how to query specific records using .find()
   // http://mongodb.github.io/mongo-java-driver/3.9/driver/tutorials/perform-read-operations/
   //
   // HINT: you can access the customerID passed in with "c.CustomerID"
	public static Order [] GetCustomerOrders (Customer c)
	{
		Order o = new Order();
		o.OrderID = 1;
		o.Customer = new Customer();
		o.Customer.CustomerID = 1;
		o.Customer.Name = "Kevin";
		o.Customer.Email = "kevin@pathology.washington.edu";
		o.OrderDate = new Date();
		o.Status = "ORDERED";
		o.TotalCost = 520.20;
		o.BillingAddress = "1959 NE Pacific St, Seattle, WA 98195";
		o.BillingInfo	 = "PO 12345";
		o.ShippingAddress= "1959 NE Pacific St, Seattle, WA 98195";

		return new Order [] { o };
	}
   
   // Don't worry about this.	
	public static Product [] SearchProductReviews(String query)
	{
		// DUMMY VALUES
		Product p = new Product();
		p.Description = "A great monitor";
		p.Name = "Monitor, 19 in";
		p.InStock = 10;
		p.Price = 196;
		p.ProductID = 1;
		p.Relavance = 0.7;
		return new Product [] { p} ;
	}
	                    
   // I will do this one.                    
	public static void MakeOrder(Customer c, LineItem [] LineItems)
	{
		// TODO: Insert data into your database.
		// Show an error message if you can not make the reservation.
		
		JOptionPane.showMessageDialog(null, "Create order for " + c.Name + " for " + Integer.toString(LineItems.length) + " items.");
	}
}
