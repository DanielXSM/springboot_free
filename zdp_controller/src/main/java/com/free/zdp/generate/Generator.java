package com.free.zdp.generate;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class Generator {

    public static void main(String[] args) {
        String packageName = "com.freeze.zdp";
        boolean serviceNameStartWithI = false;//auth -> UserService, 设置成true: auth -> IUserService
        gennerateByTables(false, packageName, "zdp", "test2", "user");

        System.out.println("completed...");

    }

    /**
     * @param serviceNameStartWithI
     * @param packageName           包名
     * @param author                作者
     * @param database              数据库名
     * @param tableNames            表名
     */

    private static void gennerateByTables(boolean serviceNameStartWithI, String packageName, String author, String database, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:postgresql://10.100.20.211:5432/" + database + "?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
        ;

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.POSTGRE_SQL).setUrl(dbUrl).setUsername("postgres")
                .setPassword("postgres").setDriverName("org.postgresql.Driver");

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true)
                .setEntityLombokModel(false)
                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)//驼峰式
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        config.setActiveRecord(false)
                .setAuthor(author)
                .setOutputDir("c:\\codeGen")
                .setFileOverride(true)
                .setEnableCache(false);
        if(!serviceNameStartWithI){
            config.setServiceName("%sService");
        }

        new AutoGenerator()
                .setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                new PackageConfig()
                        .setParent(packageName)
                        .setController("web")
                        .setEntity("model")
                        .setMapper("mapper")
                        .setService("service")
                        .setServiceImpl("service.impl")
                        .setXml("mybatis.mappers")
        ).execute();

    }
}
