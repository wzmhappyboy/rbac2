package com.cisco.rbac;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RbacApplication.class)
@WebAppConfiguration
public class PermissionControllerTest {
    private  static final Logger logger = LoggerFactory.getLogger(PermissionControllerTest.class);
    private RestTemplate template =new RestTemplate();
    @Test
    public  void test1(){
        try{
            String url="localhost:8084/userrolerelation";
            MultiValueMap<String,Object> map=new LinkedMultiValueMap<String, Object>();
            map.add("role_id","1001");
            map.add("user_id", "250");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
