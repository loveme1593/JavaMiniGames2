package Server;

import java.io.*;
import java.util.*;

public class ServerThread implements Runnable {

	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private ArrayList<ObjectOutputStream> oosList;
	private ArrayList<String> readyCount;

	public ServerThread(ObjectOutputStream oos, ObjectInputStream ois, ArrayList<ObjectOutputStream> oosList,
			ArrayList<String> readyCount) {
		this.oos = oos;
		this.ois = ois;
		this.oosList = oosList;
		this.readyCount = readyCount;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean truth = true;
		while (truth) {
			try {
				Object[] obj = (Object[]) ois.readObject();
				String protocol = (String) obj[0];
				switch (protocol) {
				case "enter":
					// 방장 기준으로 접속자 리스트 받음(첫번째 들어왔으니)
					if (this.oos == oosList.get(0)) {
						obj[0] = "head";
					}
					break;
				case "ready":
					readyCount.add("ready");
					int readySize = readyCount.size();
					if (readySize % 2 == 0) {
						// 2명이서 하는 거니까 readySize가 2의배수라면 무조건 시작
						obj[0] = "start";
					}
					break;
				case "out":
					oosList.remove(oos);
					break;
				}
				for (ObjectOutputStream oos : oosList) {
					oos.writeObject(obj);
				}
			} catch (Exception e) {
				// oosList.remove(oos);
				truth = false;
			}
		}
	}

}
