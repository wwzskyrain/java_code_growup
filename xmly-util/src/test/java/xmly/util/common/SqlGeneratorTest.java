package xmly.util.common;

import org.junit.Test;

public class SqlGeneratorTest {

    @Test
    public void test_sql_generate(){

        SqlGenerator sqlGenerator= new SqlGenerator();

        String createTableSql = "CREATE TABLE `UCP_USER_COUPON` (\n" +
                "  `ID` bigint(20) NOT NULL COMMENT '持有券ID',\n" +
                "  `COUPON_ID` bigint(20) NOT NULL COMMENT '优惠券ID',\n" +
                "  `USER_ID` bigint(20) NOT NULL COMMENT '用户ID',\n" +
                "  `DOMAIN` tinyint(4) NOT NULL DEFAULT '1' COMMENT '用户领域',\n" +
                "  `STATUS_ID` tinyint(4) NOT NULL DEFAULT '1' COMMENT '持有券状态',\n" +
                "  `TRADE_ORDER_NO` varchar(40) DEFAULT NULL COMMENT '业务订单号',\n" +
                "  `COMMENTS` varchar(200) DEFAULT NULL COMMENT '备注',\n" +
                "  `START_TIME` datetime NOT NULL COMMENT '有效期起始时间',\n" +
                "  `END_TIME` datetime NOT NULL COMMENT '有效期截止时间',\n" +
                "  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
                "  `LAST_UPDATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近跟新时间',\n" +
                "  `VERSION` int(11) DEFAULT '1' COMMENT '版本',\n" +
                "  PRIMARY KEY (`ID`),\n" +
                "  UNIQUE KEY `UK_TRADE_ORDER_NO` (`TRADE_ORDER_NO`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='持有券';";

        sqlGenerator.generateInsertSql(createTableSql,
                "UCP_USER_COUPON",
                0,
                999);

    }

}
