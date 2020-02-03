import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import frankdevhub.job.automatic.core.utils.CommonBusinessUtils;
import frankdevhub.job.automatic.entities.BusinessCharacter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * <p>Title:@ClassName BusinessCharacterTest.java</p>
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

    @Test
    public void testSetAttributes() throws InvocationTargetException, IllegalAccessException {
        Map<String, Boolean> attributes;
        BusinessCharacter businessCharacter = new BusinessCharacter();
        LOGGER.begin().info("run test method {{testSetAttributes}} start");

        System.out.println("using example CN_CHAR: " + CN_CHAR);
        attributes = CommonBusinessUtils.getCharacterAttributes(CN_CHAR);
        businessCharacter.setValue(CN_CHAR).setAttributes(attributes).toString();


        LOGGER.begin().info("run test method {{testSetAttributes}} complete");
    }

}
