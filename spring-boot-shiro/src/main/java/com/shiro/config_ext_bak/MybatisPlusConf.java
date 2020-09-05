package com.shiro.config_ext_bak;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.shiro.config.BlankUtil;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

//@Configuration
public class MybatisPlusConf {
    private static Logger logger = LoggerFactory.getLogger(MybatisPlusConf.class);
    //    mybatisPlus全局配置
    @Bean(name = "globalConfig")
    public GlobalConfiguration globalConfig(
            @Value("${mybatisPlus.globalConfig.idType}") Integer idType, //主键类型  0:"数据库ID自增", 1:"用户输入ID",2:"全局唯一ID (数字类型唯一ID)", 3:"全局唯一ID UUID";
            @Value("${mybatisPlus.globalConfig.fieldStrategy}") Integer fieldStrategy, //字段策略 0:"忽略判断",1:"非 NULL 判断"),2:"非空判断"
            @Value("${mybatisPlus.globalConfig.dbColumnUnderline}") Boolean dbColumnUnderline, //驼峰下划线转换
            @Value("${mybatisPlus.globalConfig.isRefresh}") Boolean isRefresh, //刷新mapper 调试神器
            @Value("${mybatisPlus.globalConfig.isCapitalMode}") Boolean isCapitalMode, //数据库大写下划线转换
            @Value("${mybatisPlus.globalConfig.logicDeleteValue}") String logicDeleteValue, //逻辑删除配置
            @Value("${mybatisPlus.globalConfig.logicNotDeleteValue}") String logicNotDeleteValue //逻辑删除配置
    ) {
        logger.info("初始化GlobalConfiguration");
        GlobalConfiguration globalConfig = new GlobalConfiguration();
        if ( !BlankUtil.isBlank(idType)) {
            globalConfig.setIdType(idType);  //主键类型
        }
        if ( !BlankUtil.isBlank(fieldStrategy)) {
            //        globalConfig.setFieldStrategy(fieldStrategy); //字段策略
        }
        if ( !BlankUtil.isBlank(dbColumnUnderline)) {
            globalConfig.setDbColumnUnderline(dbColumnUnderline);  //驼峰下划线转换
        }
        if ( !BlankUtil.isBlank(isRefresh)) {
            //        globalConfig.setRefresh(isRefresh); //刷新mapper 调试神器
        }
        if ( !BlankUtil.isBlank(isCapitalMode)) {
            globalConfig.setCapitalMode(isCapitalMode); //数据库大写下划线转换
        }
        if ( !BlankUtil.isBlank(logicDeleteValue)) {
            //        globalConfig.setLogicDeleteValue(logicDeleteValue);  //逻辑删除配置
        }
        if ( !BlankUtil.isBlank(logicNotDeleteValue)) {
            //        globalConfig.setLogicNotDeleteValue(logicNotDeleteValue);  //逻辑删除配置
        }
        return globalConfig;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier(value = "globalConfig")GlobalConfiguration globalConfig,
                                               @Qualifier(value = "basisDataSource")DruidDataSource dataSource) throws Exception {
        logger.info("初始化SqlSessionFactory");
        String mapperLocations = "classpath:db-ason/sql/**/*.xml";
        String configLocation = "classpath:db-ason/mybatis/mybatis-sqlconfig.xml";
        String typeAliasesPackage = "com.ason.entity.**";
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource); //数据源
        sqlSessionFactory.setGlobalConfig(globalConfig); //全局配置
        Interceptor[] interceptor = {new PaginationInterceptor()};
        sqlSessionFactory.setPlugins(interceptor); //分页插件
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            //自动扫描Mapping.xml文件
            sqlSessionFactory.setMapperLocations(resolver.getResources(mapperLocations));
            sqlSessionFactory.setConfigLocation(resolver.getResource(configLocation));
            sqlSessionFactory.setTypeAliasesPackage(typeAliasesPackage);
            return sqlSessionFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    //    MyBatis 动态扫描
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        logger.info("初始化MapperScannerConfigurer");
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        String basePackage = "com.ason.db.mapper";
        mapperScannerConfigurer.setBasePackage(basePackage);
        return mapperScannerConfigurer;
    }

    //    配置事务管理
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier(value = "basisDataSource")DruidDataSource dataSource) {
        logger.info("初始化DataSourceTransactionManager");
        return new DataSourceTransactionManager(dataSource);
    }
}
