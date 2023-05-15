package com.study.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 提取IP地址
 */
public class Test03 {

    public static void main(String[] args) {
        String str = "公有地址\n" +
                "公有地址（Public address）由Inter NIC（Internet Network Information Center因特网信息中心）负责。这些IP地址分配给注册并向Inter NIC提出申请的组织机构。通过它直接访问因特网。\n" +
                "私有地址\n" +
                "私有地址（Private address）属于非注册地址，专门为组织机构内部使用。\n" +
                "以下列出留用的内部私有地址\n" +
                "A类 10.0.0.0--10.255.255.255\n" +
                "B类 172.16.0.0--172.31.255.255\n" +
                "C类 192.168.0.0--192.168.255.255";
        Pattern pattern = Pattern.compile("\\d+\\.\\d+\\.\\d+\\.\\d+");
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()){
            System.out.println("找到IP地址为："+ matcher.group(0));
        }
    }

}
