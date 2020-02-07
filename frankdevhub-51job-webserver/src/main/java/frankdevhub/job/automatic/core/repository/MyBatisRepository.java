package frankdevhub.job.automatic.core.repository;

import com.github.pagehelper.PageHelper;

import java.util.Calendar;
import java.util.TimeZone;

public class MyBatisRepository {
    public void setPage(Integer pageNum, Integer pageSize) {
        if (pageSize != null && pageSize != null)
            PageHelper.startPage(pageNum, pageSize, true);
    }

    public Long[] getTimeStampRange(Calendar c) {
        Long range[] = new Long[2];

        String date = "";
        Long zeroT = null;
        Long endT = null;
        range[0] = zeroT;
        range[1] = endT;

        System.out.println("date = " + date + ", "
                + "zeroT = " + zeroT + ""
                + ", endT=" + endT + "");
        return range;
    }


    public Long[] getTimeStampRange(Long timeStamp) {
        if (timeStamp < 1000000000000L)
            throw new RuntimeException("please use timestamp format in millisecond");

        Long[] range = new Long[2];
        long zeroT = timeStamp / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        long endT = zeroT + (24 * 3600 * 1000) - 1;

        range[0] = zeroT;
        range[1] = endT;

        System.out.println(
                "timeStamp = "
                        + timeStamp
                        + ", zeroT = " + zeroT
                        + ", endT = " + endT + "");

        return range;
    }
}
