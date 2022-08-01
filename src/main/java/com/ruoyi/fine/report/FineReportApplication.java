package com.ruoyi.fine.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @author JupiterMouse
 * @since 1.0
 */
@SpringBootApplication
public class FineReportApplication {

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication application = new SpringApplication(FineReportApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
        System.out.println("(♥◠‿◠)ﾉﾞ  帆软报表启动成功   ლ(´ڡ`ლ)ﾞ");
    }

}
