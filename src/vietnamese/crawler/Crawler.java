package vietnamese.crawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import vietnamese.crawler.notrss.ReadNotRss;
import vietnamese.crawler.rss.ReadRss;

public class Crawler {

	private static final String VIETNAMESE_WORD = "word.txt";
	private static final String NON_VIETNAMESE_WORD = "notVietnamese.txt";
	private static final String NON_VIETNAMESE_STATICS_WORD = "notVietnameseStatics.txt";
	private static final String URL_CRAWLED = "URL.txt";

	// Trainghiemso.vn: lấy RSS bị lỗi xác thực=> cần xử lý riêng
	// "https://trainghiemso.vn/feed/",

	public static Pattern pattern = Pattern.compile("[A-Za-z]");

	private String[] mVietnameseList;
	private int[] mWordHashcodeList;
	private HashMap<String, Integer> mMap;
	private String[] mUrlCrawledList;

	public Crawler() {
		initNotVietnameseMap();
		initVietnameseDictionary();
		initURL();
	}

	private void initVietnameseDictionary() {
		ArrayList<String> vietnameseList = new ArrayList<String>();
		File f = new File(VIETNAMESE_WORD);
		BufferedReader br;
		String st = "";
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(f), "UTF8"));
			while ((st = br.readLine()) != null) {
				vietnameseList.add(st);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mVietnameseList = new String[vietnameseList.size()];
		mWordHashcodeList = new int[vietnameseList.size()];
		for (int i = 0; i < mVietnameseList.length; i++) {
			mWordHashcodeList[i] = vietnameseList.get(i).hashCode();
		}
		Arrays.sort(mWordHashcodeList);
		vietnameseList.toArray(mVietnameseList);
		System.out.println("initVietnameseDictionary END: "
				+ vietnameseList.size() + " - " + mVietnameseList.length);
	}

	private void initURL() {
		ArrayList<String> mUrlCrawledList = new ArrayList<String>();
		this.mUrlCrawledList = new String[0];
		File f = new File(URL_CRAWLED);
		if (!f.exists())
			return;
		BufferedReader br = null;
		String st = "";
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(f), "UTF8"));
			while ((st = br.readLine()) != null) {
				mUrlCrawledList.add(st);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		Collections.sort(mUrlCrawledList);

		this.mUrlCrawledList = (String[]) mUrlCrawledList
				.toArray(new String[0]);
	}

	private void initNotVietnameseMap() {
		mMap = new HashMap<String, Integer>();
		File f = new File("");
		if (!f.exists())
			return;
		BufferedReader br = null;
		String st = "";
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(f), "UTF8"));
			while ((st = br.readLine()) != null) {
				if (!st.contains(":")) {
					System.out.println("initNotVietnameseMap load ERR: " + st);
					continue;
				}
				try {
					int i = st.lastIndexOf(":");
					String key = st.substring(0, i);
					int value = Integer.parseInt(st.substring(i + 1));
					mMap.put(key, value);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("initNotVietnameseMap load ERR: " + st);
					continue;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	private void crawl() {
		ArrayList<String> link = new ArrayList<String>();

		// checkUrlCrawled(new ReadRss().crawl(), link);
		checkUrlCrawled(new ReadNotRss().crawl(), link);

		Collections.sort(link);
		System.out.println("crawl readRss: " + link.size());

		for (int i = 0; i < link.size(); i++) {
			String url = link.get(i);
			try {
				System.out.println("crawl executing " + i + "/" + link.size()
						+ ": " + url);
				Document document = Jsoup.connect(url).get();

				Elements elements;
				elements = document.getElementsByTag("header");
				for (int ji = 0; ji < elements.size(); ji++) {
					elements.get(ji).remove();
				}
				elements = document.getElementsByTag("footer");
				for (int ji = 0; ji < elements.size(); ji++) {
					elements.get(ji).remove();
				}

				elements = document.getElementsByTag("p");
				StringBuilder builder = new StringBuilder();
				for (int ji = 0; ji < elements.size(); ji++) {
					builder.append(getString(elements.get(ji))).append(" ");
				}
				String[] text = builder.toString().
				// replaceAll(Pattern.quote("\\t"), " ").
				// replaceAll(Pattern.quote("\\n"), " ").
						replaceAll("\\s+", " ").trim().toLowerCase().split(" ");
				for (String string : text) {
					String s = Util.check(string);
					if (s == null) {
//						System.out.println("!containLetter: " + string);
						continue;
					}
					if (s.contains("25/3")) {
						Util.check(string);
					}
					if (s.length() != string.length() && !isVietnameseWord(s)) {
						System.out.println("!containLetter: " + s + " \t\t\t\t " + string);
						Util.check(string);
					}
					if (!isVietnameseWord(s)) {
						if (mMap.containsKey(s)) {
							mMap.put(s, mMap.get(s) + 1);
						} else {
							mMap.put(s, 1);
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<String> crawledLink = new ArrayList<String>(
				Arrays.asList(mUrlCrawledList));
		crawledLink.addAll(link);
//		saveURL(crawledLink);

		System.out.println("crawl END: " + mMap.size());
		List<Map.Entry<String, Integer>> list = new LinkedList<>(
				mMap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1,
					Map.Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		saveNotVietnamese(list);
	}

	private String getString(Element element) {
		StringBuilder builder = new StringBuilder(element.ownText())
				.append(" ");
		for (int i = 0; i < element.childrenSize(); i++) {
			builder.append(getString(element.child(i))).append(" ");
		}
		return builder.toString();
	}

	private void checkUrlCrawled(ArrayList<String> list,
			ArrayList<String> target) {
		ArrayList<String> newList = new ArrayList<String>();
		for (String url : list) {
			if (!isUrlCrawled(url))
				newList.add(url);
		}
		Util.checkUrlExist(newList, target);
	}

	private boolean isVietnameseWord(String string) {
		return Arrays.binarySearch(mWordHashcodeList, string.hashCode()) >= 0;
	}

	private boolean isUrlCrawled(String url) {
		return Arrays.binarySearch(mUrlCrawledList, url) >= 0;
	}

	private static void saveURL(List<String> listURL) {
		File f = new File(URL_CRAWLED);
		try {
			FileWriter fw = new FileWriter(f.getAbsoluteFile(), false);
			BufferedWriter bw = new BufferedWriter(fw);
			for (String data : listURL) {
				bw.write(data);
				bw.newLine();
			}
			bw.close();
			fw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void saveNotVietnamese(List<Map.Entry<String, Integer>> mMap) {
		File f = new File(NON_VIETNAMESE_STATICS_WORD);
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			FileWriter fw = new FileWriter(f.getAbsoluteFile(), false);
			BufferedWriter bw = new BufferedWriter(fw);
			for (Map.Entry<String, Integer> map : mMap) {
				String data = map.getKey() + ":" + map.getValue();
				bw.write(data);
				bw.newLine();
			}
			bw.close();
			fw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		f = new File(NON_VIETNAMESE_WORD);
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			FileWriter fw = new FileWriter(f.getAbsoluteFile(), false);
			BufferedWriter bw = new BufferedWriter(fw);
			for (Map.Entry<String, Integer> map : mMap) {
				String data = map.getKey();
				bw.write(data);
				bw.newLine();
			}
			bw.close();
			fw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Crawler().crawl();
	}

}
