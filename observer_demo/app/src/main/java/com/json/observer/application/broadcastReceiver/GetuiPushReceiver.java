package com.json.observer.application.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.igexin.sdk.PushConsts;

/**
 * Created by json on 16/2/17.
 */
public class GetuiPushReceiver extends BroadcastReceiver {
    public final String TAG=getClass().getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "onReceive() action=" + bundle.getInt("action"));
        switch (bundle.getInt(PushConsts.CMD_ACTION)) {
            case PushConsts.GET_CLIENTID:
                // 获取ClientID(CID)
                // 第三方应用通常需要将CID上传到第三方服务器，并且将当前用户帐号和CID进行关联，以便日后通过用户帐号查找CID进行消息推送。
                // 部分特殊情况下CID可能会发生变化，为确保应用服务端保存的最新的CID，应用程序在每次获取CID广播后，如果发现CID出现变化，需要重新进行一次关联绑定
                String cid = bundle.getString("clientid");
                Log.d(TAG, "Got CID:" + cid);

                break;
            case PushConsts.GET_MSG_DATA:
                // 获取透传（payload）数据
                String taskid = bundle.getString("taskid");
                String messageid = bundle.getString("messageid");
                byte[] payload = bundle.getByteArray("payload");
                if (payload != null)
                {
                    String data = new String(payload);
                    Log.d(TAG, "Got Payload:" + data);
                    // TODO:接收处理透传（payload）数据
                }
                break;
            //添加其他case
            //.........
            default:
                break;
        }
    }
}
