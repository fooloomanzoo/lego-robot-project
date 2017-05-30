import java.util.ArrayList;

import lejos.nxt.Button;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;

public class Connection {

	BTConnection conn = null;
	Game game;

	public Connection(Game game) {
		this.game = game;
		// TODO Auto-generated constructor stub
	}

	public void connect() {
		conn = Bluetooth.waitForConnection(0, NXTConnection.PACKET);

		
		while (!Button.ESCAPE.isPressed()) {

			try {
				byte[] b = new byte[100];
				int l = conn.read(b, b.length);
				String cmd = new String(b, 0, 1);

				System.out.println(cmd);
				
				String[] commands = split(cmd);

				if(commands.length == 1) {
					// handle as setField operation
					int pos;
					try {
						pos = Integer.parseInt(commands[0]);
						game.setField(pos);
						if (pos == 0) {
							disconnect();
							break;
						}
					} catch (Exception e) {
						handleCmds(commands);
					}
					
				} else {
					handleCmds(commands);
				}
				

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
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
		String tmp = "";
		int start = 0;
		
		for (int i = 0; i < str.length(); i++) {
			if(start != i && str.charAt(i) == ' ') {
				spl.add(str.substring(start, i));
				start = i + 1;
			}
		}
		if(start < str.length() && str.charAt(str.length()-1) == ' ') {
			spl.add(str.substring(start, str.length()-1));
		}
		
		String[] ret = new String[spl.size()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = spl.get(i);
		}
		return ret;
	}

}
