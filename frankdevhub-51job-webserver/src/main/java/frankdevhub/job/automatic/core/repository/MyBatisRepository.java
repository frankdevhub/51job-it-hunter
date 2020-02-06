package frankdevhub.job.automatic.core.repository;

import com.github.pagehelper.PageHelper;

import java.util.Calendar;

public class MyBatisRepository {
    public void setPage(Integer pageNum, Integer pageSize) {
        if (pageSize != null && pageSize != null)
            PageHelper.startPage(pageNum, pageSize, true);
    }

    public Long[] getTimeStampRange(Calendar c) {
        Long[] range = null;
        return range;
    }
}
