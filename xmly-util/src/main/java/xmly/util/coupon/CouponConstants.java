package xmly.util.coupon;

public class CouponConstants {

    public static String INSERT_DISCOUNT_COUPON_PATTERN = "INSERT INTO ads_coupon.coupon ( name, description, activity_id, generate_rule, type, using_rule, valid_date, create_time, update_time, status, seq, batch_no, is_plus_coupon) VALUES ( '{couponName}', null, {activityId}, '{\"generateType\":null,\"quantity\":0}', 'PAYED_SUBSCRIBE', '{\"usingLimit\":{\"usingLimitType\":\"UNLIMIT\",\"minLimitValue\":0},\"discount\":{\"discountType\":\"RATE\",\"couponValue\":null,\"plusRate\":{plusRate},\"broadCasterRate\":{broadCasterRate}},\"timeRanges\":null,\"amount\":0,\"recvTimes\":0,\"isShowOnAlbum\":0,\"weight\":0,\"manualWeight\":0,\"labelName\":null,\"usingScope\":\"APPOINTED\",\"albums\":[{albumIds}]}', '{\"type\":\"FIXED_TIME_PERIOD\",\"startTime\":{startTime},\"endTime\":{endTime},\"validDays\":0}', now(), now(), 'CREATED', 1, 0, 0);";
    public static String INSERT_VALUE_COUPON_PATTERN = "INSERT INTO ads_coupon.coupon ( name, description, activity_id, generate_rule, type, using_rule, valid_date, create_time, update_time, status, seq, batch_no, is_plus_coupon) VALUES ( '{couponName}', null, {activityId}, '{\"generateType\":null,\"quantity\":0}', 'PAYED_SUBSCRIBE', '{\"usingLimit\":{\"usingLimitType\":\"UNLIMIT\",\"minLimitValue\":3},\"discount\":{\"discountType\":\"VALUE\",\"couponValue\":{couponValue},\"plusRate\":null,\"broadCasterRate\":null},\"timeRanges\":null,\"amount\":0,\"recvTimes\":0,\"isShowOnAlbum\":0,\"weight\":0,\"manualWeight\":0,\"labelName\":null,\"usingScope\":\"APPOINTED\",\"albums\":[{albumIds}]}', '{\"type\":\"FIXED_TIME_PERIOD\",\"startTime\":{startTime},\"endTime\":{endTime},\"validDays\":0}', now(), now(), 'CREATED', 1, 0, 0);";

    public static final String KEY_NAME_COUPON_NAME = "couponName";
    public static final String KEY_NAME_ACTIVITY_ID = "activityId";
    public static final String KEY_NAME_ALBUM_IDS = "albumIds";
    public static final String KEY_NAME_START_TIME = "startTime";
    public static final String KEY_NAME_END_TIME = "endTime";
    public static final String KEY_NAME_PLUS_RATE = "plusRate";
    public static final String KEY_NAME_BROAD_CASTER_RATE = "broadCasterRate";
    public static final String KEY_NAME_COUPON_VALUE = "couponValue";


    public static final Integer KEY_COLUMN_NO_ALBUM_IDS = 0;
    public static final Integer KEY_COLUMN_NO_COUPON_NAME = 1;
    public static final Integer KEY_COLUMN_NO_START_TIME = 5;
    public static final Integer KEY_COLUMN_NO_END_TIME = 6;
    public static final Integer KEY_COLUMN_NO_PLUS_RATE = 7;
    public static final Integer KEY_COLUMN_NO_BROAD_CASTER_RATE = 8;


}
