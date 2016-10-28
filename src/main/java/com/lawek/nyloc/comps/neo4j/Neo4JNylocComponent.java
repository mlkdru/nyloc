package com.lawek.nyloc.comps.neo4j;

import org.neo4j.cypher.ExecutionEngine;
import org.neo4j.cypher.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.util.StringBuilderStringLogger;
import org.neo4j.kernel.impl.util.StringLogger;

import com.lawek.nyloc.contracts.IComponent;

public class Neo4JNylocComponent implements IComponent, INeo4j
{
	private GraphDatabaseService graphDb;
	
	public Neo4JNylocComponent()
	{
		init();
	}
	
	@SuppressWarnings( "unchecked" )
	@Override
	public <T> T queryFace( Class<T> clazz )
	{
		if( clazz == INeo4j.class )
			return (T) this;
		
		return null;
	}
	
	@Override
	public ExecutionResult exec( String query )
	{
		StringLogger logger = new StringBuilderStringLogger(new StringBuilder()); 
    	ExecutionEngine engine = new ExecutionEngine( graphDb, logger );

    	try ( Transaction ignored = graphDb.beginTx() )
    	{
    		ExecutionResult result = engine.execute( query );
    		
    		return result;
    	}
	}
	
	private void init()
	{
		graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( "neo4jdb" );
    	//registerShutdownHook( graphDb );
	}
}
