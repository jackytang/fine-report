package cn.site.jm.fine.report.infra.config;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import java.io.File;

/**
 * <p>
 * 帆软内嵌tomcat配置类
 * </p>
 *
 * @author JupiterMouse
 * @since 1.0
 */
@Configuration
public class FineReportConfiguration {

    @Bean
    FineReportWebServerFactoryCustomizer fineReportWebServerFactoryCustomizer() {
        return new FineReportWebServerFactoryCustomizer();
    }

    /**
     * 自定义帆软webapp目录，从src/main/webapp重定向到自定义的目录下
     */
    public static class FineReportWebServerFactoryCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory>, Ordered {

        @Override
        public void customize(TomcatServletWebServerFactory factory) {
            String docBase = System.getProperty("DOC_BASE");
            if (StrUtil.isBlank(docBase)) {
                throw new IllegalArgumentException("DOC_BASE system property can't empty");
            }

            File file = FileUtil.file(docBase);
            if (!file.exists()) {
                FileUtil.mkdir(file);
            }

            if (FileUtil.exist(file) && FileUtil.isDirectory(file)) {
                factory.setDocumentRoot(file);
            } else {
                throw new IllegalArgumentException("Fine webapp dir [env] not found");
            }

            // 加载帆软Web
            factory.getTomcatContextCustomizers()
                    .add(context -> context.addApplicationListener("com.fr.startup.FineServletContextListener"));
        }

        @Override
        public int getOrder() {
            return 0;
        }
    }

}
