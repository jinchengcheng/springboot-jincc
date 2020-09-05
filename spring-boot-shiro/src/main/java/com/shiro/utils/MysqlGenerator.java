package com.shiro.utils;

import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.PostgreSqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.SqlServerTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.*;

public class MysqlGenerator {
    private static String packageName="spring-boot-mybatis";    //文件路径
    private static String authorName="jincc";     //作者
    private static String table="sys_user";                  //table名字
    private static String prefix="sc_";                     //table前缀
    private static File file = new File(packageName);
    private static String path = file.getAbsolutePath();

    private static String dataSourceType = "mysql";
    private static String driverName = "com.mysql.jdbc.Driver";
    private static String userName = "root";
    private static String password = "password";
    private static String url = "jdbc:mysql://127.0.0.1:3306/springboot?characterEncoding=utf8";



    /**
     * 判断类型
     * @param type
     * @return DataSourceConfig对象
     */
    private static DataSourceConfig getDataSouceType(String type) {
        DataSourceConfig dataSourceConfig = null;
        if(StringUtils.equals(type, "mysql")) {
            dataSourceConfig = new DataSourceConfig()
                    .setDbType(DbType.MYSQL)// 数据库类型
                    .setTypeConvert(new MySqlTypeConvert() {
                        // 自定义数据库表字段类型转换【可选】
                        @Override
                        public DbColumnType processTypeConvert(String fieldType) {
                            System.out.println("转换类型：" + fieldType);
                            // if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
                            //    return DbColumnType.BOOLEAN;
                            // }
                            return super.processTypeConvert(fieldType);
                        }
                    });

        } else if(StringUtils.equals(type, "oracle")) {
            dataSourceConfig = new DataSourceConfig()
                    .setDbType(DbType.ORACLE)// 数据库类型
                    .setTypeConvert(new OracleTypeConvert() {
                        // 自定义数据库表字段类型转换【可选】
                        @Override
                        public DbColumnType processTypeConvert(String fieldType) {
                            System.out.println("转换类型：" + fieldType);
                            // if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
                            //    return DbColumnType.BOOLEAN;
                            // }
                            return super.processTypeConvert(fieldType);
                        }
                    });
        } else if(StringUtils.equals(type, "sql_server")) {
            dataSourceConfig = new DataSourceConfig()
                    .setDbType(DbType.SQL_SERVER)// 数据库类型
                    .setTypeConvert(new SqlServerTypeConvert() {
                        // 自定义数据库表字段类型转换【可选】
                        @Override
                        public DbColumnType processTypeConvert(String fieldType) {
                            System.out.println("转换类型：" + fieldType);
                            // if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
                            //    return DbColumnType.BOOLEAN;
                            // }
                            return super.processTypeConvert(fieldType);
                        }
                    });
        } else if(StringUtils.equals(type, "postgre_sql")) {
            dataSourceConfig = new DataSourceConfig()
                    .setDbType(DbType.POSTGRE_SQL)// 数据库类型
                    .setTypeConvert(new PostgreSqlTypeConvert() {
                        // 自定义数据库表字段类型转换【可选】
                        @Override
                        public DbColumnType processTypeConvert(String fieldType) {
                            System.out.println("转换类型：" + fieldType);
                            // if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
                            //    return DbColumnType.BOOLEAN;
                            // }
                            return super.processTypeConvert(fieldType);
                        }
                    });
        }
        dataSourceConfig.setDriverName(driverName)
                .setUsername(userName)
                .setPassword(password)
                .setUrl(url);
        return dataSourceConfig;
    }

    public static void main(String[] args) {
        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("ASDD_SS", FieldFill.INSERT_UPDATE));
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator().setGlobalConfig(
                // 全局配置
                new GlobalConfig()
                        .setOutputDir(path+"/src/main/java")//输出目录
                        .setFileOverride(true)// 是否覆盖文件
                        .setActiveRecord(true)// 开启 activeRecord 模式
                        .setEnableCache(false)// XML 二级缓存
                        .setBaseResultMap(true)// XML ResultMap
                        .setBaseColumnList(true)// XML columList
                        .setOpen(true)//生成后打开文件夹
                        .setAuthor(authorName)
                        // 自定义文件命名，注意 %s 会自动填充表实体属性！
                        .setMapperName("%sMapper")
                        .setXmlName("%sMapper")
                        .setServiceName("%sService")
                        .setServiceImplName("%sServiceImpl")
                        .setControllerName("%sController")
        ).setDataSource(
                // 数据源配置
                getDataSouceType(dataSourceType)
        ).setStrategy(
                // 策略配置
                new StrategyConfig()
                        // .setCapitalMode(true)// 全局大写命名
                        //.setDbColumnUnderline(true)//全局下划线命名
                        .setTablePrefix(new String[]{prefix})// 此处可以修改为您的表前缀
                        .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                        .setInclude(new String[] { table }) // 需要生成的表
                        .setRestControllerStyle(true)
                        //.setExclude(new String[]{"test"}) // 排除生成的表
                        // 自定义实体父类
                        // .setSuperEntityClass("com.baomidou.demo.TestEntity")
                        // 自定义实体，公共字段
                        //.setSuperEntityColumns(new String[]{"test_id"})
                        .setTableFillList(tableFillList)
                        // 自定义 mapper 父类
                        // .setSuperMapperClass("com.baomidou.demo.TestMapper")
                        // 自定义 service 父类
                        // .setSuperServiceClass("com.baomidou.demo.TestService")
                        // 自定义 service 实现类父类
                        // .setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl")
                        // 自定义 controller 父类
                        //.setSuperControllerClass("com.mybatis."+packageName+".controller.AbstractController")
                        .setSuperControllerClass("com.mybatis.controller.AbstractController")
                // 【实体】是否生成字段常量（默认 false）
                // public static final String ID = "test_id";
                // .setEntityColumnConstant(true)
                // 【实体】是否为构建者模型（默认 false）
                // public User setName(String name) {this.name = name; return this;}
                // .setEntityBuilderModel(true)
                // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
                // .setEntityLombokModel(true)
                // Boolean类型字段是否移除is前缀处理
                // .setEntityBooleanColumnRemoveIsPrefix(true)
                // .setRestControllerStyle(true)
                // .setControllerMappingHyphenStyle(true)
        ).setPackageInfo(
                // 包配置
                new PackageConfig()
                        //.setModuleName("User")
                        //.setParent("com.mybatis."+packageName)// 自定义包路径
                        .setParent("com.mybatis")// 自定义包路径
                        .setController("controller")// 这里是控制器包名，默认 web
                        .setEntity("pojo")
                        .setMapper("dao")
                        .setService("service")
                        .setServiceImpl("service.impl")
                //.setXml("mapper")
        ).setCfg(
                // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
                new InjectionConfig() {
                    @Override
                    public void initMap() {
                        Map<String, Object> map = new HashMap<>();
                        map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                        this.setMap(map);
                    }
                }.setFileOutConfigList(Collections.<FileOutConfig>singletonList(new FileOutConfig("/templates/mapper.xml.vm") {
                    // 自定义输出文件目录
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        return path+"/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
                    }
                }))
        ).setTemplate(
                // 关闭默认 xml 生成，调整生成 至 根目录
                new TemplateConfig().setXml(null)
                // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
                // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
                // .setController("...");
                // .setEntity("...");
                // .setMapper("...");
                // .setXml("...");
                // .setService("...");
                // .setServiceImpl("...");
        );

        // 执行生成
        mpg.execute();

        // 打印注入设置，这里演示模板里面怎么获取注入内容【可无】
        System.err.println(mpg.getCfg().getMap().get("abc"));
    }
}
