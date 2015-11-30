import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.*;

public class Ballots extends JPanel implements ActionListener 
{
	
	private JLabel _label;
	private ArrayList<JToggleButton> _buttons;
	
	public Ballots()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		_label = new JLabel();
		_buttons = new ArrayList<JToggleButton>();
	}
	
	//Sets the text in the JLabels
	public void setLabel(String text)
	{
		_label.setText(text);
		this.add(_label);
	}
	
	//adds new buttons
	public void addButton(JToggleButton button)
	{
		button.addActionListener(this);
		_buttons.add(button);
		this.add(button);
	}
	
	//disables the button 
	public void disableButtons()
	{
		for(int k = 0; k < _buttons.size(); k++)
		{
			_buttons.get(k).setEnabled(false);
		}
	}
	
	//enables the buttons
	public void enableButtons()
	{
		for(int k = 0; k < _buttons.size(); k++)
		{
			_buttons.get(k).setEnabled(true);
		}
	}
	
	public void fileWriter()
	{
		try
		{
			PrintWriter printWriter = new PrintWriter(new FileWriter(_label.getText() + ".txt"));
			for(int k = 0; k < _buttons.size(); k++)
			{
				printWriter.println(_buttons.get(k).getText() + ":" + ((_buttons.get(k).isSelected())?"1":"0") );
			}
			printWriter.close();
		}
		catch(Exception ex)
		{
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JToggleButton button = (JToggleButton)e.getSource();
		if(button.isSelected())
		{
			button.setForeground(Color.RED);
		}
		for(int k = 0; k < _buttons.size(); k++)
		{
			if(_buttons.get(k) != button)
			{
				_buttons.get(k).setSelected(false);
				_buttons.get(k).setForeground(Color.BLACK);
			}
		}
	}
}
