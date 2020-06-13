package view.statusbar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.Events;
import controller.game.GameController;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class DiceStatusBar extends JPanel implements PropertyChangeListener
{
	JLabel label;
	GameController gameController;
	
	public DiceStatusBar(GameController gameController)
	{
		this.gameController = gameController;
		
		setBackground(Color.decode("#B8C4BB"));
		setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
		setLayout(new BorderLayout());
		
		label = new JLabel("House MD");
		
		add(label, BorderLayout.CENTER);
		
		gameController.addListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		String event = evt.getPropertyName();
		
		switch(event)
		{
		case Events.PLAYER_SELECTED:
			label.setText(gameController.getSelectedPlayer().getPlayerName());
			break;
			
		case Events.HOUSE_SELECTED:
			label.setText("House MD");
			break;
		}
	}
}
