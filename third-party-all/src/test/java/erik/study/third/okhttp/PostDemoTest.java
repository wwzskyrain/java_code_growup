package erik.study.third.okhttp;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author erik.wang
 * @date 2020-03-28 10:08
 */
public class PostDemoTest {
    private static final Logger logger = LoggerFactory.getLogger(PostDemoTest.class);
    private static final String HEADER_AUTHORIZATION = "Authorization";
    public static final MediaType MEDIA_TYPE_JSON
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    @Test
    public void test_post_to_fetch_message_from_queue() {
        String json = "{\"vhost\": \"/\",\"truncate\": \"300\",\"name\": \"xmkp.edu.business.monitor\",\"count\": \"3\",\"encoding\": \"auto\",\"ackmode\": \"ack_requeue_true\",\"requeue\":false}";
        String url = "http://localhost:15672/api/queues/%2F/xmkp.edu.business.monitor/get";
        RequestBody body = RequestBody.create(json, MEDIA_TYPE_JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        String responseBody = "";
        try (Response response = client.newCall(request).execute()) {
            responseBody = response.body().string();
        } catch (Exception e) {
            logger.info("error");
            Assert.fail();
        }
        Assert.assertEquals("", responseBody);
    }

    @Test
    public void test_() {
        client = new OkHttpClient.Builder()
                .authenticator((@Nullable Route route, @NotNull Response response) -> {
                    if (response.request().header(HEADER_AUTHORIZATION) != null) {
                        logger.info("has authorization");
                        return null;
                    }
                    logger.info("Authorization for response: " + response);
                    logger.info("Challenges:" + response.challenges());
                    String credential = Credentials.basic("guest", "guest");

                    return response.request().newBuilder()
                            .header(HEADER_AUTHORIZATION, credential)
                            .build();

                }).build();
        String json = "{\"vhost\": \"/\",\"truncate\": \"300\",\"name\": \"xmkp.edu.business.monitor\",\"count\": \"3\",\"encoding\": \"auto\",\"ackmode\": \"ack_requeue_true\",\"requeue\":true}";
        String url = "http://localhost:15672/api/queues/%2F/xmkp.edu.business.monitor/get";
        RequestBody body = RequestBody.create(json, MEDIA_TYPE_JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();


        String responseBody = null;
        try (Response response = client.newCall(request).execute()) {
            Assert.assertEquals(200, response.code());
            responseBody = response.body().string();
        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("responseBody:{}", JSON.toJSONString(responseBody));
        Assert.assertNotEquals("", responseBody);

    }


}
