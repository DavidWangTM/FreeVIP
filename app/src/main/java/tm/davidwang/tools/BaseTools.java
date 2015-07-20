package tm.davidwang.tools;

import android.app.Activity;
import android.content.Context;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by mac on 15/7/9.
 */
public class BaseTools {

    private Toast mToast;
    private static BaseTools baseTools;

    public static BaseTools getInstance() {
        if (baseTools == null) {
            baseTools = new BaseTools();
        }
        return baseTools;
    }

    /**
     * 显示提示信息
     *
     * @param text 提示文本
     */
    public void showToast(Context context,String text) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    /**
     * 清空Toast
     */
    public void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

    /**
     * 打印LOGO
     */
    public void ShowLog(String text){
        Log.e("1",text);
    }

    /**
     * 得到屏幕的宽
     */
    public int GetPhoneWidth(Activity activity){
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        return dm.widthPixels;

    }

    /**
     * 得到屏幕的高
     */
    public int GetPhoneHeight(Activity activity){
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * 得到系统设备号
     */
    public String GetPhoneUUID(Context context){
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     */
    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     */
    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}
