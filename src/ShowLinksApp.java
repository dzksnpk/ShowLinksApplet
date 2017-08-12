import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ShowLinksApp {
	public static void main(String[] args) throws IOException {
		
		System.out.println("Type valid url e.g.\"http://economist.com\":");
		
		Scanner sc = new Scanner(System.in);
		String url = sc.next();
		sc.close();
		
		System.out.printf("Processing %s...\n", url);
		
		Map<String,Integer> linkMap = new HashMap<String,Integer>(); 
		Document doc = Jsoup.connect(url).get();
		Elements links = doc.select("a[href^=http]");
		
		
		
		System.out.printf("\nLinks: (%d)\n", links.size());
		
		for (Element link : links) {
			
			String absURL = link.attr("abs:href");
			URL u = new URL(absURL);
			String hostURL = u.getHost();
			
			if (linkMap.containsKey(hostURL)) {
				linkMap.put(hostURL, linkMap.get(hostURL) + 1);
            		} 
			else {
                		linkMap.put(hostURL, 1);
            		}
			
		}
		for (Entry<String, Integer> e : linkMap.entrySet()) {
            		System.out.println(e.getKey() + " - " + e.getValue());
        	}
	}
}
