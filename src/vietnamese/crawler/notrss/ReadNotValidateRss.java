package vietnamese.crawler.notrss;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import vietnamese.crawler.Util;

public class ReadNotValidateRss {
	// query RSS bị lỗi cấu trúc rss not validate => Xử lý riêng
	public static String[] ARR_RSS = {
			
			"https://nld.com.vn/tin-moi-nhat.rss",
			
			"https://www.doisongphapluat.com/trang-chu.rss",
			"https://www.doisongphapluat.com/rss/tin-tuc.rss", "https://www.doisongphapluat.com/rss/tin-the-gioi.rss",
			"https://www.doisongphapluat.com/rss/tin-tuc/tin-trong-nuoc.rss",
			"https://www.doisongphapluat.com/rss/phap-luat.rss",
			"https://www.doisongphapluat.com/rss/phap-luat/an-ninh-hinh-su.rss",
			"https://www.doisongphapluat.com/rss/phap-luat/tinh-huong-phap-luat.rss",
			"https://www.doisongphapluat.com/rss/kinh-doanh.rss",
			"https://www.doisongphapluat.com/rss/kinh-doanh/thi-truong.rss",
			"https://www.doisongphapluat.com/rss/kinh-doanh/bi-quyet-lam-giau.rss",
			"https://www.doisongphapluat.com/rss/doi-song.rss",
			"https://www.doisongphapluat.com/rss/doi-song/gia-dinh-tinh-yeu.rss",
			"https://www.doisongphapluat.com/rss/doi-song/suc-khoe-lam-dep.rss",
			"https://www.doisongphapluat.com/rss/cong-dong-mang.rss",
			"https://www.doisongphapluat.com/rss/giai-tri.rss",
			"https://www.doisongphapluat.com/rss/giai-tri/chuyen-lang-sao.rss",
			"https://www.doisongphapluat.com/rss/giai-tri/tin-tuc-giai-tri.rss",
			"https://www.doisongphapluat.com/rss/the-thao.rss", "https://www.doisongphapluat.com/rss/cong-nghe.rss",
			"https://www.doisongphapluat.com/rss/oto-xemay.rss", "https://www.doisongphapluat.com/rss/can-biet.rss",

			"https://soha.vn/giai-tri.rss", "https://soha.vn/the-thao.rss", "https://soha.vn/thoi-su.rss",
			"https://soha.vn/kinh-doanh.rss", "https://soha.vn/quoc-te.rss", "https://soha.vn/song-khoe.rss",
			"https://soha.vn/quan-su.rss", "https://soha.vn/cu-dan-mang.rss", "https://soha.vn/kham-pha.rss",
			"https://soha.vn/infographic.rss", "https://soha.vn/doi-song.rss", "https://soha.vn/apec-viet-nam-2017.rss",
			"https://soha.vn/cong-nghe.rss", "https://soha.vn/hanh-trinh-tu-trai-tim.rss", "https://soha.vn/events.rss",

			"https://tuoitre.vn/rss/tin-moi-nhat.rss", "https://tuoitre.vn/rss/the-gioi.rss",
			"https://tuoitre.vn/rss/kinh-doanh.rss", "https://tuoitre.vn/rss/xe.rss",
			"https://tuoitre.vn/rss/van-hoa.rss", "https://tuoitre.vn/rss/the-thao.rss",
			"https://tuoitre.vn/rss/khoa-hoc.rss", "https://tuoitre.vn/rss/gia-that.rss",
			"https://tuoitre.vn/rss/thoi-su.rss", "https://tuoitre.vn/rss/phap-luat.rss",
			"https://tuoitre.vn/rss/nhip-song-so.rss", "https://tuoitre.vn/rss/ban-doc-lam-bao.rss",
			"https://tuoitre.vn/rss/giai-tri.rss", "https://tuoitre.vn/rss/suc-khoe.rss",
			"https://tuoitre.vn/rss/giao-duc.rss", "https://tuoitre.vn/rss/thu-gian.rss",
			"https://tuoitre.vn/rss/du-lich.rss"

	};

	public ArrayList<String> crawl() {
		ArrayList<String> arrayList = new ArrayList<>();
		for (String url : ARR_RSS) {
			crawl(url, arrayList);
		}
		return arrayList;
	}

	public void crawl(String url, ArrayList<String> arrLink) {
		Document webPage = null;

		try {
			webPage = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (webPage != null) {
			System.out.println(url);
			Elements items = webPage.select("item");
			for (Element element : items) {
				Util.checkUrlExist(element.getElementsByTag("link").text(), arrLink);
			}
		} else {
			System.out.println("ReadSohaNews webPage == null: " + url);
		}
	}

}
