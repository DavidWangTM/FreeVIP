package tm.davidwang.httpmanage;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import tm.davidwang.tools.BaseTools;
import tm.davidwang.tools.Constant;
import tm.davidwang.tools.TimeDate;

/**
 * Created by mac on 15/7/9.
 */
public class HttpManage {

    public static AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    private static HttpManage httpManage;

    public static HttpManage getInstance() {
        if (httpManage == null) {
            Class var0 = HttpManage.class;
            synchronized (HttpManage.class) {
                if (httpManage == null) {
                    httpManage = new HttpManage();
                }
            }
        }
        return httpManage;
    }

    public void GetFreeVip(Context context,int type, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        RequestParams params = new RequestParams();
        params.put("nt", TimeDate.getNowTimeline()+"");
        params.put("appid", BaseTools.getInstance().GetPhoneUUID(context));
        params.put("type", type+"");
        asyncHttpClient.get(context, Constant.URL, params, asyncHttpResponseHandler);
    }

    public void GetFreeVip_VIP(Context context,int type, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        RequestParams params = new RequestParams();
        params.put("nt", TimeDate.getNowTimeline()+"");
        params.put("appid", BaseTools.getInstance().GetPhoneUUID(context));
        params.put("type", type+"");
        asyncHttpClient.get(context, Constant.VIPURL, params, asyncHttpResponseHandler);
    }

}
