package com.cisco.rbac.common.interceptor;

import com.alibaba.druid.util.StringUtils;
import com.cisco.rbac.common.jwt.JwtConstant;
import com.cisco.rbac.common.annotation.JwtIgnore;
import com.cisco.rbac.common.jwt.JwtParam;
import com.cisco.rbac.common.jwt.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt 拦截器
 * Created by Hilox on 2018/11/16 0016.
 */
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtParam jwtParam;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        // 忽略带JwtIgnore注解的请求, 不做后续token认证校验
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            JwtIgnore jwtIgnore = handlerMethod.getMethodAnnotation(JwtIgnore.class);
            if (jwtIgnore != null) {
                return true;
            }
        }

        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        final String authHeader = request.getHeader(JwtConstant.AUTH_HEADER_KEY);

        if (StringUtils.isEmpty(authHeader)) {
            // TODO 这里自行抛出异常
           // log.info("===== 用户未登录, 请先登录 =====");
           System.out.println("用户未登陆，请先登录");
            return false;
        }

        // 校验头格式校验
        if (!JwtUtils.validate(authHeader)) {
            // TODO 这里自行抛出异常
            //log.info("===== token格式异常 =====");
            System.out.println("token格式异常1");
            return false;
        }

        // token解析
        final String authToken = JwtUtils.getRawToken(authHeader);
        Claims claims = JwtUtils.parseToken(authToken, jwtParam.getBase64Secret());
        //System.out.println("bb:"+jwtParam.getBase64Secret());
        if (claims == null) {
            //log.info("===== token解析异常 =====");
            System.out.println("token解析异常2");
            return false;
        }

        // 传递所需信息
        request.setAttribute("CLAIMS", claims);
      //  System.out.println("claims:"+claims);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {

    }
}

