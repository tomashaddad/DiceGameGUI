package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import model.GameEngineImpl;
import model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class DiceFrame extends JFrame
{
	private final GameEngine model = new GameEngineImpl();
	private DicePanel dicePanel = new DicePanel(this);
	private DiceToolbar diceToolbar = new DiceToolbar(this);
	private DiceSummaryPanel diceSummaryPanel = new DiceSummaryPanel(this);

	public DiceFrame()
	{
		super("Knockoff Dice Game");
		
		// enable or disable GUI logging for debugging
		disableGUILogger(true);

		model.addGameEngineCallback(new GameEngineCallbackImpl());
		model.addGameEngineCallback(new GameEngineCallbackGUI(this));

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
		add(new DiceStatus(model), BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public GameEngine getGameEngine()
	{
		return model;
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