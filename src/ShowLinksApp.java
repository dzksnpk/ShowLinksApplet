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
		
		// starting prompt 
		System.out.println("Type valid url e.g.\"http://economist.com\":");
		
		// taking user input 
		Scanner sc = new Scanner(System.in);
		String url = sc.next();
		sc.close();
		System.out.printf("Processing %s...\n", url);
		
		// parsing html document 
		Document doc = Jsoup.connect(url).get();
		
		// regex for reading only URLs
		Elements links = doc.select("a[href^=http]");

		System.out.printf("\nLinks: (%d)\n", links.size());
		
		// creating map for hostURLs with incrementing numbers of them
		Map<String, Integer> linkMap = new HashMap<String, Integer>();
		
		// iterating trough URLs element list 
		for (Element link : links) {
			
			// standardizing to absolute URLs only 
			String absURL = link.attr("abs:href");
			URL u = new URL(absURL);
			
			// taking only host element of URL
			String hostURL = u.getHost();
			
			// loop for either putting new URL on list or incrementing already existing host one
			if (linkMap.containsKey(hostURL)) {
				linkMap.put(hostURL, linkMap.get(hostURL) + 1);
			} else {
				linkMap.put(hostURL, 1);
			}

		}
		// loop for displaying logic above 
		for (Entry<String, Integer> e : linkMap.entrySet()) {
			System.out.println(e.getKey() + " - " + e.getValue());
		}
	}
}
