package erik.study.spring.xmly.m3.appmessage;

import com.ximalaya.xchat.msg.v2.model.*;
import com.ximalaya.xchat.msg.v2.service.impl.ThriftRemoteSyncXChatSendMsgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date 2019-08-27
 * @Created by erik
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appcontext-service-thrift.xml")
public class AppMessageSenderTest {

    private final static Logger logger = LoggerFactory.getLogger(AppMessageSenderTest.class);

    public static final String DEFAULT_BIZ_CODE = "groupon";

    public final static Long FROM_USER_ID_SYSTEM_NOTIFY = 1L;

    @Autowired
    private ThriftRemoteSyncXChatSendMsgService xChatSendMsgService;

    public final static String GROUPON_CONFIRMED = "【拼团进度通知】\n 恭喜亲爱的，您参与的 %s 拼团活动，已拼团成功，您可在<a href=\"iting://open?msg_type=41&play_first=true\">「 APP－账号－我的已购」</a>中查看收听。\n 如您有任何疑问或需要帮助，可在「喜马拉雅 APP－账号－我的客服」中咨询在线客服，或拨打客服电话 400-838-5616。";
    public final static String GROUPON_CANCELED = "【拼团进度通知】亲爱的，很遗憾的通知您，您参与的 %s 拼团活动，拼团失败了，系统将在2-5个工作日按照原路径退款。如您有任何疑问或需要帮助，可在「 喜马拉雅APP－账号－我的客服」中咨询在线客服，或拨打客服电话 400-838-5616。";

    @Test
    public void sendAppMessage() {

        TextMessage textMessage = new TextMessage();
        textMessage.setMsgContent(GROUPON_CANCELED);

        MsgAttr msgAttr = new MsgAttr();
        msgAttr.setMsgType(XChatMsgType.MSG_TYPE_TEXT.getValue());
        msgAttr.setTextMsg(textMessage);

        List<MsgAttr> msgAttrs = new ArrayList<>();
        msgAttrs.add(msgAttr);

        XChatMsg xChatMsg = new XChatMsg();
        xChatMsg.setBizCode(DEFAULT_BIZ_CODE);
        xChatMsg.setMsgAttrList(msgAttrs);

        List<Long> userIds = Arrays.asList(311319L, 204717L);
        xChatSendMsgService.sendMsgByUserList(FROM_USER_ID_SYSTEM_NOTIFY, userIds, xChatMsg);

        logger.info("success send app message!");
    }




}
