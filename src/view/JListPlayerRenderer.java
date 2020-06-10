package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import model.interfaces.Player;

public class JListPlayerRenderer extends JPanel implements ListCellRenderer<Player>
{
	private String name = "Placeholder";
	
	private JLabel points = new JLabel("");
	private JLabel bet = new JLabel("");
	
	public JListPlayerRenderer()
	{
		setOpaque(true);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		setBackground(Color.WHITE);
		setFocusable(true);

		add(points);
		add(bet);
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Player> list, Player value, int index,
			boolean isSelected, boolean cellHasFocus)
	{
		name = value.getPlayerName();
		points.setText("Points: " + Integer.toString(value.getPoints()));
		
//		if (isSelected)
//		{
//			setBackground(list.getSelectionBackground());
//			setForeground(list.getSelectionForeground());
//		}
//		
//		else
//		{
//			setBackground(list.getBackground());
//			setForeground(list.getForeground());
//		}
		
		setFont(list.getFont());
		
		setEnabled(list.isEnabled());
		
		if (isSelected && cellHasFocus)
		{
			setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createTitledBorder(new LineBorder(Color.RED, 5), name, TitledBorder.LEFT,
							TitledBorder.TOP, new Font("Arial", Font.ITALIC, 16)),
					BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		}
		
		else
			setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createTitledBorder(new LineBorder(Color.decode("#415e4a"), 5), name, TitledBorder.LEFT,
							TitledBorder.TOP, new Font("Arial", Font.ITALIC, 16)),
					BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		return this;
	}
	
	public void setPointText(String text)
	{
		points.setText("Points :" + text);
	}
}
