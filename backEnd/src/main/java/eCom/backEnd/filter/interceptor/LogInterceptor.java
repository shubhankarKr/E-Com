package eCom.backEnd.filter.interceptor;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LogInterceptor implements HandlerInterceptor {

	private Logger logger = LogManager.getLogger(LogInterceptor.class);

	private final Cache cache;

	@Autowired
	public LogInterceptor(Cache cache) {
		this.cache = cache;
	}

	@SuppressWarnings("static-access")
	public String getStartKey(HttpServletRequest request) {
		return generateKey(request).join("_", "start");
	}

	@SuppressWarnings("static-access")
	public String getEndKey(HttpServletRequest request) {
		return generateKey(request).join("_", "end");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		this.cache.put(getStartKey(request), System.currentTimeMillis());
		logger.info("Received RequestTimeStamp {} for URL : {}", LocalDateTime.now(), request.getRequestURL());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		this.cache.put(getEndKey(request), System.currentTimeMillis());
		Long startTime = (Long) this.cache.get(getStartKey(request)).get();
		Long endTime = (Long) this.cache.get(getEndKey(request)).get();
		logger.info("Received ResponseTimeStamp {} for URL : {}", LocalDateTime.now(), request.getRequestURL());
		logger.info("Total time taken in millis is : {} ms", endTime - startTime);
	}

	public String generateKey(HttpServletRequest request) {
		String finalString = String.join("_", request.getHeader("host"), request.getHeader("X-XSRF-TOKEN"),
				request.getRemoteAddr(), request.getRequestURL(), request.getRequestedSessionId(),
				request.getContentLengthLong() + "");
		String unique = DigestUtils.md5DigestAsHex(finalString.getBytes());
		return unique;
	}

}