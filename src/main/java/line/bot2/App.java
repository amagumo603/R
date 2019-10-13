package line.bot2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import repositoryS3.S3Service;

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

		// S3二テキストを登録
		S3Service ama = new S3Service();
		String reText = ama.s3Service(originalMessageText);
		/*
				Random random = new Random();
				if (originalMessageText.equals("ももも")) {
					return new TextMessage("もちもち");
				}
				int randomValue = random.nextInt(2);
				if (randomValue == 0) {
					return new TextMessage("ももも");
				}
		*/
		//return new TextMessage(event.getMessage().getText());
		return new TextMessage(reText);
	}

	@EventMapping
	public void handleDefaultMessageEvent(Event event) {
		System.out.println("event: " + event);
	}
}