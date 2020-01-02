package com.cisco.rbac;


import com.cisco.rbac.controller.PermissionController;
import com.cisco.rbac.entity.Permission;
import com.cisco.rbac.service.PermissionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PermissionControllerTest {
//    private  static Logger logger = LoggerFactory.getLogger(PermissionControllerTest.class);
    @Autowired
    private WebApplicationContext wac;

    private  MockMvc mvc;

    private ObjectMapper objectMapper =new ObjectMapper();

    @Before
    public  void setUp() throws  Exception{
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void  getPermissionList() throws  Exception{
        RequestBuilder request =get("/permissions");
        mvc.perform(request).andExpect(status().isOk());
    }

    @Test
    public  void postPermission() throws  Exception{
        Permission permission=new Permission(3,"permission3","description3");
        String postJson =objectMapper.writeValueAsString(permission);

        RequestBuilder requestBuilder =post("/permissions")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(postJson);

        mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("permission3"))
                .andDo(print());
    }



}
