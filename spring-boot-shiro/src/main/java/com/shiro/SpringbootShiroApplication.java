package com.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.regex.Pattern;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.**.dao")
public class SpringbootShiroApplication {
    public static void main(String args []) {
        SpringApplication.run(SpringbootShiroApplication.class, args);
        //需要去除后台打印的springboot花纹使用下面的
        /*SpringApplication app = new SpringApplication(SpringbootShiroApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);*/

        /*String url = "http://www.csfwfwn.cn?1231=sjkfjkf&sfwfw=";
        url = "http://59.172.39.236:8081/lifeline/subscribe/deviceSubscribe";
        url = "http://127.0.0.1:8080/a?a=1";
        String regex = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";

        regex =  "^(http|https|ftp)//://([a-zA-Z0-9//.//-]+(//:[a-zA-"
                + "Z0-9//.&%//$//-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"
                + "2}|[1-9]{1}[0-9]{1}|[1-9])//.(25[0-5]|2[0-4][0-9]|[0-1]{1}"
                + "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)//.(25[0-5]|2[0-4][0-9]|"
                + "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)//.(25[0-5]|2[0-"
                + "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"
                + "-9//-]+//.)*[a-zA-Z0-9//-]+//.[a-zA-Z]{2,4})(//:[0-9]+)?(/"
                + "[^/][a-zA-Z0-9//.//,//?//'///////+&%//$//=~_//-@]*)*$";
        Pattern pattern = Pattern.compile(regex);
        if (pattern.matcher(url).matches()) {
            System.out.println("是正确的网址");
        } else {
            System.out.println("非法网址");
        }
        regex = "^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$";
        String url1 = "http://www.xx.com";
        String url2 = "w.xx.com";
        String url3 = "http://w.xx.com";
        String url4 = "http://127.0.0.1:8080/a?sdfjl=1&a&b=1";
        regex = "^((https|http|ftp|rtsp|mms)?://)"
                + "+(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?"
                + "(([0-9]{1,3}\\.){3}[0-9]{1,3}"
                + "|"
                + "([0-9a-z_!~*'()-]+\\.)*"
                + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\."
                + "[a-z]{2,6})"
                + "(:[0-9]{1,4})?"
                + "((/?)|"
                + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
        regex = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";
        pattern = Pattern
                .compile(regex);
        System.out.println(pattern.matcher(url1).matches());
        System.out.println(pattern.matcher(url2).matches());
        System.out.println(pattern.matcher(url3).matches());
        System.out.println(pattern.matcher(url4).matches());*/
    }

}
