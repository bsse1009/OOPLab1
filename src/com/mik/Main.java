package com.mik;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
	public static void main (String [] args) throws IOException,ClassNotFoundException
	{
		Bank bank = new Bank();
		bank.Init();
		
		while (true)
		{
			Scanner sc = new Scanner(System.in);
			
			ServerSocket server = new ServerSocket(1009);
			
			Socket client = server.accept();
			ObjectInputStream oIs = new ObjectInputStream(client.getInputStream());
			String msg = (String) oIs.readObject();
			System.out.println(client.getRemoteSocketAddress().toString() + " : " + msg);
			
			ObjectOutputStream oUs = new ObjectOutputStream(client.getOutputStream());
		
			String msg2 = "1.Create Account"
					+ "2.Log in";
			
			if (msg.equals("1"))
			{
				msg2 = "Input Account Number , initial amount , loan , password to create an account";
				oUs.writeObject(msg2);
			}
			if (msg.equals("2"))
			{
				msg2 = "Enter account Number";
				oUs.writeObject(msg2);
				msg = (String) oIs.readObject();
				System.out.println(client.getRemoteSocketAddress().toString() + " : " + msg);
				
				msg2 = "Enter password";
				oUs.writeObject(msg2);
				
				msg = (String) oIs.readObject();
				System.out.println(client.getRemoteSocketAddress().toString() + " : " + msg);
				
				if (bank.CheckAccount(msg))
				{
					msg2 = "Account Axist !"
							+ "to manupulate some operation within this Account please enter password : ";
					oUs.writeObject(msg2);
					msg = (String) oIs.readObject();
					System.out.println(client.getRemoteSocketAddress().toString() + " : " + msg);
					
					if (bank.checkPassword(msg))
					{
						msg2 = "Succesfully logged in ! "
								+ "1.Withdraw"
								+ "2.Deposit";
					}
					else msg2 = "logged in failed ! ";
					oUs.writeObject(msg2);
				}
			}
		}
		/*
		while(true)
		{						
			System.out.println("Input Data to create a Account");
			String accNum = sc.nextLine();
			double amount = sc.nextDouble();
			double loan = sc.nextDouble();
			String pass = sc.nextLine();
			Account a = new Account(accNum,amount,loan,pass);
			bank.AddAccount(a);
			System.out.println("Enter a account Number : ");
			
			//accNum = sc.nextLine();
			
			//bank.CheckAccount(accNum);
			
			
		}
		*/
	}
}