package com.shiro.config_ext_bak;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by Administrator on 2018/1/15.
 * 理念：
 * 1.读取配置中的数据配置
 * 2.如果是jdbc 那么根据注入的dataSource明注入调用即可，如果是jpa或其他的需要制定他们所代理的哪些实体。
 */
//@Configuration
//@MapperScan(basePackages = "com.example.biz.module1.mapper",sqlSessionTemplateRef = "SqlSessionTemplateOne")
public class DataSourceOneConfig {

    @ConfigurationProperties(prefix = "spring.dataSource.druid.bussDatasource")
    @Bean(name = "bussDatasource")
    @Primary
    public DataSource bussDataSource() throws SQLException {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "bussSessionFactory")
    @Primary
    public SqlSessionFactory sqlbussSessionFactory(@Qualifier(value = "bussDatasource") DataSource dataSource,
                                                PaginationInterceptor paginationInterceptor,
                                                @Qualifier(value = "globalConfiguration1") GlobalConfiguration globalConfiguration) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper1/*.xml"));
        Interceptor[] interceptors = new Interceptor[]{paginationInterceptor};
        bean.setPlugins(interceptors);
        bean.setGlobalConfig(globalConfiguration);
        return bean.getObject();
    }

    @ConfigurationProperties(prefix = "globalConfig1")
    @Bean(name = "globalConfiguration1")
    public GlobalConfiguration globalConfiguration1() {
        return new GlobalConfiguration();
    }


    @Bean(name = "transactionManager1")
    @Primary
    public DataSourceTransactionManager dataSourceTransactionManager1(@Qualifier("bussDatasource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "SqlSessionTemplateOne")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("bussSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
