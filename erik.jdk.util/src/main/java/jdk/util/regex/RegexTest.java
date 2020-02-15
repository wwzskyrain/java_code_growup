package jdk.util.regex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author erik.wang
 * @Date 2019-11-06
 */
public class RegexTest {

    private static final Logger logger = LoggerFactory.getLogger(RegexTest.class);

    @Test
    public void test_cas() {
        String pattern = ".*healthcheck$";
        String uri = "http://ops.uat.ximalaya.com/business-promotion-admin-web/healthcheck";
        Assertions.assertTrue(uri.matches(pattern));
        Assertions.assertTrue(uri.matches(".*healthcheck"));

        Assertions.assertFalse("http://healthcheck/end".matches(".*healthcheck"));
    }

    @Test
    public void test_regex() {
        String pattern = "^[a-zA-Z_]$";
        Assertions.assertTrue("a".matches(pattern));
        Assertions.assertTrue(!"aa".matches(pattern));
    }

    @Test
    public void test_repeat() {
        String pattern = "^a{2,4}$";
        Assertions.assertTrue("aa".matches(pattern));
        Assertions.assertTrue("aaa".matches(pattern));
        Assertions.assertTrue("aaaa".matches(pattern));
        Assertions.assertTrue(!"aaaaa".matches(pattern));
        Assertions.assertTrue(!"aaab".matches(pattern));
    }

    @Test
    public void test_end_string() {
        String pattern = "bucket$";
        Assertions.assertTrue("Who kept all of this cash in a bucket".matches(pattern));
        Assertions.assertTrue(!"buckets".matches(pattern));
    }

    /**
     * java的regex有点挫，比如以"word"结尾的pattern必须这样写：.*word$
     */
    @Test
    public void test_file_name_with_suffix() {
        String pattern = ".*\\.(jpg|png|tif)$";
        Assertions.assertTrue("foo.jpg".matches(pattern));
        Assertions.assertFalse("bar.txt".matches(pattern));
        Assertions.assertTrue("more.tif".matches(pattern));
    }


    @Test
    public void test_abc() {
        String[] patterns = {"abc+", "(abc)+", "(abc){2,}"};
        String target = "abcabcabcdefabc";

        for (String pattern : patterns) {
            Pattern p = Pattern.compile(pattern);
            Matcher matcher = p.matcher(target);
            logger.info("current pattern express:{}", pattern);
//            if (matcher.matches()) {
//                logger.info("match pattern:{} to target:{} success", pattern, target);
//            }
            while (matcher.find()) {
                logger.info("find {} at positions {}-{}", matcher.group(), matcher.start(), matcher.end() - 1);
            }
        }
    }
}
