package com.fkinh.monkey;

/**
 * Author: fkinh26@gmail.com
 * Date: 2016-01-20
 */
public class WhiteListNode {

    private String pkg;

    private String activity;

    private String text;

    private String id;

    public WhiteListNode(String pkg, String activity, String text, String id) {
        this.pkg = pkg;
        this.activity = activity;
        this.text = text;
        this.id = id;
    }

    public String getPkg() {
        return pkg;
    }

    public String getActivity() {
        return activity;
    }

    public String getText() {
        return text;
    }

    public String getId() {
        return id;
    }
}
