package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import DB.writeDB;

public class ClientGUI extends JFrame {
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private JTextField textField;
	private ArrayList<JButton> btnList; // ��ư�ִ¸���Ʈ
	private JTextArea textArea; // ä��â
	private JScrollPane scrollPane;
	private ArrayList<String> nickList = new ArrayList(); // �г��Ӹ���Ʈ
	private String nickName; // �г���
	private JButton btnNewButton_9; // ���� ��ư
	private JList list; // JList ������
	private boolean headOrNot = false; // ó���� ���¾ְ� ����->true
	private boolean itsMyTurn = false; // �ڱ� �����϶� true;
	private ClientManager cm;
	private ArrayList<ImageIcon> iconList = new ArrayList(); // �̹��� ����� ��̸���Ʈ
	private ArrayList<imageManager> imList = new ArrayList(); // �̹��� �� ���� ���� VO
																// ����Ʈ
	private JLabel lblNewLabel_1; // ���¸� ��Ÿ���ִ� ���̺�
	private writeDB db = new writeDB(); // ���п��� ��� �����ϱ�
	
	private actionL actionL=new actionL(); //�׼Ǹ����� ��������

	public ClientGUI() {
		cm = new ClientManager();
		connection();
		setSize(716, 532);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				if (headOrNot) {
					// ���� ������ ��������
					Object[] obj = { "headOut" };
					try {
						oos.writeObject(obj);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.exit(0);
				} else {
					Object[] obj = { "out", nickName };
					try {
						oos.writeObject(obj);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.exit(0);
				}
			}
		});

