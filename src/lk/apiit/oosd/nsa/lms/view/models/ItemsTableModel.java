package lk.apiit.oosd.nsa.lms.view.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import lk.apiit.oosd.nsa.lms.controller.DataHandler;
import lk.apiit.oosd.nsa.lms.model.types.Item;

public class ItemsTableModel extends AbstractTableModel
{
	
	private static final long	serialVersionUID	= -287234679073243403L;
	
	List<Item>					items				= new ArrayList<Item>();
	
	public ItemsTableModel()
	{
		for (Item i : DataHandler.getInstance().getAllItems())
			items.add(i);
	}
	
	@Override
	public int getColumnCount()
	{
		
		return 5;
	}
	
	@Override
	public int getRowCount()
	{
		
		return items.size();
	}
	
	@Override
	public Object getValueAt(int arg0, int arg1)
	{
		Item i = items.get(arg0);
		
		switch (arg1)
		{
			case 0:
				return i.getItemCode();
			case 1:
				return i.getItemType();
			case 2:
				return i.toString();
			case 3:
				return i.getDuplicates();
			case 4:
				return i.getAvailableCopies();
		}
		return null;
	}
	
	public String getColumnName(int col)
	{
		switch (col)
		{
			case 0:
				return "Item Code";
			case 1:
				return "Type";
			case 2:
				return "Description";
			case 3:
				return "Duplicates";
			case 4:
				return "Available";
		}
		return null;
		
	}
	
}
