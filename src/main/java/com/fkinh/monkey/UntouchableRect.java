package com.fkinh.monkey;

import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: fkinh26@gmail.com
 * Date: 2016-01-20
 */
public class UntouchableRect implements UntouchableRectFilter{

    public static UntouchableRect instance;

    private FireFightMonitor monitor;

    private WhiteListHandler handler;

    private List<Rect> rects = new ArrayList<Rect>();

    public synchronized static UntouchableRect getInstance(){
        if(instance == null){
            instance = new UntouchableRect();
        }
        return instance;
    }

    private UntouchableRect(){
        this.monitor = FireFightMonitor.getInstance();
        this.handler = WhiteListHandler.getInstance();
    }

    @Override
    public boolean accept(String activity, int x, int y) {
        System.out.println("event:(" + x + ", " + y +")");
        try {
            this.rects.clear();
            if(activity != null && !"".equals(activity)){
                List<String> textList = handler.getTextList(activity);
                List<String> idList = handler.getIdList(activity);
                AccessibilityNodeInfo infos = monitor.getUibridge().getRootInActiveWindow();
                if(!textList.isEmpty()){
                    for(String text:textList){
                        addRect(infos.findAccessibilityNodeInfosByText(text));
                    }
                }
                if(!idList.isEmpty()){
                    for(String id:idList){
                        addRect(infos.findAccessibilityNodeInfosByViewId(id));
                    }
                }
                if(rects.isEmpty()){
                    System.out.println("no untouchable rect found.");
                    return false;
                }
                for(Rect rect: rects){
                    System.out.println(rect);
                    if(rect.contains(x, y)){
                        System.out.println("this rect contains this event, can't inject.");
                        return true;
                    }
                }
            }
            else {
                System.out.println("activity name not found, do nothing.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    private void addRect(List<AccessibilityNodeInfo> nodes){
        if (nodes.isEmpty()){
            return;
        }
        for (AccessibilityNodeInfo node : nodes) {
            Rect rect = new Rect();
            if(node.isClickable()){
                node.getBoundsInScreen(rect);
            }
            else {
                System.out.println("node is unclickable, found parent, x:center" + rect.centerX() + " y:center" + rect.centerY());
                node.getParent().getBoundsInScreen(rect);
            }
            this.rects.add(rect);
        }
    }
}


interface UntouchableRectFilter {
    boolean accept(String activity, int x, int y);
}