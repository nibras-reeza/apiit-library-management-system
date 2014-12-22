package lk.apiit.oosd.nsa.lms.view.panels;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormatSymbols;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import lk.apiit.oosd.nsa.lms.view.models.DateModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelDate extends ExtendedPanel
{
	private DateModel	model;
	
	public PanelDate(DateModel model)
	{
		this.model = model;
		
		setLayout(new FormLayout(new ColumnSpec[] { FormFactory.GLUE_COLSPEC,
				FormFactory.PREF_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("pref:grow"),
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.PREF_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("pref:grow"),
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.PREF_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("pref:grow"),
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.GLUE_COLSPEC, },
				new RowSpec[] { FormFactory.PREF_ROWSPEC, }));
		
		Calendar c = Calendar.getInstance();
		
		this.lblYear = new JLabel("Year:");
		add(this.lblYear, "2, 1, right, center");
		this.cmbYear = new JComboBox<Integer>();
		for (int y = c.get(Calendar.YEAR), i = 0; i < 200; i++, y--)
			cmbYear.addItem(y);
		add(this.cmbYear, "4, 1, right, center");
		
		this.lblMonth = new JLabel("Month:");
		add(this.lblMonth, "6, 1, right, center");
		this.cmbMonth = new JComboBox<String>();
		this.cmbMonth.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent arg0)
			{
				int i = cmbMonth.getSelectedIndex() + 1;
				
				int m = 0;
				
				switch (i)
				{
					case 2:
						m = 28;
						break;
					case 1:
					case 3:
					case 5:
					case 7:
					case 8:
					case 10:
					case 12:
						m = 31;
						break;
					default:
						m = 30;
				}
				
				days.removeAllElements();
				for (i = 1; i <= m; i++)
					days.addElement(i);
			}
		});
		
		for (String s : new DateFormatSymbols().getMonths())
			cmbMonth.addItem(s);
		add(this.cmbMonth, "8, 1, right, center");
		
		this.lblDay = new JLabel("Day:");
		add(this.lblDay, "10, 1, right, center");
		
		cmbDay = new JComboBox<Integer>(days);
		add(this.cmbDay, "12, 1, right, center");
		
		cmbDay.setSelectedIndex(-1);
	}
	
	public DateModel getModel()
	{
		return this.model;
	}
	
	public void setModel(DateModel model)
	{
		this.model = model;
	}
	
	/**
	 * 
	 */
	private static final long				serialVersionUID	= 572292037368261230L;
	private JLabel							lblYear;
	private JLabel							lblMonth;
	private JLabel							lblDay;
	private JComboBox<Integer>				cmbYear;
	private JComboBox<String>				cmbMonth;
	private JComboBox<Integer>				cmbDay;
	private DefaultComboBoxModel<Integer>	days				= new DefaultComboBoxModel<Integer>();
	
	@Override
	public void displayModel()
	{
		cmbDay.setSelectedItem(model.getDay());
		cmbYear.setSelectedItem(model.getYear());
		
		cmbMonth.setSelectedIndex(model.getMonth() - 1);
		
	}
	
	@Override
	public void updateModel()
	{
		model.setDay((Integer) cmbDay.getSelectedItem());
		model.setYear((Integer) cmbYear.getSelectedItem());
		
		model.setMonth(cmbMonth.getSelectedIndex() + 1);
	}
	
}
