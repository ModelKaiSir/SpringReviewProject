package service;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class JdbcUserService {

    class DataSourceConnectionManager implements AutoCloseable {

        private DataSource dataSource;
        private boolean transactional;

        private Optional<Connection> connection = Optional.empty();

        public DataSourceConnectionManager(DataSource dataSource) {

            this.dataSource = dataSource;
        }

        public DataSourceConnectionManager(DataSource dataSource, boolean transactional) {

            this(dataSource);
            this.transactional = transactional;
        }

        public Connection getConnection() {

            connection = Optional.of(DataSourceUtils.getConnection(dataSource));
            return connection.get();
        }

        @Override
        public void close() throws Exception {

            if (!transactional && connection.isPresent() && !connection.get().isClosed()) {

                System.out.println("need auto close");
                DataSourceUtils.releaseConnection(connection.get(), dataSource);
            } else {

                System.out.println("dont need auto close");
            }
        }
    }

    @Autowired
    private JdbcTemplate template;

    @Transactional
    public void logOn(String id) {

        try  {

            Connection c = template.getDataSource().getConnection();

            template.update("UPDATE T_USER SET last_visit=? WHERE user_id =?", new Date(), id);
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void syncLogOn(JdbcUserService service, String id) {

        new Thread(() -> {

            service.logOn(id);
        }).start();
    }

    public static void reportConnection(BasicDataSource dataSource) {

        System.out.printf("连接数 %s : %s%n", dataSource.getNumActive(), dataSource.getNumIdle());
    }

    public static void main(String[] args) throws Exception {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-datasource.xml");
        JdbcUserService service = ctx.getBean("jdbcUserService", JdbcUserService.class);
        BasicDataSource dataSource = ctx.getBean("dataSource", BasicDataSource.class);

        reportConnection(dataSource);

        syncLogOn(service, "admin");
        TimeUnit.MILLISECONDS.sleep(500);
        reportConnection(dataSource);

        TimeUnit.SECONDS.sleep(2);
        reportConnection(dataSource);

        syncLogOn(service, "admin");
        TimeUnit.MILLISECONDS.sleep(500);
        reportConnection(dataSource);
    }
}
