package vietnamese.crawler.rss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import vietnamese.crawler.Util;

public class ReadRss {

	public static String[] ARR_RSS = {

			// vnexpress.net
			"https://vnexpress.net/rss/the-gioi.rss",
			"https://vnexpress.net/rss/tin-moi-nhat.rss",
			"https://vnexpress.net/rss/thoi-su.rss",
			"https://vnexpress.net/rss/kinh-doanh.rss",
			"https://vnexpress.net/rss/startup.rss",
			"https://vnexpress.net/rss/giai-tri.rss",
			"https://vnexpress.net/rss/the-thao.rss",
			"https://vnexpress.net/rss/phap-luat.rss",
			"https://vnexpress.net/rss/giao-duc.rss",
			"https://vnexpress.net/rss/suc-khoe.rss",
			"https://vnexpress.net/rss/gia-dinh.rss",
			"https://vnexpress.net/rss/du-lich.rss",
			"https://vnexpress.net/rss/khoa-hoc.rss",
			"https://vnexpress.net/rss/so-hoa.rss",
			"https://vnexpress.net/rss/oto-xe-may.rss",
			"https://vnexpress.net/rss/y-kien.rss",
			"https://vnexpress.net/rss/tam-su.rss",
			"https://vnexpress.net/rss/cuoi.rss",
			// 24h.com
			"https://cdn.24h.com.vn/upload/rss/bongda.rss",
			"https://cdn.24h.com.vn/upload/rss/asiancup2019.rss",
			"https://cdn.24h.com.vn/upload/rss/anninhhinhsu.rss",
			"https://cdn.24h.com.vn/upload/rss/thoitrang.rss",
			"https://cdn.24h.com.vn/upload/rss/thoitrang.rss",
			"https://cdn.24h.com.vn/upload/rss/thoitranghitech.rss",
			"https://cdn.24h.com.vn/upload/rss/taichinhbatdongsan.rss",
			"https://cdn.24h.com.vn/upload/rss/amthuc.rss",
			"https://cdn.24h.com.vn/upload/rss/lamdep.rss",
			"https://cdn.24h.com.vn/upload/rss/phim.rss",
			"https://cdn.24h.com.vn/upload/rss/giaoducduhoc.rss",
			"https://cdn.24h.com.vn/upload/rss/bantrecuocsong.rss",
			"https://cdn.24h.com.vn/upload/rss/canhacmtv.rss",
			"https://cdn.24h.com.vn/upload/rss/thethao.rss",
			"https://cdn.24h.com.vn/upload/rss/phithuongkyquac.rss",
			"https://cdn.24h.com.vn/upload/rss/oto.rss",
			"https://cdn.24h.com.vn/upload/rss/thitruongtieudung.rss",
			"https://cdn.24h.com.vn/upload/rss/dulich24h.rss",
			"https://cdn.24h.com.vn/upload/rss/suckhoedoisong.rss",
			"https://cdn.24h.com.vn/upload/rss/cuoi24h.rss",

			// thanhnien.vn
			"https://thanhnien.vn/rss/home.rss",
			"https://thanhnien.vn/rss/viet-nam.rss",
			"https://thanhnien.vn/rss/viet-nam/phap-luat.rss",
			"https://thanhnien.vn/rss/thoi-su/quoc-phong.rss",
			"https://thanhnien.vn/rss/chinh-tri-xa-hoi/phong-su.rss",
			"https://thanhnien.vn/rss/doi-song/quyen-duoc-biet.rss",
			"https://thanhnien.vn/rss/doi-song/dan-sinh.rss",
			"https://thanhnien.vn/rss/thoi-su/viec-lam.rss",
			"https://thanhnien.vn/rss/viec-lam/can-biet.rss",
			"https://thanhnien.vn/rss/viec-lam/ky-nang-tim-viec.rss",
			"https://thanhnien.vn/rss/viec-lam/nghe-hot.rss",
			"https://thanhnien.vn/rss/viec-lam/san-viec.rss",
			"https://thanhnien.vn/rss/viec-lam/tuyen-dung.rss",
			"https://thanhnien.vn/rss/chinh-tri.rss",

			"https://thanhnien.vn/rss/toi-viet.rss",
			"https://thanhnien.vn/rss/thoi-su/chao-buoi-sang.rss",
			"https://thanhnien.vn/rss/toi-viet/blog-phong-vien.rss",

			"https://thanhnien.vn/rss/the-gioi.rss",
			"https://thanhnien.vn/rss/the-gioi/nguoi-viet-nam-chau.rss",
			"https://thanhnien.vn/rss/the-gioi/goc-nhin.rss",
			"https://thanhnien.vn/rss/the-gioi/ho-so.rss",
			"https://thanhnien.vn/rss/the-gioi/quan-su.rss",
			"https://thanhnien.vn/rss/the-gioi/chuyen-la.rss",
			"https://thanhnien.vn/rss/the-gioi/kinh-te-the-gioi.rss",

			"https://thanhnien.vn/rss/van-hoa-nghe-thuat.rss",
			"https://thanhnien.vn/rss/truyen-hinh.rss",
			"https://thanhnien.vn/rss/van-hoa/phim.rss",
			"https://thanhnien.vn/rss/van-hoa/thanh-pho-toi-yeu.rss",
			"https://thanhnien.vn/rss/van-hoa/cau-chuyen.rss",
			"https://thanhnien.vn/rss/van-hoa/doi-nghe-si.rss",

			"https://thethao.thanhnien.vn/rss/home.rss",

			"https://thanhnien.vn/rss/doi-song/am-thuc.rss",
			"https://thanhnien.vn/rss/doi-song/gia-dinh.rss",
			"https://thanhnien.vn/rss/doi-song/cong-dong.rss",
			"https://thanhnien.vn/rss/doi-song/nguoi-song-quanh-ta.rss",
			"https://thanhnien.vn/rss/doi-song/spa-tham-my.rss",
			"https://thanhnien.vn/rss/doi-song/song-xanh.rss",

			"https://thanhnien.vn/rss/kinh-doanh.rss",
			"https://thanhnien.vn/rss/kinh-te/chung-khoan.rss",
			"https://thanhnien.vn/rss/tai-chinh-kinh-doanh/doanh-nhan.rss",
			"https://thanhnien.vn/rss/kinh-doanh/dia-oc.rss",
			"https://thanhnien.vn/rss/kinh-doanh/tieu-dung.rss",
			"https://thanhnien.vn/rss/kinh-doanh/lam-giau.rss",
			"https://thanhnien.vn/rss/kinh-te/chinh-sach-phat-trien.rss",
			"https://thanhnien.vn/rss/tai-chinh-kinh-doanh/ngan-hang.rss",
			"https://thanhnien.vn/rss/tai-chinh-kinh-doanh/doanh-nghiep.rss",

			"https://thanhnien.vn/rss/the-gioi-tre.rss",
			"https://thanhnien.vn/rss/the-gioi-tre/the-gioi-mang.rss",
			"https://thanhnien.vn/rss/the-gioi-tre/song-yeu-an-choi.rss",
			"https://thanhnien.vn/rss/%C4%90o%C3%A0n%20-%20H%E1%BB%99i.rss",
			"https://thanhnien.vn/rss/the-gioi-tre/ket-noi.rss",

			"https://thanhnien.vn/rss/giao-duc.rss",
			"https://thanhnien.vn/rss/giao-duc/du-hoc.rss",
			"https://thanhnien.vn/rss/giao-duc/chon-nghe.rss",
			"https://thanhnien.vn/rss/giao-duc/nguoi-thay.rss",
			"https://thanhnien.vn/rss/giao-duc/chon-truong.rss",
			"https://thanhnien.vn/rss/giao-duc/on-thi-thpt-quoc-gia-2019.rss",
			"https://thanhnien.vn/rss/giao-duc/hop-thu-tu-van-24-7.rss",
			"https://thanhnien.vn/rss/giao-duc/on-thi-thpt-quoc-gia-2019.rss",

			"https://thanhnien.vn/rss/thong-tin-khuyen-mai.rss",
			"https://thanhnien.vn/rss/thong-tin-khuyen-mai/dien-may.rss",
			"https://thanhnien.vn/rss/thong-tin-khuyen-mai/cong-nghe.rss",
			"https://thanhnien.vn/rss/thong-tin-khuyen-mai/tieu-dung.rss",
			"https://thanhnien.vn/rss/thong-tin-khuyen-mai/khoe-dep.rss",
			"https://thanhnien.vn/rss/thong-tin-khuyen-mai/du-lich.rss",
			"https://thanhnien.vn/rss/thong-tin-khuyen-mai/am-thuc.rss",
			"https://thanhnien.vn/rss/thong-tin-khuyen-mai/noi-that.rss",

			"https://thanhnien.vn/rss/ban-can-biet.rss",
			"https://thanhnien.vn/rss/ban-can-biet/tuyen-dung.rss",
			"https://thanhnien.vn/rss/ban-can-biet/san-pham.rss",
			"https://thanhnien.vn/rss/ban-can-biet/giai-tri.rss",
			"https://thanhnien.vn/rss/ban-can-biet/dich-vu.rss",
			"https://thanhnien.vn/rss/ban-can-biet/giai-thuong.rss",
			"https://thanhnien.vn/rss/ban-can-biet/thong-bao.rss",
			"https://thanhnien.vn/rss/ban-can-biet/mien-bac.rss",
			"https://thanhnien.vn/rss/ban-can-biet/mien-nam.rss",
			"https://thanhnien.vn/rss/ban-can-biet/mien-trung.rss",

			"https://xe.thanhnien.vn/rss/home.rss",

			"https://thanhnien.vn/rss/doi-song/du-lich.rss",
			"https://thanhnien.vn/rss/doi-song/du-lich/kham-pha.rss",
			"https://thanhnien.vn/rss/doi-song/du-lich/a-z.rss",
			"https://thanhnien.vn/rss/doi-song/du-lich/san-tour.rss",

			"https://thanhnien.vn/rss/doi-song/suc-khoe.rss",
			"https://thanhnien.vn/rss/suc-khoe/lam-dep.rss",
			"https://thanhnien.vn/rss/suc-khoe/khoe-dep-moi-ngay.rss",
			"https://thanhnien.vn/rss/doi-song/gioi-tinh.rss",
			"https://thanhnien.vn/rss/suc-khoe/song-vui-khoe.rss",

			"https://thanhnien.vn/rss/cong-nghe-thong-tin.rss",
			"https://thanhnien.vn/rss/cong-nghe-thong-tin/kinh-nghiem.rss",
			"https://thanhnien.vn/rss/cong-nghe-thong-tin/san-pham-moi.rss",
			"https://thanhnien.vn/rss/cong-nghe/xu-huong.rss",
			"https://thanhnien.vn/rss/cong-nghe-thong-tin/y-tuong.rss",

			// Vietnamnet.vn
			"https://vietnamnet.vn/rss/tin-moi-nong.rss",
			"https://vietnamnet.vn/rss/thoi-su-chinh-tri.rss",
			"https://vietnamnet.vn/rss/talkshow.rss",
			"https://vietnamnet.vn/rss/thoi-su.rss",
			"https://vietnamnet.vn/rss/kinh-doanh.rss",
			"https://vietnamnet.vn/rss/giai-tri.rss",
			"https://vietnamnet.vn/rss/the-gioi.rss",
			"https://vietnamnet.vn/rss/giao-duc.rss",
			"https://vietnamnet.vn/rss/doi-song.rss",
			"https://vietnamnet.vn/rss/phap-luat.rss",
			"https://vietnamnet.vn/rss/the-thao.rss",
			"https://vietnamnet.vn/rss/cong-nghe.rss",
			"https://vietnamnet.vn/rss/suc-khoe.rss",
			"https://vietnamnet.vn/rss/bat-dong-san.rss",
			"https://vietnamnet.vn/rss/ban-doc.rss",
			"https://vietnamnet.vn/rss/tuanvietnam.rss",
			"https://vietnamnet.vn/rss/oto-xe-may.rss",
			"https://vietnamnet.vn/rss/goc-nhin-thang.rss",
			"https://vietnamnet.vn/rss/tin-moi-nhat.rss",

			// ictnews.vietnamnet.vn
			"https://ictnews.vietnamnet.vn/rss/thoi-su",
			"https://ictnews.vietnamnet.vn/rss/thoi-su/bien-dao",
			"https://ictnews.vietnamnet.vn/rss/thoi-su/tai-co-cau",
			"https://ictnews.vietnamnet.vn/rss/vien-thong",
			"https://ictnews.vietnamnet.vn/rss/vien-thong/cai-cach-hanh-chinh",
			"https://ictnews.vietnamnet.vn/rss/vien-thong/so-hoa-truyen-hinh",
			"https://ictnews.vietnamnet.vn/rss/internet",
			"https://ictnews.vietnamnet.vn/rss/internet/xa-hoi",
			"https://ictnews.vietnamnet.vn/rss/internet/cai-cach-hanh-chinh",
			"https://ictnews.vietnamnet.vn/rss/chinh-phu-dien-tu",
			"https://ictnews.vietnamnet.vn/rss/cntt",
			"https://ictnews.vietnamnet.vn/rss/cntt/phan-mem",
			"https://ictnews.vietnamnet.vn/rss/cntt/phan-cung",
			"https://ictnews.vietnamnet.vn/rss/kinh-doanh",
			"https://ictnews.vietnamnet.vn/rss/kinh-doanh/ho-so",
			"https://ictnews.vietnamnet.vn/rss/kinh-doanh/thi-truong",
			"https://ictnews.vietnamnet.vn/rss/the-gioi-so",
			"https://ictnews.vietnamnet.vn/rss/the-gioi-so/may-anh-so",
			"https://ictnews.vietnamnet.vn/rss/the-gioi-so/di-dong",
			"https://ictnews.vietnamnet.vn/rss/game",
			"https://ictnews.vietnamnet.vn/rss/khoi-nghiep",
			"https://ictnews.vietnamnet.vn/rss/khoi-nghiep/fintech",
			"https://ictnews.vietnamnet.vn/rss/khoi-nghiep/goc-doanh-nghiep",
			"https://ictnews.vietnamnet.vn/rss/cong-nghe-360",
			"https://ictnews.vietnamnet.vn/rss/cong-nghe-360/o-to-xe-may",
			"https://ictnews.vietnamnet.vn/rss/cong-nghe-360/dien-may",
			"https://ictnews.vietnamnet.vn/rss/video",

			// nhandan.com.vn
			"https://www.nhandan.com.vn/rss/chinhtri.html",
			"https://www.nhandan.com.vn/rss/kinhte.html",
			"https://www.nhandan.com.vn/rss/vanhoa.html",
			"https://www.nhandan.com.vn/rss/bancanbiet.html",
			"https://www.nhandan.com.vn/rss/xahoi.html",
			"https://www.nhandan.com.vn/rss/du-lich.html",
			"https://www.nhandan.com.vn/rss/phapluat.html",
			"https://www.nhandan.com.vn/rss/khoahoc-congnghe.html",
			"https://www.nhandan.com.vn/rss/giaoduc.html",
			"https://www.nhandan.com.vn/rss/y-te.html",
			"https://www.nhandan.com.vn/rss/thegioi.html",
			"https://www.nhandan.com.vn/rss/thethao.html",

			// bongda.com.v
			"http://www.bongda.com.vn/feed.rss",
			"http://www.bongda.com.vn/ngoai-hang-anh.rss",
			"http://www.bongda.com.vn/cup-lien-doan-anh.rss",
			"http://www.bongda.com.vn/cup-fa.rss",
			"http://www.bongda.com.vn/tin-khac-anh.rss",
			"http://www.bongda.com.vn/tin-chuyen-nhuong.rss",
			"http://www.bongda.com.vn/viet-nam.rss",
			"http://www.bongda.com.vn/doi-tuyen-quoc-gia.rss",
			"http://www.bongda.com.vn/v-league.rss",
			"http://www.bongda.com.vn/cup-quoc-gia-vn.rss",
			"http://www.bongda.com.vn/hang-nhat-vn.rss",
			"http://www.bongda.com.vn/giai-tre-vn.rss",
			"http://www.bongda.com.vn/bong-da-nu.rss",
			"http://www.bongda.com.vn/vff.rss",
			"http://www.bongda.com.vn/tin-khac-vn.rss",
			"http://www.bongda.com.vn/thu-vien-anh.rss",
			"http://www.bongda.com.vn/anh-ngoi-sao-san-co.rss",
			"http://www.bongda.com.vn/anh-nhung-tran-dau-hap-dan.rss",
			"http://www.bongda.com.vn/anh-vo-bo-cau-thu.rss",
			"http://www.bongda.com.vn/nguoi-dep.rss",
			"http://www.bongda.com.vn/van-dong-vien.rss",
			"http://www.bongda.com.vn/anh-khac.rss",
			"http://www.bongda.com.vn/champions-league.rss",
			"http://www.bongda.com.vn/tin-champions-league.rss",
			"http://www.bongda.com.vn/bong-da-tbn.rss",
			"http://www.bongda.com.vn/la-liga.rss",
			"http://www.bongda.com.vn/cup-nha-vua.rss",
			"http://www.bongda.com.vn/tin-khac-tbn.rss",
			"http://www.bongda.com.vn/bong-da-y.rss",
			"http://www.bongda.com.vn/serie-a.rss",
			"http://www.bongda.com.vn/cup-quoc-gia-y.rss",
			"http://www.bongda.com.vn/tin-khac-italia.rss",
			"http://www.bongda.com.vn/bong-da-duc.rss",
			"http://www.bongda.com.vn/bundesliga.rss",
			"http://www.bongda.com.vn/cup-quoc-gia-duc.rss",
			"http://www.bongda.com.vn/tin-khac-duc.rss",
			"http://www.bongda.com.vn/bong-da-phap.rss",
			"http://www.bongda.com.vn/ligue-1.rss",
			"http://www.bongda.com.vn/cup-lien-doan-phap.rss",
			"http://www.bongda.com.vn/tin-khac-phap.rss",
			"http://www.bongda.com.vn/hau-truong-san-co.rss",
			"http://www.bongda.com.vn/video.rss",
			"http://www.bongda.com.vn/tong-hop-ngoai-hang-anh-2018-19.rss",
			"http://www.bongda.com.vn/ban-tin-bongda.rss",
			"http://www.bongda.com.vn/video-tong-hop-tran-dau.rss",
			"http://www.bongda.com.vn/top-10-top-5.rss",
			"http://www.bongda.com.vn/vao-ngay-nay.rss",
			"http://www.bongda.com.vn/sao-song-sao.rss",
			"http://www.bongda.com.vn/thu-tai-bongda.rss",
			"http://www.bongda.com.vn/ban-co-biet.rss",
			"http://www.bongda.com.vn/europa-league.rss",
			"http://www.bongda.com.vn/bong-da-chau-a.rss",
			"http://www.bongda.com.vn/aff-cup.rss",
			"http://www.bongda.com.vn/tin-chau-a.rss",
			"http://www.bongda.com.vn/afc-cup.rss",
			"http://www.bongda.com.vn/afc-champions-league.rss",
			"http://www.bongda.com.vn/sea-games.rss",
			"http://www.bongda.com.vn/bong-da-nam.rss",
			"http://www.bongda.com.vn/bong-da-nu-sea-game.rss",
			"http://www.bongda.com.vn/fustal.rss",
			"http://www.bongda.com.vn/clip-tu-kuala-lumpur.rss",
			"http://www.bongda.com.vn/thu-tu-kuala-lumpur.rss",
			"http://www.bongda.com.vn/euro-2020.rss",
			"http://www.bongda.com.vn/do-vui.rss",
			"http://www.bongda.com.vn/do-vui-bong-da-viet-nam.rss",
			"http://www.bongda.com.vn/do-vui-ngoai-hang-anh.rss",
			"http://www.bongda.com.vn/do-vui-champions-league.rss",
			"http://www.bongda.com.vn/goc-ban-doc.rss",
			"http://www.bongda.com.vn/asian-cup.rss",
			"http://www.bongda.com.vn/giao-huu.rss",
			"http://www.bongda.com.vn/bong-da-chau-au.rss",
			"http://www.bongda.com.vn/tin-uefa.rss",
			"http://www.bongda.com.vn/bong-da-chau-my.rss",
			"http://www.bongda.com.vn/tin-chau-my.rss",
			"http://www.bongda.com.vn/copa-america.rss",
			"http://www.bongda.com.vn/gold-cup.rss",
			"http://www.bongda.com.vn/copa-libertadores.rss",
			"http://www.bongda.com.vn/bong-da-chau-phi.rss",
			"http://www.bongda.com.vn/tin-chau-phi.rss",
			"http://www.bongda.com.vn/can-cup.rss",
			"http://www.bongda.com.vn/goc-chuyen-gia.rss",
			"http://www.bongda.com.vn/infographic.rss",

			// Tinhte.vn
			"https://tinhte.vn/rss/",
			// Techrum.vn
			"https://www.techrum.vn/forums/-/index.rss",
			// Vnreview.vn
			"https://vnreview.vn/feed/-/rss/home",
			// Gamek.vn
			"https://gamek.vn/home.rss" };

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

	public ArrayList<String> crawl() {
		ArrayList<String> arrayList = new ArrayList<String>();
		for (String url : ARR_RSS) {
			Util.checkUrlExist(onPostExecute(url), arrayList);
		}
		return arrayList;
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
				System.out.println("ReadRss onPostExecute link empty: " + link);
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
