package webserverExample;

import java.net.*;

public class MultiThreadedWebserver {
	public static void main(String[] arg) {
		ServerSocket serversocket;
		int port = 8001;
		try {
			serversocket = new ServerSocket(port);
			System.out.println("Webserver er startet op og lytte p� port " + port);
			while (true) {
				Socket connection = serversocket.accept();
				System.out.println("asd");
				Handler a = new Handler(connection);
				new Thread(a).start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

}