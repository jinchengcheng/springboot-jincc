package com.shiro.config;

import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.mapper.ISqlInjector;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.baomidou.mybatisplus.spring.boot.starter.SpringBootVFS;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(MybatisProperties.class)
public class MybatisPlusConfig {
    private static Logger logger = LoggerFactory.getLogger(MybatisPlusConfig.class);
    /*@Autowired
    private Environment environment;*/
    @Autowired
    private DataSource dataSource;
    @Autowired
    private MybatisProperties properties;
    @Autowired
    //private ResourceLoader resourceLoader = new DefaultResourceLoader(); 如果出现读取错误，打开此注释，并注释下面
    private ResourceLoader resourceLoader;
    @Autowired(required = false)
    private Interceptor[] interceptors;
    @Autowired(required = false)
    private DatabaseIdProvider databaseIdProvider;

    /**
     * @Description : mybatis-plus SQL执行效率插件【生产环境可以关闭】
     * ---------------------------------
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }
    /**
     * @Description : mybatis-plus分页插件
     * ---------------------------------
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }


    //    mybatisPlus全局配置
    @Bean(name = "basisGlobalConfig")
    public GlobalConfiguration globalConfig(
            @Value("${mybatis.globalConfig.idType}") Integer idType, //主键类型  0:"数据库ID自增", 1:"用户输入ID",2:"全局唯一ID (数字类型唯一ID)", 3:"全局唯一ID UUID";
            @Value("${mybatis.globalConfig.fieldStrategy}") Integer fieldStrategy, //字段策略 0:"忽略判断",1:"非 NULL 判断"),2:"非空判断"
            @Value("${mybatis.globalConfig.dbColumnUnderline}") Boolean dbColumnUnderline, //驼峰下划线转换
            @Value("${mybatis.globalConfig.isRefresh}") Boolean isRefresh, //刷新mapper 调试神器
            @Value("${mybatis.globalConfig.isCapitalMode}") Boolean isCapitalMode, //数据库大写下划线转换
            @Value("${mybatis.globalConfig.keyGenerator}") String keyGenerator,//Sequence序列接口实现类配置
            @Value("${mybatis.globalConfig.logicDeleteValue}") String logicDeleteValue, //逻辑删除配置
            @Value("${mybatis.globalConfig.logicNotDeleteValue}") String logicNotDeleteValue, //逻辑删除配置
            @Value("${mybatis.globalConfig.sqlInjector}")String sqlInjector,//逻辑删除配置
            @Value("${mybatis.globalConfig.metaObjectHandler}")String metaObjectHandler//自定义填充策略接口实现
            ) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
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

        if(!BlankUtil.isBlank(sqlInjector)) {
            globalConfig.setSqlInjector((ISqlInjector)Class.forName(sqlInjector).newInstance());//逻辑删除配置
        }
        if ( !BlankUtil.isBlank(logicDeleteValue)) {
            //        globalConfig.setLogicDeleteValue(logicDeleteValue);  //逻辑删除配置
        }
        if ( !BlankUtil.isBlank(logicNotDeleteValue)) {
            //        globalConfig.setLogicNotDeleteValue(logicNotDeleteValue);  //逻辑删除配置
        }
        if(!BlankUtil.isBlank(keyGenerator)) {
            //globalConfig.setKeyGenerator((IKeyGenerator)Class.forName(keyGenerator).newInstance());//Sequence序列接口实现类配置
        }
        if(!BlankUtil.isBlank(metaObjectHandler)) {
            //globalConfig.setMetaObjectHandler((MetaObjectHandler)Class.forName(metaObjectHandler).newInstance());//自定义填充策略接口实现
        }
        return globalConfig;
    }

    /**
     * 这里全部使用mybatis-autoconfigure 已经自动加载的资源。不手动指定
     * 配置文件和mybatis-boot的配置文件同步
     * @return
     */
    @Bean
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean(@Qualifier(value = "basisGlobalConfig")GlobalConfiguration globalConfig) {
        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
        mybatisPlus.setDataSource(dataSource);
        mybatisPlus.setVfs(SpringBootVFS.class);
        if (StringUtils.hasText(this.properties.getConfigLocation())) {
            mybatisPlus.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
        }
        mybatisPlus.setConfiguration(properties.getConfiguration());
        if (!ObjectUtils.isEmpty(this.interceptors)) {
            mybatisPlus.setPlugins(this.interceptors);
        }

        /*// MP 全局配置，更多内容进入类看注释
        GlobalConfiguration globalConfig = new GlobalConfiguration();
        globalConfig.setDbType(DBType.MYSQL.name());
        // ID 策略 AUTO->`0`("数据库ID自增") INPUT->`1`(用户输入ID") ID_WORKER->`2`("全局唯一ID") UUID->`3`("全局唯一ID")
        globalConfig.setIdType(2);
        globalConfig.setDbColumnUnderline(true);
//        globalConfig.setFieldStrategy();*/
        mybatisPlus.setGlobalConfig(globalConfig);
        MybatisConfiguration mc = new MybatisConfiguration();
        mc.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        //mybatisPlus.setConfiguration(mc);
        if (this.databaseIdProvider != null) {
            mybatisPlus.setDatabaseIdProvider(this.databaseIdProvider);
        }
        if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
            mybatisPlus.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
        }
        if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
            mybatisPlus.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
        }
        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
            mybatisPlus.setMapperLocations(this.properties.resolveMapperLocations());
        }
        return mybatisPlus;
    }
}
