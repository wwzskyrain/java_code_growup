package erik.little.util.db.connection.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author erik.wang
 * @date 2019/03/24
 **/
public class ConnectionTest {

    private static Logger logger = LoggerFactory.getLogger(ConnectionTest.class);

    //通过标识名来创建相应连接池
    ComboPooledDataSource dataSource = new ComboPooledDataSource("mysql");

    @Test
    public void test_connection(){
        try {
            Connection connection = dataSource.getConnection();

            String schema = connection.getSchema();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_max_activity() {

        for (int i = 0; i < 20; i++) {
            Connection connection = null;
            try {
                connection = dataSource.getConnection();
                logger.info("No {} : acquire connect:{}", i, connection);

                new Thread(new UseConnection(connection)).start();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




}
