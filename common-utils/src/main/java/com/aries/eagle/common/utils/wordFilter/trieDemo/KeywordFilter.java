package com.aries.eagle.common.utils.wordFilter.trieDemo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class KeywordFilter {
    //  private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public static Map<String, HashMap> currentMap = new ConcurrentHashMap<String, HashMap>();
    public static Map nowhash = null;
    public static Object wordMap;// map子节点

    // 不建立对象
    private KeywordFilter() {
    }

    private static String getKey(int id) {
        return "id" + id;
    }

    /*
     * 说明:清扫内容
     */
    public static void clear() {
        try {
            currentMap.clear();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    /*
     * 说明:各个渠道的过滤字符
     */
    public static void saveKeywords(int id, List<String> keywords) {
        try {
            Map tempAllMap = currentMap;
            String key = getKey(id);
            int l = keywords.size();
            int il;
            Map tempMap;
            for (int i = 0; i < l; i++) {
                String key2 = keywords.get(i).trim();// 去掉空白
                nowhash = currentMap;
                il = key2.length();
                for (int j = 0; j < il; j++) {
                    char word = key2.charAt(j);
                    tempMap = (Map) nowhash.get(word);
                    wordMap = nowhash.get(word);
                    if (wordMap != null) {// 检查数据
                        if (!tempMap.containsKey(key)) {
                            nowhash.put(key, 0);
                        }
                        nowhash = (HashMap) wordMap;
                    } else {
                        HashMap<String, String> newWordHash = new HashMap<String, String>();
                        newWordHash.put(key, "0");
                        nowhash.put(word, newWordHash);
                        nowhash = newWordHash;
                    }
                    if (j == il - 1) {
                        nowhash.put(key, "1");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            nowhash = null;
            wordMap = null;
        }
    }

    /*
     * 说明:替换掉对应的渠道规定掉敏感字
     */
    public static List<String> repword(int id, String txt) {
        Map tempMap = currentMap;
        List<String> result = new ArrayList<String>();
        String key = getKey(id);
        nowhash = currentMap;
        int l = txt.length();
        char word;
        String keywordStr = "";
        String keyStatu;
        StringBuilder keyword = new StringBuilder();// 敏感字
        for (int i = 0; i < l; i++) {
            word = txt.charAt(i);
            wordMap = nowhash.get(word);
            if (wordMap != null) {// 找到类似敏感字的字体，开始查询
                keyword.append(word);
                Object te = nowhash = (HashMap) wordMap;
                // 遍历到这一步，就符合完整的关键字模板
                if (nowhash.get(key) != null
                        && nowhash.get(key).toString().equals("1")) {// 确定是敏感字，开始替换
                    if (i < l - 1 && nowhash.get(txt.charAt(i + 1)) != null) {// 优先过滤长敏感词，去掉就槟城了优先过滤段敏感词
                        continue;
                    }
                    txt = txt.replaceAll(keyword.toString(), "*");
                    nowhash = currentMap;
                    keywordStr += keyword.toString() + ",";
                    i = i - keyword.length() + 1;
                    l = txt.length();// 重新获取字符长度
                    keyword.delete(0, keyword.length());// 清空数据
                }
            } else {// 这个字不是敏感字，直接排除
                nowhash = currentMap;
                keyword.delete(0, keyword.length());// 清空数据
                continue;
            }
        }
        // 清除内存指向
        nowhash = null;
        wordMap = null;
        result.add(txt);
        result.add(keywordStr.length() - 1 > 0 ? keywordStr.substring(0,
                keywordStr.length() - 1) : keywordStr);
        return result;
    }

    /*
     * 说明:检查是否存在敏感字
     */
    private static int checkKeyWords(String txt, int id, int begin) {
        int result = 0;
        String key = getKey(id);
        try {
            nowhash = currentMap;
            int l = txt.length();
            char word = 0;
            for (int i = begin; i < l; i++) {
                word = txt.charAt(i);
                wordMap = nowhash.get(word);
                if (wordMap != null) {
                    result++;
                    nowhash = (HashMap) wordMap;
                    if (((String) nowhash.get(key)).equals("1")) {
                        nowhash = null;
                        wordMap = null;
                        return result;
                    }
                } else {
                    result = 0;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            nowhash = null;
            wordMap = null;
            return result;
        }
    }

    /*
     * 说明:返回检查的文本中包含的敏感字
     */
    public static String getTxtKeyWords(String txt, int id) {
        String result = null;
        StringBuilder temp = new StringBuilder();
        String key;
        int l = txt.length();
        for (int i = 0; i < l; ) {
            int len = checkKeyWords(txt, id, i);
            if (len > 0) {
                key = (txt.substring(i, i + len));// 挑选出来的关键字
                temp.append(key + ",");
                txt = txt.replaceAll(key, "");// 挑选出来的关键字替换成空白，加快挑选速度
                l = txt.length();
            } else {
                i++;
            }
        }
        if (temp.length() > 0) {
            result = temp.substring(0, temp.length() - 1);
        }
        return result;
    }

    /*
     * 说明:判断文中是否包含渠道规定的敏感字
     */
    public boolean isKeyWords(String txt, int id) {
        for (int i = 0; i < txt.length(); i++) {
            int len = checkKeyWords(txt, id, i);
            if (len > 0) {
                return true;
            }
        }
        return false;
    }
}




