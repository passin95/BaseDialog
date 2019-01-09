## Dialog

Dialog的实用封装，保留高性能的情况下，尽可能的方便使用。

只有一个类，代码简单易懂实用，易扩展易修改，需要的直接复制代码。

## 使用方式

```java
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
                  .setText(R.id.tv_test, "测试文字")
                  .setText(R.id.et_test,"测试输入框")
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
                                          "长按 imgeview 监听", Toast.LENGTH_LONG).show();
                                  break;
                          }
                          return true;
                      }
                  })
                  .show();
```

## 致谢

[BaseRecyclerViewAdapterHelper](https://github.com/passin95/BaseRecyclerViewAdapterHelper)



