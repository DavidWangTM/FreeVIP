package tm.davidwang.tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mac on 15/7/10.
 */
public class SaveDataTools {

    private static SaveDataTools saveDataTools;
    private static SharedPreferences share ;
    private static String NAME = "FREE_VIP";
    private SharedPreferences.Editor editor;

    public static SaveDataTools getInstance(Context context) {
        if (saveDataTools == null) {
            saveDataTools = new SaveDataTools();
            share = context.getSharedPreferences(NAME,
                    Context.MODE_PRIVATE);
        }
        return saveDataTools;
    }

    public void setContent(String Key,String content){
        editor = share.edit();
        editor.putString(Key,content);
        editor.commit();
    }

    public void setContent(String Key,int content){
        editor = share.edit();
        editor.putInt(Key, content);
        editor.commit();
    }

    public void setContent(String Key,float content){
        editor = share.edit();
        editor.putFloat(Key, content);
        editor.commit();
    }

    public void setContent(String Key,boolean content){
        editor = share.edit();
        editor.putBoolean(Key, content);
        editor.commit();
    }

    public String getContent(String key){
        return share.getString(key, "");
    }

    public int getContentInt(String key){
        return share.getInt(key,0);
    }

    public float getContentFloat(String key){
        return share.getFloat(key,0);
    }

    public boolean getContentBoolean(String key){
        return share.getBoolean(key,false);
    }


}
