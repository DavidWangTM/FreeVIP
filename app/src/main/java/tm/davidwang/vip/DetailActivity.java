package tm.davidwang.vip;

import android.app.ActionBar;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import cn.waps.AppConnect;
import cn.waps.UpdatePointsNotifier;
import tm.davidwang.BaseContent.BaseActivity;
import tm.davidwang.baseinterface.DialogBack;
import tm.davidwang.httpmanage.HttpManage;
import tm.davidwang.model.VipInfo;
import tm.davidwang.tools.BaseTools;
import tm.davidwang.tools.SaveDataTools;
import tm.davidwang.views.TypeDialog;

/**
 * Created by mac on 15/7/9.
 */
public class DetailActivity extends BaseActivity implements UpdatePointsNotifier,View.OnClickListener,DialogBack{

    private TextView zh,mm,money_text,name_text;
    private int type;
    private Button save_zh,save_mm;
    private int money;
    final Handler mHandler = new Handler();
    private String name1;
    private String name2;
    private TypeDialog typeDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        type = getIntent().getIntExtra("type",0);
        name1 = "zh"+(type+"");
        name2 = "mm"+(type+"");
        findID();
        GetName();
        InData();
        Listener();
    }

    @Override
    protected void findID() {
        super.findID();
        typeDialog = new TypeDialog(DetailActivity.this,R.style.Theme_dialog,this);
        zh = (TextView)findViewById(R.id.zh);
        mm = (TextView)findViewById(R.id.mm);
        save_zh = (Button)findViewById(R.id.save_zh);
        save_mm = (Button)findViewById(R.id.save_mm);
        name_text = (TextView)findViewById(R.id.name_text);
        money_text = (TextView)findViewById(R.id.money_text);
        zh.setText(SaveDataTools.getInstance(DetailActivity.this).getContent(name1));
        mm.setText(SaveDataTools.getInstance(DetailActivity.this).getContent(name2));
    }

    @Override
    protected void Listener() {
        super.Listener();
        save_zh.setOnClickListener(this);
        save_mm.setOnClickListener(this);
    }

    @Override
    public void InData() {
        super.InData();
        showDialog();
        HttpManage.getInstance().GetFreeVip(DetailActivity.this, type, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                didDialog();
                String content = new String(responseBody);
                BaseTools.getInstance().ShowLog(content);
                VipInfo info = null;
                try {
                    info = JSON.parseObject(content,VipInfo.class);
                    zh.setText(info.ACCOUNT);
                    mm.setText(info.PASS);

                    SaveDataTools.getInstance(DetailActivity.this).setContent(name1,zh.getText().toString());
                    SaveDataTools.getInstance(DetailActivity.this).setContent(name2,mm.getText().toString());
                }catch (Exception e){
//                    BaseTools.getInstance().showToast(DetailActivity.this, getResources().getString(R.string.Loaderr_1));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                didDialog();
                BaseTools.getInstance().showToast(DetailActivity.this,getResources().getString(R.string.Loaderr_0));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        ClipboardManager cmb = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip;
        switch (v.getId()) {
            case R.id.save_zh:
                clip = ClipData.newPlainText("simple text", zh.getText().toString());
                cmb.setPrimaryClip(clip);
                showToast("账号复制成功");
                break;
            case R.id.save_mm:
                clip = ClipData.newPlainText("simple text", mm.getText().toString());
                cmb.setPrimaryClip(clip);
                showToast("密码复制成功");
                break;
            default:
                break;
        }
    }


    public void SXZHOnclick(View view){
        InData();
    }

    public void VipOnclick(View view){
        if (money > 20){
            GetVip();
        }else{
            typeDialog.show();
        }


    }

    private void GetVip(){
        showDialog();
        HttpManage.getInstance().GetFreeVip_VIP(this, type, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                didDialog();
                String content = new String(responseBody);
                showLog(content);
                BaseTools.getInstance().ShowLog(content);
                VipInfo info = null;

                try {
                    info = JSON.parseObject(content,VipInfo.class);
                    zh.setText(info.ACCOUNT);
                    mm.setText(info.PASS);
                    SaveDataTools.getInstance(DetailActivity.this).setContent(name1,zh.getText().toString());
                    SaveDataTools.getInstance(DetailActivity.this).setContent(name2,mm.getText().toString());
                    AppConnect.getInstance(DetailActivity.this).spendPoints(20, DetailActivity.this);
                }catch (Exception e){
                    BaseTools.getInstance().showToast(DetailActivity.this, getResources().getString(R.string.Loaderr_1));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                didDialog();
//                BaseTools.getInstance().showToast(DetailActivity.this,getResources().getString(R.string.Loaderr_0));
            }
        });

    }

    /**
     * AppConnect.getPoints()方法的实现，必须实现
     *
     * @param currencyName
     *            虚拟货币名称.
     * @param pointTotal
     *            虚拟货币余额.
     */
    public void getUpdatePoints(String currencyName, int pointTotal) {
        money = pointTotal;

        mHandler.post(mUpdateResults);
    }
    /**
     * AppConnect.getPoints() 方法的实现，必须实现
     *
     * @param error
     *            请求失败的错误信息
     */
    public void getUpdatePointsFailed(String error) {
//        displayPointsText = error;
        money = 0;
        mHandler.post(mUpdateResults);
    }


    @Override
    protected void onResume() {
        // 从服务器端获取当前用户的虚拟货币.
        // 返回结果在回调函数getUpdatePoints(...)中处理
        AppConnect.getInstance(this).getPoints(this);
        super.onResume();

    }

    private void GetName(){
        String name = "";
        switch (type){
            case 0:
                name = "爱奇艺VIP";
                break;
            case 1:
                name = "优酷VIP";
                break;
            case 2:
                name = "迅雷VIP";
                break;
            case 3:
                name = "土豆VIP";
                break;
            case 4:
                name = "乐视VIP";
                break;
            case 5:
                name = "搜狐VIP";
                break;
            default:
                break;
        }
        name_text.setText(name);
    }

    // 创建一个线程
    final Runnable mUpdateResults = new Runnable() {
        public void run() {
            if (money_text != null) {
                money_text.setText("我的积分:"+money);
            }
        }
    };

    public void SureOnclick(){
        AppConnect.getInstance(this).showOffers(this);
    }

    public void CancelOnclick(){

    }


}
