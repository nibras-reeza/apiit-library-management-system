package lk.apiit.oosd.nsa.lms.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.security.auth.login.LoginException;
import javax.swing.UIManager;

import lk.apiit.oosd.nsa.lms.controller.types.Permission;
import lk.apiit.oosd.nsa.lms.model.people.Patron;
import lk.apiit.oosd.nsa.lms.model.people.Staff;
import lk.apiit.oosd.nsa.lms.model.people.User;
import lk.apiit.oosd.nsa.lms.model.people.User.UserType;
import lk.apiit.oosd.nsa.lms.model.types.Item;
import lk.apiit.oosd.nsa.lms.model.types.Transaction;
import lk.apiit.oosd.nsa.lms.view.models.FrmItemModel;
import lk.apiit.oosd.nsa.lms.view.models.FrmUserModel;

public class MainController
{
	private static MainController	instance	= null;
	
	private MainController()
	{
	}
	
	public static MainController getInstance()
	{
		if (instance == null)
			instance = new MainController();
		
		return instance;
	}
	
	public void launch()
	{
		try
		{
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		UIFactory.getInstance().showMainWindow();
		// login();
		
	}
	
	// MY ACCOUNT
	// complete
	public void login()
	{
		try
		{
			AuthManager.getInstance().login();
		}
		catch (LoginException e)
		{
			UIFactory.getInstance().warn("Unable to login!", e.getMessage());
		}
		
		UIFactory.getInstance().enableAccountManagement(
				AuthManager.getInstance().hasPermission(
						Permission.MANAGE_ACCOUNTS));
		
		UIFactory.getInstance().enableItemManagement(
				AuthManager.getInstance()
						.hasPermission(Permission.MANAGE_ITEMS));
	}
	
	// Complete
	public void logout()
	{
		if (UIFactory.getInstance().confirm("Confirm Logout",
				"Are you sure you want to log out?"))
			AuthManager.getInstance().logout();
	}
	
	public void updateProfile()
	{
		if (!AuthManager.getInstance().hasPermission(Permission.MEMBER_ACTIONS))
		{
			UIFactory.getInstance().warn("Permission Denied",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		throw new UnsupportedOperationException();
		
	}
	
	public void changePassword()
	{
		if (!AuthManager.getInstance().hasPermission(Permission.MEMBER_ACTIONS))
		{
			UIFactory.getInstance().warn("Permission Denied",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		AuthManager.getInstance().changePassword();
	}
	
	public void exit()
	{
		if (!UIFactory.getInstance().confirm("Confirm exit: ",
				"Are you sure you want to exit?"))
			return;
		
		System.exit(0);
	}
	
	// MANAGE LOANS
	public void checkin()
	{
		if (!AuthManager.getInstance().hasPermission(Permission.MANAGE_LOANS))
		{
			UIFactory.getInstance().warn("Permission Denied",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		String itemId = UIFactory.getInstance().prompt("Item Code",
				"Enter code of Item being returned: ");
		
		if (itemId == null || itemId.equals(""))
			return;
		
		Item i;
		try
		{
			i = DataHandler.getInstance()
					.find(new Item(itemId), Item.CompareBy.itemCode).get(0);
		}
		catch (Exception e)
		{
			UIFactory.getInstance().warn("Item not found!",
					"Item not found in database!");
			return;
		}
		
		Transaction t;
		
		List<Transaction> ts = DataHandler.getInstance()
				.getAllActiveTransactions();
		for (int c = 0; c < ts.size(); c++)
		{
			t = ts.get(c);
			if (t.getItem().equals(i))
			{
				
				double fine;
				t.setReturnDate(new Date());
				
				long ms = t.getReturnDate().getTime()
						- t.getReturnDate().getTime();
				
				if (ms > 24 * 3600 * 1000)
				{
					fine = ms / (24 * 3600 * 1000) * 10;
					UIFactory.getInstance().warn("Overdue Item",
							"The item returned has a file of Rs. " + fine);
				}
				
				t.getPatron().checkin(i);
				i.checkin();
				
				DataHandler.getInstance().remove(t);
				
				UIFactory.getInstance().inform("Return Successful!",
						"The item was returned successfully!");
			}
		}
		
	}
	
	public void checkout()
	{
		if (!AuthManager.getInstance().hasPermission(Permission.MANAGE_LOANS))
		{
			UIFactory.getInstance().warn("Permission Denies",
					"You do not have priviledges to carry out given action!");
			return;
		}
		String userId = UIFactory.getInstance().prompt("Patron ID",
				"Enter Patron ID of member who is borrowing: ");
		
		if (userId == null || userId.equals(""))
			return;
		
		Patron p = new Patron(userId);
		
		List<Patron> ps = DataHandler.getInstance().find(p,
				Patron.CompareBy.PatronId);
		
		if (ps.size() == 0)
		{
			UIFactory.getInstance().warn("Invalid ID", "Invalid User ID!");
			return;
		}
		
		p = ps.get(0);
		String itemId;
		Item i;
		List<Item> is;
		do
		{
			itemId = UIFactory.getInstance().prompt(
					"Item ID",
					"Enter Item ID of item being borrowed by "
							+ ps.get(0).getName().getFirstName());
			
			i = new Item(itemId);
			
			is = DataHandler.getInstance().find(i, Item.CompareBy.itemCode);
			
			if (is.size() == 0)
			{
				UIFactory.getInstance().warn("Invalide Item",
						"Invalid Item ID!");
				continue;
			}
			break;
		}
		while (true);
		
		i = is.get(0);
		
		Transaction t;
		try
		{
			t = p.borrow(i);
		}
		catch (Exception e)
		{
			UIFactory.getInstance().warn("Cannot borrow item!", e.getMessage());
			return;
		}
		
		i.checkout();
		
		t.setCheckoutStaff((Staff) AuthManager.getInstance().getUser());
		
		DataHandler.getInstance().add(t);
		
	}
	
	// Complete
	public void reserve()
	{
		if (!AuthManager.getInstance().hasPermission(Permission.MEMBER_ACTIONS))
		{
			UIFactory.getInstance().warn("Permission Denies",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		String itemId = UIFactory.getInstance().prompt("Item Code",
				"Enter code of Item being renewed: ");
		
		if (itemId == null || itemId.equals(""))
			return;
		Item i;
		try
		{
			i = DataHandler.getInstance()
					.find(new Item(itemId), Item.CompareBy.itemCode).get(0);
		}
		catch (Exception e)
		{
			UIFactory.getInstance().warn("Item not found!",
					"Item not found in database!");
			return;
		}
		
		if (i.getAvailableCopies() > 0)
		{
			UIFactory.getInstance().warn("Already available!",
					"You cannot reserve an item that is already available!");
			return;
		}
		
		i.addReservation(AuthManager.getInstance().getUser());
	}
	
	// Complete
	public void renew()
	{
		if (!AuthManager.getInstance().hasPermission(Permission.MEMBER_ACTIONS))
		{
			UIFactory.getInstance().warn("Permission Denies",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		String itemId = UIFactory.getInstance().prompt("Item Code",
				"Enter code of Item being renewed: ");
		
		if (itemId == null || itemId.equals(""))
			return;
		Item i;
		try
		{
			i = DataHandler.getInstance()
					.find(new Item(itemId), Item.CompareBy.itemCode).get(0);
		}
		catch (Exception e)
		{
			UIFactory.getInstance().warn("Item not found!",
					"Item not found in database!");
			return;
		}
		
		Transaction t;
		
		List<Transaction> ts = DataHandler.getInstance()
				.getAllActiveTransactions();
		for (int c = 0; c < ts.size(); c++)
		{
			t = ts.get(c);
			if (t.getItem().equals(i))
			{
				
				if (t.getDueDate().before(new Date()))
				{
					UIFactory.getInstance().warn("Due already!",
							"This item is due already! Cannot renew!");
					return;
				}
				
				if (t.getPatron().getUsername() != AuthManager.getInstance()
						.getUser().getUsername())
				{
					UIFactory
							.getInstance()
							.warn("Not authorized!",
									"You cannot renew an item that was not borrowed under your account!");
					return;
				}
				
				t.setDueDate(new Date(t.getDueDate().getTime() + 7 * 3600 * 24
						* 1000));
				
				UIFactory.getInstance().inform(
						"Renew Successful!",
						"The item was renewed successfully! It is due on "
								+ t.getDueDate().toString());
			}
		}
		
	}
	
	// ITEMS
	public void newItem()
	{
		if (!AuthManager.getInstance().hasPermission(Permission.MANAGE_ITEMS))
		{
			UIFactory.getInstance().warn("Permission Denied",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		
		UIFactory.getInstance().showAddNewItem(new FrmItemModel());
		
	}
	
	public void submitNewItem(FrmItemModel model)
	{
		if (!AuthManager.getInstance().hasPermission(Permission.MANAGE_ITEMS))
		{
			UIFactory.getInstance().warn("Permission Denied",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		Item item = model.newItem();
		DataHandler.getInstance().add(item);
		UIFactory.getInstance().inform("Added successfuly!",
				item.getItemCode() + " was successfully added!");
	}
	
	// complete
	public void removeItem()
	{
		if (!AuthManager.getInstance().hasPermission(Permission.MANAGE_ITEMS))
		{
			UIFactory.getInstance().warn("Permission Denied",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		String itemcode = UIFactory.getInstance().prompt("Item Removal",
				"Enter the code of the item to delete: ");
		
		deleteByItemCode(itemcode);
	}
	
	public void updateItem()
	{
		if (!AuthManager.getInstance().hasPermission(Permission.MANAGE_ITEMS))
		{
			UIFactory.getInstance().warn("Permission Denied",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		String itemcode = UIFactory.getInstance().prompt("Item Update",
				"Enter the code of the item to update: ");
		
		updateByItemCode(itemcode);
	}
	
	public void updateByItemCode(String itemcode)
	{
		if (!AuthManager.getInstance().hasPermission(Permission.MANAGE_ITEMS))
		{
			UIFactory.getInstance().warn("Permission Denied",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		Item item = new Item(itemcode);
		
		List<Item> items = DataHandler.getInstance().find(item,
				Item.CompareBy.itemCode);
		
		if (items.size() == 0)
		{
			UIFactory.getInstance().warn("Item not found!",
					"The item was not found!");
			return;
		}
		
		item = items.get(0);
		
		UIFactory.getInstance().showEditItem(new FrmItemModel(item));
	}
	
	public void updateByUserCode(String username)
	{
		if (!AuthManager.getInstance()
				.hasPermission(Permission.MANAGE_ACCOUNTS))
		{
			UIFactory.getInstance().warn("Permission Denied",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		User user = new User(username);
		
		List<User> items = DataHandler.getInstance().find(user,
				User.CompareBy.UserName);
		
		if (items.size() == 0)
		{
			UIFactory.getInstance().warn("User not found!",
					"The user was not found!");
			return;
		}
		
		user = items.get(0);
		
		UIFactory.getInstance().showEditPatron(new FrmUserModel(user));
	}
	
	public void viewItems()
	{
		if (!AuthManager.getInstance().hasPermission(Permission.MEMBER_ACTIONS))
		{
			UIFactory.getInstance().warn("Permission Denied",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		UIFactory.getInstance().showAllItems();
	}
	
	public void viewItem()
	{
		if (!AuthManager.getInstance().hasPermission(Permission.MEMBER_ACTIONS))
		{
			UIFactory.getInstance().warn("Permission Denied",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		String itemcode = UIFactory.getInstance().prompt("Item Details",
				"Enter the code of the item to view: ");
		
		viewByItemCode(itemcode);
	}
	
	public void viewPatron()
	{
		if (!AuthManager.getInstance().hasPermission(Permission.MANAGE_ITEMS))
		{
			UIFactory.getInstance().warn("Permission Denied",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		String username = UIFactory.getInstance().prompt("User Details",
				"Enter the username of the user to view: ");
		
		viewByUserName(username);
	}
	
	public void viewByUserName(String username)
	{
		if (!AuthManager.getInstance().hasPermission(Permission.MANAGE_LOANS))
		{
			UIFactory.getInstance().warn("Permission Denied",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		User user = new User(username);
		
		List<User> users = DataHandler.getInstance().find(user,
				User.CompareBy.UserName);
		
		if (users.size() == 0)
		{
			UIFactory.getInstance().warn("User not found!",
					"The user was not found!");
			return;
		}
		
		user = users.get(0);
		
		UIFactory.getInstance().showViewPatron(new FrmUserModel(user));
		
	}
	
	public void duplicateItem()
	{
		if (!AuthManager.getInstance().hasPermission(Permission.MANAGE_ITEMS))
		{
			UIFactory.getInstance().warn("Permission Denies",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		String itemId = UIFactory.getInstance().prompt("Item Code",
				"Enter code of Item being returned: ");
		
		if (itemId == null || itemId.equals(""))
			return;
		Item i;
		try
		{
			i = DataHandler.getInstance()
					.find(new Item(itemId), Item.CompareBy.itemCode).get(0);
		}
		catch (Exception e)
		{
			UIFactory.getInstance().warn("Item not found!",
					"Item not found in database!");
			return;
		}
		
		i.addCopy();
	}
	
	// MEMBER ACCOUNTS
	public void newMember()
	{
		if (!AuthManager.getInstance()
				.hasPermission(Permission.MANAGE_ACCOUNTS))
		{
			UIFactory.getInstance().warn("Permission Denied",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		FrmUserModel model = new FrmUserModel();
		
		UIFactory.getInstance().showAddNewPatron(model);
		
	}
	
	public void submitNewMember(FrmUserModel model)
	{
		if (!AuthManager.getInstance()
				.hasPermission(Permission.MANAGE_ACCOUNTS))
		{
			UIFactory.getInstance().warn("Permission Denied",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		User u = model.newUser();
		
		if (model.getUserType() == UserType.STAFF)
			DataHandler.getInstance().add((Staff) u);
		
		String username;
		do
		{
			username = UIFactory.getInstance().prompt("Select User Name",
					"Select a username to assign to the new user:");
			
			if (username != null && validUsername(username))
				break;
			
			if (username == null)
			{
				UIFactory.getInstance().warn(
						"Username cannot be empty!",
						"You cannot leave the username empty. Please assign a username for "
								+ u.getName().getFirstName());
				continue;
			}
			
			if (!validUsername(username))
				UIFactory
						.getInstance()
						.inform("Username taken!",
								"The selected username is taken! Please try assigning a new username?");
		}
		while (true);
		
		u.setUsername(username);
		
		u.setPassword(username
				+ Integer.toHexString((int) (Math.random() * 1000)));
		
		DataHandler.getInstance().add((Patron) u);
		DataHandler.getInstance().add(u);
		
		UIFactory.getInstance().inform(
				"Added successfully!",
				"Username: " + u.getUsername() + "\nPassword: "
						+ u.getPassword());
		
	}
	
	public void removePatron()
	{
		if (!AuthManager.getInstance()
				.hasPermission(Permission.MANAGE_ACCOUNTS))
		{
			UIFactory.getInstance().warn("Permission Denied",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		String username = UIFactory.getInstance().prompt("User Removal",
				"Enter the username of user to delete: ");
		
		deleteByUsername(username);
	}
	
	public void deleteByUsername(String username)
	{
		if (!AuthManager.getInstance()
				.hasPermission(Permission.MANAGE_ACCOUNTS))
		{
			UIFactory.getInstance().warn("Permission Denied",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		
		if (!UIFactory.getInstance().confirm("Confirm Deletion?",
				"Are you sure you want to attempt removing " + username))
			return;
		
		User user = new User(username);
		
		if (DataHandler.getInstance().remove(user))
			
			UIFactory.getInstance().inform("User removed!",
					user.getUsername() + " was remove successfully!");
		else
			UIFactory.getInstance().inform("User cannot beremoved!",
					user.getUsername() + " was not found and not removed!");
	}
	
	public void updatePatron()
	{
		if (!AuthManager.getInstance()
				.hasPermission(Permission.MANAGE_ACCOUNTS))
		{
			UIFactory.getInstance().warn("Permission Denied",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		
		String usercode = UIFactory.getInstance().prompt("User Update",
				"Enter the username of the user to update: ");
		
		updateByUserCode(usercode);
	}
	
	public void viewPatrons()
	{
		if (!AuthManager.getInstance().hasPermission(Permission.MANAGE_ITEMS))
		{
			UIFactory.getInstance().warn("Permission Denied",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		UIFactory.getInstance().showAllMembers();
	}
	
	public void passwordReset()
	{
		
		if (!AuthManager.getInstance()
				.hasPermission(Permission.MANAGE_ACCOUNTS))
		{
			UIFactory.getInstance().warn("Permission Denied",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		
		String username = UIFactory.getInstance().prompt("Username",
				"Enter username of the account whose password is to be reset:");
		User user = new User(username);
		
		List<User> users = DataHandler.getInstance().find(user,
				User.CompareBy.UserName);
		
		if (users.size() == 0)
		{
			UIFactory.getInstance().warn("User not found!",
					"The user was not found!");
			return;
		}
		
		user = users.get(0);
		
		user.setPassword(username
				+ Integer.toHexString((int) (Math.random() * 1000)));
		
		UIFactory.getInstance().inform(
				"New Password",
				"New password for " + user.getUsername() + " is "
						+ user.getPassword());
	}
	
	// HELP
	// Complete
	public void guide()
	{
		try
		{
			Desktop.getDesktop().open(new File("guide.pdf"));
		}
		catch (IOException e)
		{
			UIFactory
					.getInstance()
					.warn("Error opening file!",
							"Cannot launch user guide! Please ensure that all application files are present and you have installed a PDF viewer!");
		}
	}
	
	// complete
	public void uml()
	{
		try
		{
			Desktop.getDesktop().open(new File("uml.pdf"));
		}
		catch (IOException e)
		{
			UIFactory
					.getInstance()
					.warn("Error opening file!",
							"Cannot launch user guide! Please ensure that all application files are present and you have installed a PDF viewer!");
		}
	}
	
	public void about()
	{
		UIFactory.getInstance().showAbout();
	}
	
	public static void main(String... strings)
	{
		MainController.getInstance().launch();
		
	}
	
	public void deleteByItemCode(String itemCode)
	{
		if (!AuthManager.getInstance().hasPermission(Permission.MANAGE_ITEMS))
		{
			UIFactory.getInstance().warn("Permission Denied",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		if (!UIFactory.getInstance().confirm("Confirm Deletion?",
				"Are you sure you want to attempt removing " + itemCode))
			return;
		
		Item item = new Item(itemCode);
		
		if (DataHandler.getInstance().remove(item))
			
			UIFactory.getInstance().inform("Item removed!",
					item.getItemCode() + " was remove successfully!");
		else
			UIFactory.getInstance().inform("Item cannot beremoved!",
					item.getItemCode() + " was not found and not removed!");
	}
	
	public void viewByItemCode(String itemCode)
	{
		if (!AuthManager.getInstance().hasPermission(Permission.MEMBER_ACTIONS))
		{
			UIFactory.getInstance().warn("Permission Denied",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		Item item = new Item(itemCode);
		
		List<Item> items = DataHandler.getInstance().find(item,
				Item.CompareBy.itemCode);
		
		if (items.size() == 0)
		{
			UIFactory.getInstance().warn("Item not found!",
					"The item was not found!");
			return;
		}
		
		item = items.get(0);
		
		UIFactory.getInstance().showViewItem(new FrmItemModel(item));
	}
	
	public void submitUpdateItem(FrmItemModel model)
	{
		if (!AuthManager.getInstance().hasPermission(Permission.MANAGE_ITEMS))
		{
			UIFactory.getInstance().warn("Permission Denied",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		model.updateItem();
		DataHandler.getInstance().update(model.getItem());
		
	}
	
	public void submitUpdateUser(FrmUserModel model)
	{
		if (!AuthManager.getInstance()
				.hasPermission(Permission.MANAGE_ACCOUNTS))
		{
			UIFactory.getInstance().warn("Permission Denied",
					"You do not have priviledges to carry out given action!");
			return;
			
		}
		model.updateUser();
		DataHandler.getInstance().update(model.getUser());
		
	}
	
	public boolean validUsername(String name)
	{
		User u = new User(name);
		
		return DataHandler.getInstance().find(u, User.CompareBy.UserName)
				.size() < 1;
	}
}
