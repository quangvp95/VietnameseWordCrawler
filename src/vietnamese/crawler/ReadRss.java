package vietnamese.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ReadRss {

	protected String requestServer(String strings) {
		StringBuilder content = new StringBuilder();
		try {
			URL url = new URL(strings);
			InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line);
			}
			bufferedReader.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content.toString();
	}

	public ArrayList<String> onPostExecute(String urlRss) {
		ArrayList<String> arrayList = new ArrayList<String>();
		System.out.println(urlRss);
		String rss = requestServer(urlRss);
		if (rss.indexOf('<') != -1)
			rss = rss.substring(rss.indexOf('<'), rss.length());
		XMLDOMParser parser = new XMLDOMParser();
		Document document = parser.getDocument(rss);
		if (document == null)
			return arrayList;
		NodeList nodeList = document.getElementsByTagName("item");
		String link = "";
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			link = parser.getValue(element, "link");
			if (link.equals("") || link == null) {
				System.out.println("ReadRss onPostExecute: " + link);
				continue;
			}
			if(checkURL(link,arrayList))
				continue;
			arrayList.add(link);
		}
		return arrayList;
	}
	private boolean checkURL(String url, ArrayList<String> list) {
		for(String link: list) {
			if(url.equals(link))
				return true;
		}
		return false;
	}

}
