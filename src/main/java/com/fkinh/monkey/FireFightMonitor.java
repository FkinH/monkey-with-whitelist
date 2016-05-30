package com.fkinh.monkey;

import android.app.UiAutomation;
import android.app.UiAutomationConnection;
import android.os.HandlerThread;
import android.view.accessibility.AccessibilityEvent;

/**
 * Author: fkinh26@gmail.com
 * Date: 2016-01-20
 */
public class FireFightMonitor {

    public static FireFightMonitor instance;

    public static synchronized FireFightMonitor getInstance(){
        if(instance == null){
            instance = new FireFightMonitor();
        }
        return instance;
    }

    private UiAutomation uiTestAutomationBridge;

    private FireFightMonitor(){
        HandlerThread handlerThread = new HandlerThread("accessbility worker");
        handlerThread.setDaemon(true);
        handlerThread.start();
        this.uiTestAutomationBridge = new UiAutomation(handlerThread.getLooper(),
                new UiAutomationConnection());
    }

    public void run() {
        try {
            uiTestAutomationBridge.connect();
            uiTestAutomationBridge.setOnAccessibilityEventListener(new UiAutomation.OnAccessibilityEventListener() {
                @Override
                public void onAccessibilityEvent(AccessibilityEvent event) {
                    try {
                        switch (event.getEventType()){
                            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                                break;
                            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                                break;
                            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                                break;
                            case AccessibilityEvent.TYPE_WINDOWS_CHANGED:
                                break;
                            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                                break;
                            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                                break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UiAutomation getUibridge(){
        return uiTestAutomationBridge;
    }

}
