package com.lawek;

import static spark.Spark.*;

import java.util.Map;

import org.neo4j.cypher.ExecutionEngine;
import org.neo4j.cypher.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.helpers.collection.IteratorUtil;
import org.neo4j.kernel.impl.util.StringBuilderStringLogger;
import org.neo4j.kernel.impl.util.StringLogger;

import scala.collection.Iterator;

import com.google.gson.Gson;


/**
 * Hello world!
 *
 */
public class App 
{
	static void createNodeIfNotExist( GraphDatabaseService graphDb, String ip )
	{
		//String q = "MATCH (n:Copain) WHERE n.ip=\""+ip+"\" CREATE UNIQUE (n {ip: \""+ip+"\" })";
		String q = "MATCH (n:Copain) WHERE n.ip=\""+ip+"\" CREATE UNIQUE (c) RETURN n";
		exec( graphDb, q );
	}
	
	static ExecutionResult exec( GraphDatabaseService graphDb, String query )
	{
		StringLogger logger = new StringBuilderStringLogger(new StringBuilder()); 
    	ExecutionEngine engine = new ExecutionEngine( graphDb, logger );

    	try ( Transaction ignored = graphDb.beginTx() )
    	{
    		ExecutionResult result = engine.execute( query );
    		
    		return result;
    	}
	}
	
    public static void main( String[] args )
    {    	    	
    	
    	// Objets à stocker
    	Relationship relationship;

    	// Ouvrir la base    	
    	GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( "neo4jdb" );
    	//registerShutdownHook( graphDb );
    	
    	//createNodeIfNotExist( graphDb, "localhost" );
    	//createNodeIfNotExist( graphDb, "192.168.0.19" );
    	
//    	try ( Transaction tx = graphDb.beginTx() )
//    	{
//    		// Ecrire les Copain
//    		Node firstNode = graphDb.createNode(NodeLabels.COPAIN);
//        	firstNode.setProperty( "ip", "Hello, " );
//        	Node secondNode = graphDb.createNode();
//        	secondNode.setProperty( "message", "World!" );
//
//        	relationship = firstNode.createRelationshipTo( secondNode, RelTypes.KNOWS );
//        	relationship.setProperty( "message", "brave Neo4j " );
//        	
//        	System.out.print( firstNode.getProperty( "message" ) );
//        	System.out.print( relationship.getProperty( "message" ) );
//        	System.out.print( secondNode.getProperty( "message" ) );
//        	
//    	    tx.success();
//    	}
    	
    	
    	
    	StringLogger logger = new StringBuilderStringLogger(new StringBuilder()); 
    	ExecutionEngine engine = new ExecutionEngine( graphDb, logger );

//    	try ( Transaction ignored = graphDb.beginTx() )
//    	{
//    		ExecutionResult result = engine.execute( "match (n:Copain) return n" );
//    	    Iterator<Object> nodes = result.columnAs("n");
//    	    while(nodes.hasNext())
//    	    {
//	    	   Object obj = nodes.next();
//	    	   Node node = (Node) obj;
//	    	   
//	    	   System.out.println(node + " " + node.getProperty("message"));
//    	    }
//    	}
    	
		ExecutionResult result = exec( graphDb, "match (n:Copain) return n" );
	    Iterator<Object> nodes = result.columnAs("n");
	    while(nodes.hasNext())
	    {
    	   Object obj = nodes.next();
    	   Node node = (Node) obj;
    	   
    	   System.out.println(node + " " + node.getProperty("ip"));
	    }
    	
    	
    	
    	System.out.println( "fsdf" );    	
        System.out.println( "Hello World! Dru" );
        
        get("/hello/:nom", (req, res) -> 
        {
        	Gson gson = new Gson();
        	
        	Neuron n = new Neuron();
        	String nJ = gson.toJson(n);
        	
        	return nJ;
        } );
    }
}
