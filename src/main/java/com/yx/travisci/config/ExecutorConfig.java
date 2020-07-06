package com.yx.travisci.config;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * ExecutorConfig.
 *
 * @author Zhipeng Ma.
 */
@EnableAsync(proxyTargetClass = true)
@Configuration
public class ExecutorConfig {

	@Bean(name = "taskExecutor")
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		// 设置核心线程数
		executor.setCorePoolSize(5);
		// 设置最大线程数
		executor.setMaxPoolSize(1000);
		// 设置队列容量
		executor.setQueueCapacity(1000);
		// 设置线程活跃时间（秒）
		executor.setKeepAliveSeconds(30000);
		// 核心线程超时不会关闭.
		executor.setAllowCoreThreadTimeOut(false);
		// 设置默认线程名称
		executor.setThreadNamePrefix("download-");
		// 设置拒绝策略(多次尝试，直到成功，或者执行器关闭)
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		// 等待所有任务结束后再关闭线程池
		executor.setWaitForTasksToCompleteOnShutdown(true);
		return executor;
	}

}
