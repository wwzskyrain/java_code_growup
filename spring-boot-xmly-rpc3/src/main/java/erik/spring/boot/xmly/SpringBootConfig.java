package erik.spring.boot.xmly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;


@SpringBootConfiguration
@ImportResource("classpath:appcontext-service-thrift.xml")
public class SpringBootConfig {
}
