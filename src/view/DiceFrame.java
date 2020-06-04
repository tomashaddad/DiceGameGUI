package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

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
	
	public DiceFrame()
	{
		super("Knockoff Dice Game");

		model.addGameEngineCallback(new GameEngineCallbackImpl());
		model.addGameEngineCallback(new GameEngineCallbackGUI(this));
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
		
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		int width = (int) screenSize.getWidth();
//		int height = (int) screenSize.getHeight();
//		setPreferredSize(new Dimension(width/2, height/2));
		
		setJMenuBar(new GameMenu());
		
		this.add(diceToolbar, BorderLayout.NORTH);
		this.add(new DiceSummaryPanel(model), BorderLayout.WEST);
		this.add(dicePanel, BorderLayout.CENTER);
		this.add(new DiceStatus(model), BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
	}
	
	public DicePanel getDicePanel()
	{
		return dicePanel;
	}
	
	public GameEngine getGameEngine()
	{
		return model;
	}
}