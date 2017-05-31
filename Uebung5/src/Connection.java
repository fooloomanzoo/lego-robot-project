import java.util.ArrayList;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTCommConnector;
import lejos.nxt.comm.NXTConnection;
import lejos.util.TextMenu;

import javax.bluetooth.RemoteDevice;

/*
 * hilfreicher Link: http://www.juanantonio.info/docs/2008/LEJOS-BLUETOOTH.pdf
 */

public class Connection {

	BTConnection conn = null;
	Game game;

	public Connection(Game game) {
		this.game = game;
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("deprecation")
	public void connect() {
		LCD.clear();
		
		// conn = Bluetooth.waitForConnection(0, NXTConnection.PACKET);
		// Bluetooth.connect->
		// list.get().getBluetoothConnection
		// <-- Byte Array 1234 (pairing code)

		System.out.println("Searching...\n");
		ArrayList<RemoteDevice> devList = new ArrayList<RemoteDevice>();
		String[] names = null;
		
		while (devList.size() == 0) {
			devList = Bluetooth.inquire(5, 5, 0);
			System.out.println("No device found. Retrying...\n");
		}
		if (devList.size() > 0) {
			LCD.clear();
			names = new String[devList.size()];
			for (int i = 0; i < devList.size(); i++) {
				RemoteDevice btrd = ((RemoteDevice) devList.get(i));
				names[i] = btrd.getFriendlyName(false);
			}
		}
		// Once you discover others Bluetooth devices, it is n
		for (int i = 0; i < devList.size(); i++) {
			RemoteDevice btrd = ((RemoteDevice) devList.get(i));
			Bluetooth.addDevice(btrd);
		}
		TextMenu menu = new TextMenu(names, 1, "Connect to ");
		int modeSel = menu.select();
		
		while(true) {
			
		}

		// while (!Button.ESCAPE.isPressed()) {
		//
		// try {
		// System.out.println(conn.getAddress());
		// byte[] b = new byte[100];
		// int l = conn.read(b, b.length);
		// String cmd = new String(b, 0, l);
		//
		// System.out.println(cmd);
		//
		// String[] commands = split(cmd);
		//
		// if(commands.length == 1) {
		// // handle as setField operation
		// int pos;
		// try {
		// pos = Integer.parseInt(commands[0]);
		// game.setField(pos);
		// if (pos == 0) {
		// disconnect();
		// break;
		// }
		// } catch (Exception e) {
		// handleCmds(commands);
		// }
		//
		// } else {
		// handleCmds(commands);
		// }
		//
		//
		// } catch (Exception e) {
		// System.out.println(e.getMessage());
		// }
		// }
	}

	private void disconnect() {
		this.conn.close();
		this.conn = null;
	}

	private void handleCmds(String[] commands) {

	}

	public void send(byte[] msg) {
		try {
			if (conn != null) {
				conn.write(msg, msg.length);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public String[] split(String str) {
		// TODO: test string split
		ArrayList<String> spl = new ArrayList<String>();
		int start = 0;

		for (int i = 0; i < str.length(); i++) {
			if (start != i && str.charAt(i) == ' ') {
				spl.add(str.substring(start, i));
				start = i + 1;
			}
		}
		if (start < str.length() && str.charAt(str.length() - 1) == ' ') {
			spl.add(str.substring(start, str.length() - 1));
		}

		String[] ret = new String[spl.size()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = spl.get(i);
		}
		return ret;
	}

}
