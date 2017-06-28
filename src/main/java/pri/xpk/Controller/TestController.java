package pri.xpk.Controller;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by xpk on 2017/6/6 16:14.
 */
@Controller
@RequestMapping(value="api/test")
public class TestController {

    public static String prePath ="private.xpk.model.Test.";

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> test(@RequestParam(value="name",required = false ,defaultValue = "RefExtend1") String name) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        Map<String,Object> maps = Maps.newHashMap();
       name = "RefExtend2";
       Class myClass = Class.forName(prePath  + name);
       Method method = myClass.getMethod("print1",String.class);
       Object msg = method.invoke(myClass.newInstance(),"意不意外");
       System.out.println(msg);
       return maps;
    }
}
