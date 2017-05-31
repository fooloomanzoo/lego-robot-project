import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

import lejos.nxt.LCD;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import lejos.util.TextMenu;

import javax.bluetooth.RemoteDevice;

/*
 * hilfreicher Link: http://www.juanantonio.info/docs/2008/LEJOS-BLUETOOTH.pdf
 */

public class Connection {

	BTConnection connection = null;
	RemoteDevice connectedDevice;
	String mode;
	
	public Connection(String mode) {
		this.mode = mode;
	}

	public void connect() {
		LCD.clear();

		// conn = Bluetooth.waitForConnection(0, NXTConnection.PACKET);
		// Bluetooth.connect->
		// list.get().getBluetoothConnection
		// <-- Byte Array 1234 (pairing code)
		byte[] pin = {(byte) '1', (byte) '2', (byte) '3', (byte) '4'};
		
		if (mode.equals("master")) {
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
			int devSel = menu.select();

			connection = Bluetooth.connect(devList.get(devSel).getDeviceAddr(), NXTConnection.PACKET, pin);
			LCD.clear();
			if (connection != null) {
				System.out.println("You are connected to " + names[devSel] + "\n");
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Connection Error to " + names[devSel] + "\n");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.exit(1);
			}
		} else {
			System.out.println("Wait for Connection...\n");
			connection = Bluetooth.waitForConnection(0, NXTConnection.PACKET);
		}

	}

	public void disconnect() {
		if (connection != null) {
			connection.close();
		}
		connection = null;
		System.out.println("You are disconnected...\n");
	}

	public int receive() {
		byte[] msg = new byte[1];
		byte in = 0;
		connection.read(msg, msg.length);
		in = msg[0];
		return in;
	}

	public void send(int out) {
		byte[] msg = new byte[1];
		msg[0] = (byte) out;
		connection.write(msg, msg.length);
	}

//	public String[] split(String str) {
//		// TODO: test string split
//		ArrayList<String> spl = new ArrayList<String>();
//		int start = 0;
//
//		for (int i = 0; i < str.length(); i++) {
//			if (start != i && str.charAt(i) == ' ') {
//				spl.add(str.substring(start, i));
//				start = i + 1;
//			}
//		}
//		if (start < str.length() && str.charAt(str.length() - 1) == ' ') {
//			spl.add(str.substring(start, str.length() - 1));
//		}
//
//		String[] ret = new String[spl.size()];
//		for (int i = 0; i < ret.length; i++) {
//			ret[i] = spl.get(i);
//		}
//		return ret;
//	}

}
