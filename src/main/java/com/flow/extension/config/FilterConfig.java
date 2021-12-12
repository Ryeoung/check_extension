package com.flow.extension.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
public class FilterConfig {
    @Bean
    public CharacterEncodingFilter  characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }
    @Bean
    public FilterRegistrationBean setFilterRegistration(CharacterEncodingFilter filter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(filter);
        filterRegistrationBean.addUrlPatterns("/api/*"); // string 여러개를 가변인자로 받는 메소드
        return filterRegistrationBean;
    }
}
