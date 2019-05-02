package com.chaoge.other.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;

import java.util.*;

/**
 *         作者：孤天浪雨
 *         来源：CSDN
 *         原文：https://blog.csdn.net/u010246789/article/details/52539576
 *         版权声明：本文为博主原创文章，转载请附上博文链接！
 */
public class SerializerFeatureTest {

    private static Word word;

    public static void init() {
        word = new Word();
        word.setA("a");
        word.setB(2);
        word.setC(true);
        word.setD("d");
        word.setE("");
        word.setF(null);
        word.setObject(null);
        word.setDate(new Date());

        List<User> list = new ArrayList<User>();
        User user1 = new User();
        user1.setId(1);
        user1.setOld("11");
        user1.setName("用户1");
        user1.setAdd("北京");
        User user2 = new User();
        user2.setId(2);
        user2.setOld("22");
        user2.setName("用户2");
        user2.setAdd("上海");
        User user3 = new User();
        user3.setId(3);
        user3.setOld("33");
        user3.setName("用户3");
        user3.setAdd("广州");

        list.add(user3);
        list.add(user2);
        list.add(null);
        list.add(user1);

        word.setList(list);

        Map<String , Object> map = new HashMap();
        map.put("mapa", "mapa");
        map.put("mapo", "mapo");
        map.put("mapz", "mapz");
        map.put("user1", user1);
        map.put("user3", user3);
        map.put("user4", null);
        map.put("list", list);
        word.setMap(map);
    }

static {
    init();
}

    /**
     * 10:自定义
     * 格式化输出
     * 显示值为null的字段
     * 将为null的字段值显示为""
     * DisableCircularReferenceDetect:消除循环引用
     */
    @Test
    public void showJsonBySelf() {
        System.out.println(JSON.toJSONString(word));
        System.out.println(JSON.toJSONString(word,
                SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteNullListAsEmpty));
    }

    /**
     * 9:
     * 将对象转为array输出
     */
    @Test
    public void beanToArray() {
        word.setMap(null);
        word.setList(null);
        System.out.println(JSON.toJSONString(word));
        System.out.println(JSON.toJSONString(word, SerializerFeature.BeanToArray));
    }



    /**
     * 8:
     * PrettyFormat
     */
    public void prettyFormat() {
        word.setMap(null);
        word.setList(null);
        System.out.println(JSON.toJSONString(word));
        System.out.println(JSON.toJSONString(word, SerializerFeature.PrettyFormat));
    }

    /**
     * 7:
     * SortField:按字段名称排序后输出。默认为false
     * 这里使用的是fastjson：为了更好使用sort field martch优化算法提升parser的性能，fastjson序列化的时候,缺省把SerializerFeature.SortField特性打开了。反序列化的时候也缺省把SortFeidFastMatch的选项打开了。
     * 这样，如果你用fastjson序列化的文本，输出的结果是按照fieldName排序输出的，parser时也能利用这个顺序进行优化读取。
     * 这种情况下，parser能够获得非常好的性能。
     */
    @Test
    public void sortField() {
        System.out.println(JSON.toJSONString(word));
        System.out.println(JSON.toJSONString(word, SerializerFeature.SortField));
    }

    /**
     *  6:
     *  WriteNullStringAsEmpty:字符类型字段如果为null,输出为"",而非null
     */
    @Test
    public void writeNullStringAsEmpty() {
        word.setE(null);
        word.setMap(null);
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置WriteMapNullValue后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.WriteMapNullValue));
        System.out.println("设置WriteMapNullValue、WriteNullStringAsEmpty后：");
        System.out.println(JSONObject.toJSONString(word,  SerializerFeature.WriteNullStringAsEmpty));
    }


    /**
     * 5:
     * WriteNullListAsEmpty:List字段如果为null,输出为[],而非null
     */
    @Test
    public void writeNullListAsEmpty() {

        word.setList(null);
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置WriteNullListAsEmpty后：");
        System.out.println(JSONObject.toJSONString(word,SerializerFeature.WriteNullListAsEmpty));
    }

    /**
     * 4:
     * UseISO8601DateFormat:Date使用ISO8601格式输出，默认为false
     */
    @Test
    public void useISO8601DateFormat() {
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置UseISO8601DateFormat后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.UseISO8601DateFormat));
    }
    /**
     * 3:
     * WriteDateUseDateFormat:全局修改日期格式,默认为false。
     */
    @Test
    public void writeDateUseDateFormat() {
        word.setMap(null);
        word.setList(null);
         System.out.println(JSON.toJSONString(word));
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        System.out.println(JSON.toJSONString(word, SerializerFeature.WriteDateUseDateFormat));

        System.out.println(JSONObject.toJSONString(word, SerializerFeature.UseISO8601DateFormat));

        System.out.println(JSON.toJSONString(word));
        System.out.println(JSON.toJSONString(word,SerializerFeature.WriteDateUseDateFormat));//也需要指定给特征。但是默认的时间格式就不用在设置了
    }

    /**
     * 2:
     * WriteMapNullValue:是否输出值为null的字段,默认为false
     */
    @Test
    public void writeMapNullValue() {
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置WriteMapNullValue后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.WriteMapNullValue));
    }

    /**
     * 1:
     * UseSingleQuotes:使用单引号而不是双引号,默认为false
     */
    @Test
    public void useSingleQuotes() {
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置useSingleQuotes后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.UseSingleQuotes));
    }
}
