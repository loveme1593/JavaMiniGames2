package Client;

import java.util.*;

import javax.swing.*;

public class ClientManager {

	// �ش� ���� �г۸���Ʈ ����� ����Ǿ��ִ��� Ȯ��
	public int getIndex(ArrayList<String> nickList, String nickName) {
		for (int a = 0; a < nickList.size(); a++) {
			if (nickList.get(a).equals(nickName)) {
				return a;
			}
		}
		return -1;
	}

	public void setBtnFree(ArrayList<JButton> btnList) {
		// ��ư����Ʈ�� �پ��ִ� ��ư�� ��� true �� �ٲ���
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
