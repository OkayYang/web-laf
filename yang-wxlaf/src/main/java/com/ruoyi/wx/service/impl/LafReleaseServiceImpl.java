package com.ruoyi.wx.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.wx.domain.*;
import com.ruoyi.wx.mapper.LafApiTokenMapper;
import com.ruoyi.wx.mapper.LafPushMapper;
import com.ruoyi.wx.mapper.LafStudentMapper;
import com.ruoyi.wx.util.echart.Graph;
import com.ruoyi.wx.util.tencent.domain.TemplateData;
import com.ruoyi.wx.util.tencent.domain.WxMessVo;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ruoyi.wx.mapper.LafReleaseMapper;
import com.ruoyi.wx.service.ILafReleaseService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.web.client.RestTemplate;

/**
 * 帖子Service业务层处理
 * 
 * @author yang
 * @date 2021-10-08
 */
@Service
public class LafReleaseServiceImpl implements ILafReleaseService 
{
    @Autowired
    private LafReleaseMapper lafReleaseMapper;
    @Autowired
    private LafApiTokenMapper lafApiTokenMapper;
    @Autowired
    private LafStudentMapper lafStudentMapper;
    @Autowired
    private LafPushMapper lafPushMapper;
    //设置JSON时间格式
    private SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 查询帖子
     * 
     * @param relId 帖子主键
     * @return 帖子
     */
    @Override
    public LafRelease selectLafReleaseByRelId(Long relId)
    {
        return lafReleaseMapper.selectLafReleaseByRelId(relId);
    }

    /**
     * 查询帖子列表
     * 
     * @param lafRelease 帖子
     * @return 帖子
     */
    @Override
    public List<LafRelease> selectLafReleaseList(LafRelease lafRelease)
    {
        return lafReleaseMapper.selectLafReleaseList(lafRelease);
    }

    /**
     * 新增帖子
     * 
     * @param lafRelease 帖子
     * @return 结果
     */
    @Override
    public int insertLafRelease(LafRelease lafRelease)
    {
        lafRelease.setCreateTime(DateUtils.getNowDate());
        int result = lafReleaseMapper.insertLafRelease(lafRelease);
        if (result==1){
            new Thread(){
                @Override
                public void run(){
                    pushSubscribe(lafRelease);
                }
            }.start();
        }
        return result;
    }

    /**
     * 修改帖子
     * 
     * @param lafRelease 帖子
     * @return 结果
     */
    @Override
    public int updateLafRelease(LafRelease lafRelease)
    {
        int result = lafReleaseMapper.updateLafRelease(lafRelease);

        if (lafRelease.getRelClaimId()!=null&&result==1){
            pushClaim(lafRelease.getRelId(),lafRelease.getRelClaimId());
        }

        return result;


    }

    /**
     * 批量删除帖子
     * 
     * @param relIds 需要删除的帖子主键
     * @return 结果
     */
    @Override
    public int deleteLafReleaseByRelIds(String relIds)
    {
        return lafReleaseMapper.deleteLafReleaseByRelIds(Convert.toStrArray(relIds));
    }

    /**
     * 删除帖子信息
     * 
     * @param relId 帖子主键
     * @return 结果
     */
    @Override
    public int deleteLafReleaseByRelId(Long relId)
    {
        return lafReleaseMapper.deleteLafReleaseByRelId(relId);
    }

    /**
     * 统计信息量
     */
    public int informationCount(){
        return lafReleaseMapper.informationCount();
    }
    /**
     * 统计成功量
     */
    public int successCount(){
        return lafReleaseMapper.successCount();
    }

    /**
     * 扇形图数据
     */

    public List<Graph> fanChart(){
        return lafReleaseMapper.fanChart();
    }

    /**
     * 查询VIEW列表
     *
     * @param lafWxRelease VIEW
     * @return VIEW
     */
    @Override
    public List<LafWxRelease> selectLafWxReleaseList(LafWxRelease lafWxRelease)
    {
        return lafReleaseMapper.selectLafWxReleaseList(lafWxRelease);
    }
    /**
     * 查询VIEW
     *
     * @param relId VIEW主键
     * @return VIEW
     */
    @Override
    public LafWxRelease selectLafWxReleaseByRelId(Long relId)
    {
        return lafReleaseMapper.selectLafWxReleaseByRelId(relId);
    }

    /**
     * 更新浏览量
     * @param relId
     * @return
     */

    @Override
    public int updateBrowse(Long relId) {
        return lafReleaseMapper.updateBrowse(relId);
    }

