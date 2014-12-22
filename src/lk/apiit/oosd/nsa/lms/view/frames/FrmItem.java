package lk.apiit.oosd.nsa.lms.view.frames;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import lk.apiit.oosd.nsa.lms.controller.MainController;
import lk.apiit.oosd.nsa.lms.controller.UIFactory;
import lk.apiit.oosd.nsa.lms.model.types.ItemType;
import lk.apiit.oosd.nsa.lms.view.models.FrmItemModel;
import lk.apiit.oosd.nsa.lms.view.panels.PanelDate;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class FrmItem extends JInternalFrame
{
	
	private FrmItemModel	model;
	
	public FrmItem(FrmItemModel model, FRM_TYPE frmType)
	{
		setBounds(new Rectangle(100, 100, 300, 400));
		setResizable(true);
		this.model = model;
		
		this.panel = new JPanel();
		getContentPane().add(this.panel, BorderLayout.NORTH);
		this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.X_AXIS));
		this.lblItemType = new JLabel("Item Type: ");
		this.panel.add(this.lblItemType);
		this.comboBox = new JComboBox<ItemType>();
		this.comboBox.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				itemTypeChange();
				
			}
		});
		
		this.comboBox.setModel(new DefaultComboBoxModel<ItemType>(ItemType
				.values()));
		this.panel.add(this.comboBox);
		
		this.panelInput = new JPanel();
		getContentPane().add(this.panelInput, BorderLayout.CENTER);
		
		initInputPanels(model);
		
		initButtons();
		
		switch (frmType)
		{
			case MODIFY:
				this.setTitle("Update Item");
				buttonsLayout.show(panelButtons, "name_22832690572997");
				comboBox.setSelectedItem(model.getItemType());
				switchItemTypePanel(model.getItemType());
				comboBox.setEditable(false);
				comboBox.setEnabled(false);
				displayModel();
				break;
			case NEW:
				this.setTitle("Add Item");
				buttonsLayout.show(panelButtons, "name_22809918370537");
				break;
			case VIEW:
				this.setTitle("View Item");
				
				buttonsLayout.show(panelButtons, "name_22822116951719");
				comboBox.setSelectedItem(model.getItemType());
				itemTypeChange();
				comboBox.setEditable(false);
				comboBox.setEnabled(false);
				displayModel();
				disableEditing();
				break;
			default:
				break;
		
		}
		
		this.setClosable(true);
		
		this.moveToFront();
		
	}
	
	private void initInputPanels(FrmItemModel model)
	{
		inputLayout = new CardLayout(0, 0);
		this.panelInput.setLayout(inputLayout);
		this.panelBook = new JPanel();
		this.panelInput.add(this.panelBook, "name_22288144955575");
		this.panelBook.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.GLUE_COLSPEC,
				FormFactory.PREF_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.PREF_COLSPEC, FormFactory.GLUE_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, }));
		this.lblIsbn = new JLabel("ISBN:");
		this.panelBook.add(this.lblIsbn, "4, 2, left, center");
		this.txtISBN = new JTextField();
		this.panelBook.add(this.txtISBN, "6, 2, right, center");
		this.txtISBN.setColumns(24);
		this.lblTitle = new JLabel("Title:");
		this.panelBook.add(this.lblTitle, "4, 4, left, center");
		this.txtTitle_1 = new JTextField();
		this.panelBook.add(this.txtTitle_1, "6, 4, right, center");
		this.txtTitle_1.setColumns(24);
		this.lblAuthor = new JLabel("Author:");
		this.panelBook.add(this.lblAuthor, "4, 6, left, center");
		this.txtAuthor = new JTextField();
		this.panelBook.add(this.txtAuthor, "6, 6, right, center");
		this.txtAuthor.setColumns(24);
		this.lblEdition = new JLabel("Edition:");
		this.panelBook.add(this.lblEdition, "4, 8, left, center");
		this.txtEdition = new JTextField();
		this.panelBook.add(this.txtEdition, "6, 8, right, center");
		this.txtEdition.setColumns(24);
		this.panelCD = new JPanel();
		this.panelInput.add(this.panelCD, "name_22328905050030");
		this.panelCD.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.GLUE_COLSPEC, FormFactory.PREF_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.PREF_COLSPEC,
				FormFactory.GLUE_COLSPEC, }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, }));
		this.lblTitle_1 = new JLabel("Title:");
		this.panelCD.add(this.lblTitle_1, "2, 2, left, center");
		this.txtTitle_2 = new JTextField();
		this.panelCD.add(this.txtTitle_2, "4, 2, right, fill");
		this.txtTitle_2.setColumns(24);
		this.lblVersion = new JLabel("Version:");
		this.panelCD.add(this.lblVersion, "2, 4, left, center");
		this.txtVersion = new JTextField();
		this.panelCD.add(this.txtVersion, "4, 4, right, fill");
		this.txtVersion.setColumns(24);
		this.panelPeriodical = new JPanel();
		this.panelInput.add(this.panelPeriodical, "name_22339558892351");
		this.panelPeriodical.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.GLUE_COLSPEC, FormFactory.PREF_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.PREF_COLSPEC, },
				new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.PREF_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.PREF_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.PREF_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.PREF_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.PREF_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.PREF_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC, }));
		this.lblIssn = new JLabel("ISSN:");
		this.panelPeriodical.add(this.lblIssn, "2, 2, left, default");
		this.txtISSN = new JTextField();
		this.panelPeriodical.add(this.txtISSN, "4, 2, right, center");
		this.txtISSN.setColumns(24);
		this.lblName = new JLabel("Name:");
		this.panelPeriodical.add(this.lblName, "2, 4, left, default");
		this.txtName = new JTextField();
		this.panelPeriodical.add(this.txtName, "4, 4, right, default");
		this.txtName.setColumns(24);
		this.lblVolume = new JLabel("Volume:");
		this.panelPeriodical.add(this.lblVolume, "2, 6, left, default");
		this.txtVolume = new JTextField();
		this.panelPeriodical.add(this.txtVolume, "4, 6, right, default");
		this.txtVolume.setColumns(24);
		this.lblIssue = new JLabel("Issue:");
		this.panelPeriodical.add(this.lblIssue, "2, 8, left, default");
		this.txtIssue = new JTextField();
		this.panelPeriodical.add(this.txtIssue, "4, 8, right, default");
		this.txtIssue.setColumns(24);
		this.lblPublisher = new JLabel("Publisher:");
		this.panelPeriodical.add(this.lblPublisher, "2, 10, left, default");
		this.txtPublisher = new JTextField();
		this.panelPeriodical.add(this.txtPublisher, "4, 10, right, default");
		this.txtPublisher.setColumns(24);
		this.lblPublished = new JLabel("Published:");
		this.panelPeriodical.add(this.lblPublished, "2, 12, left, top");
		
		pubslihedDate = new PanelDate(model.getPublishedDateModel());
		
		this.panelPeriodical.add(this.pubslihedDate, "4, 12, right, fill");
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
				switch ((ItemType) comboBox.getSelectedItem())
				{
				
					case BOOK:
						if (txtTitle_1.getText() == null
								|| txtTitle_1.getText().length() == 0)
						{
							UIFactory.getInstance().warn("Validation error!",
									"Title of a book cannot be empty!\n");
							return;
						}
						
						break;
					case CD:
						if (txtTitle_1.getText() == null
								|| txtTitle_1.getText().length() == 0)
						{
							UIFactory.getInstance().warn("Validation error!",
									"Title of a CD cannot be empty!\n");
							return;
						}
						if (txtVersion.getText() == null
								|| txtTitle_1.getText().length() == 0)
						{
							UIFactory.getInstance().warn("Validation error!",
									"Title version of a CD cannot be empty!\n");
							return;
						}
						break;
					case PERIODICAL:
						break;
					default:
						break;
				
				}
				updateModel();
				addItem();
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
	
	private void update()
	{
		updateModel();
		MainController.getInstance().submitUpdateItem(model);
		this.dispose();
	}
	
	private void edit()
	{
		MainController.getInstance().updateByItemCode(model.getItemCode());
		this.dispose();
	}
	
	private void addItem()
	{
		MainController.getInstance().submitNewItem(model);
		dispose();
	}
	
	private void clear()
	{
		for (Component c : this.getComponents())
			if (c instanceof JTextField)
				((JTextField) c).setText("");
			else if (c instanceof JComboBox)
				((JComboBox<?>) c).setSelectedIndex(-1);
		
	}
	
	private void delete()
	{
		MainController.getInstance().deleteByItemCode(model.getItemCode());
		this.dispose();
		
	}
	
	private void cancel()
	{
		this.dispose();
		
	}
	
	private void itemTypeChange()
	{
		
		switchItemTypePanel((ItemType) comboBox.getSelectedItem());
		
		pack();
		
		setVisible(true);
		
	}
	
	public void switchItemTypePanel(ItemType itemType)
	{
		switch (itemType)
		{
			case BOOK:
				inputLayout.show(panelInput, "name_22288144955575");
				break;
			case CD:
				inputLayout.show(panelInput, "name_22328905050030");
				break;
			case PERIODICAL:
				inputLayout.show(panelInput, "name_22339558892351");
				break;
			default:
				break;
		
		}
	}
	
	public void displayModel()
	{
		switch (model.getItemType())
		{
			case BOOK:
				txtISBN.setText(model.getISBN());
				txtTitle_1.setText(model.getTitle());
				txtAuthor.setText(model.getAuthor());
				txtEdition.setText(model.getEdition().toString());
				break;
			case CD:
				txtTitle_2.setText(model.getTitle());
				txtVersion.setText(model.getVersion());
				break;
			case PERIODICAL:
				txtISSN.setText(model.getISSN());
				txtName.setText(model.getName());
				txtVolume.setText(Integer.toString(model.getVolume()));
				txtIssue.setText(Integer.toString(model.getIssue()));
				txtPublisher.setText(model.getPublisher());
				pubslihedDate.setModel(model.getPublishedDateModel());
				pubslihedDate.displayModel();
				break;
			default:
				break;
		
		}
		
	}
	
	public void updateModel()
	{
		model.setItemType((ItemType) comboBox.getSelectedItem());
		switch ((ItemType) comboBox.getSelectedItem())
		{
			case BOOK:
				model.setISBN(txtISBN.getText());
				model.setTitle(txtTitle_1.getText());
				model.setAuthor(txtAuthor.getText());
				model.setEdition(Integer.parseInt(txtEdition.getText()));
				break;
			case CD:
				model.setTitle(txtTitle_2.getText());
				model.setVersion(txtVersion.getText());
				break;
			case PERIODICAL:
				model.setISSN(txtISSN.getText());
				model.setName(txtName.getText());
				model.setVolume(Integer.parseInt(txtVolume.getText()));
				model.setIssue(Integer.parseInt(txtIssue.getText()));
				model.setPublisher(txtPublisher.getText());
				pubslihedDate.setModel(model.getPublishedDateModel());
				pubslihedDate.updateModel();
				
				break;
			default:
				break;
		
		}
		
	}
	
	private static final long	serialVersionUID	= 1L;
	private JPanel				panel;
	private JLabel				lblItemType;
	private JComboBox<ItemType>	comboBox;
	private JPanel				panelInput;
	private JPanel				panelBook;
	private JPanel				panelCD;
	private JPanel				panelPeriodical;
	private JLabel				lblIsbn;
	private JLabel				lblTitle;
	private JLabel				lblAuthor;
	private JLabel				lblEdition;
	private JTextField			txtISBN;
	private JTextField			txtTitle_1;
	private JTextField			txtAuthor;
	private JTextField			txtEdition;
	private JPanel				panelButtons;
	private JPanel				panelAdd;
	private JPanel				panelView;
	private JPanel				panelUpdate;
	private JButton				btnAdd;
	private JButton				btnClear;
	private JButton				btnCancel;
	private JButton				btnEdit;
	private JButton				btnDelete;
	private JButton				btnCancel_1;
	private JButton				btnUpdate;
	private JButton				btnDelete_1;
	private JButton				btnCancel_2;
	private CardLayout			inputLayout;
	private CardLayout			buttonsLayout;
	private JLabel				lblTitle_1;
	private JLabel				lblVersion;
	private JTextField			txtTitle_2;
	private JTextField			txtISSN;
	private JTextField			txtName;
	private JTextField			txtVolume;
	private JTextField			txtIssue;
	
	private PanelDate			pubslihedDate;				;
	private JTextField			txtPublisher;
	private JLabel				lblIssn;
	private JLabel				lblVolume;
	private JLabel				lblName;
	private JLabel				lblIssue;
	private JLabel				lblPublisher;
	private JLabel				lblPublished;
	private JTextField			txtVersion;
	
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
