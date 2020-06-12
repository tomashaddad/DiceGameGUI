package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.Events;
import controller.manager.EventManager;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class DiceStatusBar extends JPanel implements PropertyChangeListener
{
	JLabel label;
	
	public DiceStatusBar(EventManager eventManager)
	{
		setBackground(Color.decode("#B8C4BB"));
		setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
		setLayout(new BorderLayout());
		
		label = new JLabel("Placeholder");
		
		add(label, BorderLayout.CENTER);
		
		eventManager.addListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		Player player = (Player) evt.getNewValue();
		
		String event = evt.getPropertyName();
		
		switch(event)
		{
		case Events.PLAYER_SELECTED:
			label.setText(player.getPlayerName());
			break;
		}
	}
}
