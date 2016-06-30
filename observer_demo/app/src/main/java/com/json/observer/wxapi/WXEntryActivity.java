package com.json.observer.wxapi;

import android.app.Activity;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;

/**
 * 接收微信的请求及返回值
 * Created by json on 16/3/30.
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
  @Override public void onReq(BaseReq baseReq) {
    //当微信发送请求到你的应用，将通过IWXAPIEventHandler接口的onReq方法进行回调，

  }

  @Override public void onResp(BaseResp baseResp) {
    //当微信发送请求到你的应用，应用请求微信的响应结果将通过onResp回调。
  }
}
