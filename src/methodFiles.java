import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class methodFiles {

//////////////시, 분, 초를 초로 바꾸는 메서드////////////////
	public static int Jip(int s, int m, int h) {
		return s + m * 60 + h * 3600;
	}

//////////////현재 시스템상 날짜을 String타입으로 리턴하는 메서드////////////////
	public static String now() {
		SimpleDateFormat format1 = new SimpleDateFormat("MM.dd");

		Date t = new Date();
		String time = format1.format(t);

		return time;
	}

//////////////db 추가 메서드////////////////
	@SuppressWarnings("unchecked")
	public static void add_timerecord(String start, String end, int attandsc) {
		JSONParser parser = new JSONParser();

		if (fileExistence("Time") == false) {
			create_json_write(start, end, attandsc); // json 파일을 생성하는 메서드 호출
			return;
		}

		try {
			Object obj = parser.parse(new FileReader("Time.json"));// Time.json을 읽어서 object에 넣음
			JSONObject jsonObject = (JSONObject) obj; // object 을 jsonobject에 넣음
			JSONArray TimeArray = (JSONArray) jsonObject.get("Time"); // object에 있는 time array를 jsonarray에 넣음

			JSONObject TimeObject = new JSONObject(); // 새 오브젝트 추가
			TimeObject.put("num", TimeArray.size() + 1); // add num
			TimeObject.put("start", start); // add start date
			TimeObject.put("end", end);// add end date
			TimeObject.put("attention", attandsc);// add attantiontime

			TimeArray.add(TimeObject);// 추가된 오브젝트 array에 넣음

			FileWriter file = new FileWriter("Time.json");
			file.write(jsonObject.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		//////////////////////////////////////////////////////////////////
		try {
			Object obj2 = parser.parse(new FileReader("Days.json"));// Days.json을 읽어서 object에 넣음
			JSONObject jsonObject2 = (JSONObject) obj2; // object 을 jsonobject에 넣음
			JSONArray DaysArray = (JSONArray) jsonObject2.get("Days"); // object에 있는 time array를 jsonarray에 넣음

			JSONObject DaysObject = (JSONObject) DaysArray.get(DaysArray.size() - 1); // 마지막 오브젝트 추가

			if (start.contentEquals((String) DaysObject.get("Date"))) {

				int jip = ((Long) DaysObject.get("TodayJip")).intValue() + attandsc;
				int co = ((Long) DaysObject.get("count")).intValue() + 1;

				DaysObject.put("count", co);
				DaysObject.put("TodayJip", jip);
				DaysObject.put("average", (int) (jip / co));

				FileWriter file2 = new FileWriter("Days.json");
				file2.write(jsonObject2.toJSONString());
				file2.flush();
				file2.close();
				/////////////////////////
			} else {
				JSONObject DaysObject2 = new JSONObject();
				DaysObject2.put("Date", start);
				DaysObject2.put("TodayJip", attandsc);
				DaysObject2.put("count", 1);
				DaysObject2.put("average", attandsc);

				DaysArray.add(DaysObject2);

				FileWriter file2 = new FileWriter("Days.json");
				file2.write(jsonObject2.toJSONString());
				file2.flush();
				file2.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

//////////////첫 db 생성 메서드////////////////
	@SuppressWarnings("unchecked")
	public static void create_json_write(String st, String ed, int attandsc) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Stopwatch", "db");

		JSONArray jaon_array = new JSONArray();

		JSONObject Time = new JSONObject();
		Time.put("num", 1);
		Time.put("start", st);
		Time.put("end", ed);
		Time.put("attention", attandsc);

		jaon_array.add(Time);

		jsonObject.put("Time", jaon_array);

		try {
			FileWriter file = new FileWriter("Time.json");
			file.write(jsonObject.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//////////////////////////
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("Day", "db");

		JSONArray jaon_array2 = new JSONArray();

		JSONObject Days = new JSONObject();
		Days.put("Date", st);
		Days.put("TodayJip", attandsc);
		Days.put("count", 1);
		Days.put("average", attandsc);

		jaon_array2.add(Days);

		jsonObject2.put("Days", jaon_array2);

		try {
			FileWriter file = new FileWriter("Days.json");
			file.write(jsonObject2.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

//////////집중 데이터 중 최댓값을 구하여 리턴하는 메서드//////////
	public static int LongGip() {
		int max = 0;

		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("Time.json"));
			JSONObject jsonObject = (JSONObject) obj;

			JSONArray Timer = (JSONArray) jsonObject.get("Time");
			JSONObject personObject;
			for (int i = 0; i < Timer.size(); i++) {
				personObject = (JSONObject) Timer.get(i);

				int m = ((Long) personObject.get("attention")).intValue();

				if (m > max) {
					max = m;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return max;
	}

	/////////// 초를 string 시간으로 리턴//////////
	public static String timeToString(int sec) {
		int s = 0;
		int m = 0;
		int h = 0;

		if (sec >= 3600) {
			h = (sec / 3600);
			sec -= (h * 3600);
		}
		if (sec >= 60) {
			m = (sec / 60);
			sec -= (m * 60);
		} // end of middle if

		s = sec;

		return h + " : " + m + " : " + s;
	}

	public static int getTimeattention(int n) {
		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader("Time.json"));// Time.json을 읽어서 object에 넣음
			JSONObject jsonObject = (JSONObject) obj; // object 을 jsonobject에 넣음
			JSONArray TimeArray = (JSONArray) jsonObject.get("Time"); // object에 있는 time array를 jsonarray에 넣음
			JSONObject TimeObject = (JSONObject) TimeArray.get(n);

			return ((Long) TimeObject.get("attention")).intValue();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;

	}

	public static int getDateJip(int n) {
		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader("Days.json"));// Time.json을 읽어서 object에 넣음
			JSONObject jsonObject = (JSONObject) obj; // object 을 jsonobject에 넣음
			JSONArray DaysArray = (JSONArray) jsonObject.get("Days"); // object에 있는 time array를 jsonarray에 넣음
			JSONObject DaysObject = (JSONObject) DaysArray.get(n);

			return ((Long) DaysObject.get("TodayJip")).intValue();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int getDateAver(int n) {
		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader("Days.json"));// Time.json을 읽어서 object에 넣음
			JSONObject jsonObject = (JSONObject) obj; // object 을 jsonobject에 넣음
			JSONArray DaysArray = (JSONArray) jsonObject.get("Days"); // object에 있는 time array를 jsonarray에 넣음
			JSONObject DaysObject = (JSONObject) DaysArray.get(n);

			return ((Long) DaysObject.get("average")).intValue();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static String getDateDay(int n) {
		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader("Days.json"));// Time.json을 읽어서 object에 넣음
			JSONObject jsonObject = (JSONObject) obj; // object 을 jsonobject에 넣음
			JSONArray DaysArray = (JSONArray) jsonObject.get("Days"); // object에 있는 time array를 jsonarray에 넣음
			JSONObject DaysObject = (JSONObject) DaysArray.get(n);

			return (String) DaysObject.get("Date");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int getTimeArray() {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("Time.json"));
			JSONObject jsonObject = (JSONObject) obj;

			JSONArray Timer = (JSONArray) jsonObject.get("Time");

			return Timer.size();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int getDateArray() {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("Days.json"));
			JSONObject jsonObject = (JSONObject) obj;

			JSONArray Days = (JSONArray) jsonObject.get("Days");

			return Days.size();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static boolean fileExistence(String file) {

		File json = new File(file + ".json"); // Time.json 파일

		if (json.isFile()) {
			return true;
		} else {
			return false;
		}
	}

	public static String TodayAver() {
		String n = now();
		if (fileExistence("Days") == false) {
			return null;
		}

		if (n.contentEquals(getDateDay(getDateArray() - 1))) {

			return timeToString(getDateAver(getDateArray() - 1));
		} else {
			return "기록이 없습니다.";
		}
	}

	public static void WrongData(String startT, String endT, int jip) {
		if (startT == null) {
			JOptionPane.showMessageDialog(null, "Start 버튼을 먼저 눌러주세요!");
			return;
		} else {
			methodFiles.add_timerecord(startT, endT, jip);
		}

	}
}
