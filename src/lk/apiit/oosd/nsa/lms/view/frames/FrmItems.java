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
import lk.apiit.oosd.nsa.lms.view.models.ItemsTableModel;

public class FrmItems extends JInternalFrame
{
	
	private static final long	serialVersionUID	= 672086686565839138L;
	private JMenuItem			mntmView;
	private JMenuItem			mntmEdit;
	private JMenuItem			mntmDelete;
	
	public FrmItems()
	{
		setTitle("View Items");
		setResizable(true);
		setClosable(true);
		
		setBounds(100, 100, 640, 480);
		
		model = new ItemsTableModel();
		
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
				String itemCode = getCodeFromEvent(arg0);
				MainController.getInstance().viewByItemCode(itemCode);
			}
		});
		this.rightclick.add(this.mntmView);
		this.mntmEdit = new JMenuItem("Edit");
		this.mntmEdit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String itemCode = getCodeFromEvent(e);
				MainController.getInstance().updateByItemCode(itemCode);
			}
		});
		this.rightclick.add(this.mntmEdit);
		this.mntmDelete = new JMenuItem("Delete");
		this.mntmDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String itemCode = getCodeFromEvent(e);
				MainController.getInstance().deleteByItemCode(itemCode);
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
				"Code", "Type", "Description", "Copies" + "Availability" }));
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
		
		setMaximizable(true);
		
		this.setVisible(true);
		
	}
	
	protected String getCodeFromEvent(ActionEvent arg0)
	{
		String itemCode = null;
		
		// http://stackoverflow.com/questions/16743427/newbie-jtable-right-click-popup-menu
		
		Component clickedon = (Component) arg0.getSource();
		JPopupMenu menu = (JPopupMenu) clickedon.getParent();
		JTable table = (JTable) menu.getInvoker();
		
		itemCode = (String) model.getValueAt(table.getSelectedRow(), 0);
		return itemCode;
		
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
	
	private JScrollPane							scrollPane;
	private JTable								table;
	private JLabel								lblFilterBy;
	private JComboBox<String>					comboBox;
	private JPanel								filterPanel;
	private JLabel								lblRegex;
	private JTextField							textRegex;
	private TableRowSorter<AbstractTableModel>	filter;
	private JPopupMenu							rightclick;
	private AbstractTableModel					model;
	
}
