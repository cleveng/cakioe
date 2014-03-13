/**
 * 
 */
package com.webviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * @author liu
 * 
 */
public class WebviewActivity extends BaseActivity implements OnClickListener {

	EditText met_path;
	ImageView madd_iv;
	String path;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.include_path);
		initViews();
		initEvents();
	}

	@Override
	protected void initViews() {
		met_path = (EditText) findViewById(R.id.add_path);
		madd_iv = (ImageView) findViewById(R.id.add_iv);
	}

	@Override
	protected void initEvents() {
		met_path.setOnClickListener(this);
		madd_iv.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.add_iv:
			path = met_path.getText().toString();
			try {
				// 判断得到的edittext文本中是否包含http://
				if (path.indexOf("http://") != -1) {
					StartUp();
				} else {
					// 若没有包含 则我们在edittext文本前增加该内容
					path = "http://" + path;
					StartUp();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			break;

		default:
			break;
		}
	}
	//传递文本的路径 类似于MainActivity 传递URL 加载WEBView 内容
	private void StartUp() {
		Intent URL_s = new Intent();
		URL_s.putExtra("URL", path);
		URL_s.setClass(this, WebSiteActivity.class);
		startActivityForResult(URL_s, RESULT_OK);
	}

}
