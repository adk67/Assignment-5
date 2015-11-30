import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToggleButton;


public class Assig5  
{

	public static void main(String[] args) 
	{
			try
			{
				ArrayList<Ballots> ballots = new ArrayList<Ballots>();
				Scanner scanner = new Scanner(new File("ballots.txt"));
				String[] label;
				int num = Integer.parseInt(scanner.nextLine());
				for(int k = 0; k < num; k++)
				{
					String txt = scanner.nextLine().trim();
					Ballots ballot = new Ballots();
					label = txt.split(":");
					ballot.setLabel(label[1]);
					String[] buttonTitles = txt.substring(txt.indexOf(':') + 1, txt.length()).split(",");
					for(int j = 0; j < buttonTitles.length; j++)
					{
						ballot.addButton(new JToggleButton(buttonTitles[j]));
					}
					ballots.add(ballot);
				}
				
				
				JButton vote = new JButton("Login to Vote");
				JButton cast = new JButton("Cast Your Vote");
				
				
				new Frame(ballots, vote, cast);
			}
			catch(Exception ex)
			{
				System.out.println(ex.toString());
			}
		

	}

}
