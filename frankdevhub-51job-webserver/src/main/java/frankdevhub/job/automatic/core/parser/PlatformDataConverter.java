package frankdevhub.job.automatic.core.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import frankdevhub.job.automatic.entities.PlatformDataJson;
import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.util.Assert;

/**
 * @Title: PlatformDataConverter
 * @Description: 平台json转换为业务实体类
 * @date: 2021/1/31 0:20
 * @author: frankdevhub@gmail.com
 * @blog: blog.frankdevhub.site
 * @version: 1.0
 */

@Slf4j
@SuppressWarnings("all")
public class PlatformDataConverter {

    /**
     * 平台json转换为ORM持久化对象
     *
     * @param json 返回的json字符串
     * @return ORM业务对象实体类
     */
    public static PlatformDataJson convert(String json) {
        Assert.notNull(json, "cannot find json");

        PlatformDataJson data = new PlatformDataJson();
        JSONObject obj = JSON.parseObject(json);
        //二次封装转换为ORM对象
        data.setType(obj.getString("type")) //type  engine_search_result
                .setJt(obj.getInteger("jt")) //jt
                .setTags(obj.getString("tag")) //tags
                .setAdTrack(obj.getString("ad_track"))     //ad_track
                .setJobId(obj.getString("jobid"))//jobid 128689972
                .setCoId(obj.getString("coid"))  //coid  5728619
                .setEffect(obj.getInteger("effect"))//effect
                .setIsSpecialJob(obj.getString("is_special_job"))//is_special_job
                .setJobHref(obj.getString("job_href"))//job_href
                .setJobName(obj.getString("job_name"))//job_name
                .setJobTitle(obj.getString("job_title"))//job_title "高级Java开发"
                .setCompanyHref(obj.getString("company_href"))//company_href
                .setCompanyName(obj.getString("company_name"))//company_name 北明软件有限公司
                .setProvidesalaryText(obj.getString("providesalary_text"))//providesalary_text 1.3-2.6万/月
                .setWorkArea(obj.getString("workarea"))//workarea 021000
                .setWorkareaText(obj.getString("workarea_text"))//workarea_text 上海-浦东新区
                .setUpdateDate(obj.getString("updatedate"))//updatedate  01-29
                .setIsIntern(obj.getString("isIntern"))//isIntern
                .setIsCommunicate(obj.getString("iscommunicate"))//iscommunicate
                .setCompanytypeText(obj.getString("companytype_text"))//companytype_text 上市公司
                .setDegreeFrom(obj.getInteger("degreefrom"))//degreefrom 6
                .setWorkYear(obj.getInteger("workyear"))//workyear 5
                .setIssueData(obj.getString("issuedate"))//issuedate 2021-01-29 18:06:46
                .setIsFromXyz(obj.getString("isFromXyz"))//isFromXyz
                .setJobwelf(obj.getString("jobwelf"))//jobwelf 餐饮补贴 免费班车 五险一金 高温补贴 节日福利 年终奖金 加班补贴
                .setJobwelfList(obj.getString("jobwelf_list"))//jobwelf_list
                .setAttributeText(obj.getString("attribute_text"))//attribute_text
                .setCompanysizeText(obj.getString("companysize_text"))//companysize_text 500-1000人
                .setCompanyindText(obj.getString("companyind_text"))//companyind_text 互联网/电子商务
                .setAdid(obj.getString("adid"));//adid
        return data;
    }
}
