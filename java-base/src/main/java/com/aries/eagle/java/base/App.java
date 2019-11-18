package com.aries.eagle.java.base;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import java.util.regex.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception {
        String path = "/config/api/{id}/{version}";
        HashMap<String, String> mapping = new HashMap<>();
        mapping.put("id", "55");
        mapping.put("version","aaa");
        String res = new App().testMatch(path, mapping);
        System.out.println(res);
//        System.out.println(path.charAt(3));
    }

    private String testMatch(String path, HashMap<String, String> mapping) throws Exception {
        StringBuilder resStr = new StringBuilder();
        boolean hasLeftBracket = false;
        int leftIndex = 0, rightIndex = 0;
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == '{') {
                if (!hasLeftBracket) {
                    hasLeftBracket = true;
                    leftIndex = i;
                } else {
                    throw new Exception("URL路径不合法");
                }
            } else if (path.charAt(i) == '}') {
                if (hasLeftBracket) {
                    rightIndex = i;
                    String str = path.substring(leftIndex + 1, rightIndex);
                    if (mapping.get(str) == null) {
                        throw new Exception("参数对应值不存在");
                    }
                    String ss = mapping.get(str);
                    resStr.append(ss);
                    hasLeftBracket = false;
                    leftIndex = 0;
                    rightIndex = 0;
                    continue;
                } else {
                    throw new Exception("URL路径不合法");
                }
            }
            if (!hasLeftBracket) {
                resStr.append(path.charAt(i));
            }
        }
        if (hasLeftBracket) {
            throw new Exception("URL路径不合法");
        }
        return resStr.toString();
    }

    private void testGroup() {
        String str = "/api/config/{id}";
        System.out.println(str.matches("(\\{[a-zA-Z0-9]*})"));
    }

    private void test() {
        Person p1 = new Person(0, "a");
        Person p2 = new Person(5, "b");
        Person p3 = new Person(10, "c");
        List<Person> personList = new ArrayList<>();
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
        Set<Integer> collect = personList.stream().map(Person::getId).collect(Collectors.toSet());
        System.out.println(collect.size() == 1 && collect.contains(0));
    }

    public void checkPath() {
        String path = "getVersion//id{versionId}";
        String[] str = path.split("/");
        for (String s : str) {
            System.out.println("s是否为null" + s == null);
            System.out.println("======" + s + "========");
            if (!(s.matches("[a-zA-Z0-9]+") || s.matches("\\{[a-zA-Z0-9]+}"))) {
                System.out.println("路径不符合规范-" + s);
            } else {
                System.out.println("路径符合规范-" + s);
            }
        }
    }

    public static final String DATE_STRING = "2017-04-25";
    public static final String P_COMM = "(\\d{4})-((\\d{2})-(\\d{2}))";

    private void regexPath() {
        Pattern pattern = Pattern.compile("(^/[a-zA-Z0-9]*$)");
        Matcher matcher = pattern.matcher("/config/{version}/get/{id}");
        matcher.find();//必须要有这句
        System.out.printf("\nmatcher.group(0) value:%s", matcher.group(0));
        System.out.printf("\nmatcher.group(1) value:%s", matcher.group(1));
        System.out.printf("\nmatcher.group(2) value:%s", matcher.group(2));
        System.out.printf("\nmatcher.group(3) value:%s", matcher.group(3));
        System.out.printf("\nmatcher.group(4) value:%s", matcher.group(4));
    }

    private void getPath() {
        String line = "/config/{version}/get/{id}";
        String[] strLines = line.substring(1).split("/");

        StringBuilder result = new StringBuilder();
        if (line.matches(".*\\{[a-zA-Z0-9]*\\}")) {
            for (String str : strLines) {
                result.append("/");
                if (str.startsWith("{") && str.endsWith("}")) {
                    str = str.substring(1, str.length() - 1);
                    result.append(str);
                } else {
                    result.append(str);
                }
            }
        }
        System.out.println(result);
    }


    private void testMap() {
        HashMap<String, String> paramMap = new HashMap();
        paramMap.put("a", "aaa");
        paramMap.put("b", "bbb");

        HashMap<String, String> headerMap = new HashMap();
        headerMap.put("b", "ccc");
        headerMap.put("d", "ddd");

        paramMap.putAll(headerMap);


        for (Map.Entry entry : paramMap.entrySet()) {
            System.out.println(entry.getKey() + "   " + entry.getValue());
        }
    }


}

