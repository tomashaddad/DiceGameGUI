package client;

import javax.swing.SwingUtilities;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.DiceFrame;
import view.GameEngineCallbackGUI;
import view.GameEngineCallbackImpl;

public class DiceGameApp
{
	public static void main(String[] args)
	{		
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new DiceFrame();
			}
		});
	}
}