package vietnamese.crawler;

import java.util.ArrayList;

public class Util {
	public static void checkUrlExist(ArrayList<String> list, ArrayList<String> target) {
		for (String string : list) {
			checkUrlExist(string, target);
		}
	}

	public static void checkUrlExist(String string, ArrayList<String> target) {
		for (String s : target)
			if (string.equals(s)) {
				return;
			}
		target.add(string);
	}

	public static String check(String s) {
		int start = 0, end = s.length();
		for (int i = 0; i < s.length(); i++) {
			if ((Character.isLetter(s.charAt(i)))) {
				start = i;
				break;
			}
		}
		for (int i = s.length() - 1; i >= 0; i--) {
			if ((Character.isLetter(s.charAt(i)))) {
				end = i + 1;
				break;
			}
		}
		if (start >= end)
			return null;
		return s.substring(start, end);
	}

}
