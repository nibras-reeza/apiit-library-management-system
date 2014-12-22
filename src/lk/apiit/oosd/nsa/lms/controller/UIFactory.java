package lk.apiit.oosd.nsa.lms.controller;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import lk.apiit.oosd.nsa.lms.view.frames.FRM_TYPE;
import lk.apiit.oosd.nsa.lms.view.frames.FrmAbout;
import lk.apiit.oosd.nsa.lms.view.frames.FrmItem;
import lk.apiit.oosd.nsa.lms.view.frames.FrmItems;
import lk.apiit.oosd.nsa.lms.view.frames.FrmMain;
import lk.apiit.oosd.nsa.lms.view.frames.FrmMembers;
import lk.apiit.oosd.nsa.lms.view.frames.FrmUser;
import lk.apiit.oosd.nsa.lms.view.models.FrmItemModel;
import lk.apiit.oosd.nsa.lms.view.models.FrmUserModel;

public class UIFactory
{
	private static UIFactory	instance	= null;
	private FrmMain				mainWin		= null;
	
	private UIFactory()
	{
	}
	
	public static UIFactory getInstance()
	{
		if (instance == null)
			instance = new UIFactory();
		return instance;
	}
	
	public void showMainWindow()
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					mainWin = new FrmMain();
					mainWin.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public void showAddNewPatron(FrmUserModel model)
	{
		JInternalFrame frame = new FrmUser(model, FRM_TYPE.NEW);
		
		mainWin.getContentPane().add(frame);
		
		frame.setVisible(true);
		
	}
	
	public void showEditPatron(FrmUserModel model)
	{
		JInternalFrame frame = new FrmUser(model, FRM_TYPE.MODIFY);
		
		mainWin.getContentPane().add(frame);
		
		frame.setVisible(true);
		
	}
	
	public void showViewPatron(FrmUserModel model)
	{
		JInternalFrame frame = new FrmUser(model, FRM_TYPE.VIEW);
		
		mainWin.getContentPane().add(frame);
		
		frame.setVisible(true);
	}
	
	public void showAllMembers()
	{
		JInternalFrame frame = new FrmMembers();
		mainWin.getContentPane().add(frame);
		
		frame.setVisible(true);
	}
	
	public void showAllItems()
	{
		JInternalFrame frame = new FrmItems();
		
		mainWin.getContentPane().add(frame);
		
		frame.setVisible(true);
	}
	
	public void showAddNewItem(FrmItemModel model)
	{
		JInternalFrame frame = new FrmItem(model, FRM_TYPE.NEW);
		
		mainWin.getContentPane().add(frame);
		
		frame.setVisible(true);
		
	}
	
	public void showEditItem(FrmItemModel model)
	{
		JInternalFrame frame = new FrmItem(model, FRM_TYPE.MODIFY);
		
		mainWin.getContentPane().add(frame);
		
		frame.setVisible(true);
		
	}
	
	public void showViewItem(FrmItemModel model)
	{
		JInternalFrame frame = new FrmItem(model, FRM_TYPE.VIEW);
		
		mainWin.getContentPane().add(frame);
		
		frame.setVisible(true);
		
	}
	
	public boolean confirm(String title, String message)
	{
		
		int result = JOptionPane.showConfirmDialog(mainWin, message, title,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if (result == JOptionPane.YES_OPTION)
			return true;
		
		return false;
	}
	
	public String prompt(String title, String message)
	{
		return JOptionPane.showInputDialog(mainWin, message, title,
				JOptionPane.QUESTION_MESSAGE);
	}
	
	public void enableAccountManagement(boolean enable)
	{
		mainWin.enableAccountManagement(enable);
	}
	
	public void enableItemManagement(boolean enable)
	{
		mainWin.enableItemManagement(enable);
	}
	
	public void warn(String title, String message)
	{
		JOptionPane.showMessageDialog(mainWin, message, title,
				JOptionPane.WARNING_MESSAGE);
	}
	
	public void inform(String title, String message)
	{
		JOptionPane.showMessageDialog(mainWin, message, title,
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void showAbout()
	{
		JInternalFrame frame = new FrmAbout();
		mainWin.add(frame);
		frame.setVisible(true);
		
	}
}
