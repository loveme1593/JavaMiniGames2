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
	private ArrayList<JButton> btnList; // 버튼넣는리스트
	private JTextArea textArea; // 채팅창
	private JScrollPane scrollPane;
	private ArrayList<String> nickList = new ArrayList(); // 닉네임리스트
	private String nickName; // 닉네임
	private JButton btnNewButton_9; // 시작 버튼
	private JList list; // JList 접속자
	private boolean headOrNot = false; // 처음에 들어온애가 방장->true
	private boolean itsMyTurn = false; // 자기 차례일땐 true;
	private ClientManager cm;
	private ArrayList<ImageIcon> iconList = new ArrayList(); // 이미지 저장된 어레이리스트
	private ArrayList<imageManager> imList = new ArrayList(); // 이미지 비교 위해 만든 VO
																// 리스트
	private JLabel lblNewLabel_1; // 상태를 나타내주는 레이블
	private writeDB db = new writeDB(); // 승패여부 디비에 저장하기
	
	private actionL actionL=new actionL(); //액션리스너 전역변수

	public ClientGUI() {
		cm = new ClientManager();
		connection();
		setSize(716, 532);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				if (headOrNot) {
					// 방장 나가면 게임종료
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

		// 시작버튼
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

		// 버튼리스트에 버튼 붙이기
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

		// 각 버튼에 액션리스너 붙이기
		for (int a = 0; a < btnList.size(); a++) {
			//수정
			btnList.get(a).addActionListener(actionL);
			btnList.get(a).setActionCommand(a + ""); // 버튼 0번부터 8번까지 액션커맨드 설정
			imList.add(new imageManager(btnList.get(a), -1)); // imList
																// 버튼리스트 순서와
																// 동일하게 붙임
		}

		// 입장 시
		boolean nickFlag = true;
		while (nickFlag) {
			nickName = JOptionPane.showInputDialog("닉네임을 입력해주세요> ");
			if (!nickName.equals("")) {
				// 닉네임은 공백불가
				setTitle(nickName + " 님의 게임방");
				nickFlag = false;
			} else if (nickName == null) {
				System.exit(0);
			}
		}
		// 이미지 만들어서 붙이기
		setVisible(true);
		makeImage();
		Thread t = new Thread(new connectionThread());
		t.start();
		nickList.add(nickName);
		list.setListData(nickList.toArray());
		Object[] obj = { "enter", nickName, nickName + " 님이 입장하셨습니다.\n" };
		try {
			oos.writeObject(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 이미지 만들기
	public void makeImage() {
		// 이미지 생성
		// 이미지 만들기=> 서버 디비에 올려서 이미지는 서버에서 불러오는식으로...
		ImageIcon ico1 = new ImageIcon("images/pikachu.jpg"); // 첫번째
		// 들어온애
		Image newImg = ico1.getImage().getScaledInstance(btnList.get(0).getWidth(), btnList.get(0).getWidth(),
				java.awt.Image.SCALE_SMOOTH);
		ico1 = new ImageIcon(newImg);

		ImageIcon ico2 = new ImageIcon("images/rapuras.png"); // 두번째
		// 들어온애
		Image newImag = ico2.getImage().getScaledInstance(btnList.get(0).getWidth(), btnList.get(0).getHeight(),
				java.awt.Image.SCALE_SMOOTH);
		ico2 = new ImageIcon(newImag);
		iconList.add(ico1);
		iconList.add(ico2);
	}

	// 통신메서드
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

	// 통신스레드
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
						textArea.append("시작합니다. \n");
						scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
						cm.setBtnFree(btnList); // 눌려졌던 버튼들 원상복구
						for (int a = 0; a < imList.size(); a++) {
							imList.get(a).setIconNum(-1); // imList 기본값(-1)로 설정
						}
						if (nickList.get(0).equals(nickName)) {
							itsMyTurn = true;
							cm.setBtnFree(btnList);
							int idx = cm.getIndex(nickList, nickList.get(0));
							list.setSelectedIndex(idx);
							lblNewLabel_1.setText("당신의 차례입니다.");
						} else {
							lblNewLabel_1.setText("당신의 차례가 아닙니다.");
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
							lblNewLabel_1.setText("당신의 차례입니다.");
							int num = cm.getIndex(nickList, nickName);
							if (command != -1) { // command=-1 ->제한시간 내에 선택을 못한
													// 경우-> 상대방이 클릭한거 설정할 필요x
								if (nickList.get(0).equals(nickName)) {
									// 내가 아닌 상대방이 클릭한거 설정하는거임...
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
							lblNewLabel_1.setText("당신의 차례가 아닙니다.");
							int idx = cm.getIndex(nickList, nickname);
							list.setSelectedIndex(idx);
						}
						break;
					case "win":
						// 이긴사람이 나온 경우-> 게임 종료
						lblNewLabel_1.setText("게임이 끝났습니다.");
						btnNewButton_9.setEnabled(true); // 시작버튼 다시 활성화
						itsMyTurn = false;
						nickname = (String) obj[1];
						textArea.append(nickname + " 님이 승리하셨습니다.\n");
						int nickIdx = cm.getIndex(nickList, nickname);
						/*
						if (nickname.equals(nickName)) {
							// db에 기록은 한번만하도록!
							db.writeWin(nickname, 0); // 이긴애 기록
							db.writeWin(nickList.get(1 - nickIdx), 1); // 진애 기록
						}*/
						// 각 버튼에 액션리스너 붙이기
						for (int a = 0; a < btnList.size(); a++) {
							//수정
							btnList.get(a).addActionListener(actionL);
							btnList.get(a).setActionCommand(a + ""); // 버튼 0번부터 8번까지 액션커맨드 설정
							imList.add(new imageManager(btnList.get(a), -1)); // imList
																				// 버튼리스트 순서와
																				// 동일하게 붙임
						}
						scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
						JOptionPane.showMessageDialog(null, nickname + " 님이 승리하셨습니다.");
						break;
					case "headOut": // 방장이 접속종료한경우
						JOptionPane.showMessageDialog(null, "방장이 종료하여 종료합니다.");
						dispose();
						break;
					case "out": // 방장 아닌 애가 접속종료한경우
						nickname = (String) obj[1];
						textArea.append(nickname + " 님이 종료하셨습니다.\n");
						scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
						nickList.remove(nickname);
						list.setListData(nickList.toArray());
						break;
					case "draw": // 비긴경우
						
						/*if (nickList.get(0).equals(nickName)) {
							// 한번만 기록 올라가도록
							db.writeWin(nickList.get(0), 2); // 둘 다 플레이 횟수만
																// 올라가도록 기록
							db.writeWin(nickList.get(1), 2); // 둘 다 플레이 횟수만
																// 올라가도록 기록
						}*/
						lblNewLabel_1.setText("게임이 끝났습니다.");
						btnNewButton_9.setEnabled(true); // 시작버튼 다시 활성화
						itsMyTurn = false;
						// 각 버튼에 액션리스너 붙이기
						for (int a = 0; a < btnList.size(); a++) {
							//수정
							btnList.get(a).addActionListener(actionL);
							btnList.get(a).setActionCommand(a + ""); // 버튼 0번부터 8번까지 액션커맨드 설정
							imList.add(new imageManager(btnList.get(a), -1)); // imList
																				// 버튼리스트 순서와
																				// 동일하게 붙임
						}
						textArea.append("우승자가 없습니다.\n");
						scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
						JOptionPane.showMessageDialog(null, "비겼습니다. ");
						break;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					truth = false;
				}
			}
		}

	}

	// 액션리스너
	class actionL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand().equals("시작")) {
				Object[] obj = { "ready", nickName, nickName + " 님이 레디를 눌렀습니다. \n" };
				btnNewButton_9.setEnabled(false);
				try {
					oos.writeObject(obj);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getSource() == textField && !textField.getText().equals("")) {
				String message = nickName + " 님: " + textField.getText() + "\n";
				Object[] obj = { "message", message };
				try {
					oos.writeObject(obj);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textField.setText("");
			} else if (e.getSource() instanceof JButton) {
				// 시작버튼이 아닌 다른 버튼들 눌렀을 시..
				int winCount = 0;
				if (itsMyTurn) {
					lblNewLabel_1.setText("당신의 차례입니다.");
					// 내 차례일때만 버튼이 눌려서 진행될 수 있도록..
					JButton button = (JButton) e.getSource();
					int num = cm.getIndex(nickList, nickName);
					String actionCommand = button.getActionCommand();
					int btnIndex = Integer.parseInt(actionCommand);
					if (nickList.get(0).equals(nickName)) {
						// 방장이면 ico1, 아니면 ico2 설정
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
						//enable가 아닌 해당 버튼의 액션리스너를 삭제
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
						//enable가 아닌 해당 버튼의 액션리스너를 삭제
						button.removeActionListener(actionL);
					}
					// 2명이서 하는겜-> 내 차례에서 내가 하고 상대방에게 넘김
					Object[] obj = { "myTurn", nickList.get(1 - num), actionCommand };
					try {
						oos.writeObject(obj);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// 삼목 완성되는 순간 게임 끝남 ->여기부터
					if (imList.get(0).getIconNum() != -1 && imList.get(0).getIconNum() == imList.get(4).getIconNum()
							&& imList.get(0).getIconNum() == imList.get(8).getIconNum()
							&& imList.get(4).getIconNum() == imList.get(8).getIconNum()) {
						// 오른쪽 대각선 3개의 이미지가 같은 경우
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
						// 왼쪽 대각선 3개의 이미지가 같은 경우
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
							// 가로 3개의 이미지가 같은 경우
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
							// 세로 3개의 이미지가 같은 경우
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
						// win 나온게 없고 모든 버튼이 비활성화된경우->draw(이거 안하면 마지막까지 다 맞추고 찾은
						// 경우 우승+드로우뜸)
						Object[] objDraw = { "draw" };
						try {
							oos.writeObject(objDraw);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					itsMyTurn = false; // 내 차례를 끝냄
				} else {
					if (btnNewButton_9.isEnabled()) {
						JOptionPane.showMessageDialog(null, "아직 게임이 시작되지 않았습니다.");
					} 
					else
					{
						JOptionPane.showMessageDialog(null, "당신의 차례가 아닙니다.");
					}
				}
			}
		}

	}
}
