package erik.spring.boot.xmly.groupon;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Date 2019-08-27
 * @Created by erik
 */
@Configuration
@ImportResource(value = "classpath:appcontext-service-thrift.xml")
public class GrouponConfig {
}
