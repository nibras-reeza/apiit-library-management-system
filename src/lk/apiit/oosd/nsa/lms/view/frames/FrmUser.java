package lk.apiit.oosd.nsa.lms.view.frames;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import lk.apiit.oosd.nsa.lms.controller.MainController;
import lk.apiit.oosd.nsa.lms.view.models.FrmUserModel;
import lk.apiit.oosd.nsa.lms.view.panels.ExtendedPanel;
import lk.apiit.oosd.nsa.lms.view.panels.PanelAddress;
import lk.apiit.oosd.nsa.lms.view.panels.PanelContact;
import lk.apiit.oosd.nsa.lms.view.panels.PanelDate;
import lk.apiit.oosd.nsa.lms.view.panels.PanelGender;
import lk.apiit.oosd.nsa.lms.view.panels.PanelNIC;
import lk.apiit.oosd.nsa.lms.view.panels.PanelName;
import lk.apiit.oosd.nsa.lms.view.panels.PanelPersonType;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class FrmUser extends JInternalFrame
{
	public FrmUserModel getModel()
	{
		return this.model;
	}
	
	public void setModel(FrmUserModel model)
	{
		this.model = model;
	}
	
	private static final long	serialVersionUID	= -5728529633014946699L;
	private FrmUserModel		model;
	
	private ExtendedPanel[]		panels				= new ExtendedPanel[7];
	private JPanel				panelButtons;
	private JPanel				panelAdd;
	private JButton				btnAdd;
	private JButton				btnClear;
	private JButton				btnCancel;
	private JPanel				panelView;
	private JButton				btnEdit;
	private JButton				btnDelete;
	private JPanel				panelUpdate;
	private JButton				btnUpdate;
	private JButton				btnDelete_1;
	private JButton				btnCancel_2;
	private JButton				btnCancel_1;
	private JPanel				panelInformation;
	private CardLayout			buttonsLayout;
	
	public FrmUser(FrmUserModel model, FRM_TYPE type)
	{
		this.model = model;
		
		setBounds(100, 100, 104, 358);
		
		initPanels(model);
		
		initButtons();
		
		this.panelInformation = new JPanel();
		getContentPane().add(this.panelInformation, BorderLayout.CENTER);
		this.panelInformation.setLayout(new BoxLayout(this.panelInformation,
				BoxLayout.Y_AXIS));
		
		for (ExtendedPanel p : panels)
			panelInformation.add(p);
		switch (type)
		{
			case MODIFY:
				this.setTitle("Update User");
				buttonsLayout.show(panelButtons, "name_22832690572997");
				displayModel();
				break;
			case NEW:
				this.setTitle("Add User");
				buttonsLayout.show(panelButtons, "name_22809918370537");
				break;
			case VIEW:
				this.setTitle("View User");
				displayModel();
				buttonsLayout.show(panelButtons, "name_22822116951719");
				disableEditing();
				break;
			default:
				break;
		
		}
		
		pack();
		this.moveToFront();
		
	}
	
	private void initPanels(FrmUserModel model)
	{
		panels[0] = new PanelName(model.getNameModel());
		panels[1] = new PanelAddress(model.getAddressModel());
		panels[2] = new PanelGender(model.getGenderModel());
		panels[3] = new PanelContact(model.getContactModel());
		panels[4] = new PanelPersonType(model);
		panels[5] = new PanelDate(model.getDateOfBirthModel());
		panels[6] = new PanelNIC(model.getNICModel());
	}
	
	private void initButtons()
	{
		buttonsLayout = new CardLayout(0, 0);
		this.panelButtons = new JPanel();
		getContentPane().add(this.panelButtons, BorderLayout.SOUTH);
		
		this.panelButtons.setLayout(buttonsLayout);
		this.panelAdd = new JPanel();
		this.panelButtons.add(this.panelAdd, "name_22809918370537");
		this.panelAdd.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.GLUE_COLSPEC, FormFactory.PREF_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.PREF_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.PREF_COLSPEC,
				FormFactory.GLUE_COLSPEC, }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, }));
		this.btnAdd = new JButton("Add");
		this.btnAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				updateModel();
				addUser();
			}
		});
		this.panelAdd.add(this.btnAdd, "2, 2, center, center");
		this.btnClear = new JButton("Clear");
		this.btnClear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clear();
			}
		});
		this.panelAdd.add(this.btnClear, "4, 2, center, center");
		this.btnCancel = new JButton("Cancel");
		this.btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				cancel();
			}
		});
		
		this.panelAdd.add(this.btnCancel, "6, 2, center, center");
		this.panelView = new JPanel();
		this.panelButtons.add(this.panelView, "name_22822116951719");
		this.panelView.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.GLUE_COLSPEC, FormFactory.PREF_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.PREF_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.PREF_COLSPEC,
				FormFactory.GLUE_COLSPEC, }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, }));
		this.btnEdit = new JButton("Edit");
		this.btnEdit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				edit();
			}
		});
		this.panelView.add(this.btnEdit, "2, 2, center, center");
		this.btnDelete = new JButton("Delete");
		this.btnDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				delete();
			}
		});
		this.panelView.add(this.btnDelete, "4, 2, center, center");
		this.btnCancel_1 = new JButton("Cancel");
		this.btnCancel_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				cancel();
			}
		});
		this.panelView.add(this.btnCancel_1, "6, 2, center, center");
		this.panelUpdate = new JPanel();
		this.panelButtons.add(this.panelUpdate, "name_22832690572997");
		this.panelUpdate.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.GLUE_COLSPEC, FormFactory.PREF_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.PREF_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.PREF_COLSPEC,
				FormFactory.GLUE_COLSPEC, }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, }));
		this.btnUpdate = new JButton("Update");
		this.btnUpdate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				updateModel();
				update();
			}
		});
		this.panelUpdate.add(this.btnUpdate, "2, 2, center, center");
		this.btnDelete_1 = new JButton("Delete");
		this.btnDelete_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				delete();
			}
		});
		this.panelUpdate.add(this.btnDelete_1, "4, 2, center, center");
		this.btnCancel_2 = new JButton("Cancel");
		this.btnCancel_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				cancel();
			}
		});
		this.panelUpdate.add(this.btnCancel_2, "6, 2, center, center");
	}
	
	protected void update()
	{
		updateModel();
		MainController.getInstance().submitUpdateUser(model);
		this.dispose();
		
	}
	
	protected void delete()
	{
		MainController.getInstance().deleteByUsername(model.getUsername());
		this.dispose();
	}
	
	protected void edit()
	{
		MainController.getInstance().updateByUserCode(model.getUsername());
		this.dispose();
		
	}
	
	protected void cancel()
	{
		this.dispose();
		
	}
	
	protected void clear()
	{
		clearComponents(this);
		
	}
	
	@SuppressWarnings("rawtypes")
	private void clearComponents(Container c)
	{
		for (Component con : c.getComponents())
			if (con instanceof JTextField)
				((JTextField) con).setText("");
			else if (con instanceof JComboBox)
				((JComboBox) con).setSelectedIndex(-1);
			else if (con instanceof Container)
				clearComponents((Container) con);
	}
	
	protected void addUser()
	{
		MainController.getInstance().submitNewMember(model);
		this.dispose();
		
	}
	
	public void displayModel()
	{
		for (ExtendedPanel p : panels)
			p.displayModel();
	}
	
	public void updateModel()
	{
		
		for (ExtendedPanel p : panels)
			p.updateModel();
	}
	
	public void disableEditing()
	{
		disableEditing(this);
	}
	
	@SuppressWarnings("rawtypes")
	private void disableEditing(Container container)
	{
		for (Component c : container.getComponents())
			if (c instanceof JTextField)
				((JTextField) c).setEditable(false);
			else if (c instanceof JComboBox)
				((JComboBox) c).setEnabled(false);
			else if (c instanceof JRadioButton)
				((JRadioButton) c).setEnabled(false);
			else if (c instanceof Container)
				disableEditing((Container) c);
		
	}
	
}
