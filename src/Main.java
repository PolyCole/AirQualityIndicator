/**
 * Author: Cole Polyak
 * 22 May 2019
 * Air Quality Index Indicator
 * 
 * A basic program to send me a text message of the air quality every morning. 
 */
import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.*;
public class Main {
	public static void main(String[] args) throws IOException {
		// 33 : Denver
		// 84 : Minneapolis
		Document doc = Jsoup.connect("http://feeds.enviroflash.info/rss/realtime/33.xml").get();
		
		System.out.println(getTitle(doc) + " " + getAQI(doc));
		
	}
	
	private static String getTitle(Document doc) {
		String title = doc.getElementsByTag("title").toString();
		title = title.substring(title.indexOf(">") + 1);
		title = title.substring(0, title.indexOf("-"));
		title = title.trim();
		return title;
	}
	
	private static String getAQI(Document doc) {
		String AQI = doc.getElementsByTag("description").toString();
		int index = AQI.indexOf("AQI");
		AQI = AQI.substring(index - 20, index+3);
		String[] separated = AQI.split(";");
		return separated[separated.length -1];
	}

}
