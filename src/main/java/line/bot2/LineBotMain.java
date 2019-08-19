package line.bot2;
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
public class LineBotMain {
    public static void main(String[] args) {

        SpringApplication.run(LineBotMain.class, args);
        System.out.println("hello");
    }

    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        System.out.println("event: " + event);
        final String originalMessageText = event.getMessage().getText();

    		String reWord = "";

        	/*
        	Service service = new Service();
        	try {
        		List<String> list =service.service();
        		String reWord = "";
        		for(String word:list) {
        			reWord += word;
        		}



    	Random random = new Random();
    	if(originalMessageText.equals("ももも")){
    		return new TextMessage("もちもち");
        }
        int randomValue = random.nextInt(2);
        if(randomValue == 0){
        	return new TextMessage("ももも");
        }

        return new TextMessage(event.getMessage().getText());
        */

    		return new TextMessage(reWord);
    		/*
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return new TextMessage("エラー");

		}
		*/
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}