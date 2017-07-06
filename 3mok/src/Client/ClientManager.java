package Client;

import java.util.*;

import javax.swing.*;

public class ClientManager {

	// 해당 닉이 닉넴리스트 몇번에 저장되어있는지 확인
	public int getIndex(ArrayList<String> nickList, String nickName) {
		for (int a = 0; a < nickList.size(); a++) {
			if (nickList.get(a).equals(nickName)) {
				return a;
			}
		}
		return -1;
	}

	public void setBtnFree(ArrayList<JButton> btnList) {
		// 버튼리스트에 붙어있는 버튼들 모두 true 로 바꿔줌
		for (int a = 0; a < btnList.size(); a++) {
			btnList.get(a).setEnabled(true);
			btnList.get(a).setIcon(null);
		}
	}

	public void setBtnClose(ArrayList<JButton> btnList) {
		for (int a = 0; a < btnList.size(); a++) {
			btnList.get(a).setEnabled(false);
		}
	}

}
