package com.wsln.mydemo.ui.widget;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.webkit.WebView;
import android.widget.ScrollView;
import android.widget.Scroller;

/**
 * 自定义ScrollView，解决：ScrollView嵌套ViewPager，导致ViewPager不能滑动的问题
 */
public class CustomScrollView extends NestedScrollView {
	private boolean isNeedScroll = true;
	private float xDistance, yDistance, xLast, yLast;
	private int scaledTouchSlop;

	public CustomScrollView(Context context) {
		super(context);
	}

	public CustomScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				xDistance = yDistance = 0f;
				xLast = ev.getX();
				yLast = ev.getY();
				break;
			case MotionEvent.ACTION_MOVE:
				final float curX = ev.getX();
				final float curY = ev.getY();

				xDistance += Math.abs(curX - xLast);
				yDistance += Math.abs(curY - yLast);
				xLast = curX;
				yLast = curY;
				Log.e("SiberiaDante", "xDistance ：" + xDistance + "---yDistance:" + yDistance);
				return !(xDistance > yDistance || yDistance < scaledTouchSlop) && isNeedScroll;

		}
		return super.onInterceptTouchEvent(ev);
	}

	/*
    改方法用来处理NestedScrollView是否拦截滑动事件
     */
	public void setNeedScroll(boolean isNeedScroll) {
		this.isNeedScroll = isNeedScroll;
	}
}