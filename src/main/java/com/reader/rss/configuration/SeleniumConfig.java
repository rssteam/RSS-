package com.reader.rss.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeleniumConfig {
    @Bean
    public WebDriver Webdriver(){
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        return new ChromeDriver(new ChromeOptions().addArguments("--headless","--disable-gpu"));
    }
}
