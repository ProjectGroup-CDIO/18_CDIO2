package webserverExample;

import java.io.*;
import java.net.*;
import java.util.*;

public class Handler implements Runnable {
	private Socket handler;

	Handler(Socket handler) {
		this.handler = handler;
	}

	public void run() {
		try {
			PrintWriter ud = new PrintWriter(handler.getOutputStream());
			BufferedReader ind = new BufferedReader(new InputStreamReader(handler.getInputStream()));

			String anmodning = ind.readLine();
			System.out.println("Start " + new Date() + " " + anmodning);
		
			
			// kommer header
			ud.println("HTTP/0.9 200 OK");
			ud.println();
			
			// her kommer html dokumentet som skal vises
			ud.println("<html><head><title>Svar</title><meta charset= \"UTF-8\"></head>");
			ud.println("<body><h1>Svar</h1>");
			ud.println("Tænker over " + anmodning + "<br>");
			for (int i = 0; i < 10; i++) {
				ud.print(".<br>");
				ud.flush();
				Thread.sleep(1000);
			}
			ud.println("Nu har jeg tænkt færdig!</body></html>");
			ud.flush();
			handler.close();
			
			System.out.println("Slut " + new Date() + " " + anmodning);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

// her kan findes insprition til opgradering til HTTP/1.0
// http://www.jmarshall.com/easy/http/#headerlines

// for (int i=0;i<8;i++)
// System.out.println(ind.readLine());

// ud.println("HTTP/1.0 200 OK");
// ud.println("Date: Fri, 31 Dec 1999 23:59:59 GMT");
// ud.println("Content-Type: text/html");
// ud.println("Content-Length: 10000");


