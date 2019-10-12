package line.bot2;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@SpringBootApplication
@LineMessageHandler
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		System.out.println("hello");
	}

	@EventMapping
	public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
		System.out.println("eventTEST: " + event);

		final String originalMessageText = event.getMessage().getText();

		//SpreadsheetSearch sss = new SpreadsheetSearch();
		//sss.searchUnit(originalMessageText);

		Random random = new Random();
		if (originalMessageText.equals("ももも")) {
			return new TextMessage("もちもち");
		}
		int randomValue = random.nextInt(2);
		if (randomValue == 0) {
			return new TextMessage("ももも");
		}
		return new TextMessage(event.getMessage().getText());
	}

	@EventMapping
	public void handleDefaultMessageEvent(Event event) {
		System.out.println("event: " + event);
	}
}