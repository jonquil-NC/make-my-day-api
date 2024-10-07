package com.northcoders.makemydayapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync(proxyTargetClass = true)
public class AsyncConfiguration {
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);                    // Set the minimum number of threads (2 threads always running)
        executor.setMaxPoolSize(5);                     // Limit the maximum number of concurrent threads to 5
        executor.setQueueCapacity(500);                 // Allow up to 500 tasks to queue when threads are busy
        executor.setThreadNamePrefix("APILookup-");     // Set custom thread name prefix for easier debugging
        executor.initialize();                          // Initialize the executor to apply the settings
        return executor;                                // Return the executor bean to be used by Spring for @Async tasks
    }
}
