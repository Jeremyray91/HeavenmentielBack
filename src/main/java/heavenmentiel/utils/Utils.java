package heavenmentiel.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	public static Date parseDateFrToGb(String d) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = formatter.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
