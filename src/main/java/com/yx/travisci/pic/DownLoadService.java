package com.yx.travisci.pic;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * DownLoadService.
 *
 * @author Zhipeng Ma.
 * @since 2020/7/6 2:17 下午
 */
@Service
@Slf4j
public class DownLoadService {

	String endpoint = "*";

	String accessKeyId = "*";

	String accessKeySecret = "*";

	String bucketName = "*";

	OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

	@Autowired
	@Qualifier("taskExecutor")
	private TaskExecutor taskExecutor;

	AtomicInteger number = new AtomicInteger(1);

	public void download(String ossName, String filename) {
		this.taskExecutor.execute(() -> {
			log.info(filename + " 正在下载 total: {}", number);
			// 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
			ossClient.getObject(new GetObjectRequest(bucketName, ossName),
					new File("/Users/mazhipeng/Desktop/file/" + filename));
			number.getAndIncrement();
		});
	}

}
