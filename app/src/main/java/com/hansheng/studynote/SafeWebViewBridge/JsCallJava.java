package com.hansheng.studynote.SafeWebViewBridge;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;

/**
 * Created by hansheng on 16-9-1.
 */

public class JsCallJava {

    private final static String TAG="JsCallJava";
    private final static String RETURN_RESULT_FORMAT="{\\\"code\\\": %d, \\\"result\\\": %s}\"";
    private HashMap<String,Method>  methodHashMap;
    private String mInjectName;
    private String mPreloadInterfaceJS;

    private Gson gson;

    public JsCallJava(String injectedName,Class injectedCls){

            try {
                if(TextUtils.isEmpty(injectedName)){

                    throw new Exception("injected name can not be null");

                }
                mInjectName=injectedName;
                methodHashMap=new HashMap<String,Method>();
                Method[] methods=injectedCls.getDeclaredMethods();
                StringBuilder sb=new StringBuilder("javascript:(function(b){console.log(\\\"");
                sb.append(mInjectName);
                sb.append("initialization begin\\\");var a={queue:[],callback:function(){var d=Array.prototype.slice.call(arguments,0);var c=d.shift();var e=d.shift();this.queue[c].apply(this,d);if(!e){delete this.queue[c]}}};");
                for(Method method:methods){
                    String sign;
                    if(method.getModifiers() != (Modifier.PUBLIC | Modifier.STATIC) || (sign = genJavaMethodSign(method)) == null){
                        continue;
                    }
                    methodHashMap.put(sign,method);
                    sb.append(String.format(String.format("a.%s=", method.getName())));


                }
                sb.append("function(){var f=Array.prototype.slice.call(arguments,0);if(f.length<1){throw\"");
                sb.append(mInjectName);
                sb.append(" call error, message:miss method name\"}var e=[];for(var h=1;h<f.length;h++){var c=f[h];var j=typeof c;e[e.length]=j;if(j==\"function\"){var d=a.queue.length;a.queue[d]=c;f[h]=d}}var g=JSON.parse(prompt(JSON.stringify({method:f.shift(),types:e,args:f})));if(g.code!=200){throw\"");
                sb.append(mInjectName);
                sb.append(" call error, code:\"+g.code+\", message:\"+g.result}return g.result};Object.getOwnPropertyNames(a).forEach(function(d){var c=a[d];if(typeof c===\"function\"&&d!==\"callback\"){a[d]=function(){return c.apply(a,[d].concat(Array.prototype.slice.call(arguments,0)))}}});b.");
                sb.append(mInjectName);
                sb.append("=a;console.log(\"");
                sb.append(mInjectName);
                sb.append(" initialization end\")})(window);");
                mPreloadInterfaceJS = sb.toString();


            } catch (Exception e) {
                e.printStackTrace();
        }

    }

    private String genJavaMethodSign(Method method) {
        String sign=method.getName();
        Class[] argsTypes=method.getParameterTypes();
        int len=argsTypes.length;
        if(len<1||argsTypes[0]!= WebView.class){
            Log.w(TAG, "method(" + sign + ") must use webview to be first parameter, will be pass");
            return null;
        }
        for (int k = 1; k < len; k++) {
            Class cls = argsTypes[k];
            if (cls == String.class) {
                sign += "_S";
            } else if (cls == int.class ||
                    cls == long.class ||
                    cls == float.class ||
                    cls == double.class) {
                sign += "_N";
            } else if (cls == boolean.class) {
                sign += "_B";
            } else if (cls == JSONObject.class) {
                sign += "_O";
            } else if (cls == JsCallBack.class) {
                sign += "_F";
            } else {
                sign += "_P";
            }
        }
        return sign;


    }

}
