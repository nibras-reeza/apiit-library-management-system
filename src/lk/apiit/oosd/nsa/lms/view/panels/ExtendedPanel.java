package lk.apiit.oosd.nsa.lms.view.panels;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public abstract class ExtendedPanel extends JPanel
{
	
	private static final long	serialVersionUID	= 6222418387585684161L;
	
	public void clear()
	{
		for (Component c : getComponents())
			if (c instanceof JTextField)
				((JTextField) c).setText("");
			else if (c instanceof JComboBox)
				((JComboBox<?>) c).setSelectedIndex(-1);
			else if (c instanceof JRadioButton)
				((JRadioButton) c).setSelected(false);
	}
	
	public abstract void displayModel();
	
	public abstract void updateModel();
	
}
