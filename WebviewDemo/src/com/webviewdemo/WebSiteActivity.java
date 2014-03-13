/**
 * 
 */
package com.webviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * @author liu
 * @deprecated this activity is add the website
 * @date 2014-03-13
 */
public class WebSiteActivity extends BaseActivity implements OnClickListener {
	// 4个ImageView按钮 按下会变成灰色
	private ImageView mhome, madd, mtrash, mexit;
	private String URL_PATH;
	private String mpath;
	private WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		initEvents();
		Setting();
	}

	private void Setting() {
		Bundle extras = getIntent().getExtras();
		URL_PATH = (String) extras.get("URL");
		WebSettings webSettings = mWebView.getSettings();
		// 设置WebView属性，能够执行Javascript脚本
		webSettings.setJavaScriptEnabled(true);
		// 设置禁止访问文件数据
		webSettings.setAllowFileAccess(false);
		// 设置是支持缩放
		webSettings.setBuiltInZoomControls(true);

		MyWebViewClient myWebViewClient = new MyWebViewClient();
		mWebView.setWebViewClient(myWebViewClient);
		// 加载自定义web网址

		try {
			// 再次判断传递进来的路径是否为空或者包含http
			if (URL_PATH != null && URL_PATH.indexOf("http://") != -1) {
				mpath = URL_PATH;
				mWebView.loadUrl(mpath);
			} else {

				Toast.makeText(this, "链接不存在", 3000).show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initViews() {
		mhome = (ImageView) findViewById(R.id.website_home);
		madd = (ImageView) findViewById(R.id.website_add);
		mtrash = (ImageView) findViewById(R.id.website_trash);
		mexit = (ImageView) findViewById(R.id.website_exit);
		mWebView = (WebView) this.findViewById(R.id.activity_site);
	}

	@Override
	protected void initEvents() {
		mhome.setOnClickListener(this);
		madd.setOnClickListener(this);
		mtrash.setOnClickListener(this);
		mexit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// home
		case R.id.website_home:
			String mpath1 = "Http://m.51m51m.cn";
			mWebView.loadUrl(mpath1);
			break;
		// 加载输入框
		case R.id.website_add:
			startActivity(WebviewActivity.class);
			break;
		// 重载当前页面
		case R.id.website_trash:
			mWebView.reload();
			break;
		// bye bye
		case R.id.website_exit:
			finish();
			break;
		default:
			break;
		}
	}

	private class MyWebViewClient extends WebViewClient {
		// 重写父类方法，让新打开的网页在当前的WebView中显示
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}

	// 设置回退
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
			mWebView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
