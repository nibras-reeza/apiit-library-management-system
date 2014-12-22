package lk.apiit.oosd.nsa.lms.view.frames;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import lk.apiit.oosd.nsa.lms.controller.MainController;
import lk.apiit.oosd.nsa.lms.view.models.MembersTableModel;

public class FrmMembers extends JInternalFrame
{
	private JPopupMenu	rightclick;
	private JMenuItem	mntmView;
	private JMenuItem	mntmEdit;
	private JMenuItem	mntmDelete;
	
	public FrmMembers()
	{
		setTitle("View Members");
		setResizable(true);
		setClosable(true);
		
		model = new MembersTableModel();
		
		this.table = new JTable(model);
		// http://stackoverflow.com/questions/16743427/newbie-jtable-right-click-popup-menu
		/*
		 * public void actionPerformed(ActionEvent e) { Component c =
		 * (Component)e.getSource(); JPopupMenu popup =
		 * (JPopupMenu)c.getParent(); JTable table = (JTable)popup.getInvoker();
		 * System.out.println(table.getSelectedRow() + " : " +
		 * table.getSelectedColumn()); }
		 */
		
		rightclick = new JPopupMenu();
		
		this.mntmView = new JMenuItem("View");
		this.mntmView.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				String username = getCodeFromEvent(arg0);
				MainController.getInstance().viewByUserName(username);
			}
		});
		this.rightclick.add(this.mntmView);
		this.mntmEdit = new JMenuItem("Edit");
		this.mntmEdit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String username = getCodeFromEvent(e);
				MainController.getInstance().updateByUserCode(username);
			}
		});
		this.rightclick.add(this.mntmEdit);
		this.mntmDelete = new JMenuItem("Delete");
		this.mntmDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String username = getCodeFromEvent(e);
				MainController.getInstance().deleteByUsername(username);
			}
		});
		this.rightclick.add(this.mntmDelete);
		
		table.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
			}
			
			public void mouseReleased(MouseEvent e)
			{
				if (e.isPopupTrigger())
				{
					JTable source = (JTable) e.getSource();
					int row = source.rowAtPoint(e.getPoint());
					int column = source.columnAtPoint(e.getPoint());
					
					if (!source.isRowSelected(row))
						source.changeSelection(row, column, false, false);
					
					rightclick.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		
		filter = new TableRowSorter<AbstractTableModel>(model);
		
		this.scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		table.setRowSorter(filter);
		
		getContentPane().add(this.scrollPane, BorderLayout.CENTER);
		this.filterPanel = new JPanel();
		getContentPane().add(this.filterPanel, BorderLayout.NORTH);
		this.filterPanel.setLayout(new BoxLayout(this.filterPanel,
				BoxLayout.X_AXIS));
		
		this.lblFilterBy = new JLabel("Filter by: ");
		this.filterPanel.add(this.lblFilterBy);
		this.comboBox = new JComboBox<String>();
		this.comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {
				"User Name", "User Type", "First Name", "Last Name", "City",
				"NIC", "EMail" }));
		this.filterPanel.add(this.comboBox);
		this.lblRegex = new JLabel("Filter (Regex):");
		this.filterPanel.add(this.lblRegex);
		this.textRegex = new JTextField();
		this.textRegex.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				filter();
			}
		});
		this.filterPanel.add(this.textRegex);
		this.textRegex.setColumns(16);
		
		this.setBounds(100, 100, 640, 480);
		
		this.setMaximizable(true);
		
	}
	
	protected String getCodeFromEvent(ActionEvent e)
	{
		String username = null;
		
		// http://stackoverflow.com/questions/16743427/newbie-jtable-right-click-popup-menu
		
		Component clickedon = (Component) e.getSource();
		JPopupMenu menu = (JPopupMenu) clickedon.getParent();
		JTable table = (JTable) menu.getInvoker();
		
		username = (String) model.getValueAt(table.getSelectedRow(), 0);
		return username;
	}
	
	private void filter()
	{
		RowFilter<AbstractTableModel, Object> rf = null;
		
		int filterBy = comboBox.getSelectedIndex();
		
		if (filterBy == -1)
			return;
		
		try
		{
			rf = RowFilter.regexFilter(textRegex.getText(), filterBy);
		}
		catch (java.util.regex.PatternSyntaxException e)
		{
			return;
		}
		filter.setRowFilter(rf);
		pack();
		setVisible(true);
	}
	
	private static final long					serialVersionUID	= 1121188625444661057L;
	private JScrollPane							scrollPane;
	private JTable								table;
	private JLabel								lblFilterBy;
	private JComboBox<String>					comboBox;
	private JPanel								filterPanel;
	private JLabel								lblRegex;
	private JTextField							textRegex;
	private TableRowSorter<AbstractTableModel>	filter;
	private AbstractTableModel					model;
	
}
