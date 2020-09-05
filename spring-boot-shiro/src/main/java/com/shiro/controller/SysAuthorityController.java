package com.shiro.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.shiro.pojo.SysAuthority;
import com.shiro.pojo.SysRole;
import com.shiro.service.SysRoleService;
import com.shiro.utils.Pager;
import com.shiro.utils.model.PageContext;
import com.shiro.utils.model.ResponseModel;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class SysAuthorityController extends AbstractController{


    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     *
     * @param length
     * @param start
     * @return
     */
    @GetMapping("/search")
    public ResponseModel<Object> pageForSysRole (@RequestParam(value = "length") Integer length,
                                                 @RequestParam(value = "start") Integer start,
                                                 @RequestParam(value = "name") String name) {
//        Integer pageNo = getPageNo(start, length);
        Page<SysRole> page = new Page<SysRole> (start, length);
        try {
            return new ResponseModel<Object>("success","查询成功", sysRoleService.selectSysRoles(page, start, name));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseModel<Object>("error", "错误");
        }
    }

    @PostMapping("/createOrUpdate")
    @ResponseBody
    //public ResponseModel<Object> createOrUpdateSysRoles(String role,String description,boolean available,int id) {
    public ResponseModel<Object> createOrUpdateSysRoles(SysRole sysRole) {
        try {
            /*SysRole sysRole  = new SysRole();
            sysRole.setId(id);
            sysRole.setAvailable(available);
            sysRole.setDescription(description);
            sysRole.setRole(role);*/
            return sysRoleService.createOrUpdateSysRoles(sysRole);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseModel<Object>("error", e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResponseModel<Object> deleteSysRoles(String ids) {
        try{
            return sysRoleService.deleteSysRoles(Arrays.asList(ids.split(",")));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseModel<Object>("error", e.getMessage());
        }
    }

    @PostMapping("/login1")
    public Map<String, Object> login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        Map<String, Object> map = new HashMap<>();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            map.put("token", subject.getSession().getId());
            map.put("msg", "登录成功");
            //jsonObject.put("token", subject.getSession().getId());
            //jsonObject.put("msg", "登录成功");
            //return subject.;
        } catch (IncorrectCredentialsException e) {
            //jsonObject.put("msg", "密码错误");
            map.put("msg", "密码错误" );
        } catch (LockedAccountException e) {
            //jsonObject.put("msg", "登录失败，该用户已被冻结");
            map.put("msg", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            //jsonObject.put("msg", "该用户不存在");
            map.put("msg", "该用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @GetMapping("/testSysAuthority")
    public List<SysAuthority> getSysAuthority() {
        List<SysAuthority> sysAuthorities = new ArrayList<>();
        SysAuthority sysAuthority = new SysAuthority();
        sysAuthority.setId("1");
        sysAuthority.setDataUrl("/aaaaa");
        sysAuthority.setDelState(0);
        sysAuthority.setMenuClass("/img/aa.png");
        sysAuthority.setMenuCode("code1");
        sysAuthority.setMenuName("一级菜单");
        sysAuthority.setMenuType("1");
        sysAuthority.setCreateTime(new Date());
        sysAuthority.setMenuState(0);
        sysAuthority.setOrderNum(1);
        sysAuthority.setParentMenucode("-1");

        List<SysAuthority> childs = new ArrayList<>();
        SysAuthority child = new SysAuthority();
        child.setId("2");
        child.setDataUrl("/bbbb");
        child.setDelState(0);
        child.setMenuClass("/img/bb.png");
        child.setMenuCode("code1");
        child.setMenuName("二级菜单");
        child.setMenuType("2");
        child.setCreateTime(new Date());
        child.setMenuState(0);
        child.setOrderNum(1);
        child.setParentMenucode("1");
        childs.add(child);
        sysAuthority.setChilds(childs);
        sysAuthorities.add(sysAuthority);

        return sysAuthorities;
    }

    //下拉框数据组装
    @PostMapping("/testVue")
    public Object testVue() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        sb.append("{val:'name',label:'姓名',checked:false},{val:'age',label:'年龄',checked:false},{val:'phone',label:'电话',checked:false} , {val:'card',label:'证件',checked:false}");
        sb.append("]");
        LiepFilterModel liepFilterModel = new LiepFilterModel();

        LiepFieldModel liepFieldModel = new LiepFieldModel();

        List<LiepFilterModel> liepFilterModels = new ArrayList<>();

        //liepFilterModel.setOriginalFields(liepFilterModel.getOriginalFields().add(liepFieldModel));


        liepFieldModel.setAliasName("字段1");
        liepFieldModel.setChecked(false);
        liepFieldModel.setDescribe("测试字段1");
        liepFieldModel.setFieldName("name");

        liepFilterModel.getOriginalFields().add(liepFieldModel);

        liepFieldModel = new LiepFieldModel();
        liepFieldModel.setAliasName("字段2");
        liepFieldModel.setChecked(true);
        liepFieldModel.setDescribe("测试字段2");
        liepFieldModel.setFieldName("age");

        liepFilterModel.getOriginalFields().add(liepFieldModel);

        liepFilterModel.setAliasName("SensorData");
        liepFilterModel.setClassFullName("com.test.SensorData");
        liepFilterModel.setDescribe("测试SensorData");


        liepFilterModels.add(liepFilterModel);

        liepFilterModel = new LiepFilterModel();

        liepFilterModel.setAliasName("SensorDetail");
        liepFilterModel.setClassFullName("com.test.SensorDetail");
        liepFilterModel.setDescribe("测试SensorDetail");


        liepFieldModel = new LiepFieldModel();
        liepFieldModel.setAliasName("字段3");
        liepFieldModel.setChecked(true);
        liepFieldModel.setDescribe("测试字段3");
        liepFieldModel.setFieldName("name1");

        liepFilterModel.getOriginalFields().add(liepFieldModel);

        liepFieldModel = new LiepFieldModel();
        liepFieldModel.setAliasName("字段4");
        liepFieldModel.setChecked(true);
        liepFieldModel.setDescribe("测试字段4");
        liepFieldModel.setFieldName("age1");
        liepFilterModel.getOriginalFields().add(liepFieldModel);

        liepFilterModels.add(liepFilterModel);
        return liepFilterModels;
    }

    @PostMapping("/testAccount")
    public Object testAccount() {
        Account account = new Account();
        account.setAccessExpiresSecond(1800);
        account.setRefreshExpiresSecond(2592000);
        account.setAppId("admin");
        account.setSecret("123456");

        account.setSalt("aaaaaaaaa");
        account.setPassword("jlfskdjfljsldfjsldjflsd");
        return account;
    }

    /**
     * 获取app全量数据
     * @return
     */
    @PostMapping("/getAppList")
    public Object getAppList( String appId) {
        List<Account> list = new ArrayList<>();
        Account a1 = new Account();
        a1.setAppId("admin1");
        a1.setPassword("123456");
        a1.setSalt("aaaaaaa");
        a1.setSecret("aaaaaa1");
        a1.setRefreshExpiresSecond(20);
        a1.setAccessExpiresSecond(10);
        Account a2 = new Account();
        a2.setAppId("admin2");
        a2.setPassword("654321");
        a2.setSalt("bbbbbbb");
        a2.setSecret("bbbbbb1");
        a2.setRefreshExpiresSecond(40);
        a2.setAccessExpiresSecond(20);
        Account a3 = new Account();
        a3.setAppId("admin3");
        a3.setPassword("678910");
        a3.setSalt("cccccc");
        a3.setSecret("ccccccc1");
        a3.setRefreshExpiresSecond(10);
        a3.setAccessExpiresSecond(5);
        list.add(a1);
        list.add(a2);
        list.add(a3);

        list.add(a1);
        list.add(a2);
        list.add(a3);

        list.add(a1);
        list.add(a2);
        list.add(a3);

        list.add(a1);
        list.add(a2);
        list.add(a3);
        /*Pager<Account> page = new Pager<>();
        page.setCount(10);*/
        Pager<Account> page = PageContext.getPager();

        int count;

        if(StringUtils.isNotBlank(appId)) {
            List<Account> searList = new ArrayList<>();
            for (Account acc : list) {
                if(StringUtils.equals(acc.getAppId(), appId)) {
                   searList.add(acc);
                }
            }
            count = searList.size();
            searList = pagination(searList);
            page.setCount(count);
            page.setData(searList);
            return page;
        }
        count = list.size();
        list = pagination(list);
        page.setCount(count);
        page.setData(list);
        return page;
    }

    private <T> List<T>  pagination(List<T> list) {
        Pager<T> page = PageContext.getPager();
        int size;
        if(list.size() >= page.getStart()+ page.getPageSize()) {
            size = page.getStart() + page.getPageSize();
        } else {
            size = list.size();
        }
        List<T> tmp = new ArrayList<>();
        for(int i = page.getStart(); i < size ; i++) {
            tmp.add(list.get(i));
        }

        return tmp;
    }

    /*class AppInfo {
        private String appId;
        private String accessExpiresSecond;
        private String refreshExpiresSecond;
    }*/

    //帐号信息
    class Account{

        private String appId;
        private String secret;
        private int accessExpiresSecond;
        private int refreshExpiresSecond;

        private String salt;
        private String password;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public int getAccessExpiresSecond() {
            return accessExpiresSecond;
        }

        public void setAccessExpiresSecond(int accessExpiresSecond) {
            this.accessExpiresSecond = accessExpiresSecond;
        }

        public int getRefreshExpiresSecond() {
            return refreshExpiresSecond;
        }

        public void setRefreshExpiresSecond(int refreshExpiresSecond) {
            this.refreshExpiresSecond = refreshExpiresSecond;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    //用于测试数据共享平台的 数据资源过滤
    class LiepFilterModel {
        //类全限定名
        private String classFullName;

        //资源别名
        private String aliasName;

        //描述
        private String describe;

        //该资源能够发送的数据字段
        private List<LiepFieldModel> concentFields;

        //用于存储该类原始数据
        private List<LiepFieldModel> originalFields;

        public String getClassFullName() {
            return classFullName;
        }

        public void setClassFullName(String classFullName) {
            this.classFullName = classFullName;
        }

        public String getAliasName() {
            return aliasName;
        }

        public void setAliasName(String aliasName) {
            this.aliasName = aliasName;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public List<LiepFieldModel> getConcentFields() {
            if(null == concentFields || concentFields.isEmpty()) {
                concentFields = new ArrayList<>();
            }
            return concentFields;
        }

        public void setConcentFields(List<LiepFieldModel> concentFields) {
            this.concentFields = concentFields;
        }

        public List<LiepFieldModel> getOriginalFields() {
            if(null == originalFields || originalFields.isEmpty()) {
                originalFields = new ArrayList<>();
            }
            return originalFields;
        }

        public void setOriginalFields(List<LiepFieldModel> originalFields) {
            this.originalFields = originalFields;
        }
    }

    //用于测试数据共享平台的 数据资源实体
    class LiepFieldModel {
        //字段别名
        private String aliasName;

        //字段描述
        private String describe;

        //属性名
        private String fieldName;

        //是否选中
        private boolean checked;

        public String getAliasName() {
            return aliasName;
        }

        public void setAliasName(String aliasName) {
            this.aliasName = aliasName;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }
    }
}
