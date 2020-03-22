package vietnamese.crawler.notrss;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import vietnamese.crawler.Util;

public class ReadZingNews {
	public static String ZING = "https://news.zing.vn/";

	public ArrayList<String> crawl() {
		ArrayList<String> arrLink = new ArrayList<>();
		String url = ZING;
		Document webPage = null;
		Elements pageText;

		try {
			webPage = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (webPage != null) {
			System.out.println(url);
			pageText = webPage.select("article").select("header").select("p").select("a");
			for (int i = 0; i < pageText.size(); i++) {
				String link = "https://news.zing.vn" + pageText.get(i).attr("href").toString();
				Util.checkUrlExist(link, arrLink);
			}
		} else {
			return null;
		}
		return arrLink;
	}
}
