package com.lawek.nyloc.comps.copain;

import java.util.List;

import com.lawek.Copain;
import com.lawek.nyloc.contracts.IComponent;

public interface ICopain extends IComponent
{
	List<Copain> getCopains();
	Copain addCopain(String name, String address);
}
