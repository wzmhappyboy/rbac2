package com.cisco.rbac.annotation;

import java.lang.annotation.*;

/**
 * @description 与拦截器结合使用 验证权限
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RequiredPermission {
    String value();
}