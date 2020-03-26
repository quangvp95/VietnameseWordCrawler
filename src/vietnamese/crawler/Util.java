package vietnamese.crawler;

import java.util.ArrayList;

public class Util {
	public static void checkUrlExist(ArrayList<String> list,
			ArrayList<String> target) {
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
			if (!isLetter(s.charAt(i))) {
				start = i + 1;
			} else {
				break;
			}
		}
		for (int i = s.length() - 1; i >= 0; i--) {
			if ((!isLetter(s.charAt(i)))) {
				end = i;
			} else {
				break;
			}
		}
		if (start >= end)
			return null;
		
		s = s.substring(start, end);
		
		boolean isDigit = true;
		for (int i = s.length() - 1; i >= 0; i--) {
			if ((!isDigit(s.charAt(i)))) {
				isDigit = false;
				break;
			}
		}
		if (isDigit)
			return null;

		return s;
	}

	private static final String NOT_CHAR = ",\".(){}[]!@#$%^&*()~`=-+_\\|,./<>?'”;:";
	static boolean isLetter(char c) {
		return !NOT_CHAR.contains(c + "");
	}

	private static final String ONLY_DIGIT = "1234567890/*-+.:";
	static boolean isDigit(char c) {
		return ONLY_DIGIT.contains(c + "");
	}
}
