package com.ozexpert.devicemeta;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;

import java.util.Formatter;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class DeviceMeta extends CordovaPlugin {

    private static Context ctx;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        ctx = this.cordova.getActivity().getApplicationContext();

        if (action.equals("getDeviceMeta")) {
            
            JSONObject r = new JSONObject();
            r.put("debug", this.isDebug());

            callbackContext.success(r);
        } else {
            return false;
        }
        return true;
    }

    private boolean isDebug() {
        try {
            if ((ctx.getPackageManager().getPackageInfo(
                ctx.getPackageName(), 0).applicationInfo.flags & 
                ApplicationInfo.FLAG_DEBUGGABLE) != 0) {
                //Debug and development mode
                return true;
            }
        } catch (NameNotFoundException e){
            // do nothing
        }
        return false;
    }
}
