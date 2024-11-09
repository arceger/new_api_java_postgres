package com.over.newsalesapi.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories; 
import org.springframework.transaction.annotation.EnableTransactionManagement;


  @Configuration
  @EnableTransactionManagement
  @EnableJpaRepositories(basePackages = "com.over.newsalesapi.repository")
   public class JpaConfig {
    
   }

