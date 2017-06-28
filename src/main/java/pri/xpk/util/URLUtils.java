package pri.xpk.util;


import org.springframework.util.StringUtils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by Administrator on 2017/4/27.
 */
public class URLUtils {

    /**
     *
     * 方法用途: 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），并且生成url参数串<br>
     * 实现步骤
     * @param paraMap   要排序的Map对象
     * @param urlEncode   是否需要URLENCODE
     * @param decode 是否解码
     * @param keyToLower    是否需要将Key转换为全小写
     *            true:key转化成小写，false:不转化
     * @return
     */
    public static String formatUrlMap(Map<String, String> paraMap, boolean urlEncode, boolean keyToLower,boolean decode ) {
        String buff ;
        Map<String, String> tmpMap = paraMap;
        try{
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(tmpMap.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, (o1, o2) -> (o1.getKey()).toString().compareTo(o2.getKey()));
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds) {
                if (!StringUtils.isEmpty(item.getKey()))
                {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (urlEncode){
                        val = URLEncoder.encode(val, "utf-8");
                    }
                    if(decode) {
                        val = URLDecoder.decode(val);
                    }
                    if (keyToLower) {
                        buf.append(key.toLowerCase() + "=" + val);
                    } else{
                        buf.append(key + "=" + val);
                    }
                    buf.append("&");
                }
            }
            buff = buf.toString();
            if (buff.isEmpty() == false){
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e){
            return null;
        }
        return buff;
    }



}
