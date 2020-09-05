package com.shiro.utils.json;

import com.shiro.utils.json.exception.NestedException;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.util.JSONPObject;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/** 
* 简单封装Jackson，实现JSON String<->Java Object的Mapper. 
*  
* 封装不同的输出风格, 使用不同的builder函数创建实例. 
*  
*/  
public class JsonMapper {
	private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);
	  
    private ObjectMapper mapper;  
  
    public JsonMapper(Inclusion inclusion) {  
        mapper = new ObjectMapper();  
        //设置输出时包含属性的风格  
        mapper.setSerializationInclusion(inclusion);  
        //设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性  
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);  
        //禁止使用int代表Enum的order()來反序列化Enum,非常危險  
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_NUMBERS_FOR_ENUMS, true);  
    }  
  
    /** 
     * 创建输出全部属性到Json字符串的Mapper. 
     */  
    public static JsonMapper buildNormalMapper() {  
        return new JsonMapper(Inclusion.ALWAYS);  
    }  
  
    /** 
     * 创建只输出非空属性到Json字符串的Mapper. 
     */  
    public static JsonMapper buildNonNullMapper() {  
        return new JsonMapper(Inclusion.NON_NULL);  
    }  
  
    /** 
     * 创建只输出初始值被改变的属性到Json字符串的Mapper. 
     */  
    public static JsonMapper buildNonDefaultMapper() {  
        return new JsonMapper(Inclusion.NON_DEFAULT);  
    }  
  
    /** 
     * 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper. 
     */  
    public static JsonMapper buildNonEmptyMapper() {  
        return new JsonMapper(Inclusion.NON_EMPTY);  
    }  
  
    /** 
     * 如果对象为Null, 返回"null". 
     * 如果集合为空集合, 返回"[]". 
     */  
    public String toJson(Object object) {  
  
        try {  
            return mapper.writeValueAsString(object);  
        } catch (IOException e) {  
            throw NestedException.wrap(e);
        }  
    }  
  
    /** 
     * 如果JSON字符串为Null或"null"字符串, 返回Null. 
     * 如果JSON字符串为"[]", 返回空集合. 
     *  
     * 如需读取集合如List/Map, 且不是List<String>这种简单类型时,先使用函數constructParametricType构造类型. 
     * @see #constructParametricType(Class, Class...) 
     */  
    public <T> T fromJson(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;  
        }  
  
        try {  
            return mapper.readValue(jsonString, clazz);  
        } catch (IOException e) {  
            throw NestedException.wrap(e);  
        }  
    }  
  
    /** 
     * 如果JSON字符串为Null或"null"字符串, 返回Null. 
     * 如果JSON字符串为"[]", 返回空集合. 
     *  
     * 如需读取集合如List/Map, 且不是List<String>这种简单类型时,先使用函數constructParametricType构造类型. 
     * @see #constructParametricType(Class, Class...) 
     */  
    @SuppressWarnings("unchecked")  
    public <T> T fromJson(String jsonString, JavaType javaType) {  
        if (StringUtils.isEmpty(jsonString)) {  
            return null;  
        }  
  
        try {  
            return (T) mapper.readValue(jsonString, javaType);  
        } catch (IOException e) {  
            throw NestedException.wrap(e);  
        }  
    }  
      
    @SuppressWarnings("unchecked")  
    public <T> T fromJson(String jsonString, Class<?> parametrized, Class<?>... parameterClasses) {  
        return (T) this.fromJson(jsonString, constructParametricType(parametrized, parameterClasses));  
    }  
      
    @SuppressWarnings("unchecked")  
    public <T> List<T> fromJsonToList(String jsonString, Class<T> classMeta){  
        return (List<T>) this.fromJson(jsonString,constructParametricType(List.class, classMeta));  
    }  
      
    @SuppressWarnings("unchecked")  
    public <T> T fromJson(JsonNode node, Class<?> parametrized, Class<?>... parameterClasses) {  
        JavaType javaType = constructParametricType(parametrized, parameterClasses);  
        try {  
            return (T) mapper.readValue(node, javaType);  
        } catch (IOException e) {  
            throw NestedException.wrap(e);  
        }  
    }  
      
    @SuppressWarnings("unchecked")  
    public <T> T pathAtRoot(String json, String path, Class<?> parametrized, Class<?>... parameterClasses){  
        JsonNode rootNode = parseNode(json);  
        JsonNode node = rootNode.path(path);  
        return (T) fromJson(node, parametrized, parameterClasses);  
    }  
      
    @SuppressWarnings("unchecked")  
    public <T> T pathAtRoot(String json, String path, Class<T> clazz){  
        JsonNode rootNode = parseNode(json);  
        JsonNode node = rootNode.path(path);  
        return (T) fromJson(node, clazz);  
    }  
  
    /** 
     * 构造泛型的Type如List<MyBean>, 则调用constructParametricType(ArrayList.class,MyBean.class) 
     *             Map<String,MyBean>则调用(HashMap.class,String.class, MyBean.class) 
     */  
    public JavaType constructParametricType(Class<?> parametrized, Class<?>... parameterClasses) {  
        return mapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);  
    }  
  
    /** 
     * 当JSON里只含有Bean的部分属性时，更新一个已存在Bean，只覆盖该部分的属性. 
     */  
    @SuppressWarnings("unchecked")  
    public <T> T update(T object, String jsonString) {  
        try {  
            return (T) mapper.readerForUpdating(object).readValue(jsonString);  
        } catch (JsonProcessingException e) {  
            logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);  
        } catch (IOException e) {  
            logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);  
        }  
        return null;  
    }  
  
    /** 
     * 输出JSONP格式数据. 
     */  
    public String toJsonP(String functionName, Object object) {  
        return toJson(new JSONPObject(functionName, object));  
    }  
  
    /** 
     * 设定是否使用Enum的toString函数来读写Enum, 
     * 为False時時使用Enum的name()函数来读写Enum, 默认为False. 
     * 注意本函数一定要在Mapper创建后, 所有的读写动作之前调用. 
     */  
    public void setEnumUseToString(boolean value) {  
        mapper.configure(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING, value);  
        mapper.configure(DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING, value);  
    }  
  
    /** 
     * 取出Mapper做进一步的设置或使用其他序列化API. 
     */  
    public ObjectMapper getMapper() {  
        return mapper;  
    }  
      
    public JsonNode parseNode(String json){  
        try {  
            return mapper.readValue(json, JsonNode.class);  
        } catch (IOException e) {  
            throw NestedException.wrap(e);  
        }  
    }  
      
    /** 
     * 输出全部属性 
     * @param object 
     * @return 
     */  
    public static String toNormalJson(Object object){  
        return new JsonMapper(Inclusion.ALWAYS).toJson(object);  
    }  
      
    /** 
     * 输出非空属性 
     * @param object 
     * @return 
     */  
    public static String toNonNullJson(Object object){  
        return new JsonMapper(Inclusion.NON_NULL).toJson(object);  
    }  
      
    /** 
     * 输出初始值被改变部分的属性 
     * @param object 
     * @return 
     */  
    public static String toNonDefaultJson(Object object){  
        return new JsonMapper(Inclusion.NON_DEFAULT).toJson(object);  
    }  
      
    /** 
     * 输出非Null且非Empty(如List.isEmpty)的属性 
     * @param object 
     * @return 
     */  
    public static String toNonEmptyJson(Object object){  
        return new JsonMapper(Inclusion.NON_EMPTY).toJson(object);  
    }  
      
    public void setDateFormat(String dateFormat){  
        mapper.setDateFormat(new SimpleDateFormat(dateFormat));  
    }  
      
    public static String toLogJson(Object object){  
        JsonMapper jsonMapper = new JsonMapper(Inclusion.NON_EMPTY);  
        jsonMapper.setDateFormat("yyyy-MM-dd HH:mm:ss");  
        return jsonMapper.toJson(object);  
    }  
    
    
    public static void main(String[] args) {
		List l =new ArrayList();
		l.add("0.1");
		l.add("0.2");
		l.add("0.3");
		l.add("0.4");
		Ts ts1 = new Ts("CH4",l);
		Ts ts2 = new Ts("C2H6",l);
		Ts ts3 = new Ts("CO",l);
		Ts ts4 = new Ts("H2S",l);
		List list = new ArrayList();
		list.add(ts1);
		list.add(ts2);
		list.add(ts3);
		list.add(ts4);
		System.out.println(JsonMapper.toLogJson(list));
	    Long beginTime = System.currentTimeMillis();
	    //bean2json
	    String json = JsonMapper.toLogJson(list);
	    System.out.println("对象转换为json：" + json);
	    System.out.println("转换用时：" + (System.currentTimeMillis()-beginTime) + "ms");
	    //json2bean，需要注意：Student类和Teacher类必须有一个空的构造方法
	    beginTime = System.currentTimeMillis();
	    //JsonMapper提供了很多创建Mapper的方法，不是非要用buildNonDefaultMapper，你可以对比几种方法的转换时间，挑个最快的
	    /*Student student2 = JsonMapper.buildNonDefaultMapper().fromJson(json, Student.class);
	    System.out.println("json转换成对象：" + student2);
	    System.out.println("转换用时：" + (System.currentTimeMillis()-beginTime) + "ms");*/
	}
}
class Ts {
    private String name;
    private List data;
    public Ts(String name, List data) {
        this.name = name;
        this.data = data;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List getData() {
        return data;
    }
    public void setData(List data) {
        this.data = data;
    }
}

