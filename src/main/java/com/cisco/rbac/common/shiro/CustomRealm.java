package com.cisco.rbac.common.shiro;



import com.cisco.rbac.common.jwt.JwtParam;
import com.cisco.rbac.common.jwt.JwtUtils;
import com.cisco.rbac.model.entity.User;
import com.cisco.rbac.model.service.impl.UserServiceImpl;

import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    UserServiceImpl usersService;

    @Autowired
    private JwtParam jwtParam;

    /**
     * 必须重写此方法，不然会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证方法————");

        String token = (String) authenticationToken.getCredentials();
     //   System.out.println("token:"+token);

        String authToken = JwtUtils.getRawToken(token);
        Claims claims = JwtUtils.parseToken(authToken, jwtParam.getBase64Secret());
String uid= (String) claims.get("userId");
int id=Integer.parseInt(uid);
        User userinfo = usersService.getUserById(id);
        if(userinfo==null){
            throw new AuthenticationException("用户不存在！");
        }
        return new SimpleAuthenticationInfo(userinfo, token, "MyRealm");
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("————权限认证————");
      //  int uid = JwtUtils.getuseridbytoken(principals.toString());
       //User userinfo = usersService.getUserById(uid);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色

        //每个角色拥有默认的权限

        //每个用户可以设置新的权限

        Set<String> roleSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();
        //需要将 role, permission 封装到 Set 作为 info.setRoles(), info.setStringPermissions() 的参数
        //测试数据  给用户 admin 角色 以及 add,test 权限
        roleSet.add("admin");
        permissionSet.add("add");
        permissionSet.add("test");
        //设置该用户拥有的角色和权限
        info.setRoles(roleSet);
        info.setStringPermissions(permissionSet);
        return info;
    }
}

