import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Frame extends JFrame implements ActionListener
{

	private String userID;
	private ArrayList<Ballots> _ballots;
	private JButton _vote;
	private JButton _cast;
	
	public Frame(ArrayList<Ballots> ballots, JButton vote, JButton cast )
	{
		//initialization
		userID = "";
		_ballots = ballots;
		_vote = vote;
		_cast = cast;
		
		//setting up the window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
		this.setTitle("E-Vote Version 1.0");
		this.setSize(650,250);
		
		
		//setting commands and adding actions to the vote and cast buttons
	
		
		vote.setActionCommand("vote");
		vote.addActionListener(this);
		cast.setActionCommand("cast");
		cast.addActionListener(this);
		
		for(int k = 0; k < ballots.size(); k++)
		{
			this.add(_ballots.get(k));
			_ballots.get(k).disableButtons();
		}
		this.add(vote);
		this.add(cast);
		cast.setEnabled(false);
		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println(e.getActionCommand());
		//if the the vote button is pressed
		if(e.getActionCommand().equals("vote"));
		{
			//System.out.println("vote");
			userID = JOptionPane.showInputDialog(this, "Please enter your voter ID");
			
			try
			{
				//scanner reads in the voters.txt file - rename if needed
				Scanner scanner = new Scanner(new File("voters.txt"));
				while(scanner.hasNext())
				{
					String txt = scanner.nextLine();
					String[] string = txt.split(":");
					//checks to see if the ID entered is correct
					if(string[0].equals(userID))
					{
						//checks to see if the person has already voted
						if(string[2].trim().equals("true"))
						{
							JOptionPane.showMessageDialog(this, string[1] + "you have already voted");
						}
						else
						{
							JOptionPane.showMessageDialog(this, string[1] + ", please make your choices");
							_vote.setEnabled(false);
							_cast.setEnabled(true);
							for(int k = 0; k < _ballots.size(); k++)
							{
								_ballots.get(k).enableButtons();
							}
						}
					}
				}
			}
			catch(Exception ex)
			{
				
			}
		}
		
		//if the cast button is pressed
		if(e.getActionCommand().equals("cast"))
		{
			for(int k = 0; k < _ballots.size(); k++)
			{
				_ballots.get(k).fileWriter();
			}
			for(int j = 0; j < _ballots.size(); j++)
			{
				_ballots.get(j).disableButtons();
			}
			_cast.setEnabled(false);
			_vote.setEnabled(true);
			updateVoters();
			JOptionPane.showMessageDialog(this, "Thank you for your vote!");
		}
		
	}

	//updates the .txt files
	private void updateVoters() 
	{
		try
		{
			PrintWriter printWriter = new PrintWriter(new FileWriter("tmpVoters.txt"));
			Scanner scanner = new Scanner(new File("voters.txt"));
			while(scanner.hasNext())
			{
				String txt = scanner.nextLine();
				String string[] = txt.trim().split(":");
				if(string[0].equals(userID))
				{
					txt = string[0]+ ":" + string[1] + ":" + true;
				}
				printWriter.println(txt);
			}
			scanner.close();
			printWriter.close();
			
			//switching the files, deleting the original voters.txt
			File file = new File("voters.txt");
			File file2 = new File("tmpVoter.txt");
			if(file.exists())
			{
				file.delete();
				file2.renameTo(new File("voters.txt")); //doesn't work on macbook
			}
		}
		catch(Exception ex)
		{
			
		}

	}
}


