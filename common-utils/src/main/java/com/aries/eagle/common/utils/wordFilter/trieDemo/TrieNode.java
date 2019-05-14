package com.aries.eagle.common.utils.wordFilter.trieDemo;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    //当前节点是否为词末
    private boolean end = false;
    //存放
    Map<Character,TrieNode> subNode = new HashMap<>();
    public void addNode(Character key,TrieNode node){
        subNode.put(key,node);
    }
    TrieNode getsubNode(Character key){
        return subNode.get(key);
    }
    void setKeywordEnd(boolean end){
        this.end = end;
    }
    boolean isKeywordEnd(){
        return end;
    }
}
