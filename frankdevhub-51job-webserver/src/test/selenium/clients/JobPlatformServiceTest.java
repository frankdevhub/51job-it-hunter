package selenium.clients;

import frankdevhub.job.automatic.JobWebAutoService;
import frankdevhub.job.automatic.web.clients.JobPlatformService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p>Title:@ClassName JobPlatformServiceTest.java</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @Author: frankdevhub@gmail.com
 * @CreateDate: 2020/2/25 15:24
 * @Version: 1.0
 */

@Slf4j
@SpringBootTest(classes = JobWebAutoService.class)
@SuppressWarnings("all")
public class JobPlatformServiceTest {
    //默认测试的页面链接
    private final String TEST_RESULT_PAGE = "https://search.51job.com/list/020000,000000,0000,00,9,99,java,2,1.html?" +
            "lang=c" +  //默认语言
            "&stype=" +  //stype
            "&postchannel=0000" +  //postchannel
            "&workyear=99" +  //workyear 工作年限
            "&cotype=99" +  //cotype
            "&degreefrom=99" +  //degreefrom 学历要求
            "&jobterm=99" + //jobterm
            "&companysize=99" + //companysize 企业人员规模
            "&providesalary=99" + //providesalary
            "&lonlat=0%2C0" + //lonlat
            "&radius=-1" + //radius
            "&ord_field=0" +  //ord_field
            "&confirmdate=9" + //confirmdate
            "&fromType=" +  //fromType
            "&dibiaoid=0" + //dibiaoid
            "&address=" + //address 工作岗位地址
            "&line=" +  //line
            "&specialarea=00" + //specialarea 辖区编号
            "&from=" + //form
            "&welfare="; //welfare 薪资福利

    /**
     * 测试跳转搜索结果集页面并解析
     */
    @Test
    public void testDefaultDataPatrolService() {
        System.out.println("thread name = " + Thread.currentThread().getName());
        log.info("run test method {{testGetJobSearchResult}} start");

        JobPlatformService service = new JobPlatformService();
        try {
            service.defaultDataPatrolService(TEST_RESULT_PAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("run test method {{testGetJobSearchResult}} complete");
    }
}
