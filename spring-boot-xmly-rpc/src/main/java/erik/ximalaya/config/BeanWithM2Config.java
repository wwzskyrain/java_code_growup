package erik.ximalaya.config;

import com.ximalaya.ad.coupon.rpc.api.CouponActivityRpcThriftService;
import com.ximalaya.business.product.query.api.ProductItemQueryService;
import com.ximalaya.business.product.query.thrift.api.ThriftProductItemQueryService;
import com.ximalaya.business.product.query.thrift.client.ProductItemQueryServiceClient;
import com.ximalaya.business.trade.command.api.TradeCommandService;
import com.ximalaya.business.trade.command.thrift.api.ThriftTradeCommandService;
import com.ximalaya.business.trade.command.thrift.client.TradeCommandServiceClient;
import com.ximalaya.business.trade.query.api.TradeQueryService;
import com.ximalaya.business.trade.query.thrift.api.ThriftTradeQueryService;
import com.ximalaya.business.trade.query.thrift.client.TradeQueryServiceClient;
import com.ximalaya.mainstay.rpc.route.RoutingType;
import com.ximalaya.mainstay.spring.config.ClientConfig;
import com.ximalaya.mainstay.spring.config.ConnectionPoolConfig;
import com.ximalaya.mainstay.spring.config.MainstayConfig;
import com.ximalaya.mainstay.spring.thrift.MainstayClient;
import com.ximalaya.xima.accounting.account.query.api.SubAccountQueryService;
import com.ximalaya.xima.accounting.account.query.thrift.api.ThriftSubAccountQueryService;
import com.ximalaya.xima.accounting.account.query.thrift.client.ThriftSubAccountQueryServiceClient;
import com.ximalaya.xima.accounting.account.query.thrift.translators.SubAccountViewDtoTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


@Configuration
@PropertySource("classpath:mainstay.properties")
@ComponentScan(basePackages = "erik.ximalaya.proxy")
public class BeanWithM2Config {

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

    @Bean(name = "adCouponRpcServiceClientConfig")
    public ClientConfig clientConfig() {
        ClientConfig clientConfig = new ClientConfig();

        clientConfig.setGroup(environment.getProperty("thrift.group.adcouponrpc"));
        clientConfig.setRoutingType(RoutingType.valueOf(environment.getProperty("thrift.routingType.adcouponrpc")));

        return clientConfig;
    }

    @Bean(name = "adCouponMainstayClient")
    public MainstayClient adCouponMainstayClient(MainstayConfig mainstayConfig,
                                                 ConnectionPoolConfig connectionPoolConfig,
                                                 @Qualifier("adCouponRpcServiceClientConfig") ClientConfig clientConfig) {

        MainstayClient mainstayClient = new MainstayClient();
        mainstayClient.setMainstayConfig(mainstayConfig);
        mainstayClient.setPoolConfig(connectionPoolConfig);
        mainstayClient.setClientConfig(clientConfig);
        mainstayClient.setIfaceClass(CouponActivityRpcThriftService.Iface.class);

        return mainstayClient;
    }

//  trade command service

    @Bean(name = "tradeServiceClientConfig")
    public ClientConfig tradeServiceClientConfig() {

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setGroup(environment.getProperty("business.trade.service.group"));
        clientConfig.setRoutingType(RoutingType.valueOf(environment.getProperty("business.trade.service.routingType")));
        clientConfig.setMultiplex(true);
        return clientConfig;
    }

    @Bean(name = "tradeCommandServiceIFace")
    public MainstayClient tradeCommandServiceMainstayClient(MainstayConfig mainstayConfig,
                                                            ConnectionPoolConfig connectionPoolConfig,
                                                            @Qualifier("tradeServiceClientConfig") ClientConfig clientConfig) {

        MainstayClient mainstayClient = new MainstayClient();
        mainstayClient.setMainstayConfig(mainstayConfig);
        mainstayClient.setPoolConfig(connectionPoolConfig);
        mainstayClient.setClientConfig(clientConfig);
        mainstayClient.setIfaceClass(ThriftTradeCommandService.Iface.class);
        return mainstayClient;
    }

    @Bean
    public TradeCommandService tradeCommandService() {
        return new TradeCommandServiceClient();
    }

    @Bean(name = "tradeQueryServiceIFace")
    public MainstayClient tradeQueryServiceMainstayClient(MainstayConfig mainstayConfig,
                                                          ConnectionPoolConfig connectionPoolConfig,
                                                          @Qualifier("tradeServiceClientConfig") ClientConfig clientConfig) {

        MainstayClient mainstayClient = new MainstayClient();
        mainstayClient.setMainstayConfig(mainstayConfig);
        mainstayClient.setPoolConfig(connectionPoolConfig);
        mainstayClient.setClientConfig(clientConfig);
        mainstayClient.setIfaceClass(ThriftTradeQueryService.Iface.class);
        return mainstayClient;

    }

    //  很有意思，TradeQueryServiceClient中的Iface会被自动注入-byType
    @Bean
    public TradeQueryService tradeQueryService() {
        return new TradeQueryServiceClient();
    }


    @Bean(name = "subAccountingQueryServiceClientConfig")
    public ClientConfig subAccountingQueryServiceClientConfig() {

        ClientConfig clientConfig = new ClientConfig();

        clientConfig.setGroup(environment.getProperty("xima.account.group.name"));
        clientConfig.setRoutingType(RoutingType.valueOf(environment.getProperty("xima.account.routing.type")));
        clientConfig.setMultiplex(true);

        return clientConfig;
    }

    @Bean(name = "subAccountingQueryServiceMainstayClient")
    public MainstayClient subAccountingQueryServiceMainstayClient(MainstayConfig mainstayConfig,
                                                                  ConnectionPoolConfig connectionPoolConfig,
                                                                  @Qualifier("subAccountingQueryServiceClientConfig") ClientConfig clientConfig) {

        MainstayClient mainstayClient = new MainstayClient();
        mainstayClient.setMainstayConfig(mainstayConfig);
        mainstayClient.setPoolConfig(connectionPoolConfig);
        mainstayClient.setClientConfig(clientConfig);
        mainstayClient.setIfaceClass(ThriftSubAccountQueryService.Iface.class);

        return mainstayClient;
    }

    @Bean
    public SubAccountQueryService getSubAccountQueryServiceBean() {
        return new ThriftSubAccountQueryServiceClient();
    }

    @Bean
    public SubAccountViewDtoTranslator subAccountViewDtoTranslator() {
        return new SubAccountViewDtoTranslator();
    }


}
