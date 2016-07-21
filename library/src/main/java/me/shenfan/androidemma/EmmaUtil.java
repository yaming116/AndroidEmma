package me.shenfan.androidemma;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Sun on 2016/7/20.
 */
public class EmmaUtil {

    public static final String DUMP_ACTION = "me.shenfan.DUMP_INTERMEDIATE_COVERAGE";


    public static void dump(Context context){
        Intent intent = new Intent(DUMP_ACTION);
        intent.setPackage(context.getPackageName());
        context.sendBroadcast(intent);
    }
}
