package com.wangyg.flink;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OperatorChainTest {

    /**
     * 将数据写入到Hbase中，使用Put方式
     */
    @Test
    public void test1(){
        List<List<String>> list = new ArrayList<>();
        for(int i=0; i<21398; i++){
            String json = "{\"TS\":[{\"ZTS\": \"734380\",\"KSJLS\": \"0\",\"JSJLS\": \"1\"}],  \"Field\":[{\"fieldCname\":\"组织机构代码\",\"fieldEname\":\"ORG_CODE\"},{\"fieldCname\":\"机构名称\",\"fieldEname\":\"ORG_NAME\"},{\"fieldCname\":\"机构地址\",\"fieldEname\":\"ORG_ADDRESS\"},{\"fieldCname\":\"机构类型\",\"fieldEname\":\"ORG_TYPE_CODE\"},{\"fieldCname\":\"机构类型名称\",\"fieldEname\":\"ORG_TYPE_CODE_NAME\"},{\"fieldCname\":\"法定代表人姓名\",\"fieldEname\":\"LEGAL_PERSON\"},{\"fieldCname\":\"注册号\",\"fieldEname\":\"REG_NO\"},{\"fieldCname\":\"成立日期\",\"fieldEname\":\"REG_DATE\"},{\"fieldCname\":\"联系电话\",\"fieldEname\":\"TEL_NO\"},{\"fieldCname\":\"状态\",\"fieldEname\":\"STATUS\"},{\"fieldCname\":\"状态名称\",\"fieldEname\":\"STATUS_NAME\"},{\"fieldCname\":\"经济类型\",\"fieldEname\":\"ECNOMIC_CODE\"},{\"fieldCname\":\"经济类型名称\",\"fieldEname\":\"ECNOMIC_CODE_NAME\"},{\"fieldCname\":\"行业类别\",\"fieldEname\":\"INDUSTRY_CODE\"},\t{\"fieldCname\":\"行业类别名称\",\"fieldEname\":\"INDUSTRY_CODE_NAME\"},{\"fieldCname\":\"行政区划\",\"fieldEname\":\"AREA_CODE\"},\t{\"fieldCname\":\"经营范围\",\"fieldEname\":\"BUSINESS_SCOPE\"},{\"fieldCname\":\"数据来源\",\"fieldEname\":\"DATA_SOURCE\"},{\"fieldCname\":\"数据来源名称\",\"fieldEname\":\"DATA_SOURCE_NAME\"},{\"fieldCname\":\"注册资金\",\"fieldEname\":\"REG_CAPITAL\"},{\"fieldCname\":\"币种\",\"fieldEname\":\"CURRENCY_TYPE\"},{\"fieldCname\":\"邮政编码\",\"fieldEname\":\"POSTCODE\"},{\"fieldCname\":\"变更日期\",\"fieldEname\":\"UPDATE_DATE\"},\t{\"fieldCname\":\"注吊销日期\",\"fieldEname\":\"REVOKE_DATE\"},\t{\"fieldCname\":\"计算机代码\",\"fieldEname\":\"JSJDM\"},{\"fieldCname\":\"税务登记证号\",\"fieldEname\":\"SWDJZH\"},{\"fieldCname\":\"开业登记日期\",\"fieldEname\":\"KYDJRQ\"},{\"fieldCname\":\"主管税务机关名称\",\"fieldEname\":\"SWJGZZJGDM_NAME\"},{\"fieldCname\":\"纳税人状态\",\"fieldEname\":\"NSRZT\"},{\"fieldCname\":\"纳税人状态名称\",\"fieldEname\":\"NSRZT_NAME\"},{\"fieldCname\":\"发证日期\",\"fieldEname\":\"FZRQ\"},{\"fieldCname\":\"有效日期\",\"fieldEname\":\"ZFRQ\"},{\"fieldCname\":\"地税注销日期\",\"fieldEname\":\"DSZXRQ\"},{\"fieldCname\":\"发证机构\",\"fieldEname\":\"FZJG\"},{\"fieldCname\":\"发证机构名称\",\"fieldEname\":\"FZJG_NAME\"},{\"fieldCname\":\"有效期至\",\"fieldEname\":\"JYQXZ\"},{\"fieldCname\":\"统一信用代码\",\"fieldEname\":\"UNI_SCID\"}],\"Date\":[{\"ORG_CODE\":\"085515862\",  \"ORG_NAME\":\"北京神州联东工程技术有限公司\",\"ORG_ADDRESS\":\"北京市海淀区东北旺村南1号楼7层7202室\",\"ORG_TYPE_CODE\":\"1\",\"ORG_TYPE_CODE_NAME\":\"企业法人\", \"LEGAL_PERSON\":\"李伟\",\"REG_NO\":\"110108016544996\", \"REG_DATE\":\"2013-12-09\",\"TEL_NO\":\"82580050\",\"STATUS\":\"0\",\"STATUS_NAME\":\"正常\", \"ECNOMIC_CODE\":\"\",\"ECNOMIC_CODE_NAME\":\"有限责任公司\", \"INDUSTRY_CODE\":\"\",\"INDUSTRY_CODE_NAME\":\"工程管理服务\", \"AREA_CODE\":\"110108\",\"BUSINESS_SCOPE\":\"工程技术咨询；计算机系统服务；\", \"DATA_SOURCE\":\"2\",\"DATA_SOURCE_NAME\":\"工商\", \"REG_CAPITAL\":\"50\",\"CURRENCY_TYPE\":\"156\", \"POSTCODE\":\"100094\",\"UPDATE_DATE\":\"2015-03-17\", \"REVOKE_DATE\":\"\",\"JSJDM\":\"10121101000002190600\", \"SWDJZH\":\"110108085515862\",\"KYDJRQ\":\"2013-12-13\",\"SWJGZZJGDM_NAME\":\"北京市海淀区地方税务局\",\"NSRZT\":\"03\",\"NSRZT_NAME\":\"正常\",\"FZRQ\":\"2015-03-17\",\"ZFRQ\":\"2019-03-16\",\"DSZXRQ\":\"\",\"FZJG\":\"110108000\",\"FZJG_NAME\":\"海淀分局\",\"JYQXZ\":\"2033-12-08\",\"UNI_SCID\":\"\" }]}";
            List<String> tmp = new ArrayList<>();
            tmp.add(json);
            list.add(tmp);
        }
        System.out.println(list.size());

    }
}
