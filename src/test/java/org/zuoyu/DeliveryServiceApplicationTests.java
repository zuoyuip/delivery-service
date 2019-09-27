package org.zuoyu;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.fdfs.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

}
