/**
 * 
 */
package com.webviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author maus
 * @deprecated 第一个界面传递一个URL给webview
 * @deprecated 因为想用来重载使用的，所以不会把URL直接加载在webview
 */
public class MainActivity extends Activity {
	//定义开始按钮 
	private Button mstar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide_main);
		mstar = (Button) findViewById(R.id.star);
		mstar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent star = new Intent();
				star.putExtra("URL", "http://www.baidu.com");
				star.setClass(MainActivity.this, WebSiteActivity.class);
				startActivityForResult(star, RESULT_OK);
			}

		});
	}
}
