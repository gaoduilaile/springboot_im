package com.wyj.springboot.im.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wyj.springboot.im.authorize.UserContext;
import com.wyj.springboot.im.authorize.cache.KeySerilizable;
import com.wyj.springboot.im.authorize.cache.LongRedisCacheManager;
import com.wyj.springboot.im.authorize.cache.RedisCacheManager;
import com.wyj.springboot.im.entity.User;


/**
 * 
 * @author wuyingjie
 * @date 2017年9月29日
 */

@Configuration
public class BeanIocConfig {
	
	@Bean
	public UserContext userContext() {
		return new UserContext();
	}


	public static final String LOGIN_TIMES_CACHE = "loginTimesCache";
	
	@Bean(name=LOGIN_TIMES_CACHE)
	public LongRedisCacheManager<User> loginTimeCM() {
		return new LongRedisCacheManager<User>(RedisCacheManager.DEFAULT_TIMEOUT, new KeySerilizable<User>() {
			@Override
			public String serilizableKey(User key) {
				return LOGIN_TIMES_CACHE+"_"+key.getId();
			}
		});
	}
	
	public static final String USER_CACHE = "userCache";
	
	@Bean(name=USER_CACHE)
	public RedisCacheManager<String, User> userCM() {
		return new RedisCacheManager<String, User>(20*60, new KeySerilizable<String>() {
			@Override
			public String serilizableKey(String key) {
				return USER_CACHE+"_"+key;
			}
		});
	}
	
}
