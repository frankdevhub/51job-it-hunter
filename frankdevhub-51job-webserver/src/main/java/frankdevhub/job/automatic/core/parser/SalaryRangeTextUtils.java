package frankdevhub.job.automatic.core.parser;

import frankdevhub.job.automatic.core.constants.BusinessConstants;
import frankdevhub.job.automatic.core.enums.DateUnit;
import frankdevhub.job.automatic.core.enums.NumericUnit;
import frankdevhub.job.automatic.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@SuppressWarnings("all")
public class SalaryRangeTextUtils {

    //薪资范围描述的正则表达式
    private final String rangeRegex =
            "(?<min>(([1-9]\\d*\\.?\\d+)|(0\\.\\d*[1-9])|(\\d+))?)" +
                    "(?<hyphen>((—|-)+)?)" +
                    "(?<max>(([1-9]\\d*\\.?\\d+)|(0\\.\\d*[1-9])|(\\d+))?)" +
                    "(?<numeric>[\\u4e00-\\u9fa5]?)(/?)(?<date>[\\u4e00-\\u9fa5]?)";

    private String text; //薪资范围描述的字符串
    private String minimize; //薪资范围(最小值)
    private String maximum; //薪资范围(最大值)
    private String timeUnit; //计量的时间单位
    private String numericUnit; //计量的数值单位

    public SalaryRangeTextUtils() {

    }

    public SalaryRangeTextUtils(String text) {
        this.text = text.trim();
    }

    private void clear() throws IllegalAccessException {
        log.info("invoke {{SalaryRangeTextUtils::clear()}}");
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
        log.info("invoke {{SalaryRangeTextUtils::toString()}}");

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
        log.info("invoke {{SalaryRangeTextUtils::parse()}}");
        Assert.notNull(this.getText(), "text should not be null");
        clear();
        this.text = getText().trim();
        log.info("parsing test: " + this.getText());
        Matcher matcher = Pattern.compile(rangeRegex).matcher(text);
        if (matcher.find()) {
            this.minimize = matcher.group("min"); //匹配最小值
            this.maximum = matcher.group("max"); //匹配最大值
            this.numericUnit = matcher.group("numeric"); //匹配数值单位
            this.timeUnit = matcher.group("date"); //匹配计量时间(年月日天)
        } else
            throw new BusinessException(BusinessConstants.SALARY_RANGE_REGEX_MATCH_ERROR);
        log.info("salary range text parse complete");
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

    public Double getMinimizeValue() {
        if (null != minimize)
            return Double.parseDouble(minimize.trim());
        else
            return 0.0;
    }

    public String getMaximum() {
        return maximum;
    }

    public Double getMaximumValue() {
        if (null != maximum)
            return Double.parseDouble(maximum);
        else
            return 0.0;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public DateUnit getTimeUnitType() {
        char[] array = this.timeUnit.trim().toCharArray();
        if (array.length > 1)
            throw new RuntimeException("invalid time unit format, length out of size");
        if (array.length == 0)
            return null;
        return DateUnit.getUnitType(array[0]);
    }

    public String getNumericUnit() {
        return numericUnit;
    }

    public NumericUnit getNumericUnitType() {
        char[] array = this.numericUnit.trim().toCharArray();
        if (array.length > 1)
            throw new RuntimeException("invalid numeric unit format, length out of size");
        if (array.length == 0)
            return null;
        return NumericUnit.getUnitType(array[0]);
    }

    public Boolean isUnitByThousand() {
        NumericUnit unit = getNumericUnitType();
        if (null == unit)
            return Boolean.FALSE;
        else if (unit.equals(NumericUnit.Thousand_CN_TW) || unit.equals(NumericUnit.Thousand_EN))
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

    public Boolean isUnitByTenThousand() {
        NumericUnit unit = getNumericUnitType();
        if (null == unit)
            return Boolean.FALSE;
        else if (unit.equals(NumericUnit.Ten_Thousand_CN)
                || unit.equals(NumericUnit.Ten_Thousand_TW)
                || unit.equals(NumericUnit.Ten_Thousand_EN))

            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

    public Boolean isUnitByDay() {
        DateUnit unit = getTimeUnitType();
        if (null == unit)
            return Boolean.FALSE;
        else if (unit.equals(DateUnit.DAY_1) || unit.equals(DateUnit.DAY_2))
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

    public Boolean isUnitByMonth() {
        DateUnit unit = getTimeUnitType();
        if (null == unit)
            return Boolean.FALSE;
        else if (unit.equals(DateUnit.MONTH))
            return Boolean.TRUE;
        else
            return Boolean.FALSE;

    }

    public Boolean isUnitByYear() {
        DateUnit unit = getTimeUnitType();
        if (null == unit)
            return Boolean.FALSE;
        else if (unit.equals(DateUnit.YEAR))
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }
}
