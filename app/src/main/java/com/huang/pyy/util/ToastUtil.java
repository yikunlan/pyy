package com.huang.pyy.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by hyk on 2018/11/12.
 */

public class ToastUtil {

    public static void showMsg(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
