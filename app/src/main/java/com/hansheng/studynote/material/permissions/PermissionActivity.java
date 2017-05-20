package com.hansheng.studynote.material.permissions;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 17-5-18.
 */

public class PermissionActivity extends AppCompatActivity {
    private Intent intent;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void revel(View view){
        // previously invisible view
        Button myView = (Button) findViewById(R.id.btn_anim);

        // get the center for the clipping circle
        int cx = (myView.getLeft() + myView.getRight()) / 2;
        int cy = (myView.getTop() + myView.getBottom()) / 2;

        // get the final radius for the clipping circle
        int finalRadius = Math.max(myView.getWidth(), myView.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);

        // make the view visible and start the animation
        myView.setVisibility(View.VISIBLE);
        anim.start();

        // previously visible view
        final Button myView1 = (Button) findViewById(R.id.btn_anim_reve);

        // get the center for the clipping circle
        int cx1 = (myView1.getLeft() + myView.getRight()) / 2;
        int cy1 = (myView1.getTop() + myView.getBottom()) / 2;

        // get the initial radius for the clipping circle
        int initialRadius = myView1.getWidth();

        // create the animation (the final radius is zero)
        Animator anim1 =
                ViewAnimationUtils.createCircularReveal(myView1, cx1, cy1, initialRadius, 0);

        // make the view invisible when the animation is done
        anim1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                myView1.setVisibility(View.INVISIBLE);
            }
        });

        // start the animation
        anim1.start();
    }


    public void testCall(View view) {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
        } else {
            callPhone();
        }
    }

    public void callPhone() {
        intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + "10086");
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhone();
            } else {
                // Permission Denied
                Toast.makeText(PermissionActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
