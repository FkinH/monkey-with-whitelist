package com.fkinh.monkey;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: fkinh26@gmail.com
 * Date: 16/1/20
 */
public class WhiteListHandler {

    public static WhiteListHandler instance;

    private List<WhiteListNode> whiteListNodes = new ArrayList<WhiteListNode>();;

    public synchronized static WhiteListHandler getInstance(){
        if(instance == null){
            instance = new WhiteListHandler();
        }
        return instance;
    }

    private WhiteListHandler(){
//        WhiteListNode node = new WhiteListNode("com.android.tv.settings", "com.android.tv.settings.about.AboutActivity",
//                "恢复出厂设置", "");
//        this.whiteListNodes.add(node);
    }

    public List<WhiteListNode> getWhiteListNodes(){
        return this.whiteListNodes;
    }

    public List<String> getTextList(String activity){
        List<String> list = new ArrayList<String>();
        for(WhiteListNode node:whiteListNodes){
            String text = node.getText();
            if(text != null | !"".equals(text)){
                if(activity.equals(node.getActivity())){
                    list.add(text);
                }
            }
        }
        return list;
    }

    public List<String> getIdList(String activity){
        List<String> list = new ArrayList<String>();
        for(WhiteListNode node:whiteListNodes){
            String id = node.getId();
            if(id != null | !"".equals(id)){
                if(activity.equals(node.getActivity())){
                    list.add(id);
                }
            }
        }
        return list;
    }
}