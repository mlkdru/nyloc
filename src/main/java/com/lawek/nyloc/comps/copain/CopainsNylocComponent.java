package com.lawek.nyloc.comps.copain;

import com.lawek.nyloc.contracts.IComponent;

public class CopainsNylocComponent implements IComponent
{
	@SuppressWarnings( "unchecked" )
	@Override
	public <T> T queryFace( Class<T> clazz )
	{
		return (T) this;
	}
}
