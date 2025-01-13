package com.example.SpringBatchTest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

//2개 이상의 DB를 연결하려면 Config 클래스를 필수적으로 작성해야 하며, 충돌을 방지하여 우선 순위를 위해 @Primary Config를 설정해야 합니다.
//스프링 부트에서 다중 데이터 소스를 관리하기 위해 각각의 데이터 소스를 명시적으로 등록하고, 하나를 기본 데이터 소스로 지정해야 하기 때문이다.
@Configuration
public class MetaDBConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource-meta") //YAML 파일에 spring.datasource-meta로 시작하는 설정들을 객체에 매핑한다는 뜻이다
    public DataSource metaDBSource() {

        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager metaTransactionManager() {

        return new DataSourceTransactionManager(metaDBSource());
    }
}
