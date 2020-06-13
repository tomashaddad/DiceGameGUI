package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import controller.game.GameController;
import model.GameEngineImpl;
import view.dice.GamePanel;
import view.menu.GameMenu;
import view.statusbar.DiceStatusBar;
import view.summaries.DiceSummaryPanel;
import view.toolbar.DiceToolbar;

@SuppressWarnings("serial")
public class DiceFrame extends JFrame
{
	private GameController gameController;
	private GamePanel gamePanel;
	private DiceToolbar diceToolbar;
	private DiceSummaryPanel diceSummaryPanel;
	private DiceStatusBar diceStatus;

	public DiceFrame()
	{
		super("Knockoff Dice Game");
		
		// enable or disable GUI logging
		disableGUILogger(true);

		setLayout(new BorderLayout());
		
		gameController = new GameController(new GameEngineImpl());				
		gameController.getGameEngine().addGameEngineCallback(new GameEngineCallbackImpl());
		gameController.getGameEngine().addGameEngineCallback(new GameEngineCallbackGUI(gameController));
		
		gamePanel = new GamePanel(gameController);
		diceToolbar = new DiceToolbar(gameController);
		diceSummaryPanel = new DiceSummaryPanel(gameController);
		diceStatus = new DiceStatusBar(gameController);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		setPreferredSize(new Dimension(width / 2, height / 2)); 

		setJMenuBar(new GameMenu());

		add(diceSummaryPanel, BorderLayout.WEST);
		add(gamePanel, BorderLayout.CENTER);
		add(diceStatus, BorderLayout.SOUTH);

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

}