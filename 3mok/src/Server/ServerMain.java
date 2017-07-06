package Server;

import java.io.*;
import java.net.*;
import java.util.*;

import Client.ClientMain;

public class ServerMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket ss;
		Socket socket;
		ObjectOutputStream oos;
		ObjectInputStream ois;
		ArrayList<ObjectOutputStream> oosList = new ArrayList();
		ArrayList<String> readyCount = new ArrayList();
		try {
			ss = new ServerSocket(8999);
			while (true) {
				System.out.println("waiting");
				socket = ss.accept();
				System.out.println("connected");
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());
				oosList.add(oos);
				Thread t = new Thread(new ServerThread(oos, ois, oosList, readyCount));
				t.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
