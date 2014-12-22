/*
 * This is part of the assignment for OOSD, September 2013
 * 
 * This is the data handler. It currently uses an internal
 * data store that uses ArrayLists. Ideally, this would be
 * replaced with code to write/read from a JPA store. Part
 * of database configuration is already done using Derby
 * and eclipselink.
 * 
 * References
 *	Javapractices.com. 2013. Java Practices -> Singleton. 
 *		[online] 
 *		Available at: http://www.javapractices.com/topic/TopicAction.do?Id=46 
 *		[Accessed: 19 Sep 2013]. 
 * 
 * */

package lk.apiit.oosd.nsa.lms.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import lk.apiit.oosd.nsa.lms.model.people.Patron;
import lk.apiit.oosd.nsa.lms.model.people.Staff;
import lk.apiit.oosd.nsa.lms.model.people.User;
import lk.apiit.oosd.nsa.lms.model.types.Item;
import lk.apiit.oosd.nsa.lms.model.types.Transaction;

// (Javapractices.com, 2013)
public class DataHandler
{
	
	private static DataHandler	instance	= null;
	
	private String				path		= "lms.dat";
	
	private DataStore			store;
	
	public static DataHandler getInstance()
	{
		if (instance == null)
			instance = new DataHandler();
		
		return instance;
	}
	
	private class DataSaver extends Thread
	{
		
		public void run()
		{
			synchronized (store)
			{
				try (ObjectOutputStream file = new ObjectOutputStream(
						new FileOutputStream(path)))
				{
					file.writeObject(store);
					file.flush();
					file.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		
	}
	
	private DataHandler()
	{
		
		try (ObjectInputStream file = new ObjectInputStream(
				new FileInputStream(path)))
		{
			store = (DataStore) file.readObject();
		}
		catch (IOException e)
		{
			store = new DataStore();
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Cannot find data in file!");
			e.printStackTrace();
			System.exit(1);
		}
		
		Runtime.getRuntime().addShutdownHook(new DataSaver());
		
	}
	
	public void add(Patron patron)
	{
		store.patrons.add(patron);
	}
	
	public List<Patron> find(Patron patron, Comparator<Patron> comparator)
	{
		List<Patron> results = new LinkedList<Patron>();
		
		for (Patron p : store.patrons)
			if (comparator.compare(p, patron) == 0)
				results.add(p);
		
		return results;
	}
	
	public boolean remove(Patron patron)
	{
		int index;
		
		Collections.sort(store.patrons, Patron.CompareBy.PatronId);
		
		index = Collections.binarySearch(store.patrons, patron,
				Patron.CompareBy.PatronId);
		
		return store.patrons.remove(index) != null;
	}
	
	public boolean update(Patron patron)
	{
		Collections.sort(store.patrons);
		int i = Collections.binarySearch(store.patrons, patron);
		
		if (i < 0)
			return false;
		
		store.patrons.set(i, patron);
		
		return true;
	}
	
	public void add(Item item)
	{
		store.items.add(item);
	}
	
	public List<Item> find(Item item, Comparator<Item> comparator)
	{
		List<Item> results = new LinkedList<Item>();
		
		for (Item p : store.items)
			if (comparator.compare(p, item) == 0)
				results.add(p);
		
		return results;
	}
	
	public boolean remove(Item item)
	{
		int index;
		
		Collections.sort(store.items, Item.CompareBy.itemCode);
		
		index = Collections.binarySearch(store.items, item,
				Item.CompareBy.itemCode);
		
		return store.items.remove(index) != null;
	}
	
	public boolean update(Item item)
	{
		Collections.sort(store.items);
		int i = Collections.binarySearch(store.items, item);
		
		if (i < 0)
			return false;
		
		store.items.set(i, item);
		
		return true;
	}
	
	public void add(Staff s)
	{
		store.staff.add(s);
	}
	
	public List<Staff> find(Staff s, Comparator<Staff> comparator)
	{
		List<Staff> results = new LinkedList<Staff>();
		
		for (Staff p : store.staff)
			if (comparator.compare(p, s) == 0)
				results.add(p);
		
		return results;
	}
	
	public boolean remove(Staff s)
	{
		int index;
		
		Collections.sort(store.staff, Staff.CompareBy.StaffId);
		
		index = Collections.binarySearch(store.staff, s,
				Staff.CompareBy.StaffId);
		
		return store.staff.remove(index) != null;
	}
	
	public boolean update(Staff s)
	{
		Collections.sort(store.staff);
		int i = Collections.binarySearch(store.staff, s);
		
		if (i < 0)
			return false;
		
		store.staff.set(i, s);
		
		return true;
	}
	
	public void add(Transaction transaction)
	{
		store.activeTransactions.add(transaction);
	}
	
	public List<Transaction> find(Transaction transaction,
			Comparator<Transaction> comparator)
	{
		List<Transaction> results = new LinkedList<Transaction>();
		
		for (Transaction p : store.activeTransactions)
			if (comparator.compare(p, transaction) == 0)
				results.add(p);
		
		return results;
	}
	
	public boolean remove(Transaction transaction)
	{
		int index;
		
		Collections.sort(store.activeTransactions,
				Transaction.CompareBy.transactionId);
		
		index = Collections.binarySearch(store.activeTransactions, transaction,
				Transaction.CompareBy.transactionId);
		
		if (store.activeTransactions.remove(index) != null)
		{
			store.pastTransactions.add(transaction);
			return true;
		}
		
		return false;
	}
	
	public boolean update(Transaction transaction)
	{
		Collections.sort(store.activeTransactions);
		int i = Collections.binarySearch(store.activeTransactions, transaction);
		
		if (i < 0)
			return false;
		
		store.activeTransactions.set(i, transaction);
		
		return true;
	}
	
	public void add(User user)
	{
		store.users.add(user);
	}
	
	public List<User> find(User user, Comparator<User> comparator)
	{
		List<User> results = new LinkedList<User>();
		
		for (User p : store.users)
			if (comparator.compare(p, user) == 0)
				results.add(p);
		
		return results;
	}
	
	public boolean remove(User user)
	{
		int index;
		
		Collections.sort(store.users, User.CompareBy.UserName);
		
		index = Collections.binarySearch(store.users, user,
				User.CompareBy.UserName);
		
		return store.users.remove(index) != null;
		
	}
	
	public boolean update(User user)
	{
		Collections.sort(store.users);
		int i = Collections.binarySearch(store.users, user);
		if (i < 0)
			return false;
		
		store.users.set(i, user);
		
		return true;
		
	}
	
	public Iterable<Patron> getAllPatrons()
	{
		return store.patrons;
	}
	
	public Iterable<Staff> getAllStaff()
	{
		return store.staff;
	}
	
	public Iterable<User> getAllUsers()
	{
		return store.users;
	}
	
	public Iterable<Item> getAllItems()
	{
		return store.items;
	}
	
	public List<Transaction> getAllActiveTransactions()
	{
		return store.activeTransactions;
	}
	
	public List<Transaction> getAllPastTransactions()
	{
		return store.pastTransactions;
	}
	
	public List<User> find(User u)
	{
		
		return find(u, User.CompareBy.UserName);
	}
	
}
