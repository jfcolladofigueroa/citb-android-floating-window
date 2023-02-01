package com.citb.plugin;

import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.getcapacitor.Bridge;
import com.getcapacitor.JSObject;
import com.getcapacitor.Logger;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "FloatingWindow")
public class FloatingWindowPlugin extends Plugin {

    private AlertDialog dialog;

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");
        JSObject ret = new JSObject();
        call.resolve(ret);
    }

    @PluginMethod
    public void minimize(PluginCall call) {
        if (checkOverlayDisplayPermission()) {
            this.bridge.getContext().startService(new Intent(this.bridge.getContext(), FloatingWindowGFG.class));
//            this.bridge.getActivity().finish();
            this.bridge.getActivity().moveTaskToBack(true);
        } else {
            requestOverlayDisplayPermission();
        }
        call.resolve();
    }


    @PluginMethod
    public void sendMessage(PluginCall call) {
//        String message = "Hello from MyPlugin!";
//        JSObject result = new JSObject();
//        result.put("message", message);
//        call.resolve(result);
        Context context = this.bridge.getContext();
        Intent intent = new Intent(context, this.bridge.getContext().getClass());
        context.startActivity(intent);
    }

    public void startCommunication(String kind) {
        JSObject data = new JSObject();
        data.put("message", kind);
        notifyListeners("messageReceived", data);
    }

    public void prueba(){
//        String packageName = this.bridge.getContext().getPackageName();
//        Intent backToHome = this.bridge.getContext().getPackageManager().getLaunchIntentForPackage(packageName);
//        String packageName = this.bridge.getActivity().getPackageName();
//        Intent backToHome = this.bridge.getActivity().getPackageManager().getLaunchIntentForPackage(packageName);
        Intent backToHome = new Intent(this.bridge.getContext(), FloatingWindowPlugin.class);
//        backToHome.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        backToHome.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        this.bridge.getActivity().startActivity(backToHome);
    }

    private boolean checkOverlayDisplayPermission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this.getContext())) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    private void requestOverlayDisplayPermission() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setCancelable(true);
        builder.setTitle("Screen Overlay Permission Needed");
        builder.setMessage("Enable 'Display over other apps' from System Settings.");
        FloatingWindowPlugin floatingWindowPluginThat = this;
        builder.setPositiveButton("Open Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" +  floatingWindowPluginThat.getContext().getPackageName()));
                floatingWindowPluginThat.getBridge()
                        .getActivity()
                        .startActivityForResult(intent,floatingWindowPluginThat.getBridge().getActivity().RESULT_OK);
            }
        });
        dialog = builder.create();
        dialog.show();
    }

    private boolean isMyServiceRunning() {
        ActivityManager manager = (ActivityManager) this.getContext().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (FloatingWindowGFG.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}
