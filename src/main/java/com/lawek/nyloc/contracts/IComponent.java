package com.lawek.nyloc.contracts;

/**
 * The basic contract for a Nyloc component
 * 
 */
public interface IComponent
{
	/**
	 * Queries a specific interface on this component
	 * 
	 * @param clazz The interfaces's class object
	 * @return The corresponding instance
	 */
	<T> T queryFace( Class<T> clazz );
}
