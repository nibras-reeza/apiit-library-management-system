package lk.apiit.oosd.nsa.lms.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lk.apiit.oosd.nsa.lms.model.people.Patron;
import lk.apiit.oosd.nsa.lms.model.people.Staff;
import lk.apiit.oosd.nsa.lms.model.people.User;
import lk.apiit.oosd.nsa.lms.model.types.Item;
import lk.apiit.oosd.nsa.lms.model.types.Transaction;

public class DataStore implements Serializable
{
	
	private static final long	serialVersionUID	= -1595337273483986989L;
	
	List<Patron>				patrons;
	List<Item>					items;
	List<Staff>					staff;
	List<Transaction>			activeTransactions;
	List<Transaction>			pastTransactions;
	List<User>					users;
	
	public DataStore()
	{
		patrons = new ArrayList<Patron>();
		items = new ArrayList<Item>();
		staff = new ArrayList<Staff>();
		activeTransactions = new ArrayList<Transaction>();
		pastTransactions = new ArrayList<Transaction>();
		users = new ArrayList<User>();
	}
}