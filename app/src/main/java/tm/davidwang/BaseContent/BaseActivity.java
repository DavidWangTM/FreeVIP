package tm.davidwang.BaseContent;

import android.app.Activity;
import android.os.Bundle;

import tm.davidwang.tools.BaseTools;
import tm.davidwang.views.ShowDialog;
import tm.davidwang.vip.R;

/**
 * Created by mac on 15/7/9.
 */
public class BaseActivity extends Activity {

    private ShowDialog showDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showDialog = new ShowDialog(this, R.style.Theme_dialog);
    }

    /**
     * 获取资源
     */
    protected void findID() {

    }

    /**
     * 监听
     */
    protected void Listener() {
    }

    /**
     * 对传递数据
     */
    protected void initIntent() {

    }

    /**
     * 初始数据
     */
    public void InData() {

    }

    /**
     * 初始DIALOG
     */
    public void showDialog() {
        showDialog.show();
    }

    /**
     * 结束DIALOG
     */
    public void didDialog() {
        if (showDialog.isShowing()){
            showDialog.dismiss();
        }
    }

    /**
     * 提示框
     */
    public void showToast(String content) {
        BaseTools.getInstance().showToast(this,content);
    }

    /**
     * 打印
     */
    public void showLog(String content) {
        BaseTools.getInstance().ShowLog(content);
    }


}
