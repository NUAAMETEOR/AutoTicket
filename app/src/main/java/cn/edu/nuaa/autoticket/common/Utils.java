/**
 * @ClassName: Utils.java
 * @Description:
 * @author Meteor
 * @version V1.0
 * @Date 2018/4/8 22:22
 */
package cn.edu.nuaa.autoticket.common;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;

public class Utils {
    public static Activity getParentActivity(View v) {
        Context parent =v.getContext();
        while (parent instanceof ContextWrapper) {
            if (parent instanceof Activity) {
                return (Activity) parent;
            } else {
                parent=((ContextWrapper) parent).getBaseContext();
            }
        }
        return null;
    }
}
