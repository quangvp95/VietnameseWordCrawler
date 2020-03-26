package vietnamese.crawler.notrss;

import java.util.ArrayList;

import vietnamese.crawler.Util;

public class ReadNotRss {
	public ArrayList<String> crawl() {
		ArrayList<String> arrayList = new ArrayList<String>();

		ReadZingNews readZing = new ReadZingNews();
		Util.checkUrlExist(readZing.crawl(), arrayList);

//		ReadNotValidateRss readNotValidateRss = new ReadNotValidateRss();
//		Util.checkUrlExist(readNotValidateRss.crawl(), arrayList);

		return arrayList;
	}

}
