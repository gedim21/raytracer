package com.gedim.raytracer.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.gedim.raytracer.RenderOptions;
import com.gedim.raytracer.Resolution;

public class RenderPanel extends JPanel {

	private static final long	serialVersionUID	= -8672381223178599813L;

	private BufferedImage			image;
	private Resolution				resolution;

	public RenderPanel() {
	}

	public RenderPanel(RenderOptions options, BufferedImage image) {
		this.image = image;
		this.resolution = options.getResolution();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Draw image centered.
		int x = (getWidth() - image.getWidth()) / 2;
		int y = (getHeight() - image.getHeight()) / 2;
		g.drawImage(image, x, y, this);
	}

	public void showRenderPanel() {

		this.setOpaque(true);
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(this);
		frame.setSize(resolution.getX(), resolution.getY());
		frame.setLocation(200, 200);

		Toolkit kit = frame.getToolkit();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		Insets in = kit.getScreenInsets(gs[0].getDefaultConfiguration());

		Dimension d = kit.getScreenSize();
		int max_width = (d.width - in.left - in.right);
		int max_height = (d.height - in.top - in.bottom);
		frame.setLocation((int) (max_width - frame.getWidth()) / 2, (int) (max_height - frame.getHeight()) / 2);

		frame.setVisible(true);
	}
}
