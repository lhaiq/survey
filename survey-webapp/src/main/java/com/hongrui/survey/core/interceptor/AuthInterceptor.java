package com.hongrui.survey.core.interceptor;

import com.google.common.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by haiquanli on 15/11/20.
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    @Qualifier("sessionCache")
    private Cache<String, Long> sessionCache;

    private final static String AUTHORIZATION = "Authorization";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //放过只过滤已sure下的请求
//        if(!request.getRequestURI().startsWith("/sure")){
//            return true;
//        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
//
//        boolean isIgnore = checkIgnore(handler);
//
//        if (!isIgnore) {
//            //取得auth token
//            String authToken = request.getHeader(AUTHORIZATION);
//            if (StringUtils.isEmpty(authToken)) {
//                ErrorCode.throwBusinessException(ErrorCode.AUTH_TOKEN_MUST);
//            }
//
//            AuthModel authModel = sessionCache.getIfPresent(authToken);
//            if (null == authModel) {
//                ErrorCode.throwBusinessException(ErrorCode.AUTH_TOKEN_INVALID);
//            }
//
//            //检查权限
//            checkPermission(authModel.getRole(), handler);
//
//            //将UserId设置到request里面
            request.setAttribute("userId", 4L);
//        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}
