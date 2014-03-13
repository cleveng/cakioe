/**
 * 
 */
package com.webviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * @author maus
 * @deprecated 定义某些方法  偷懒
 */
public abstract class BaseActivity extends FragmentActivity {

	protected abstract void initViews();

	protected abstract void initEvents();

	/** 通过Class跳转界面 **/
	protected void startActivity(Class<?> cls) {
		startActivity(cls, null);
	}

	/** 含有Bundle通过Class跳转界面 **/
	protected void startActivity(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}
}
