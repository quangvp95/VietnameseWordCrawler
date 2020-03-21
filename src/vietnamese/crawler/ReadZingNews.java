package vietnamese.crawler;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ReadZingNews {
	public ArrayList<String> doInBackground(String string) {
		ArrayList<String> arrLink = new ArrayList<>();
		String url = string;
		Document webPage = null;
		Elements pageText;

		try {
			webPage = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (webPage != null) {
			pageText = webPage.select("article").select("header").select("p").select("a");
			for (int i = 0; i < pageText.size(); i++) {
				String link = "https://news.zing.vn" + pageText.get(i).attr("href").toString();
				if(checkURL(link, arrLink))
					continue;
				arrLink.add(link);
			}
		} else {
			return null;
		}
		return arrLink;
	}

	private boolean checkURL(String url, ArrayList<String> list) {
		for (String link : list) {
			if (url.equals(link))
				return true;
		}
		return false;
	}
}
