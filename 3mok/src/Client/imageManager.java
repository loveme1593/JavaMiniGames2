package Client;

import java.awt.Image;
import java.io.Serializable;
import java.util.*;

import javax.swing.*;

//이미지 구분 위한 VO
public class imageManager implements Serializable {
	private JButton button;
	private int iconNum;

	public imageManager(JButton button, int iconNum) {
		this.button = button;
		this.iconNum = iconNum;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	public int getIconNum() {
		return iconNum;
	}

	public void setIconNum(int iconNum) {
		this.iconNum = iconNum;
	}

}
