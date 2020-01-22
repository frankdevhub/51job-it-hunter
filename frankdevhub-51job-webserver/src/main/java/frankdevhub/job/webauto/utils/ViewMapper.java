package frankdevhub.job.webauto.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface ViewMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
