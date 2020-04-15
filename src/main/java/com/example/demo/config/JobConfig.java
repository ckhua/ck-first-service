package com.example.demo.config;

/**
 * JobConfig
 *
 * @date 2019/10/28 10:25
 * @apiNote Job 配置类
 */
//@Configuration
//@EnableTransactionManagement
//@MapperScan(basePackages = {"com.cimen.ways.educe.dal.job"}, sqlSessionTemplateRef = "jobSqlSessionTemplate")
public class JobConfig {

//    @Value("${mybatis.config.location}")
//    private String configLocation;
//
//    @Autowired
//    private DataSourceProperties properties;
//
//    /**
//     * Job 自用数据源
//     */
//    @Primary
//    @Bean(value = "jobDataSource")
//    public DataSource jobDatasource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setName("dataSource-job");
//        dataSource.setUrl(this.properties.getUrl());
//        dataSource.setUsername(this.properties.getUsername());
//        dataSource.setPassword(this.properties.getPassword());
//        return dataSource;
//    }
//
//    /**
//     * 这里不要用异步 Launcher 不然无法获取到 Oss 路径回写到数据库
//     *
//     * @param jobRepository
//     * @return
//     * @throws Exception
//     */
//    @Primary
//    @Bean(value = "educeJobLauncher")
//    public JobLauncher getJobLauncher(@Qualifier(value = "educeJobRepository") JobRepository jobRepository) throws Exception {
//        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
//        jobLauncher.setJobRepository(jobRepository);
//        jobLauncher.setTaskExecutor(new SyncTaskExecutor());
//        jobLauncher.afterPropertiesSet();
//        return jobLauncher;
//    }
//
//    /**
//     * 创建作业库
//     *
//     * @return
//     * @throws Exception
//     */
//    @Primary
//    @Bean(value = "educeJobRepository")
//    public JobRepository jobRepository(
//            @Qualifier("jobDataSource") DataSource dataSource,
//            @Qualifier("jobDataSourceTransactionManager") DataSourceTransactionManager transactionManager) throws Exception {
//        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
//        factory.setDatabaseType("MYSQL");
//        factory.setDataSource(dataSource);
//        factory.setTransactionManager(transactionManager);
//        factory.setTablePrefix("EDUCE_");
//        factory.afterPropertiesSet();
//        return factory.getObject();
//    }
//
//    @Primary
//    @Bean(name = "jobSqlSessionFactory")
//    public SqlSessionFactory createSqlSessionFactory(@Qualifier("jobDataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
//        sessionFactoryBean.setDataSource(dataSource);
//
//        // 加载全局的配置文件
//        sessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource(this.configLocation));
//        return sessionFactoryBean.getObject();
//    }
//
//    @Primary
//    @Bean(name = "jobSqlSessionTemplate")
//    public SqlSessionTemplate createSqlSessionTemplate(@Qualifier("jobSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//
//    @Primary
//    @Bean(name = "jobDataSourceTransactionManager")
//    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("jobDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean
//    public JobBuilderFactory jobBuilderFactory(JobRepository jobRepository) {
//        return new JobBuilderFactory(jobRepository);
//    }
//
//    @Bean
//    public StepBuilderFactory stepBuilderFactory(JobRepository jobRepository, @Qualifier("jobDataSourceTransactionManager") DataSourceTransactionManager transactionManager) {
//        return new StepBuilderFactory(jobRepository, transactionManager);
//    }
}
