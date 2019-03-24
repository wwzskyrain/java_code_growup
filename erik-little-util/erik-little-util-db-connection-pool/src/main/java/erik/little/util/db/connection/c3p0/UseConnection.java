package erik.little.util.db.connection.c3p0;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author erik.wang
 * @date 2019/03/24
 **/
public class UseConnection implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(UseConnection.class);


    Connection connection;

    public UseConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        logger.info("start use connection:{}", connection);
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("end use and close connection:{}", connection);
    }
}
