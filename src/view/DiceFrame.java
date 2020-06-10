package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import controller.GameController;
import model.GameEngineImpl;

@SuppressWarnings("serial")
public class DiceFrame extends JFrame
{
	private GameController gameController;
	private DicePanel dicePanel;
	private DiceToolbar diceToolbar;
	private DiceSummaryPanel diceSummaryPanel;
	private DiceStatusBar diceStatus;

	public DiceFrame()
	{
		super("Knockoff Dice Game");
		
		// enable or disable GUI logging for debugging
		disableGUILogger(true);
		
		gameController = new GameController(new GameEngineImpl());				
		gameController.addGameEngineCallback(new GameEngineCallbackImpl());
		gameController.addGameEngineCallback(new GameEngineCallbackGUI(this));
		
		dicePanel = new DicePanel(gameController);
		diceToolbar = new DiceToolbar(gameController);
		diceSummaryPanel = new DiceSummaryPanel(gameController);
		diceStatus = new DiceStatusBar(gameController);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		setPreferredSize(new Dimension(width / 2, height / 2)); 

		setJMenuBar(new GameMenu());

		add(diceToolbar, BorderLayout.NORTH);
		add(diceSummaryPanel, BorderLayout.WEST);
		add(dicePanel, BorderLayout.CENTER);
		add(diceStatus, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public DicePanel getDicePanel()
	{
		return dicePanel;
	}

	public DiceToolbar getDiceToolbar()
	{
		return diceToolbar;
	}

	public DiceSummaryPanel getDiceSummaryPanel()
	{
		return diceSummaryPanel;
	}

	public void disableGUILogger(boolean disable)
	{
		Level level = disable ? Level.OFF : Level.FINE;
		
		Logger.getLogger("java.awt").setLevel(level);
		Logger.getLogger("sun.awt").setLevel(level);
		Logger.getLogger("javax.swing").setLevel(level);
	}

}