package com.hongrui.survey.core.interceptor;

import com.google.common.cache.Cache;
import com.hongrui.survey.core.HRErrorCode;
import com.hongrui.survey.core.annotation.IgnoreAuth;
import com.wlw.pylon.core.ErrorCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by haiquanli on 15/11/20.
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    @Qualifier("sessionCache")
    private Cache<String, Long> sessionCache;

    private final static String AUTHORIZATION = "Authorization";
    private final static String ROLE = "Role";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        boolean isIgnore = checkIgnore(handler);

        if (!isIgnore) {
            Controller controller = handlerMethod.getBeanType().getAnnotation(Controller.class);
            if (null != controller) {
                HttpSession session = request.getSession();
                Object user = session.getAttribute("user");
                if (null == user) {
                    response.sendRedirect("/login");
                    return true;
                }
            }

            RestController restController = handlerMethod.getBeanType().getAnnotation(RestController.class);
            if (null != restController) {

                String role = request.getHeader(ROLE);
                if("Admin".equals(role)){
                    return true;
                }
                String authToken = request.getHeader(AUTHORIZATION);
                if (StringUtils.isEmpty(authToken)) {
                    HRErrorCode.throwBusinessException(HRErrorCode.UN_LOGIN);
                }

                Long userId = sessionCache.getIfPresent(authToken);
                if(null==userId){
                    HRErrorCode.throwBusinessException(HRErrorCode.UN_LOGIN);
                }

                request.setAttribute("userId", userId);
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception
            ex) throws Exception {

    }


    private boolean checkIgnore(Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        IgnoreAuth ignoreAuth = handlerMethod.getMethodAnnotation(IgnoreAuth.class);
        if (null != ignoreAuth) {
            return true;
        }

        return false;
    }

}
