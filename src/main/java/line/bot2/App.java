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

import repositoryS3.s3Tool;

@SpringBootApplication
@LineMessageHandler
public class App {

	public static void main(String[] args) {
		//		s3Tool ama = new s3Tool();
		//		ama.s3Service("Messer");
		SpringApplication.run(App.class, args);
		System.out.println("hello");
	}

	@EventMapping
	public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
		System.out.println("eventTEST: " + event);

		final String originalMessageText = event.getMessage().getText();

		//SpreadsheetSearch sss = new SpreadsheetSearch();
		//sss.searchUnit(originalMessageText);
		s3Tool ama = new s3Tool();
		ama.s3Service(originalMessageText);

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