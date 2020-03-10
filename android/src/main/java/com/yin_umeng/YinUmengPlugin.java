package com.yin_umeng;

import android.app.Activity;
import android.content.Context;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * YinUmengPlugin
 */
public class YinUmengPlugin implements MethodCallHandler {
    private Activity activity;

    /**
     * Plugin registration.
     */
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel =
                new MethodChannel(registrar.messenger(), "yin_umeng");
        channel.setMethodCallHandler(new YinUmengPlugin(registrar.activity()));
    }

    private YinUmengPlugin(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        if (call.method.equals("init")) {
            init(call, result);
        } else if (call.method.equals("beginPageView")) {
            MobclickAgent.onPageStart((String) call.argument("name"));
            MobclickAgent.onResume(activity);
            result.success(null);
        } else if (call.method.equals("endPageView")) {
            MobclickAgent.onPageEnd((String) call.argument("name"));
            MobclickAgent.onPause(activity);
            result.success(null);
        } else if (call.method.equals("logEvent")) {
            MobclickAgent.onEvent((Context) activity, (String) call.argument("name"));
            result.success(null);
        } else {
            result.notImplemented();
        }
    }

    public void init(MethodCall call, Result result) {
        UMConfigure.setLogEnabled(true);
        String channel = "Umeng";
        if (call.hasArgument("channel")) {
            channel = call.argument("channel");
        }
        UMConfigure.init(activity, (String) call.argument("key"), channel,
                UMConfigure.DEVICE_TYPE_PHONE, null);
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.MANUAL);
        result.success(true);
    }
}
