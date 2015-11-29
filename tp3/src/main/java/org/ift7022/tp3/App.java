package org.ift7022.tp3;

import java.net.URISyntaxException;

import org.ift7022.tp3.context.ContextBase;
import org.ift7022.tp3.context.InstantContext;
import org.ift7022.tp3.context.ServiceLocator;
import org.ift7022.tp3.services.TextService;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		ContextBase context = new InstantContext();
		context.apply();
		TextService service = ServiceLocator.getInstance().resolve(TextService.class);
		try {
			System.out.println(service.getStatistic("I'm a big big dog in his big big van.").toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		/*
		 * Scanner reader = new Scanner(System.in); // Reading from System.in
		 * String command = ""; while(command != "exit"){ System.out.println(
		 * "Enter a text: "); command = reader.next();
		 * System.out.println(service.getStatistic(command).toString()); }
		 */
	}
}
