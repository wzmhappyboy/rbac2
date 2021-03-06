package com.cisco.rbac.common.interceptor;

import com.cisco.rbac.common.annotation.RequiredPermission;
import com.cisco.rbac.model.entity.Role;
import com.cisco.rbac.common.jwt.JwtConstant;
import com.cisco.rbac.common.jwt.JwtParam;
import com.cisco.rbac.common.jwt.JwtUtils;
import com.cisco.rbac.model.service.impl.UserServiceImpl;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/**
 * @author blueriver
 * @description 权限拦截器
 * @date 2017/11/17
 * @since 1.0
 */
public class SecurityInterceptor implements HandlerInterceptor {

   // @Autowired
   // private AdminUserService adminUserService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtParam jwtParam;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String authHeader = request.getHeader(JwtConstant.AUTH_HEADER_KEY);
        final String authToken = JwtUtils.getRawToken(authHeader);
        Claims claims = JwtUtils.parseToken(authToken, jwtParam.getBase64Secret());

        List<Role> roleList=(List<Role>) claims.get("rolelist");
       Set<String> permissionSet = userService.getPermissionSet(roleList);


        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取方法上的注解
            RequiredPermission requiredPermission = handlerMethod.getMethod().getAnnotation(RequiredPermission.class);
            // 如果方法上的注解为空 则获取类的注解
            if (requiredPermission == null) {
                requiredPermission = handlerMethod.getMethod().getDeclaringClass().getAnnotation(RequiredPermission.class);
            }
//            System.out.println("方法所需权限"+requiredPermission.value());
            return permissionSet.contains(requiredPermission.value());

        }
       // response.sendError(HttpStatus.FORBIDDEN.value(), "无权限");
        return false;

    }

    /**
     * 是否有权限
     *
     * @param handler
     * @return
     */
    private boolean hasPermission(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取方法上的注解
            RequiredPermission requiredPermission = handlerMethod.getMethod().getAnnotation(RequiredPermission.class);
            // 如果方法上的注解为空 则获取类的注解
            if (requiredPermission == null) {
                requiredPermission = handlerMethod.getMethod().getDeclaringClass().getAnnotation(RequiredPermission.class);
            }
            // 如果标记了注解，则判断权限
  //          if (requiredPermission != null ) {
                // redis或数据库 中获取该用户的权限信息 并判断是否有权限
               // Set<String> permissionSet = adminUserService.getPermissionSet();
//
//                if (CollectionUtils.isEmpty(permissionSet) ){
//                    return false;
//                }
//                return permissionSet.contains(requiredPermission.value());
//            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // TODO
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // TODO
    }
}
