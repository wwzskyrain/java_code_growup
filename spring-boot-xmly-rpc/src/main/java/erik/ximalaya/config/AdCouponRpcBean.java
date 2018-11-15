package erik.ximalaya.config;

//import com.ximalaya.mainstay.spring.config.MainstayConfig;

import com.ximalaya.ad.coupon.rpc.api.CouponActivityRpcThriftService;
import com.ximalaya.mainstay.rpc.route.RoutingType;
import com.ximalaya.mainstay.spring.config.ClientConfig;
import com.ximalaya.mainstay.spring.config.ConnectionPoolConfig;
import com.ximalaya.mainstay.spring.config.MainstayConfig;
import com.ximalaya.mainstay.spring.thrift.MainstayClient;
import erik.ximalaya.model.TestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:mainstay.properties")
public class AdCouponRpcBean {

    @Autowired
    Environment environment;

    @Bean
    public MainstayConfig getMainstayConfig() {
        MainstayConfig mainstayConfig = new MainstayConfig();
        mainstayConfig.setZkConnectString(environment.getProperty("zk.connection"));
        mainstayConfig.setZkConnectionTimeoutMills(environment.getProperty("zk.connect.timeout", Integer.class));
        mainstayConfig.setZkSessionTimeoutMills(environment.getProperty("zk.session.timeout", Integer.class));
        mainstayConfig.setWebConsoleUrl(environment.getProperty("zk.console.url"));

        return mainstayConfig;
    }

    @Bean
    public ConnectionPoolConfig getConnectPoolConfig() {

        ConnectionPoolConfig config = new ConnectionPoolConfig();

        config.setMaxTotal(environment.getProperty("thrift.service.pool.maxTotal", Integer.class));
        config.setMinIdle(environment.getProperty("thrift.service.pool.minIdle", Integer.class));
        config.setMaxIdle(environment.getProperty("thrift.service.pool.maxIdle", Integer.class));
        config.setMaxWaitMillis(environment.getProperty("thrift.service.pool.maxWaitMillis", Integer.class));
        config.setTestWhileIdle(environment.getProperty("thrift.service.pool.testWhileIdle", Boolean.class));
        config.setTimeBetweenEvictionRunsMillis(environment.getProperty("thrift.service.pool.timeBetweenEvictionRunsMillis", Integer.class));
        config.setNumTestsPerEvictionRun(environment.getProperty("thrift.service.pool.numTestsPerEvictionRun", Integer.class));
        config.setMinEvictableIdleTimeMillis(environment.getProperty("thrift.service.pool.minEvictableIdleTimeMillis", Integer.class));
        config.setSoftMinEvictableIdleTimeMillis(environment.getProperty("thrift.service.pool.softMinEvictableIdleTimeMillis", Integer.class));

        return config;
    }

    @Bean
    public ClientConfig clientConfig() {
        ClientConfig clientConfig = new ClientConfig();

        clientConfig.setGroup(environment.getProperty("thrift.group.adcouponrpc"));
        clientConfig.setRoutingType(RoutingType.valueOf(environment.getProperty("thrift.routingType.adcouponrpc")));

        return clientConfig;
    }

    @Bean(name = "adCouponMainstayClient")
    public MainstayClient adCouponMainstayClient(MainstayConfig mainstayConfig, ConnectionPoolConfig connectionPoolConfig, ClientConfig clientConfig) {

        MainstayClient mainstayClient = new MainstayClient();
        mainstayClient.setMainstayConfig(mainstayConfig);
        mainstayClient.setPoolConfig(connectionPoolConfig);
        mainstayClient.setClientConfig(clientConfig);
        mainstayClient.setIfaceClass(CouponActivityRpcThriftService.Iface.class);

        return mainstayClient;

    }


}
