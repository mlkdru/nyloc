package com.lawek.nyloc.comps;

import java.util.HashMap;
import java.util.Map;

import org.neo4j.cypher.InvalidArgumentException;

import com.lawek.nyloc.contracts.IComponent;

/**
 * Manages software components in order to provide the ability to come
 * with different packages for different execution platforms (android,
 * jvm)
 * 
 * TODO In future, components will be loaded from their own class loaders
 * 
 */
public class Components
{
	private static Components INSTANCE = new Components();
	
	public static Components comps()
	{
		return INSTANCE;
	}
	
	private Map<String, Component> components = new HashMap<>();
	
	private class Component
	{
		String name;
		String className;
		IComponent component;
	}
	
	/**
	 * Register a component by associating a name with an implementation.
	 * The registered class must implement {@link IComponent}.
	 * 
	 * @param name Name of the component to be registered
	 * @param className Class name of the component
	 */
	public void register( String name, String className )
	{
		if( components.containsKey( name ) )
			throw new InvalidArgumentException( "Already registered component " + name, null );
		
		Component c = new Component();
		c.name = name;
		c.className = className;
		
		components.put( name, c );
	}
	
	/**
	 * Retreive an instance of the queried component, or null if not registered.
	 * If the component has not yet been instanciated, the method does it
	 * automatically.
	 * 
	 * @param name Name of the component to be retrieved
	 * @return
	 */
	public IComponent get( String name )
	{
		Component c = components.get( name );
		if( c == null )
			return null;
		
		if( c.component == null )
		{
			try
			{
				Class<?> clazz = Class.forName( c.className );
				
				Object obj = clazz.newInstance();
				
				c.component = (IComponent) obj; 
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
		}
		
		return c.component;
	}
}
