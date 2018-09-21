package pictureconvert;

import java.util.Calendar;
import java.util.Date;

public class TimeConvert {
	
	public static String timeConvertString(Date convertTime) {
	
	Calendar time = Calendar.getInstance();
	time.setTime(convertTime);
	int msgMonth = time.get(Calendar.MONTH) + 1;
	int msgDay = time.get(Calendar.DAY_OF_MONTH);
	int msgHour = time.get(Calendar.HOUR_OF_DAY);
	String msgHourStr = Integer.toString(msgHour);

	if (msgHour < 10)
		msgHourStr = "0" + msgHourStr;

	int msgMinute = time.get(Calendar.MINUTE);
	String msgMinuteStr = Integer.toString(msgMinute);
	if (msgMinute < 10)
		msgMinuteStr = "0" + msgMinuteStr;

	String msgtimeStr = msgMonth + " 月 " + msgDay + " 日  " + msgHourStr + " : " + msgMinuteStr;
	
	return msgtimeStr;
	}
	
	public static String dateline(Date convertTime) {
		
		// 時間轉換
		String[] week = { "(日)", "(一)", "(二)", "(三)", "(四)", "(五)", "(六)" };
		
		Calendar dl = Calendar.getInstance();
			dl.setTime(convertTime);
			int dlMonth = dl.get(Calendar.MONTH) + 1;
			int dlDay = dl.get(Calendar.DAY_OF_MONTH);
			int dlWeek = dl.get(Calendar.DAY_OF_WEEK);

			String	dateline = dlMonth + "/" + dlDay + week[dlWeek - 1];
		
			return dateline;
	}
}
