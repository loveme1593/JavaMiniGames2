package DB;

import java.sql.*;

public class writeDB {
	// �÷��� Ƚ��, ��, �� ���

	public void writeWin(String nickName, int winOrNot) {
		// 0=�¸�, 1=��, 2= ���º�-> ���º��϶� �÷���Ƚ�����ö󰡵���
		Connection con = null;
		con = ConnectionManager.getConnection();
		String sql = "";
		if (winOrNot == 0) {
			sql = "update record3mok set play=play+1, win=win+1 where id=?";
		} else if (winOrNot == 1) {
			sql = "update record3mok set play=play+1, lose=lose+1 where id=?";
		} else if (winOrNot == 2) {
			sql = "update record3mok set play=play+1 where id=?";
		}
		PreparedStatement pps;
		try {
			pps = con.prepareStatement(sql);
			pps.setString(1, nickName);
			pps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
