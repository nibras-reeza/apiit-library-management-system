package lk.apiit.oosd.nsa.lms.view.frames;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class FrmAbout extends JInternalFrame
{
	public FrmAbout()
	{
		BufferedImage image;
		try
		{
			image = ImageIO.read(new File("imgs/about.jpg"));
			ImageIcon img = new ImageIcon(image);
			add(new JLabel(img));
			
			this.setBounds(75, 75, img.getIconWidth(), img.getIconHeight());
		}
		catch (IOException e)
		{
			
		}
		
	}
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 8664700947562527434L;
	
}
