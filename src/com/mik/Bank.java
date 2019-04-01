package com.mik;

import java.util.ArrayList;

public class Bank {
	
	ArrayList <Account> list = new ArrayList<>();
	Serialize_IO io = new Serialize_IO();
	Account a1 = new Account ("1009",500,00,"10099001");
	Account a2 = new Account ("1001",500,00,"10011001");
	Account a3 = new Account ("1002",1000,00,"10022001");
	
	public void Init ()
	{
		list.add(a1);
		list.add(a2);
		list.add(a3);
	}
	
	public void AddAccount (Account a)
	{		
		list.add(a);
		
		io.writeSerializeListObject(list);
	}
	
	public boolean CheckAccount(String accNum)
	{
		return io.readDeserializeListObject(accNum);
	}
	
	public void PrintAccountInfo (String accNum)
	{
		
		
	}
	
	public boolean checkPassword(String pass)
	{
		
		return io.readDeserializeListObject(pass);
	}
}
