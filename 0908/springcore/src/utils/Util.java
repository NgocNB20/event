package utils;

import java.time.LocalTime;
import java.util.Arrays;

import org.apache.commons.io.FilenameUtils;

import model.EventList;

public class Util {

	public static int[] convertArray(int number) {
		int[] arrays = new int[number];
		for (int i = 1; i <= arrays.length; i++) {
			arrays[i - 1] = i;
		}
		return arrays;
	}

	public static boolean checkEmptyNull(Object object) {
		if (object == null || object.equals("")) {
			return true;
		}
		return false;
	}

	public static String convertURL(String url) {
		return FilenameUtils.separatorsToUnix("url");

	}
	
	public static LocalTime convertTime(String hour,String minute) {
		String time = hour+":"+minute+":"+"00";
		return LocalTime.parse(time);

	}

	public static void main(String[] args) {
		 System.out.println(convertTime("12", "30"));
	}

}
