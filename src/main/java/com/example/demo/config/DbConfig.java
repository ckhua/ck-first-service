package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Lists;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.ArrayList;

import static java.lang.Boolean.TRUE;

/**
 * @Description db配置
 * @Date 2020/4/14 11:28
 * @Author chen kang hua
 * @Version 1.0
 **/
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.example.demo.dal", sqlSessionTemplateRef = "ckhSqlSessionTemplate")
public class DbConfig {

    //@Value("${ways.jdbc.url}")
    private String url = "jdbc:mysql://47.94.13.132:33306/study?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true";

    //@Value("${jdbc.username}")
    private String userName = "root";

    //@Value("${jdbc.password}")
    private String password = "123456";

   // @Value("${druid.initialSize}")
    private int initialSize = 1;

    //@Value("${druid.logAbandoned}")
    private boolean logAbandoned = TRUE;

    //@Value("${druid.maxActive}")
    private int maxActive = 50;

    //@Value("${druid.maxWait}")
    private int maxWait = 60000;

    //@Value("${druid.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis = 300000;

    //@Value("${druid.minIdle}")
    private int minIdle = 3;

    //@Value("${druid.removeAbandoned}")
    private boolean removeAbandoned = TRUE;

    //@Value("${druid.removeAbandonedTimeout}")
    private int removeAbandonedTimeout = 1800;

    //@Value("${druid.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis = 60000;

    //@Value("${mybatis.mapper.location}")
    private String mapperLocation = "classpath:mybatis/mapper/**/*.xml";

    //@Value("${mybatis.config.location}")
    private String configLocation = "classpath:mybatis/mybatis-config.xml";

    @Value("${max.file.size:10}")
    private Long maxFileSize;

    @Value("${max.request.size:50}")
    private Long maxRequestSize;

    @Bean(value = "ckhDataSource")
    public DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        //移除jdbc url中的amp;否则在springboot2.0+mysql8.0启动报错
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.userName);
        dataSource.setPassword(this.password);
        dataSource.setInitialSize(this.initialSize);
        dataSource.setLogAbandoned(this.logAbandoned);
        dataSource.setMinIdle(this.minIdle);
        dataSource.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
        dataSource.setMaxActive(this.maxActive);
        dataSource.setMaxWait(this.maxWait);
        dataSource.setRemoveAbandoned(this.removeAbandoned);
        dataSource.setRemoveAbandonedTimeout(this.removeAbandonedTimeout);
        dataSource.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
        ArrayList<String> initSql = Lists.newArrayList("set names utf8mb4;");
        dataSource.setConnectionInitSqls(initSql);
        return dataSource;
    }

    @Bean(value = "ckhSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier(value = "ckhDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);

        // 配置mapper的扫描，找到所有的mapper.xml映射文件
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(this.mapperLocation);
        sessionFactoryBean.setMapperLocations(resources);

        // 加载全局的配置文件
        sessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource(this.configLocation));
        return sessionFactoryBean.getObject();
    }

    @Bean(value = "ckhSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier(value = "ckhSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(value = "ckhDataSourceTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
