package com.lawek.nyloc.comps.neo4j;

import org.neo4j.cypher.ExecutionResult;

import com.lawek.nyloc.contracts.IComponent;

public interface INeo4j extends IComponent
{
	/**
	 * Executes a NEO4J Cypher query and return the result
	 * 
	 * @param query
	 * @return
	 */
	ExecutionResult exec( String query );
}