		getContentPane().setLayout(new BorderLayout(0, 0));
        
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		textField.addActionListener(new actionL());

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 153, 255));
		getContentPane().add(panel_1, BorderLayout.CENTER);

		JPanel panel_2 = new JPanel();

		JPanel panel_3 = new JPanel();

		JPanel panel_16 = new JPanel();

		JPanel panel_21 = new JPanel();
		panel_21.setBackground(new Color(153, 204, 255));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup().addGroup(gl_panel_1
						.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_21, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 546, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_16, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
						.addGap(1)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_16, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_21, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)));

		lblNewLabel_1 = new JLabel("    \uC544\uC9C1 \uC2DC\uC791\uD558\uC9C0 \uC54A\uC558\uC2B5\uB2C8\uB2E4.");
		panel_21.add(lblNewLabel_1);
		panel_16.setLayout(new BorderLayout(0, 0));

		JPanel panel_17 = new JPanel();
		panel_17.setBackground(new Color(153, 204, 255));
		panel_16.add(panel_17, BorderLayout.SOUTH);

		// ���۹�ư
		btnNewButton_9 = new JButton("\uC2DC\uC791");
		btnNewButton_9.addActionListener(new actionL());
		panel_17.add(btnNewButton_9);

		JPanel panel_18 = new JPanel();
		panel_16.add(panel_18, BorderLayout.CENTER);
		panel_18.setLayout(new BorderLayout(0, 0));

		JPanel panel_19 = new JPanel();
		panel_19.setBackground(new Color(153, 204, 255));
		panel_18.add(panel_19, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("\uC811\uC18D\uC778\uC6D0");
		panel_19.add(lblNewLabel);

		JPanel panel_20 = new JPanel();
		panel_18.add(panel_20, BorderLayout.CENTER);
		panel_20.setLayout(new GridLayout(0, 1, 0, 0));

		list = new JList();
		panel_20.add(list);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 3, 0, 0));

		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7);
		panel_7.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton = new JButton("");
		btnNewButton.setBackground(new Color(153, 204, 255));
		panel_7.add(btnNewButton);

		JPanel panel_8 = new JPanel();
		panel_4.add(panel_8);
		panel_8.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBackground(new Color(153, 204, 255));
		panel_8.add(btnNewButton_1);

		JPanel panel_9 = new JPanel();
		panel_4.add(panel_9);
		panel_9.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBackground(new Color(153, 204, 255));
		panel_9.add(btnNewButton_2);

		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		panel_5.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_10 = new JPanel();
		panel_5.add(panel_10);
		panel_10.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setBackground(new Color(153, 204, 255));
		panel_10.add(btnNewButton_3);

		JPanel panel_11 = new JPanel();
		panel_5.add(panel_11);
		panel_11.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setBackground(new Color(153, 204, 255));
		panel_11.add(btnNewButton_4);

		JPanel panel_12 = new JPanel();
		panel_5.add(panel_12);
		panel_12.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.setBackground(new Color(153, 204, 255));
		panel_12.add(btnNewButton_5);

		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6);
		panel_6.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_13 = new JPanel();
		panel_6.add(panel_13);
		panel_13.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_6 = new JButton("");
		btnNewButton_6.setBackground(new Color(153, 204, 255));
		panel_13.add(btnNewButton_6);

		JPanel panel_14 = new JPanel();
		panel_6.add(panel_14);
		panel_14.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_7 = new JButton("");
		btnNewButton_7.setBackground(new Color(153, 204, 255));
		panel_14.add(btnNewButton_7);

		JPanel panel_15 = new JPanel();
		panel_6.add(panel_15);
		panel_15.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_8 = new JButton("");
		btnNewButton_8.setBackground(new Color(153, 204, 255));
		panel_15.add(btnNewButton_8);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));

		scrollPane = new JScrollPane();
		panel_2.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		panel_1.setLayout(gl_panel_1);

		// ��ư����Ʈ�� ��ư ���̱�
		btnList = new ArrayList();
		btnList.add(btnNewButton);
		btnList.add(btnNewButton_1);
		btnList.add(btnNewButton_2);
		btnList.add(btnNewButton_3);
		btnList.add(btnNewButton_4);
		btnList.add(btnNewButton_5);
		btnList.add(btnNewButton_6);
		btnList.add(btnNewButton_7);
		btnList.add(btnNewButton_8);

		// �� ��ư�� �׼Ǹ����� ���̱�
		for (int a = 0; a < btnList.size(); a++) {
			//����
			btnList.get(a).addActionListener(actionL);
			btnList.get(a).setActionCommand(a + ""); // ��ư 0������ 8������ �׼�Ŀ�ǵ� ����
			imList.add(new imageManager(btnList.get(a), -1)); // imList
																// ��ư����Ʈ ������
																// �����ϰ� ����
		}

		// ���� ��
		boolean nickFlag = true;
		while (nickFlag) {
			nickName = JOptionPane.showInputDialog("�г����� �Է����ּ���> ");
			if (!nickName.equals("")) {
				// �г����� ����Ұ�
				setTitle(nickName + " ���� ���ӹ�");
				nickFlag = false;
			} else if (nickName == null) {
				System.exit(0);
			}
		}
		// �̹��� ���� ���̱�
		setVisible(true);
		makeImage();
		Thread t = new Thread(new connectionThread());
		t.start();
		nickList.add(nickName);
		list.setListData(nickList.toArray());
		Object[] obj = { "enter", nickName, nickName + " ���� �����ϼ̽��ϴ�.\n" };
		try {
			oos.writeObject(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// �̹��� �����
	public void makeImage() {
		// �̹��� ����
		// �̹��� �����=> ���� ��� �÷��� �̹����� �������� �ҷ����½�����...
		ImageIcon ico1 = new ImageIcon("images/pikachu.jpg"); // ù��°
		// ���¾�
		Image newImg = ico1.getImage().getScaledInstance(btnList.get(0).getWidth(), btnList.get(0).getWidth(),
				java.awt.Image.SCALE_SMOOTH);
		ico1 = new ImageIcon(newImg);

		ImageIcon ico2 = new ImageIcon("images/rapuras.png"); // �ι�°
		// ���¾�
		Image newImag = ico2.getImage().getScaledInstance(btnList.get(0).getWidth(), btnList.get(0).getHeight(),
				java.awt.Image.SCALE_SMOOTH);
		ico2 = new ImageIcon(newImag);
		iconList.add(ico1);
		iconList.add(ico2);
	}

	// ��Ÿ޼���
	public void connection() {
		try {
			socket = new Socket("localhost", 8999);
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ��Ž�����
	class connectionThread implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			boolean truth = true;
			while (truth) {
				try {
					Object[] obj = (Object[]) ois.readObject();
					String protocol = (String) obj[0];
					switch (protocol) {
					case "ready":
						String message = (String) obj[2];
						textArea.append(message);
						scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
						break;
					case "start":
						textArea.append("�����մϴ�. \n");
						scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
						cm.setBtnFree(btnList); // �������� ��ư�� ���󺹱�
						for (int a = 0; a < imList.size(); a++) {
							imList.get(a).setIconNum(-1); // imList �⺻��(-1)�� ����
						}
						if (nickList.get(0).equals(nickName)) {
							itsMyTurn = true;
							cm.setBtnFree(btnList);
							int idx = cm.getIndex(nickList, nickList.get(0));
							list.setSelectedIndex(idx);
							lblNewLabel_1.setText("����� �����Դϴ�.");
						} else {
							lblNewLabel_1.setText("����� ���ʰ� �ƴմϴ�.");
							int idx = cm.getIndex(nickList, nickList.get(0));
							list.setSelectedIndex(idx);
						}
						break;
					case "enter":
						String nickname = (String) obj[1];
						nickList.add(nickname);
						message = (String) obj[2];
						textArea.append(message);
						scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
						if (headOrNot) {
							obj[0] = "refreshList";
							obj[1] = nickList;
							oos.writeObject(obj);
						}
						break;
					case "refreshList":
						nickList = (ArrayList<String>) obj[1];
						list.setListData(nickList.toArray());
						break;
					case "head":
						headOrNot = true;
						list.setListData(nickList.toArray());
						message = (String) obj[2];
						textArea.append(message);
						scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
						break;
					case "message":
						message = (String) obj[1];
						textArea.append(message);
						scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
						break;
					case "myTurn":
						nickname = (String) obj[1];
						String actionCommand = (String) obj[2];
						int btnIndex = Integer.parseInt(actionCommand);
						int command = Integer.parseInt(actionCommand);
						if (nickname.equals(nickName)) {
							itsMyTurn = true;
							int idx = cm.getIndex(nickList, nickname);
							list.setSelectedIndex(idx);
							lblNewLabel_1.setText("����� �����Դϴ�.");
							int num = cm.getIndex(nickList, nickName);
							if (command != -1) { // command=-1 ->���ѽð� ���� ������ ����
													// ���-> ������ Ŭ���Ѱ� ������ �ʿ�x
								if (nickList.get(0).equals(nickName)) {
									// ���� �ƴ� ������ Ŭ���Ѱ� �����ϴ°���...
									//btnList.get(command).setEnabled(false);
									btnList.get(command).removeActionListener(actionL);
									btnList.get(command).setIcon(iconList.get(1));
									for (int a = 0; a < imList.size(); a++) {
										if (imList.get(a).getButton().getActionCommand().equals(actionCommand)) {
											imList.get(a).setIconNum(1);
										}
									}
								} else {
									//btnList.get(command).setEnabled(false);
									btnList.get(command).removeActionListener(actionL);
									btnList.get(command).setIcon(iconList.get(0));
									for (int a = 0; a < imList.size(); a++) {
										if (imList.get(a).getButton().getActionCommand().equals(actionCommand)) {
											imList.get(a).setIconNum(0);
										}
									}
								}
							}
						} else {
							lblNewLabel_1.setText("����� ���ʰ� �ƴմϴ�.");
							int idx = cm.getIndex(nickList, nickname);
							list.setSelectedIndex(idx);
						}
						break;
					case "win":
						// �̱����� ���� ���-> ���� ����
						lblNewLabel_1.setText("������ �������ϴ�.");
						btnNewButton_9.setEnabled(true); // ���۹�ư �ٽ� Ȱ��ȭ
						itsMyTurn = false;
						nickname = (String) obj[1];
						textArea.append(nickname + " ���� �¸��ϼ̽��ϴ�.\n");
						int nickIdx = cm.getIndex(nickList, nickname);
						/*
						if (nickname.equals(nickName)) {
							// db�� ����� �ѹ����ϵ���!
							db.writeWin(nickname, 0); // �̱�� ���
							db.writeWin(nickList.get(1 - nickIdx), 1); // ���� ���
						}*/
						// �� ��ư�� �׼Ǹ����� ���̱�
						for (int a = 0; a < btnList.size(); a++) {
							//����
							btnList.get(a).addActionListener(actionL);
							btnList.get(a).setActionCommand(a + ""); // ��ư 0������ 8������ �׼�Ŀ�ǵ� ����
							imList.add(new imageManager(btnList.get(a), -1)); // imList
																				// ��ư����Ʈ ������
																				// �����ϰ� ����
						}
						scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
						JOptionPane.showMessageDialog(null, nickname + " ���� �¸��ϼ̽��ϴ�.");
						break;
					case "headOut": // ������ ���������Ѱ��
						JOptionPane.showMessageDialog(null, "������ �����Ͽ� �����մϴ�.");
						dispose();
						break;
					case "out": // ���� �ƴ� �ְ� ���������Ѱ��
						nickname = (String) obj[1];
						textArea.append(nickname + " ���� �����ϼ̽��ϴ�.\n");
						scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
						nickList.remove(nickname);
						list.setListData(nickList.toArray());
						break;
					case "draw": // �����
						
						/*if (nickList.get(0).equals(nickName)) {
							// �ѹ��� ��� �ö󰡵���
							db.writeWin(nickList.get(0), 2); // �� �� �÷��� Ƚ����
																// �ö󰡵��� ���
							db.writeWin(nickList.get(1), 2); // �� �� �÷��� Ƚ����
																// �ö󰡵��� ���
						}*/
						lblNewLabel_1.setText("������ �������ϴ�.");
						btnNewButton_9.setEnabled(true); // ���۹�ư �ٽ� Ȱ��ȭ
						itsMyTurn = false;
						// �� ��ư�� �׼Ǹ����� ���̱�
						for (int a = 0; a < btnList.size(); a++) {
							//����
							btnList.get(a).addActionListener(actionL);
							btnList.get(a).setActionCommand(a + ""); // ��ư 0������ 8������ �׼�Ŀ�ǵ� ����
							imList.add(new imageManager(btnList.get(a), -1)); // imList
																				// ��ư����Ʈ ������
																				// �����ϰ� ����
						}
						textArea.append("����ڰ� �����ϴ�.\n");
						scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
						JOptionPane.showMessageDialog(null, "�����ϴ�. ");
						break;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					truth = false;
				}
			}
		}

	}

	// �׼Ǹ�����
	class actionL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand().equals("����")) {
				Object[] obj = { "ready", nickName, nickName + " ���� ���� �������ϴ�. \n" };
				btnNewButton_9.setEnabled(false);
				try {
					oos.writeObject(obj);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getSource() == textField && !textField.getText().equals("")) {
				String message = nickName + " ��: " + textField.getText() + "\n";
				Object[] obj = { "message", message };
				try {
					oos.writeObject(obj);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textField.setText("");
			} else if (e.getSource() instanceof JButton) {
				// ���۹�ư�� �ƴ� �ٸ� ��ư�� ������ ��..
				int winCount = 0;
				if (itsMyTurn) {
					lblNewLabel_1.setText("����� �����Դϴ�.");
					// �� �����϶��� ��ư�� ������ ����� �� �ֵ���..
					JButton button = (JButton) e.getSource();
					int num = cm.getIndex(nickList, nickName);
					String actionCommand = button.getActionCommand();
					int btnIndex = Integer.parseInt(actionCommand);
					if (nickList.get(0).equals(nickName)) {
						// �����̸� ico1, �ƴϸ� ico2 ����
						button.setIcon(iconList.get(0));
						// imList.add(new imageManager(button,
						// iconList.get(0),
						// 0));
						for (int a = 0; a < imList.size(); a++) {
							if (imList.get(a).getButton().getActionCommand().equals(actionCommand)) {
								imList.get(a).setIconNum(0);
							}
						}
						//button.setEnabled(false);
						//enable�� �ƴ� �ش� ��ư�� �׼Ǹ����ʸ� ����
						button.removeActionListener(actionL);
					} else {
						button.setIcon(iconList.get(1));
						// imList.add(new imageManager(button,
						// iconList.get(1),
						// 1));
						for (int a = 0; a < imList.size(); a++) {
							if (imList.get(a).getButton().getActionCommand().equals(actionCommand)) {
								imList.get(a).setIconNum(1);
							}
						}
						//button.setEnabled(false);
						//enable�� �ƴ� �ش� ��ư�� �׼Ǹ����ʸ� ����
						button.removeActionListener(actionL);
					}
					// 2���̼� �ϴ°�-> �� ���ʿ��� ���� �ϰ� ���濡�� �ѱ�
					Object[] obj = { "myTurn", nickList.get(1 - num), actionCommand };
					try {
						oos.writeObject(obj);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// ��� �ϼ��Ǵ� ���� ���� ���� ->�������
					if (imList.get(0).getIconNum() != -1 && imList.get(0).getIconNum() == imList.get(4).getIconNum()
							&& imList.get(0).getIconNum() == imList.get(8).getIconNum()
							&& imList.get(4).getIconNum() == imList.get(8).getIconNum()) {
						// ������ �밢�� 3���� �̹����� ���� ���
						Object[] objWin = { "win", nickName };
						try {
							oos.writeObject(objWin);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						winCount++;
					} else if (imList.get(2).getIconNum() != -1
							&& imList.get(2).getIconNum() == imList.get(4).getIconNum()
							&& imList.get(2).getIconNum() == imList.get(6).getIconNum()
							&& imList.get(4).getIconNum() == imList.get(6).getIconNum()) {
						// ���� �밢�� 3���� �̹����� ���� ���
						Object[] objWin = { "win", nickName };
						try {
							oos.writeObject(objWin);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						winCount++;
					}
					for (int a = 0; a < 7; a += 3) {
						if (imList.get(a).getIconNum() != -1 && imList.get(a + 1).getIconNum() != -1
								&& imList.get(a).getIconNum() == imList.get(a + 1).getIconNum()
								&& imList.get(a).getIconNum() == imList.get(a + 2).getIconNum()
								&& imList.get(a + 1).getIconNum() == imList.get(a + 2).getIconNum()) {
							// ���� 3���� �̹����� ���� ���
							Object[] objWin = { "win", nickName };
							try {
								oos.writeObject(objWin);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							winCount++;
						}
					}
					for (int a = 0; a < 3; a++) {
						if (imList.get(a).getIconNum() != -1 && imList.get(a + 3).getIconNum() != -1
								&& imList.get(a).getIconNum() == imList.get(a + 3).getIconNum()
								&& imList.get(a).getIconNum() == imList.get(a + 6).getIconNum()
								&& imList.get(a + 3).getIconNum() == imList.get(a + 6).getIconNum()) {
							// ���� 3���� �̹����� ���� ���
							Object[] objWin = { "win", nickName };
							try {
								oos.writeObject(objWin);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							winCount++;
						}
					}
					int disablCount = 0;
					for (int a = 0; a < btnList.size(); a++) {
						if (!btnList.get(a).isEnabled())
							disablCount++;
					}
					if (winCount == 0 && disablCount == btnList.size()) {
						// win ���°� ���� ��� ��ư�� ��Ȱ��ȭ�Ȱ��->draw(�̰� ���ϸ� ���������� �� ���߰� ã��
						// ��� ���+��ο��)
						Object[] objDraw = { "draw" };
						try {
							oos.writeObject(objDraw);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					itsMyTurn = false; // �� ���ʸ� ����
				} else {
					if (btnNewButton_9.isEnabled()) {
						JOptionPane.showMessageDialog(null, "���� ������ ���۵��� �ʾҽ��ϴ�.");
					} 
					else
					{
						JOptionPane.showMessageDialog(null, "����� ���ʰ� �ƴմϴ�.");
					}
				}
			}
		}

	}
}
