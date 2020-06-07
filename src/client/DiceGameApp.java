package client;

import javax.swing.SwingUtilities;

import view.DiceFrame;

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