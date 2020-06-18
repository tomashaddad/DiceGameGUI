package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import constants.Events;
import controller.game.GameController;
import model.GameEngineImpl;
import view.game.GamePanel;
import view.menu.GameMenu;
import view.summaries.DiceSummaryPanel;

@SuppressWarnings("serial")
public class DiceFrame extends JFrame implements PropertyChangeListener
{
	private GameController gameController;
	private GamePanel gamePanel;
	private DiceSummaryPanel diceSummaryPanel;

	public DiceFrame()
	{
		super("Knockoff Dice Game");
		
		// enable or disable GUI logging
		disableGUILogger(true);

		setLayout(new BorderLayout());
		
		gameController = new GameController(new GameEngineImpl());				
		gameController.addGameEngineCallback(new GameEngineCallbackImpl());
		gameController.addGameEngineCallback(new GameEngineCallbackGUI(gameController));
		
		gamePanel = new GamePanel(gameController);
		diceSummaryPanel = new DiceSummaryPanel(gameController);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// set window size to half the length and width of monitor
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		setPreferredSize(new Dimension(width / 2, height / 2)); 

		setJMenuBar(new GameMenu(gameController));

		add(diceSummaryPanel, BorderLayout.WEST);
		add(gamePanel, BorderLayout.CENTER);

		gameController.addListener(this);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void disableGUILogger(boolean disable)
	{
		Level level = disable ? Level.OFF : Level.FINE;
		
		Logger.getLogger("java.awt").setLevel(level);
		Logger.getLogger("sun.awt").setLevel(level);
		Logger.getLogger("javax.swing").setLevel(level);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		String event = evt.getPropertyName();
		
		switch (event)
		{
		case Events.BET_FAILED:
			JOptionPane.showMessageDialog(this, "Player creation failed! Please enter a non-zero bet using your available points.");
			break;
		
		case Events.ROLL_FAILED:
			JOptionPane.showMessageDialog(this, "Roll failed! You cannot roll without placing a bet.");
			break;
			
		default:
			// do nothing
			break;
		}
	}
}