package org.zuoyu;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.fdfs.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Paths;

import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class DeliveryServiceApplicationTests {

  @Autowired
  private FastFileStorageClient fastFileStorageClient;

  @Autowired
  private ThumbImageConfig thumbImageConfig;

  @Test
  public void contextLoads() throws FileNotFoundException {
//    File file = new File(
//        "/home/zuoyu/Pictures/diagram.png");
//    StorePath storePath = fastFileStorageClient
//        .uploadImageAndCrtThumbImage(new FileInputStream(file), file.length(),
//            "png", null);
//    // 带分组的路径
//    log.info("带分组的路径\n" + storePath.getFullPath());
//    // 不带分组的路径
//    log.info("不带分组的路径\n" + storePath.getPath());
//    // 获取缩略图路径
//    String thumbImagePath = thumbImageConfig.getThumbImagePath(storePath.getPath());
//    log.info("获取缩略图路径\n" + thumbImagePath);
//    System.out.println(storePath.getGroup());
  }

    @Test
    public void generateAsciiDocs() throws Exception {
        //    输出Ascii格式
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                .withOutputLanguage(Language.ZH)
                .withGeneratedExamples()
                .build();
        //此处填写swagger项目地址
        // http://localhost:8080/delivery/swagger-ui.html
        Swagger2MarkupConverter.from(new URL("http://127.0.0.1:8080/delivery/v2/api-docs"))
                .withConfig(config)
                .build()
                .toFile(Paths.get("src/docs/asciidoc/generated/all"));
    }

}
