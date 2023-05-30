package com.pumpkin.disease;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class MuZiNanTemplateApplicationTests {

    @Test
    void contextLoads() {

        FastAutoGenerator.create("jdbc:mysql://******:3306/pumpkin-template?serverTimezone=Asia/Shanghai", "******", "******")
                // 全局配置
                .globalConfig((scanner, builder) -> {
                    builder.author("Pumpkin")
                            .dateType(DateType.ONLY_DATE)
                            .outputDir(System.getProperty("user.dir") + "\\src\\main\\java")
                            .enableSwagger()
                            .commentDate("yyyy-MM-dd HH:mm:ss");
                })
                // 包配置
                .packageConfig((scanner, builder) -> {
                    builder.parent("com.pumpkin.disease")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "\\src\\main\\resources\\mapper"));
                })
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables("role_half_menu"))
                        .controllerBuilder()
                        .enableRestStyle()
                        .enableHyphenStyle()
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl")
                        .entityBuilder()
                        .enableLombok()
                        .addTableFills(
                                new Column("gmt_create", FieldFill.INSERT),
                                new Column("gmt_update", FieldFill.INSERT_UPDATE)
                        ).build())
                .execute();

    }

    /**
     * 处理 all 情况
     */
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

}
