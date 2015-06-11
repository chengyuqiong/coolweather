package com.zhy.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

import com.nineoldandroids.view.ViewHelper;
import com.example.coolweather.R;

/**
 * 涓荤晫闈�
 * 
 * @author // 鍘熸枃浣滆�厇hy锛岀传璞敞
 * @by ttp://blog.csdn.net/lmj623565791/article/details/41531475
 * 
 */
public class MainActivity extends FragmentActivity {

	private DrawerLayout mDrawerLayout;// 鎺т欢瀵硅薄

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		initView();
		initEvents();

	}

	/**
	 * 鍒濆鍖栬鍥�
	 */
	private void initView() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
				Gravity.RIGHT);// 鍏抽棴鍙充晶鑿滃崟鐨勬粦鍔ㄥ嚭鐜版晥鏋�
	}

	/**
	 * 鍒濆鍖朌rawerLayout浜嬩欢鐩戝惉
	 */
	private void initEvents() {
		// 璁剧疆鐩戝惉
		mDrawerLayout.setDrawerListener(new DrawerListener() {

			@Override
			public void onDrawerStateChanged(int newState) {
			}

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				View mContent = mDrawerLayout.getChildAt(0);
				View mMenu = drawerView;
				float scale = 1 - slideOffset;
				float rightScale = 0.8f + scale * 0.2f;

				if (drawerView.getTag().equals(
						getResources().getString(R.string.left_tag))) {// 灞曞紑宸︿晶鑿滃崟
					float leftScale = 1 - 0.3f * scale;

					// 璁剧疆宸︿晶鑿滃崟缂╂斁鏁堟灉
					ViewHelper.setScaleX(mMenu, leftScale);
					ViewHelper.setScaleY(mMenu, leftScale);
					ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));

					// 璁剧疆涓棿View缂╂斁鏁堟灉
					ViewHelper.setTranslationX(mContent,
							mMenu.getMeasuredWidth() * (1 - scale));
					ViewHelper.setPivotX(mContent, 0);
					ViewHelper.setPivotY(mContent,
							mContent.getMeasuredHeight() / 2);
					mContent.invalidate();
					ViewHelper.setScaleX(mContent, rightScale);
					ViewHelper.setScaleY(mContent, rightScale);
				} else {// 灞曞紑鍙充晶鑿滃崟
					// 璁剧疆涓棿View缂╂斁鏁堟灉
					ViewHelper.setTranslationX(mContent,
							-mMenu.getMeasuredWidth() * slideOffset);
					ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
					ViewHelper.setPivotY(mContent,
							mContent.getMeasuredHeight() / 2);
					mContent.invalidate();
					ViewHelper.setScaleX(mContent, rightScale);
					ViewHelper.setScaleY(mContent, rightScale);
				}

			}

			// 鑿滃崟鎵撳紑
			@Override
			public void onDrawerOpened(View drawerView) {
			}

			// 鑿滃崟鍏抽棴
			@Override
			public void onDrawerClosed(View drawerView) {
				mDrawerLayout.setDrawerLockMode(
						DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
			}
		});
	}

	/**
	 * 鎵撳紑鍙充晶鑿滃崟
	 * 
	 * @param view
	 */
	public void OpenRightMenu(View view) {
		mDrawerLayout.openDrawer(Gravity.RIGHT);// 灞曞紑渚ц竟鐨勮彍鍗�
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,
				Gravity.RIGHT);// 鎵撳紑鎵嬪娍婊戝姩
	}
}