package com.lawek;

import static spark.Spark.*;
import com.google.gson.Gson;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
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
