import java.awt.*;
import javax.swing.*;

//Inherits everything from JFrame
//JFrame is the window
public class Frame extends JFrame
{
	
	private static final long serialVersionUID = 1L;
	private SortingPanel panel = new SortingPanel();
	
	public Frame()
	{
		this.setTitle("Visual Sorter");
		this.getContentPane().setPreferredSize(new Dimension(1501, 600));
		this.getContentPane().add(panel);
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public SortingPanel getPanel()
	{
		return this.panel;
	}

}
