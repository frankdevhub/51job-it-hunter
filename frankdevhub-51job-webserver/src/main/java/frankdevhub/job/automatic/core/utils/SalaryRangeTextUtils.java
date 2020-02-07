package frankdevhub.job.automatic.core.utils;

import frankdevhub.job.automatic.core.constants.BusinessConstants;
import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import frankdevhub.job.automatic.core.exception.BusinessException;
import tk.mybatis.mapper.util.Assert;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title:@ClassName SalaryRangeTextUtils.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/7 22:26
 * @Version: 1.0
 */

public class SalaryRangeTextUtils {

    private String text;
    private String minimize;
    private String maximum;
    private String timeUnit;
    private String numericUnit;

    private final String rangeRegex =
            "(?<min>([1-9]\\d*\\.?\\d+)|(0\\.\\d*[1-9])|(\\d+))" +
                    "(?<hyphen>(—|-)+)" +
                    "(?<max>([1-9]\\d*\\.?\\d+)|(0\\.\\d*[1-9])|(\\d+))" +
                    "(?<numeric>[\\u4e00-\\u9fa5]?)(/?)(?<date>[\\u4e00-\\u9fa5]?)";

    private final Logger LOGGER = LoggerFactory.getLogger(SalaryRangeTextUtils.class);

    public SalaryRangeTextUtils() {

    }

    public SalaryRangeTextUtils(String text) {
        this.text = text;
    }

    private void clear() throws IllegalAccessException {
        LOGGER.begin().info("invoke {{SalaryRangeTextUtils::clear()}}");

        Class<?> clazz = this.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            if (!f.getName().equals("text")) {
                f.setAccessible(Boolean.TRUE);
                f.set(this, null);
            }
        }
    }

    @Override
    public String toString() {
        LOGGER.begin().info("invoke {{SalaryRangeTextUtils::toString()}}");

        return "SalaryRangeTextUtils{" + '\n' +
                "text='" + text + '\'' + '\n' +
                ", minimize='" + minimize + '\'' + '\n' +
                ", maximum='" + maximum + '\'' + '\n' +
                ", timeUnit='" + timeUnit + '\'' + '\n' +
                ", numericUnit='" + numericUnit + '\'' + '\n' +
                ", rangeRegex='" + rangeRegex + '\'' + '\n' +
                '}';
    }

    public void parse() throws IllegalAccessException, BusinessException {
        LOGGER.begin().info("invoke {{SalaryRangeTextUtils::parse()}}");

        Assert.notNull(text.trim(), "text should not be null");
        clear();

        Matcher matcher = Pattern.compile(rangeRegex).matcher(text);
        if (matcher.find()) {
            this.minimize = matcher.group("min");
            this.maximum = matcher.group("max");
            this.numericUnit = matcher.group("numeric");
            this.timeUnit = matcher.group("date");

        } else
            throw new BusinessException(BusinessConstants.SALARY_RANGE_REGEX_MATCH_ERROR);

        System.out.println("salary range text parse complete");
    }

    public String getText() {
        return text;
    }

    public SalaryRangeTextUtils setText(String text) {
        this.text = text;
        return this;
    }

    public String getMinimize() {
        return minimize;
    }

    public String getMaximum() {
        return maximum;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public String getNumericUnit() {
        return numericUnit;
    }
}
