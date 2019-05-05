package com.czxy.back.web;

import com.czxy.back.service.UserService;
import com.czxy.back.service.ValidationService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginAdminController {

    @Autowired
    private ValidationService validationService;


    @RequestMapping(value = "/validation",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> validationUser(@RequestBody Map map) {

        Map<String, Object> modelMap = new HashMap<>();
        JSONObject userInfo = JSONObject.fromObject(map.get("userInfo"));
        String username = (String) userInfo.get("username");
        String password = (String) userInfo.get("password");
        boolean result = validationService.validation(username, password);
        if (result) {
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入正确的用户名或者密码");
        }
        return modelMap;
    }


    @RequestMapping(value = "/changePassword",method = RequestMethod.POST)
    @ResponseBody
    private boolean updateAdminPassword(@RequestBody Map map){

        String oldPassword = (String) map.get("oldPassword");
        String newPassword = (String) map.get("newPassword");

        return  validationService.updateAdminPassword(oldPassword, newPassword);


    }
}
