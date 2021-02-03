package data.utils;

import frankdevhub.job.automatic.core.utils.CommonBusinessUtils;
import frankdevhub.job.automatic.entities.BusinessCharacter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import tk.mybatis.mapper.util.Assert;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * <p>Title:@ClassName selenium.utils.BusinessCharacterTest.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/4 0:17
 * @Version: 1.0
 */
@Slf4j
public class BusinessCharacterTest {

    private static final Character CN_CHAR = '个'; //中文简体字符
    private static final Character TW_CHAR = '個'; //中文繁体字符
    private static final Character EN_CHAR = 'h'; //英文字符
    private static final Character NUM_CHAR = '3'; //数值类字符

    /**
     * 输出字符的属性
     *
     * @param attributes 属性集合
     */
    private void printAttributeMap(Map<String, Boolean> attributes) {
        Assert.notNull(attributes, "attribute Map should not be null");
        Set<Map.Entry<String, Boolean>> entrySet = attributes.entrySet();
        Iterator<Map.Entry<String, Boolean>> it = entrySet.iterator();

        log.info("print attribute map");
        while (it.hasNext()) {
            log.info("key = " + it.next().getKey() + "; value = " + it.next().getValue());
        }
    }

    /**
     * 测试读取字符属性
     *
     * @throws InvocationTargetException,IllegalAccessException
     */
    @Test
    public void testSetAttributes() throws InvocationTargetException, IllegalAccessException {
        Map<String, Boolean> attributes;
        BusinessCharacter businessCharacter = new BusinessCharacter();

        log.info("using example CN_CHAR: " + CN_CHAR);
        attributes = CommonBusinessUtils.getCharacterAttributes(CN_CHAR);
        printAttributeMap(attributes);
        businessCharacter.setValue(CN_CHAR).setAttributes(attributes).toString();
    }

}
