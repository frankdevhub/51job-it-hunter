package frankdevhub.job.automatic.core.repository;

import com.github.pagehelper.PageHelper;

public class MyBatisRepository {
    public void setPage(Integer pageNum, Integer pageSize) {
        if (pageSize != null && pageSize != null)
            PageHelper.startPage(pageNum, pageSize, true);
    }
}
