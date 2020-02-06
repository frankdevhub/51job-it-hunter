package utils;

import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import frankdevhub.job.automatic.core.utils.CommonBusinessUtils;
import frankdevhub.job.automatic.entities.BusinessCharacter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.util.Assert;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * <p>Title:@ClassName utils.BusinessCharacterTest.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/4 0:17
 * @Version: 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class BusinessCharacterTest {

    private final Logger LOGGER = LoggerFactory.getLogger(BusinessCharacterTest.class);

    private static final Character CN_CHAR = '个';
    private static final Character TW_CHAR = '個';
    private static final Character EN_CHAR = 'h';
    private static final Character NUM_CHAR = '3';

    private void printAttributeMap(Map<String, Boolean> attributes) {
        Assert.notNull(attributes, "attribute Map should not be null");
        Set<Map.Entry<String, Boolean>> entrySet = attributes.entrySet();
        Iterator<Map.Entry<String, Boolean>> it = entrySet.iterator();

        System.out.println("print attribute map");
        while (it.hasNext())
            System.out.println("key = " + it.next().getKey() + "; value = " + it.next().getValue());
    }

    @Test
    public void testSetAttributes() throws InvocationTargetException, IllegalAccessException {
        Map<String, Boolean> attributes;
        BusinessCharacter businessCharacter = new BusinessCharacter();
        LOGGER.begin().info("run test method {{testSetAttributes}} start");

        System.out.println("using example CN_CHAR: " + CN_CHAR);
        attributes = CommonBusinessUtils.getCharacterAttributes(CN_CHAR);
        printAttributeMap(attributes);
        businessCharacter.setValue(CN_CHAR).setAttributes(attributes).toString();


        LOGGER.begin().info("run test method {{testSetAttributes}} complete");
    }

}
