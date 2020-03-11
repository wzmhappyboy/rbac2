package com.cisco.rbac;

import com.cisco.rbac.model.controller.UserController;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes= MockServletContext.class)
@WebAppConfiguration
class RbacApplicationTests {
private MockMvc mvc;
@Before
public  void setUp() throws  Exception{
//    System.out.println("开始测试-----------");
mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
}

@Test
public void testUserController() throws  Exception {
    RequestBuilder request =null;

    request= get("/users/");
 //   mvc.perform(request)
   //         .andExpect(content().string(equals("[]"));
}
@After
public  void after(){
    System.out.println("测试结束------");
}

    @Test
    void contextLoads() {
    }

}
