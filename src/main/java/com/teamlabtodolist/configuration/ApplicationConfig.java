package com.teamlabtodolist.configuration;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 設定用のクラス
 * @author mukaihiroto
 *
 */
@Configuration
public class ApplicationConfig {

	/**
	 * SQLのログを見るためのメソッド
	 * @return
	 */
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

}