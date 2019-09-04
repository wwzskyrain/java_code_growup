package erik.spring.boot.xmly;

import com.ximalaya.xchat.msg.v2.model.MsgAttr;
import com.ximalaya.xchat.msg.v2.model.RTFMessage;
import com.ximalaya.xchat.msg.v2.model.TextMessage;
import com.ximalaya.xchat.msg.v2.model.XChatMsg;
import com.ximalaya.xchat.msg.v2.service.impl.ThriftRemoteSyncXChatSendMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date 2019-08-24
 * @Created by erik
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    public static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static final String DEFAULT_BIZ_CODE = "groupon";

    public final static Long FROM_USER_ID_SYSTEM_NOTIFY = 1L;

    @Autowired
    private ThriftRemoteSyncXChatSendMsgService xChatSendMsgService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        sendAppMessage();
    }

    private void sendAppMessage() {
        String messageContent = "【拼团进度通知】\n" +
                "恭喜亲爱的，您参与的 XXX 拼团活动，已拼团成功，您可在「 APP－账号－我的已购」中查看收听。\n" +
                "如您有任何疑问或需要帮助，可在「喜马拉雅 APP－账号－我的客服」中咨询在线客服，或拨打客服电话 400-838-5616。";

        TextMessage textMessage = new TextMessage();
        textMessage.setMsgContent(messageContent);
        RTFMessage rtfMessage = new RTFMessage();

        MsgAttr msgAttr = new MsgAttr();
        msgAttr.setTextMsg(textMessage);
        msgAttr.setRtfMsg(rtfMessage);

        List<MsgAttr> msgAttrs = new ArrayList<>();
        msgAttrs.add(msgAttr);

        XChatMsg xChatMsg = new XChatMsg();
        xChatMsg.setBizCode(DEFAULT_BIZ_CODE);
        xChatMsg.setMsgAttrList(msgAttrs);

        List<Long> userIds = Arrays.asList(311319L);
        xChatSendMsgService.sendMsgByUserList(FROM_USER_ID_SYSTEM_NOTIFY, userIds, xChatMsg);

        logger.info("success send app message!");
    }


}
