package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

// reference: https://stackoverflow.com/questions/38021262/how-to-dynamically-add-elements-to-a-jscrollpanel

@SuppressWarnings("serial")
public class DiceSummaryPanel extends JPanel
{
	private DiceFrame frame;
	private JPanel innerPanel;

	public DiceSummaryPanel(DiceFrame diceFrame)
	{
		this.frame = diceFrame;
		setLayout(new BorderLayout());

		this.setPreferredSize(new Dimension(300, 0));

		add(createScrollPane(), BorderLayout.CENTER);

		addScrollPaneItem(new SinglePlayerSummary("Tom", 1000, 300));
	}

	public JScrollPane createScrollPane()
	{

		/*
		 * Wrapper JPanel is contained in JScrollPane as north-aligned BorderLayout.
		 * Without the wrapper, any panels added to the inner panel will stretch
		 * vertically to evenly fill height
		 */

		JPanel wrapperPanel = new JPanel(new BorderLayout());
		wrapperPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		innerPanel = new JPanel(new GridLayout(0, 1, 10, 10));
		wrapperPanel.add(innerPanel, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane(wrapperPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		return scrollPane;
	}

	public void addScrollPaneItem(JPanel item)
	{
		innerPanel.add(item);
	}

	public JPanel getInnerPanel()
	{
		return innerPanel;
	}
}
