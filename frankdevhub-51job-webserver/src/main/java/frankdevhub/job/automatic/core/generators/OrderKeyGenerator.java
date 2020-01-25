package frankdevhub.job.automatic.core.generators;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//H201901030320098
public class OrderKeyGenerator {

    private static Lock lock = new ReentrantLock();

    private String getYear(Calendar c) {
        int year = c.get(Calendar.YEAR);
        return new Integer(year).toString();
    }

    private String getMonth(Calendar c) {
        int month = c.get(Calendar.MONTH) + 1;
        String str = getFullKey(new Integer(month).toString(), 2);
        return str;
    }

    private String getDate(Calendar c) {
        int date = c.get(Calendar.DATE);
        String str = getFullKey(new Integer(date).toString(), 2);
        return str;
    }

    private String getFullKey(String str, int size) {
        if (size <= 0)
            return str;
        String s = "";
        int step = size - str.length();
        for (int i = 0; i < step; i++)
            s += "0";
        return s + str;
    }

    public String generate(String header, String code, String order) {
        StringBuffer buffer = new StringBuffer();
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        buffer.append(header).append(getYear(c)).append(getMonth(c))
                .append(getDate(c)).append(getFullKey(code, 3))
                .append(getFullKey(order, 4));
        return buffer.toString();
    }

}
