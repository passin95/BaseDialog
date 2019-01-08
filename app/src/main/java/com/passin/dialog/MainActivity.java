package com.passin.dialog;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import com.passin.dialog.EHiDialog.OnLongClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_demo).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EHiDialog.newBuilder(MainActivity.this)
                        .contentId(R.layout.dialog_demo)
                        .cancelable(true)
                        .cancelOnTouchOutside(false)
                        .fullScreen()
                        .gravity(Gravity.CENTER)
                        .setOnShowListener(new OnShowListener() {
                            @Override
                            public void onShow(DialogInterface dialog) {
                                Toast.makeText(MainActivity.this,
                                        "dialog出现了", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                Toast.makeText(MainActivity.this,
                                        "dialog取消了", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setOnDismissListener(new OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                Toast.makeText(MainActivity.this,
                                        "dialog dismiss了", Toast.LENGTH_LONG).show();
                            }
                        })
                        .build()
                        .setBackgroundColor(R.id.iv_test,
                                ContextCompat.getColor(MainActivity.this,R.color.colorAccent))
                        .setText(R.id.tv_test, "你好啊")
                        .setText(R.id.et_test,"德玛西亚")
                        .setTextColor(R.id.tv_test, 0xff222222)
                        .setChecked(R.id.cb_test, true)
                        .setProgress(R.id.progress_test, 30)
                        .addOnClickListener(R.id.btn_visible, R.id.tv_test)
                        .setOnClickListener(new EHiDialog.OnClickListener() {
                            @Override
                            public void onClick(EHiDialog dialog, View view) {
                                switch (view.getId()) {
                                    case R.id.tv_test:
                                        Toast.makeText(MainActivity.this,
                                                ((TextView) view).getText().toString(), Toast.LENGTH_LONG).show();
                                        break;
                                    case R.id.btn_visible:
                                        View llTestContent = dialog.getView(R.id.ll_test_content);
                                        dialog.setGone(R.id.ll_test_content,
                                                llTestContent.getVisibility() != View.VISIBLE);
                                        break;
                                }
                            }
                        })
                        .addOnLongClickListener(R.id.btn_visible, R.id.iv_test)
                        .setOnLongClickListener(new OnLongClickListener() {
                            @Override
                            public boolean onLongClick(EHiDialog dialog, View view) {
                                switch (view.getId()) {
                                    case R.id.btn_visible:
                                        Toast.makeText(MainActivity.this,
                                                "长按消失可见按钮", Toast.LENGTH_LONG).show();
                                        break;
                                    case R.id.iv_test:
                                        Toast.makeText(MainActivity.this,
                                                "长按监听图片监听", Toast.LENGTH_LONG).show();
                                        break;
                                }
                                return true;
                            }
                        })
                        .show();
            }
        });
    }
}
