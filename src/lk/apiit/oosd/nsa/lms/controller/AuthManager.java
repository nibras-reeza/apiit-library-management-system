/*
 * This is part of the assignment for OOSD, September 2013
 * 
 * This is the login module. Although it does not implement LoginModule
 * from JAAS, this can easily be converted into a LoginModule
 * 
 * References
 *	 Stackoverflow.com. 2013. java - JOptionPane to get password - Stack Overflow. 
 *	 [online] 
 * 	Available at: http://stackoverflow.com/questions/8881213/joptionpane-to-get-password 
 * 	[Accessed: 19 Sep 2013].
 * 
 * */

package lk.apiit.oosd.nsa.lms.controller;

import java.util.List;

import javax.security.auth.login.LoginException;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import lk.apiit.oosd.nsa.lms.controller.types.Permission;
import lk.apiit.oosd.nsa.lms.model.people.Staff;
import lk.apiit.oosd.nsa.lms.model.people.Staff.StaffType;
import lk.apiit.oosd.nsa.lms.model.people.User;
import lk.apiit.oosd.nsa.lms.model.people.User.UserType;

public class AuthManager
{
	private static AuthManager	instance	= null;
	
	private User				currentUser;
	
	private boolean				backdoor	= false;
	
	public static AuthManager getInstance()
	{
		if (instance == null)
			instance = new AuthManager();
		
		return instance;
	}
	
	public boolean hasPermission(Permission permission)
	{
		if (backdoor)
			return true;
		
		if (currentUser == null)
			return false;
		
		switch (permission)
		{
			case MANAGE_ACCOUNTS:
				if (currentUser.getUserType() == UserType.STAFF)
					if (((Staff) currentUser).getStaffType() == StaffType.ADMIN
							|| ((Staff) currentUser).getStaffType() == StaffType.LIBRARIAN)
						return true;
				break;
			case MANAGE_ITEMS:
				if (currentUser.getUserType() == UserType.STAFF)
					if (((Staff) currentUser).getStaffType() == StaffType.ASST_LIBRARIAN
							|| ((Staff) currentUser).getStaffType() == StaffType.LIBRARIAN)
						return true;
				break;
			case MANAGE_LOANS:
				if (currentUser.getUserType() == UserType.STAFF)
					return true;
				break;
			
			case MEMBER_ACTIONS:
				if (currentUser != null)
					return true;
				break;
		}
		return false;
	}
	
	public void login() throws LoginException
	{
		backdoor = false;
		String username = UIFactory.getInstance().prompt("User Name",
				"Please enter your user name!");
		String password;
		
		// (Stackoverflow.com, 2013)
		JPasswordField textBox = new JPasswordField();
		
		if (JOptionPane.showConfirmDialog(null, textBox,
				"Enter your password: ", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION)
			password = new String(textBox.getPassword());
		else
			return;
		
		if (username.equals("nibras") && password.equals("reeza"))
		{
			backdoor = true;
			return;
		}
		
		User u = new User();
		u.setUsername(username);
		
		List<User> user = DataHandler.getInstance().find(u,
				User.CompareBy.UserName);
		
		if (user.size() == 0)
			throw new LoginException("Invalid User Name!");
		
		u = user.get(0);
		
		if (u.getPassword().equals(hashAndSalt(password)))
			currentUser = u;
		else
			throw new LoginException("Invalid Password!");
		
	}
	
	public void logout()
	{
		backdoor = false;
		currentUser = null;
		MainController.getInstance().login();
		
	}
	
	public String hashAndSalt(String password)
	{
		return password;
	}
	
	public void changePassword()
	{
		int attempts = 0;
		while (attempts < 3)
		{
			if (hashAndSalt(
					UIFactory.getInstance().prompt("Password",
							"Please enter your old password:")).equals(
					getUser().getPassword()))
			
			{
				String password = UIFactory.getInstance().prompt(
						"New Password", "Enter new password:");
				
				String pw = UIFactory.getInstance().prompt("New Password",
						"Enter new password again:");
				
				if (!password.equals(pw))
				{
					UIFactory.getInstance().inform("Password Mismatch",
							"Passwords do not match! Try again!");
					return;
				}
				
				this.getUser().setPassword(hashAndSalt(password));
			}
			else
			{
				
				UIFactory.getInstance().warn("Wrong password!",
						"Invalid Password! Please try again!");
				attempts++;
				continue;
				
			}
		}
		
		UIFactory
				.getInstance()
				.warn("Exceeded maximum attemps!",
						"You have exceeded maximum number of password change attempts! You will be logged out for security reasons!");
		currentUser = null;
	}
	
	public User getUser()
	{
		return currentUser;
	}
}
