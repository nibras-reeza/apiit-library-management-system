package lk.apiit.oosd.nsa.lms.view.frames;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import lk.apiit.oosd.nsa.lms.controller.MainController;

public class FrmMain extends JFrame
{
	
	private static final long	serialVersionUID	= 716830845595604225L;
	private JMenuItem			mntmAddMember;
	private JMenuItem			mntmViewMember;
	private JMenuItem			mntmUpdateMember;
	private JMenuItem			mntmViewMember_1;
	private JSeparator			separator_8;
	private JMenuItem			mntmSearchItems;
	private JMenuItem			mntmSearchMembers;
	private JSeparator			separator_9;
	
	public FrmMain()
	{
		initialize();
		setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		
		// http://stackoverflow.com/questions/10123735/get-effective-screen-size-from-java
		
		/*
		 * Stackoverflow.com. 2013. java - How to set Background image in
		 * JDesktopPane - Stack Overflow. [online] Available at:
		 * http://stackoverflow
		 * .com/questions/15681984/how-to-set-background-image-in-jdesktoppane
		 * [Accessed: 19 Sep 2013].
		 */
		Rectangle r = getGraphicsConfiguration().getBounds();
		Rectangle ref = (Rectangle) r.clone();
		
		r.height *= 3.0 / 4.0;
		r.width *= 3.0 / 4.0;
		r.y = (int) ((ref.height - r.height) * 1.0 / 4.0);
		r.x = (int) ((ref.width - r.width) * 1.0 / 4.0);
		
		final int insetH = (int) ref.height;
		final int insetW = (int) ref.width;
		setBounds(r);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// (Stackoverflow.com, 2013)
		JDesktopPane desktopPane = new JDesktopPane()
		{
			private static final long	serialVersionUID	= -3458940011148041498L;
			ImageIcon					icon				= new ImageIcon(
																	"imgs/logo.png");
			Image						image				= icon.getImage();
			
			Image						newimage			= image.getScaledInstance(
																	insetW,
																	insetH,
																	Image.SCALE_SMOOTH);
			
			@Override
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.drawImage(newimage, 0, 0, this);
			}
		}
		
		;
		this.setContentPane(desktopPane);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMyAccount = new JMenu("My Account");
		menuBar.add(mnMyAccount);
		
		JMenuItem mntmLogin = new JMenuItem("Login");
		
		mntmLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainController.getInstance().login();
			}
		});
		mntmLogin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
				InputEvent.CTRL_MASK));
		mnMyAccount.add(mntmLogin);
		
		// Complete
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainController.getInstance().logout();
			}
		});
		mntmLogout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				InputEvent.CTRL_MASK));
		mnMyAccount.add(mntmLogout);
		
		JSeparator separator_1 = new JSeparator();
		mnMyAccount.add(separator_1);
		
		JMenuItem mntmUpdateProfile = new JMenuItem("Update Profile");
		mntmUpdateProfile.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainController.getInstance().updateProfile();
			}
		});
		mnMyAccount.add(mntmUpdateProfile);
		
		// Complete
		JMenuItem mntmChangePassword = new JMenuItem("Change Password");
		mntmChangePassword.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainController.getInstance().changePassword();
			}
		});
		mnMyAccount.add(mntmChangePassword);
		
		JSeparator separator_2 = new JSeparator();
		mnMyAccount.add(separator_2);
		
		// Complete
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainController.getInstance().exit();
			}
		});
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,
				InputEvent.ALT_MASK));
		mnMyAccount.add(mntmExit);
		
		JMenu mnManageLoans = new JMenu("Manage Loans");
		menuBar.add(mnManageLoans);
		
		// Complete
		JMenuItem mntmCheckin = new JMenuItem("Checkin");
		mntmCheckin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainController.getInstance().checkin();
			}
		});
		mntmCheckin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
				InputEvent.CTRL_MASK));
		mnManageLoans.add(mntmCheckin);
		
		// Complete
		JMenuItem mntmCheckout = new JMenuItem("Checkout");
		mntmCheckout.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainController.getInstance().checkout();
			}
		});
		mntmCheckout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_MASK));
		mnManageLoans.add(mntmCheckout);
		
		JSeparator separator = new JSeparator();
		mnManageLoans.add(separator);
		
		JMenuItem mntmReserve = new JMenuItem("Reserve");
		mntmReserve.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainController.getInstance().reserve();
			}
		});
		mnManageLoans.add(mntmReserve);
		
		JMenuItem mntmRenew = new JMenuItem("Renew");
		mntmRenew.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainController.getInstance().renew();
			}
		});
		mnManageLoans.add(mntmRenew);
		
		JMenu mnManageItems = new JMenu("Manage Items");
		menuBar.add(mnManageItems);
		
		JMenuItem mntmAddNewItem = new JMenuItem("Add Item");
		mntmAddNewItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainController.getInstance().newItem();
			}
		});
		mntmAddNewItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK));
		mnManageItems.add(mntmAddNewItem);
		
		// Complete
		JMenuItem mntmViewItem = new JMenuItem("View Item");
		mntmViewItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainController.getInstance().viewItem();
			}
		});
		mntmViewItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				InputEvent.CTRL_MASK));
		mnManageItems.add(mntmViewItem);
		
		JSeparator separator_3 = new JSeparator();
		mnManageItems.add(separator_3);
		
		JMenuItem mntmUpdateItem = new JMenuItem("Update Item");
		mntmUpdateItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainController.getInstance().updateItem();
			}
		});
		mntmUpdateItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,
				InputEvent.CTRL_MASK));
		mnManageItems.add(mntmUpdateItem);
		
		JMenuItem mntmRemoveItem = new JMenuItem("Remove Item");
		mntmRemoveItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainController.getInstance().removeItem();
			}
		});
		mntmRemoveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				InputEvent.CTRL_MASK));
		mnManageItems.add(mntmRemoveItem);
		
		JSeparator separator_4 = new JSeparator();
		mnManageItems.add(separator_4);
		
		// Complete
		JMenuItem mntmAddDuplicate = new JMenuItem("Add Duplicate");
		mntmAddDuplicate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainController.getInstance().duplicateItem();
			}
		});
		mnManageItems.add(mntmAddDuplicate);
		this.separator_8 = new JSeparator();
		mnManageItems.add(this.separator_8);
		this.mntmSearchItems = new JMenuItem("Search Items");
		this.mntmSearchItems.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainController.getInstance().viewItems();
			}
		});
		mnManageItems.add(this.mntmSearchItems);
		
		JMenu mnMemberAccounts = new JMenu("Member Accounts");
		menuBar.add(mnMemberAccounts);
		this.mntmAddMember = new JMenuItem("Add Member");
		this.mntmAddMember.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainController.getInstance().newMember();
			}
		});
		
		// Complete
		mnMemberAccounts.add(this.mntmAddMember);
		this.mntmViewMember = new JMenuItem("View Member");
		this.mntmViewMember.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				MainController.getInstance().viewPatron();
			}
		});
		mnMemberAccounts.add(this.mntmViewMember);
		
		JSeparator separator_6 = new JSeparator();
		mnMemberAccounts.add(separator_6);
		this.mntmUpdateMember = new JMenuItem("Update Member");
		this.mntmUpdateMember.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainController.getInstance().updatePatron();
			}
		});
		mnMemberAccounts.add(this.mntmUpdateMember);
		this.mntmViewMember_1 = new JMenuItem("Remove Member");
		this.mntmViewMember_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainController.getInstance().removePatron();
			}
			
		});
		mnMemberAccounts.add(this.mntmViewMember_1);
		
		JSeparator separator_5 = new JSeparator();
		mnMemberAccounts.add(separator_5);
		
		JMenuItem mntmPasswordReset = new JMenuItem("Password Reset");
		mntmPasswordReset.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainController.getInstance().passwordReset();
			}
		});
		mnMemberAccounts.add(mntmPasswordReset);
		this.separator_9 = new JSeparator();
		mnMemberAccounts.add(this.separator_9);
		this.mntmSearchMembers = new JMenuItem("Search Members");
		this.mntmSearchMembers.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainController.getInstance().viewPatrons();
			}
		});
		mnMemberAccounts.add(this.mntmSearchMembers);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmUmlDocumentation = new JMenuItem("UML Documentation");
		mntmUmlDocumentation.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainController.getInstance().uml();
			}
		});
		mnHelp.add(mntmUmlDocumentation);
		
		JMenuItem mntmUserGuide = new JMenuItem("User Guide");
		mntmUserGuide.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainController.getInstance().guide();
			}
		});
		mnHelp.add(mntmUserGuide);
		
		JSeparator separator_7 = new JSeparator();
		mnHelp.add(separator_7);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainController.getInstance().about();
			}
		});
		mnHelp.add(mntmAbout);
		
		this.setIconImage(new ImageIcon("imgs/logo.png").getImage());
	}
	
	public void enableAccountManagement(boolean enable)
	{
		
		// getJMenuBar().getMenu(3).setEnabled(enable);
		
	}
	
	public void enableItemManagement(boolean enable)
	{
		// getJMenuBar().getMenu(2).setEnabled(enable);
		
	}
}
