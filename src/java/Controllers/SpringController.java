package Controllers;

import Annotion.Log;
import Services.FileUnitTool;
import Services.IUserService;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/aop")
public class SpringController {
	
	@Resource IUserService userService;
	
	@Log(name="您访问了aop1方法")
	@ResponseBody
	@RequestMapping(value="aop1")
	public String aop1(){
            FileUnitTool.RemoveFiles("‪D:/Projects/TEST/");
		return "AOP";
	}
	
	@Log(name="您访问了aop2方法")
	@ResponseBody
	@RequestMapping(value="aop2")
	public String aop2(String string) throws Throwable
               /// throws InterruptedException
        {
//		User user=new User();
//		user.setName(string);
		userService.save();
		return string;
	}
	
}
