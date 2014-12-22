package lk.apiit.oosd.nsa.lms.view.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import lk.apiit.oosd.nsa.lms.controller.DataHandler;
import lk.apiit.oosd.nsa.lms.model.people.User;

public class MembersTableModel extends AbstractTableModel
{
	
	private static final long	serialVersionUID	= 4223688340896016227L;
	
	List<User>					users				= new ArrayList<User>();
	
	public MembersTableModel()
	{
		super();
		for (User u : DataHandler.getInstance().getAllUsers())
			users.add(u);
	}
	
	@Override
	public int getColumnCount()
	{
		return 10;
	}
	
	@Override
	public int getRowCount()
	{
		return users.size();
	}
	
	@Override
	public Object getValueAt(int arg0, int arg1)
	{
		User u = users.get(arg0);
		
		switch (arg1)
		{
			case 0:
				return u.getUsername();
			case 1:
				return u.getName().getFirstName();
			case 2:
				return u.getName().getLastName();
			case 3:
				return u.getAddress().getStreetAddress();
			case 4:
				return u.getAddress().getCity();
			case 5:
				return u.getAddress().getCountry();
			case 6:
				return u.getGender();
			case 7:
				return u.getContact().getTelephone();
			case 8:
				return u.getContact().getEmail();
			case 9:
				return u.getNic();
				
		}
		
		return " ";
		
	}
	
	public String getColumnName(int column)
	{
		switch (column)
		{
			case 0:
				return "User Name";
			case 1:
				return "First Name";
			case 2:
				return "Last Name";
			case 3:
				return "Street Address";
			case 4:
				return "City";
			case 5:
				return "Country";
			case 6:
				return "Gender";
			case 7:
				return "Telephone";
			case 8:
				return "EMail";
			case 9:
				return "NIC";
			default:
				return " ";
				
		}
	}
	
}
