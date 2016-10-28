package com.lawek;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import com.lawek.nyloc.comps.Components;
import com.lawek.nyloc.comps.neo4j.INeo4j;
import com.lawek.nyloc.contracts.IComponent;

/**
 * Hello world!
 *
 */
public class Nyloc
{
	static void createNodeIfNotExist( INeo4j neo4j, String ip )
	{
		String q = "MERGE (n:Copain {ip:\"" + ip + "\"}) RETURN n";
		neo4j.exec( q );
	}

	public static void main( String[] args )
	{
		// le coeur : un service web et un gestionnaire de composants

		// services annexes : annuaire, neo4j, sqlite

		// register services to be ran here
		Components.comps().register( "Neo4j", "com.lawek.nyloc.comps.neo4j.Neo4JNylocComponent" );
		Components.comps().register( "Copain", "com.lawek.nyloc.comps.copain.CopainsNylocComponent" );

		// test
		IComponent iNeo4j = Components.comps().get( "Neo4j" );
		INeo4j neo4j = iNeo4j.queryFace( INeo4j.class );

		createNodeIfNotExist( neo4j, "localhost" );
		createNodeIfNotExist( neo4j, "192.168.0.19" );

		// try ( Transaction tx = graphDb.beginTx() )
		// {
		// // Ecrire les Copain
		// Node firstNode = graphDb.createNode(NodeLabels.COPAIN);
		// firstNode.setProperty( "ip", "Hello, " );
		// Node secondNode = graphDb.createNode();
		// secondNode.setProperty( "message", "World!" );
		//
		// relationship = firstNode.createRelationshipTo( secondNode,
		// RelTypes.KNOWS );
		// relationship.setProperty( "message", "brave Neo4j " );
		//
		// System.out.print( firstNode.getProperty( "message" ) );
		// System.out.print( relationship.getProperty( "message" ) );
		// System.out.print( secondNode.getProperty( "message" ) );
		//
		// tx.success();
		// }

		// StringLogger logger = new StringBuilderStringLogger(new
		// StringBuilder());
		// ExecutionEngine engine = new ExecutionEngine( graphDb, logger );

		// try ( Transaction ignored = graphDb.beginTx() )
		// {
		// ExecutionResult result = engine.execute( "match (n:Copain) return n"
		// );
		// Iterator<Object> nodes = result.columnAs("n");
		// while(nodes.hasNext())
		// {
		// Object obj = nodes.next();
		// Node node = (Node) obj;
		//
		// System.out.println(node + " " + node.getProperty("message"));
		// }
		// }

		// ExecutionResult result = exec( graphDb, "match (n:Copain) return n"
		// );
		// Iterator<Object> nodes = result.columnAs("n");
		// while(nodes.hasNext())
		// {
		// Object obj = nodes.next();
		// Node node = (Node) obj;
		//
		// System.out.println(node + " " + node.getProperty("ip"));
		// }

		// Spark.get("/hello/:nom", (req, res) ->
		// {
		// Gson gson = new Gson();
		//
		// Neuron n = new Neuron();
		// String nJ = gson.toJson(n);
		//
		// return nJ;
		// } );

		Undertow server = Undertow.builder().addHttpListener( 8080, "localhost" ).setHandler( new HttpHandler()
		{
			@Override
			public void handleRequest( final HttpServerExchange exchange ) throws Exception
			{
				exchange.getResponseHeaders().put( Headers.CONTENT_TYPE, "text/plain" );
				exchange.getResponseSender().send( "Hello World" );
			}
		} ).build();
		server.start();
	}
}