    public String pushClaim(Long relId,Long claimId){
        LafRelease lafRelease = lafReleaseMapper.selectLafReleaseByRelId(relId);
        LafStudent claimStudent = lafStudentMapper.selectLafStudentByStuId(claimId);
        LafStudent relStudent = lafStudentMapper.selectLafStudentByStuId(lafRelease.getCreateId());


        LafApiToken lafApiToken = lafApiTokenMapper.selectLafApiTokenById(1L);
        String accessToken = lafApiToken.getToken();

        String openId = relStudent.getOpenid();
        String title = lafRelease.getRelTitle();
        String name = claimStudent.getStuNick();
        String phone = claimStudent.getStuQq();
        String time = myDateFormat.format(lafRelease.getRelTime());
        String content = "您发布的失物找到主人啦~~";


        //这里简单起见我们每次都获取最新的access_token（时间开发中，应该在access_token快过期时再重新获取）
        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token="+accessToken;

        //拼接推送的模版
        WxMessVo wxMssVo = new WxMessVo();
        wxMssVo.setTouser(openId);//用户的openid（要发送给那个用户，通常这里应该动态传进来的）
        wxMssVo.setTemplate_id("lhQ8vRjYydEDLDni5sWTUndbzrbtfvycNyP-SHMWMmM");//订阅消息模板id
        wxMssVo.setPage("/pages/index/index");

        Map<String, TemplateData> m = new HashMap<>(3);
        m.put("thing1",new TemplateData(content));
        m.put("thing4", new TemplateData(title));
        m.put("name2", new TemplateData(name));
        m.put("phone_number3",new TemplateData(phone));
        m.put("time5",new TemplateData(time));
        wxMssVo.setData(m);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(url, wxMssVo, String.class);
        System.out.println(responseEntity.getBody());

        return responseEntity.getBody();

    }

    public void pushSubscribe(LafRelease lafRelease)  {
        List<LafPush> lafPushList = lafPushMapper.selectLafPushList(new LafPush());
        for (LafPush lafPush:lafPushList
             ) {
            String desc = lafPush.getPushClue();
            String[] descList = desc.split(";");
            boolean flag = false;
            for (String item:descList
                 ) {
                if (lafRelease.getRelTitle()!=null&&lafRelease.getRelTitle().contains(item)){
                    flag=true;
                    break;
                }
                else if (lafRelease.getRelDesc()!=null&&lafRelease.getRelDesc().contains(item)){
                    flag = true;
                    break;
                }

            }
            if (flag){
                String phone = lafRelease.getRelContact();
                String location = lafRelease.getCreatePlace();
                String openId = lafStudentMapper.selectLafStudentByStuId(lafPush.getStuId()).getOpenid();
                String title = lafPush.getPushTitle();
                String time = myDateFormat.format(lafRelease.getCreateTime());
                LafApiToken lafApiToken = lafApiTokenMapper.selectLafApiTokenById(1L);
                String accessToken = lafApiToken.getToken();
                String content = "您订阅的失物可能被找到了哦~~~";
                //这里简单起见我们每次都获取最新的access_token（时间开发中，应该在access_token快过期时再重新获取）
                String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token="+accessToken;

                //拼接推送的模版
                WxMessVo wxMssVo = new WxMessVo();
                wxMssVo.setTouser(openId);//用户的openid（要发送给那个用户，通常这里应该动态传进来的）
                wxMssVo.setTemplate_id("vnDNGaWHPOnrg-VkHcflNo9fWwFxZNLsQph8utdgQ8Y");//订阅消息模板id
                wxMssVo.setPage("/pages/detail/index");

                Map<String, TemplateData> m = new HashMap<>(3);
                m.put("thing4",new TemplateData(title));
                m.put("thing1", new TemplateData(content));
                m.put("date6", new TemplateData(time));
                m.put("thing7",new TemplateData(phone));
                m.put("thing2",new TemplateData("点击查看详细"));
                wxMssVo.setData(m);
                RestTemplate restTemplate = new RestTemplate();

                ResponseEntity<String> responseEntity =
                        restTemplate.postForEntity(url, wxMssVo, String.class);
                System.out.println(responseEntity.getBody());

                try{
                    JSONObject object= new JSONObject(responseEntity.getBody());
                    String code = object.getString("errcode");
                    System.out.println(code);
                    if ("0".equals(code)){
                        String relId = lafPush.getRelId()+";"+lafRelease.getRelId();
                        lafPushMapper.updateCount(lafPush.getPushId(),relId);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        }


    }

}
