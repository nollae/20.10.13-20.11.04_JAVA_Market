package market;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
	// Field
	Socket socket;
	ObjectInputStream ois;
	ObjectOutputStream oos;

	// Constructor
	public ServerThread(Socket socket) {
		try {
			this.socket = socket;
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method

	public void run() {
		// 2,3 : Ŭ���̾�Ʈ�� ������ ��� �ۼ����ϴ� �۾�
		try {
			boolean flag = true;
			while (flag) {
				MessageVO msgVO = (MessageVO) ois.readObject();
				RoomVO roomVO = new RoomVO();
				if (msgVO.getStatus() == MarketMgmUI.CONNECT) {
					msgVO.setMsg(msgVO.getName() + "���� ���� ~~");
					broadCastring(msgVO);
				} else if (msgVO.getStatus() == MarketMgmUI.TALKING) {
					broadCastring(msgVO);
				} else if (msgVO.getStatus() == MarketMgmUI.EXIT) {
					MultiChatServer.st_list.remove(this);
					msgVO.setMsg(msgVO.getName() + "���� ���� ~~");
					broadCastring(msgVO);
					flag = false;
				}
			}
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/** ��ü �޽��� ���� **/
	public void broadCastring(MessageVO msgVO) {
		try {

			for (ServerThread st : MultiChatServer.st_list) {
				st.oos.writeObject(msgVO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}// class
