package com.zz.shardingjdbc;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Description: 代码生成
 * @Author: zhangzhao
 **/
public class MpGenerator {
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (!StringUtils.isEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * 代码生成
     */
    public static void main(String[] args) {
        //  "/src/main/java"
        final String OUT_PATH = "/src/main/java";
        final String AUTHOR = "zhangzhao";
        final String DB_URL = "jdbc:mysql://192.168.1.152:3306/test?useUnicode=true&useSSL=false&characterEncoding=utf8";
        final String DB_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_USERNAME = "root";
        final String DB_PASSWORD = "123456";
        final String PACKAGE_DIR = "com.zz.shardingjdbc.module";
        //多表可,隔开
        final String TABLE_NAME = "";
        //mybatis-mapper 路径
        final String MAPPER_DIR = "/src/main/resources/mybatis-mapper/";
        //自定义继承的Entity类全称，带包名
        final String SUPPER_ENTITY_CLASS ="";
        //自定义继承的ServiceImpl类全称，带包名 "com.baomidou.ant.common.BaseController"
        final String SUPPER_IMPL_CLASS ="";

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //获取当前路径
        final String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + OUT_PATH);
        gc.setIdType(IdType.AUTO);
        gc.setAuthor(AUTHOR);
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(DB_URL);
        // dsc.setSchemaName("public");
        dsc.setDriverName(DB_DRIVER);
        dsc.setUsername(DB_USERNAME);
        dsc.setPassword(DB_PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent(PACKAGE_DIR);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + MAPPER_DIR + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });*/
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        // 配置模板  mapper.xml 自定义路径生效条件
        TemplateConfig templateConfig = new TemplateConfig();
//         配置自定义输出模板
//        指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
//         templateConfig.setEntity("templates/entity2.java");
//         templateConfig.setService();
//         templateConfig.setController();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 命名规则
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        if(!SUPPER_ENTITY_CLASS.isEmpty()){
            strategy.setSuperEntityClass(SUPPER_ENTITY_CLASS);
        }
        // 实体是否使用Lombok插件
        strategy.setEntityLombokModel(true);
        // 控制层是否使用Rest风格
        strategy.setRestControllerStyle(true);
        if(!SUPPER_IMPL_CLASS.isEmpty()){
            strategy.setSuperServiceImplClass(SUPPER_IMPL_CLASS);
        }
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        //驼峰
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}