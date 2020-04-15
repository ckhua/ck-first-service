package com.example.demo.config;

import org.springframework.stereotype.Component;

/**
 * @author chenkh
 * 阿里云Polardb基础配置
 */
@Component
//@RefreshScope
//@MapperScan(basePackages = "com.cimen.ways.mall.order.polardbDal", sqlSessionTemplateRef = "polardbSqlSessionTemplate")
public class PolardbConfig {

//    @Value("${polardb.jdbc.url}")
//    private String url;
//
//    @Value("${polardb.jdbc.username}")
//    private String userName;
//
//    @Value("${polardb.jdbc.password}")
//    private String password;
//
//    @Value("${polardb.mybatis.mapper.location}")
//    private String mapperLocation;
//
//    @Value("${mybatis.config.location}")
//    private String configLocation;
//
//    @Bean(name = "polardbDataSource")
//    public DataSource createPolardbDataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl(this.url);
//        dataSource.setUsername(this.userName);
//        dataSource.setPassword(this.password);
//        return dataSource;
//    }
//
//    @Bean(name = "polardbSqlSessionFactory")
//    public SqlSessionFactory createPolardbSqlSessionFactory(@Qualifier("polardbDataSource") DataSource polardbDataSource) throws Exception {
//        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
//        sessionFactoryBean.setDataSource(polardbDataSource);
//
//        // 配置mapper的扫描，找到所有的mapper.xml映射文件
//        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(mapperLocation);
//        sessionFactoryBean.setMapperLocations(resources);
//
//        // 加载全局的配置文件
//        sessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));
//        return sessionFactoryBean.getObject();
//    }
//
//
//    @Bean(name = "polardbSqlSessionTemplate")
//    public SqlSessionTemplate createPolardbSqlSessionTemplate(@Qualifier("polardbSqlSessionFactory") SqlSessionFactory polardbSqlSessionFactory) {
//        return new SqlSessionTemplate(polardbSqlSessionFactory);
//    }
//
//
//    @Bean(name = "polardbDataSourceTransactionManager")
//    public DataSourceTransactionManager polardbDataSourceTransactionManager(@Qualifier("polardbDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }

}
