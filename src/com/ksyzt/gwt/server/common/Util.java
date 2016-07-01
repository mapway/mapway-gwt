package com.ksyzt.gwt.server.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

import org.dom4j.Attribute;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Util {

	public static void WriteToFile(String filename, byte[] content) {
		try {
			FileOutputStream fo = new FileOutputStream(filename);
			fo.write(content);
			fo.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String readUTF8TextFile(String fileName) {
		byte[] bytes = ReadFromFile(fileName);
		String str = "";
		try {
			str = new String(bytes, "UTF-8");
			return str;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String readTextFile(String fileName, String encoding) {
		byte[] bytes = ReadFromFile(fileName);
		String str = "";
		try {
			str = new String(bytes, encoding);
			return str;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static byte[] ReadFromFile(String fileName) {

		File ff = new java.io.File(fileName);
		long filelength = ff.length();
		byte[] code = new byte[(int) filelength];

		InputStream inStream;
		try {
			inStream = new FileInputStream(fileName);
			inStream.read(code);
			inStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return code;
	}

	private String getLocalIP() {
		Enumeration<NetworkInterface> netinterfaces;
		try {
			netinterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			while (netinterfaces.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) netinterfaces
						.nextElement();

				if (ni.getInetAddresses().hasMoreElements()) {
					ip = (InetAddress) ni.getInetAddresses().nextElement();
					if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
							&& ip.getHostAddress().indexOf(":") == -1) {
						return ip.toString();
					} else {
						ip = null;
					}
				}
			}
		} catch (SocketException e) {

			e.printStackTrace();
		}
		return Util.randomDigital(15);
	}

	public static void text(Element e, String v) {
		e.add(DocumentHelper.createText(v));
	}

	public final static String int2path(int v) {
		String id = v + "";
		String str = "";
		for (int i = 0; i < id.length(); i++) {
			str += id.charAt(i) + "/";
		}
		return str;
	}

	public static void copyFile(String fileFromPath, String fileToPath)
			throws IOException {
		InputStream in = null;
		java.io.OutputStream out = null;
		try {
			in = new FileInputStream(fileFromPath);
			out = new FileOutputStream(fileToPath);
			byte[] b = new byte[1024];
			int count = in.read(b);
			while (count > 0) {
				out.write(b, 0, count);
				count = in.read(b);
			}
		} finally {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
		}

	}

	public static void attr(Element e, String name, String v) {
		if (name == null || name.equals(""))
			return;
		if (v == null)
			v = "";
		Attribute at = e.attribute(name);
		if (at == null) {
			at = DocumentHelper.createAttribute(e, name, v);
			e.add(at);
		} else {
			at.setValue(v);
		}
	}

	public static void WriteStringToFile(String fn, String dest) {

		FileOutputStream fo = null;
		try {
			fo = new FileOutputStream(fn);
			fo.write(dest.getBytes("UTF-8"));
			fo.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String writeToFile(File source, String dest) {
		String r = "true";
		FileOutputStream fo = null;
		FileInputStream fi = null;
		try {
			fo = new FileOutputStream(dest);
			fi = new FileInputStream(source);

			byte[] buffer = new byte[1024 * 8];

			int l = 0;
			l = fi.read(buffer);

			while (l > -1) {
				fo.write(buffer, 0, l);
				l = fi.read(buffer);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			r = e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
			r = e.getMessage();
		} finally {
			try {
				fo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fi.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return r;
	}

	public static String readTextFile(File source) throws IOException {

		String text = "";
		BufferedReader br;
		InputStreamReader isr = new InputStreamReader(new FileInputStream(
				source), "UTF-8");
		br = new BufferedReader(isr);
		String data = br.readLine();// 一次读入一行，直到读入null为文件结束
		while (data != null) {
			text += data;
			text += "\r\n";
			data = br.readLine(); // 接着读下一行
		}
		br.close();

		return text;
	}

	public static StringBuilder readFromFile(File source) throws IOException {

		StringBuilder sb = new StringBuilder();

		BufferedReader br;

		br = new BufferedReader(new FileReader(source));
		String data = br.readLine();// 一次读入一行，直到读入null为文件结束
		while (data != null) {
			sb.append(data);
			sb.append("\r\n");
			data = br.readLine(); // 接着读下一行
		}
		br.close();

		return sb;
	}

	public static String getLocalAddress() {
		Enumeration<NetworkInterface> allNetInterfaces;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "NO";
		}
		InetAddress ip = null;
		String ips = "";
		while (allNetInterfaces.hasMoreElements()) {
			NetworkInterface netInterface = (NetworkInterface) allNetInterfaces
					.nextElement();

			Enumeration addresses = netInterface.getInetAddresses();
			while (addresses.hasMoreElements()) {
				ip = (InetAddress) addresses.nextElement();
				if (ip != null && ip instanceof Inet4Address) {
					ips += ip.getHostAddress() + " ";
				}
			}
		}
		return ips;
	}

	public static String getHeadIconPathById(int id) {
		String si = String.valueOf(id);
		StringBuilder sb = new StringBuilder(32);

		for (int i = 0; i < si.length(); i++) {
			sb.append(si.charAt(i));
			sb.append("/");
		}
		return sb.toString();
	}

	public static int getDiffYear(java.sql.Timestamp start,
			java.sql.Timestamp end) {
		return end.getYear() - start.getYear();
	}

	public static java.sql.Timestamp addTime(java.sql.Timestamp start,
			int year, int month, int day) {
		return new java.sql.Timestamp(start.getTime() + year * 365 * 24 * 60
				* 60 * 1000 + month * 30 * 24 * 60 * 60 * 1000 + day * 24 * 60
				* 60 * 1000);
	}

	/**
	 * 
	 * @return
	 */
	public static final java.sql.Timestamp getCurrentSQLTimestamp() {
		Calendar c = Calendar.getInstance();
		java.sql.Timestamp t = new java.sql.Timestamp(c.getTimeInMillis());
		return t;
	}

	public static final java.sql.Timestamp toSQLTimestamp(String time) {

		return java.sql.Timestamp.valueOf(time);
	}

	public static final java.sql.Timestamp toSQLTimestamp2(String time) {

		Date d = getStrDate(time);
		return dateToSQLTimestamp(d);

	}

	public static Timestamp dateToSQLTimestamp(Date date) {
		if (date == null) {
			return java.sql.Timestamp.valueOf("2070-06-06 12:00:00");
		}
		String s = df.format(date);
		return java.sql.Timestamp.valueOf(s + " 12:00:00");
	}

	/**
	 * ȱʡ��DateFormat���󣬿��Խ�һ��java.util.Date��ʽ����yyyy-mm-dd���
	 */
	private static DateFormat df = DateFormat.getDateInstance(
			DateFormat.MEDIUM, Locale.SIMPLIFIED_CHINESE);

	/**
	 * <p>
	 * ����һ����ǰ��ʱ�䣬������ʽת��Ϊ�ַ�
	 * </p>
	 * ��17:27:03
	 * 
	 * @return String
	 */
	public static String getNowTime() {
		GregorianCalendar gcNow = new GregorianCalendar();
		java.util.Date dNow = gcNow.getTime();
		DateFormat df = DateFormat.getTimeInstance(DateFormat.MEDIUM,
				Locale.SIMPLIFIED_CHINESE);
		return df.format(dNow);
	}

	/**
	 * <p>
	 * ����һ����ǰ���ڣ�������ʽת��Ϊ�ַ�
	 * </p>
	 * ��2004-4-30
	 * 
	 * @return String
	 */
	public static String getNowDate() {
		GregorianCalendar gcNow = new GregorianCalendar();
		java.util.Date dNow = gcNow.getTime();
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM,
				Locale.SIMPLIFIED_CHINESE);
		return df.format(dNow);
	}

	/**
	 * <p>
	 * ����һ����ǰ���ڣ�������ʽת��Ϊ�ַ�
	 * </p>
	 * ��2004-4-30
	 * 
	 * @return String
	 */
	public static Date getNow() {
		GregorianCalendar gcNow = new GregorianCalendar();
		java.util.Date dNow = gcNow.getTime();
		return dNow;
	}

	/**
	 * <p>
	 * ����һ����ǰ���ڣ�������ʽת��Ϊ�ַ�
	 * </p>
	 * ��2004-4-30
	 * 
	 * @return String
	 */
	public static java.sql.Date getNowDate2() {

		return java.sql.Date.valueOf(getCurrentSQLTimestamp().toString()
				.substring(0, 10));

	}

	/**
	 * <p>
	 * ����һ����ǰ���ں�ʱ�䣬������ʽת��Ϊ�ַ�
	 * </p>
	 * ��2004-4-30 17:27:03
	 * 
	 * @return String
	 */
	public static String getNowDateTime() {
		GregorianCalendar gcNow = new GregorianCalendar();
		java.util.Date dNow = gcNow.getTime();
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
				DateFormat.MEDIUM, Locale.SIMPLIFIED_CHINESE);
		return df.format(dNow);
	}

	/**
	 * <p>
	 * ���ص�ǰ��
	 * </p>
	 * 
	 * @return int
	 */
	public static int getThisYear() {
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.YEAR);
	}

	/**
	 * ���ر���
	 * 
	 * @return int
	 */
	public static int getThisMonth() {
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.MONTH) + 1;
	}

	/**
	 * ���ؽ����Ǳ��µĵڼ���
	 * 
	 * @return int ��1��ʼ
	 */
	public static int getToDayOfMonth() {
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.DAY_OF_MONTH);
	}

	/**
	 * ���ص�ǰ��Сʱ
	 * 
	 * @return int
	 */
	public static int getHour() {
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.HOUR);
	}

	/**
	 * ���ص�ǰ�ķ���
	 * 
	 * @return int ���ص�ǰ�ķ���
	 */
	public static int getMinute() {
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.MINUTE);
	}

	/**
	 * ���ص�ǰ������
	 * 
	 * @return int �ڼ���
	 */
	public static int getSecond() {
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.SECOND);
	}

	/**
	 * ���ؽ����Ǳ���ĵڼ���
	 * 
	 * @return int ��1��ʼ
	 */

	public static int getToWeekOfYear() {
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.WEEK_OF_YEAR);
	}

	/**
	 * ����һ��ʽ��������
	 * 
	 * @param time
	 *            long
	 * @return String yyyy-mm-dd ��ʽ
	 */
	public static String formatDate(java.util.Date date) {
		if (date == null)
			return "";
		else
			return df.format(date);
	}

	/**
	 * ����һ��ʽ��������
	 * 
	 * @param time
	 *            long
	 * @return String 2005-6-17 ��ʽ
	 */
	public static String formatSDate(java.util.Date date) {
		if (date == null)
			return "";
		else {
			SimpleDateFormat bartDateFormat = new SimpleDateFormat(
					"yyyy-M-d HH:mm:ss");
			return bartDateFormat.format(date);
		}
	}

	/**
	 * ���������ָ��ʱ����������
	 * 
	 * @param interval
	 *            ��ʾҪ��ӵ�ʱ����("y":��;"d":��;"m":��;���б�Ҫ������������)
	 * @param number
	 *            ��ʾҪ��ӵ�ʱ�����ĸ���
	 * @param date
	 *            java.util.Date()
	 * @return String 2005-5-12��ʽ�������ִ�
	 */
	public static String dateAdd(String interval, int number,
			java.util.Date date) {
		String strTmp = "";
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		// ��������
		if (interval.equals("y")) {
			int currYear = gc.get(Calendar.YEAR);
			gc.set(Calendar.YEAR, currYear + number);
		}
		// ��������
		else if (interval.equals("m")) {
			int currMonth = gc.get(Calendar.MONTH);
			gc.set(Calendar.MONTH, currMonth + number);
		}
		// ��������
		else if (interval.equals("d")) {
			int currDay = gc.get(Calendar.DATE);
			gc.set(Calendar.DATE, currDay + number);
		}
		// ����Сʱ
		else if (interval.equals("h")) {
			int currDay = gc.get(Calendar.HOUR);
			gc.set(Calendar.HOUR, currDay + number);
		}
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyy-M-d HH:mm:ss");
		strTmp = bartDateFormat.format(gc.getTime());
		return strTmp;
	}

	/**
	 * <p>
	 * ������������֮��ĵ�λ�����
	 * </p>
	 * 
	 * @param a
	 *            java.util.Date
	 * @param b
	 *            java.util.Date
	 * @return int �����
	 */
	public static int dateDiff(java.util.Date a, java.util.Date b) {
		int tempDifference = 0;
		int difference = 0;
		Calendar earlier = Calendar.getInstance();
		Calendar later = Calendar.getInstance();

		if (a.compareTo(b) < 0) {
			earlier.setTime(a);
			later.setTime(b);
		} else {
			earlier.setTime(b);
			later.setTime(a);
		}

		while (earlier.get(Calendar.YEAR) != later.get(Calendar.YEAR)) {
			tempDifference = 365 * (later.get(Calendar.YEAR) - earlier
					.get(Calendar.YEAR));
			difference += tempDifference;

			earlier.add(Calendar.DAY_OF_YEAR, tempDifference);
		}

		if (earlier.get(Calendar.DAY_OF_YEAR) != later
				.get(Calendar.DAY_OF_YEAR)) {
			tempDifference = later.get(Calendar.DAY_OF_YEAR)
					- earlier.get(Calendar.DAY_OF_YEAR);
			difference += tempDifference;

			earlier.add(Calendar.DAY_OF_YEAR, tempDifference);
		}

		return difference;
	}

	/**
	 * <p>
	 * �÷����ǻ�õ�ÿ��1������һ�����
	 * </p>
	 * 
	 * @return -����һ������
	 */
	/**
	 * <p>
	 * �÷����ǻ�õ�ÿ��1������һ�����
	 * </p>
	 * 
	 * @return -����һ������
	 */
	public static int getDate(int curYear, int curMonth, int curDate) {
		int day1 = 0;
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(curYear, curMonth - 1, curDate);
		int dayOfWeek = cal.get(cal.DAY_OF_WEEK);
		System.out.println("curDate=" + curDate + " dayOfWeek " + dayOfWeek);
		switch (dayOfWeek) {
		case 1: // ������
			day1 = 0;
			break;
		case 2: // ����һ
			day1 = 1;
			break;
		case 3: // ���ڶ�
			day1 = 2;
			break;
		case 4: // ������
			day1 = 3;
			break;
		case 5: // ������
			day1 = 4;
			break;
		case 6: // ������
			day1 = 5;
			break;
		case 7: // ������
			day1 = 6;
			break;
		}
		return day1;
	}

	public static String checkTime(int id) {
		String bol = "";
		Calendar tt = Calendar.getInstance();
		String currDate = getNowDate();
		System.out.println("currDate=" + currDate);
		int result = tt.get(Calendar.DAY_OF_WEEK);

		int shour = tt.get(Calendar.HOUR_OF_DAY);

		if (id == 3) {
			switch (result) {
			case 1:
				break;
			case 7:
				if ((shour >= 8) && (shour < 12)) {
					bol = "disabled";
					break;
				}
			default:
				if ((shour >= 8) && (shour < 12)) {
					bol = "disabled";
					break;
				} else if ((shour >= 14) && (shour < 17)) {
					bol = "disabled";
					break;
				}
			}
		}
		return bol;
	}

	/**
	 * <p>
	 * �÷����ǽ��ַ���ת���������
	 * </p>
	 * 
	 * @param strX
	 *            -�����ַ�����
	 * @return -������������
	 */
	public static Date getStrDate(String strX) {
		Date date1 = new Date();
		if (!strX.equals("")) {
			try {
				date1 = (DateFormat.getDateInstance()).parse(strX);
			} catch (Exception ex) {

				System.out.println(ex.toString());
			}
		} else {
			GregorianCalendar gcNow = new GregorianCalendar();
			date1 = gcNow.getTime();
		}

		return date1;
	}

	/**
	 * <p>
	 * �Ƚ��������ַ�Ĵ�С
	 * </p>
	 * 
	 * @param d1
	 * @param d2
	 * @return (d1>d2)?2:(d1=d2)?1:0
	 */
	public static int compareDate(String d1, String d2) {
		short vl = 1;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(getStrDate(d1));
		int year = gc.get(GregorianCalendar.YEAR);
		int month = gc.get(GregorianCalendar.MONTH);
		int day = gc.get(GregorianCalendar.DAY_OF_MONTH);
		gc.setTime(getStrDate(d2));
		int tempYear = gc.get(GregorianCalendar.YEAR);
		int tempMonth = gc.get(GregorianCalendar.MONTH);
		int tempDay = gc.get(GregorianCalendar.DAY_OF_MONTH);
		if (year != tempYear) {
			if (year > tempYear)
				vl = 2;
			else
				vl = 0;
		} else {
			if (month != tempMonth) {
				if (month > tempMonth)
					vl = 2;
				else
					vl = 0;
			} else {
				if (day != tempDay) {
					if (day > tempDay)
						vl = 2;
					else
						vl = 0;
				}
			}
		}
		return vl;
	}

	/**
	 * 
	 * @param l
	 * @return
	 */
	public static String getDateString(long l, String format) {
		String v = new SimpleDateFormat(format).format(new Date(l));
		return v;
	}

	/**
	 * Pseudo-random number generator object for use with randomString(). The
	 * Random class is not considered to be cryptographically secure, so only
	 * use these random Strings for low to medium security applications.
	 */
	private static Random randGen = new Random();

	/**
	 * Array of numbers and letters of mixed case. Numbers appear in the list
	 * twice so that there is a more equal chance that a number will be picked.
	 * We can use the array to get a random number or letter by picking a random
	 * array index.
	 */
	private static char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
			+ "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

	/**
	 * Returns a random String of numbers and letters (lower and upper case) of
	 * the specified length. The method uses the Random class that is built-in
	 * to Java which is suitable for low to medium grade security uses. This
	 * means that the output is only pseudo random, i.e., each number is
	 * mathematically generated so is not truly random.
	 * <p>
	 * 
	 * The specified length must be at least one. If not, the method will return
	 * null.
	 * 
	 * @param length
	 *            the desired length of the random String to return.
	 * @return a random String of numbers and letters of the specified length.
	 */
	public static final String randomString(int length) {
		if (length < 1) {
			return null;
		}
		// Create a char buffer to put random letters and numbers in.
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	public static final String randomDigital(int length) {
		if (length < 1) {
			return null;
		}
		// Create a char buffer to put random letters and numbers in.
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(10)];
		}
		return new String(randBuffer);
	}

	public static final int randomNumber(int max) {
		return randGen.nextInt(max);
	}

	public static String getString(Object o) {
		if (o == null) {
			return "NULL";
		} else {
			return o.toString();
		}
	}

	public static String formatInteger(int i, int j) {
		String frm = "%0" + j + "d";
		String s;
		s = String.format(frm, i);
		return s;
	}

	public static Timestamp valueoftime(String b) {
		return Timestamp.valueOf(b + " 00:00:00.000000000");
	}

	public static Timestamp valueoftime2(String b) {
		return Timestamp.valueOf(b + " 24:00:00.000000000");
	}

	public static void main(String[] args) {
		GregorianCalendar gcNow = new GregorianCalendar();
		java.util.Date dNow = gcNow.getTime();

		String t = Util.getLocalAddress();
		System.out.println(t);
	}

	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(
							src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(
							src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

}
