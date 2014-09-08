package com.kill3rtaco.api.commands;

import java.util.ArrayList;

public class StringUtils {

	public static String join(String[] array) {
		return join(array, " ");
	}

	public static String join(String[] array, String delimiter) {
		String result = "";
		for (int i = 0; i < array.length; i++) {
			result += array[i];
			if (i < array.length - 1)
				result += delimiter;
		}
		return result;
	}

	public static String join(ArrayList<String> list) {
		return join(list, " ");
	}

	public static String join(ArrayList<String> list, String delimiter) {
		String result = "";
		for (int i = 0; i < list.size(); i++) {
			result += list.get(i);
			if (i < list.size() - 1)
				result += delimiter;
		}
		return result;
	}

	/**
	 * Removes the specified indices from a String array (everything before
	 * {@code startIndex} is kept). Code is from DeityAPI
	 * 
	 * @param array
	 *            - Array in which the specified indices before
	 *            {@code startIndex} is removed
	 * @param startIndex
	 *            - Any index before this is removed
	 * @return The same array given with the specified indices removed
	 */
	public static String[] removeArgs(String[] array, int startIndex) {
		if (array.length == 0)
			return array;
		if (array.length < startIndex)
			return new String[0];

		String[] newSplit = new String[array.length - startIndex];
		System.arraycopy(array, startIndex, newSplit, 0, array.length - startIndex);
		return newSplit;
	}

	/**
	 * Removes the first index in a String array. Code is from DeityAPI
	 * 
	 * @param array
	 *            - The array in which the first index is removed
	 * @return The same array given with the first index removed
	 */
	public static String[] removeFirstArg(String[] array) {
		return removeArgs(array, 1);
	}

	/**
	 * Converts a String to ProperCase.
	 * 
	 * @param s
	 *            the String to be converted
	 * @return the converted String
	 */
	public static String toProperCase(String s) {
		if (s.isEmpty())
			return "";
		String[] unimportant = new String[] { "a", "an", "and", "but", "is",
				"are", "for", "nor", "of", "or", "so", "the", "to", "yet" };
		String[] split = s.split("\\s+");
		String result = "";
		for (int i = 0; i < split.length; i++) {
			String word = split[i];
			boolean capitalize = true;
			for (String str : unimportant) {
				if (str.equalsIgnoreCase(word)) {
					if (i > 0 && i < split.length - 1) { //middle unimportant word
						capitalize = false;
						break;
					}
				}
			}
			if (capitalize)
				result += capitalize(word) + " ";
			else
				result += word.toLowerCase() + " ";
		}
		return result.trim();
	}

	public static String capitalize(String s) {
		if (s.isEmpty())
			return "";
		if (s.length() == 1) {
			return s.toUpperCase();
		} else if (s.length() == 2) {
			String first = (s.charAt(0) + "").toUpperCase();
			String sec = (s.charAt(1) + "").toLowerCase();
			return first + sec;
		} else {
			s = s.toUpperCase();
			return s.charAt(0) + s.substring(1, s.length()).toLowerCase();
		}
	}

}
