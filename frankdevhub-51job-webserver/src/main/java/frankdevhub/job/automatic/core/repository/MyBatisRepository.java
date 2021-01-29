package frankdevhub.job.automatic.core.repository;

import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
@SuppressWarnings("all")
public class MyBatisRepository {

    /**
     * 配置GithubPageHelper分页
     *
     * @param pageNum  分页参数:第几页
     * @param pageSize 分页参数:每页行数
     */
    public void setPage(Integer pageNum, Integer pageSize) {
        if (pageSize != null && pageSize != null)
            PageHelper.startPage(pageNum, pageSize, true);
    }

    /**
     * 重置当前时间
     *
     * @param c 日历时间对象
     */
    private void resetCurrentCalendarMilliSecond(Calendar c) {
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
    }

    /**
     * 获取当日的时间范围(毫秒)
     *
     * @return 当日时间戳范围
     */
    public Long[] getTodayTimeStampRange() {
        Long range[] = new Long[2];
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        c.setTime(date);
        resetCurrentCalendarMilliSecond(c);
        Long zeroT = c.getTime().getTime();
        Long endT = zeroT + (24 * 3600 * 1000) - 1;

        range[0] = zeroT;
        range[1] = endT;
        log.info(
                "timeStamp = "
                        + date.getTime()
                        + ", zeroT = " + zeroT
                        + ", endT = " + endT + "");
        return range;
    }

    /**
     * 获取某年某月某日的时间范围(毫秒)
     *
     * @param day   天
     * @param month 月
     * @param year  年
     * @return 当日时间戳范围
     */
    public Long[] getTimeStampRange(Integer year, Integer month, Integer day) {
        Long range[] = new Long[2];
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DATE, day);
        resetCurrentCalendarMilliSecond(c);
        Long zeroT = c.getTime().getTime();
        Long endT = zeroT + (24 * 3600 * 1000) - 1;

        range[0] = zeroT;
        range[1] = endT;
        log.info(
                "year = "
                        + year
                        + ", month= " + month
                        + ", day = " + day
                        + ", zeroT = " + zeroT
                        + ", endT = " + endT + "");
        return range;
    }

    /**
     * 获取某年某月某日的时间范围(毫秒)
     *
     * @param c 日历时间对象
     * @return 当日时间戳范围
     */
    public Long[] getTimeStampRange(Calendar c) {
        Long range[] = new Long[2];
        Date date = c.getTime();
        resetCurrentCalendarMilliSecond(c);
        Long zeroT = c.getTime().getTime();
        Long endT = zeroT + (24 * 3600 * 1000) - 1;
        range[0] = zeroT;
        range[1] = endT;

        log.info("timeStamp = " + date.getTime() + ", "
                + "zeroT = " + zeroT + ""
                + ", endT=" + endT + "");
        return range;
    }

    /**
     * 获取某年某月某日的时间范围(毫秒)
     *
     * @param timeStamp 时间戳
     * @return 当日时间戳范围
     */
    public Long[] getTimeStampRange(Long timeStamp) {
        if (timeStamp < 1000000000000L) {
            //非法的时间戳或时间戳实际几乎没有任何意义的不做处理
            throw new RuntimeException("please use timestamp format in millisecond");
        }
        Long[] range = new Long[2];
        long zeroT = timeStamp - (timeStamp + TimeZone.getDefault().getRawOffset()) % (1000 * 3600 * 24);
        long endT = zeroT + (24 * 3600 * 1000) - 1;
        range[0] = zeroT;
        range[1] = endT;
        log.info(
                "timeStamp = "
                        + timeStamp
                        + ", zeroT = " + zeroT
                        + ", endT = " + endT + "");
        return range;
    }

}
