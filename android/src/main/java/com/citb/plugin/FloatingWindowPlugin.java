package com.citb.plugin;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import com.getcapacitor.JSObject;
import com.getcapacitor.Logger;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

@CapacitorPlugin(name = "FloatingWindow")

public class FloatingWindowPlugin extends Plugin {

    private AlertDialog dialog;

    @Override
    public void load() {
        super.load();
        // Register to receive messages.
        // This is just like [[NSNotificationCenter defaultCenter] addObserver:...]
        // We are registering an observer (mMessageReceiver) to receive Intents
        // with actions named "custom-event-name".
        LocalBroadcastManager.getInstance(this.getContext()).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-event-name"));
    }

    // Our handler for received Intents. This will be called whenever an Intent
    // with an action named "custom-event-name" is broadcasted.
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String message = intent.getStringExtra("message");
//            Log.d("receiver", "Got message: " + message);
            notifyToApp(message);
        }
    };

    @PluginMethod
    public void minimize(PluginCall call) {
        if (checkOverlayDisplayPermission()) {
            Intent serviceIntent = new Intent(this.bridge.getContext(), FloatingWindowGFG.class);
            this.bridge.getContext().startService(serviceIntent);
            //Cerrar
//            this.bridge.getActivity().finish();
            //Poner en background
//            this.bridge.getActivity().moveTaskToBack(true);
        } else {
            requestOverlayDisplayPermission();
        }
        call.resolve();
    }

    public void notifyToApp(String event) {
        Logger.error("startCommunication " + event);
        JSObject data = new JSObject();
        data.put("message", event);
        notifyListeners("floatingControlAction", data);
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
