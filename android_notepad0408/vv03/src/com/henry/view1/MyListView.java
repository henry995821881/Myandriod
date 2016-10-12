package com.henry.view1;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class MyListView extends ListView {

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**   只重写该方法，达到使ListView适应ScrollView的效果   */
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,

		MeasureSpec.AT_MOST);

		super.onMeasure(widthMeasureSpec, expandSpec);

	}
	
	
	
	/*项目做多了之后，会发现其实 ScrollView嵌套ListVew或者GridView等很常用，但是你也会发现各种奇怪问题产生。根据个人经验现在列出常见问题以及代码最少最简单的解决方法。

	问题一 ： 嵌套在 ScrollView的 ListVew数据显示不全，我遇到的是最多只显示两条已有的数据。

	解决办法：重写 ListVew或者 GridView，网上还有很多若干解决办法，但是都不好用或者很复杂。

	@Override

	*//**   只重写该方法，达到使ListView适应ScrollView的效果   *//* 

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

	int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,

	MeasureSpec.AT_MOST);

	super.onMeasure(widthMeasureSpec, expandSpec);

	}*/

	/*问题二 、打开套有 ListVew的 ScrollView的页面布局 默认 起始位置不是最顶部。

	解决办法有两种都挺好用：

	一是把套在里面的Gridview 或者 ListVew 不让获取焦点即可。

	gridview.setFocusable(false); listview.setFocusable(false);

	注意：在xml布局里面设置android：focusable=“false”不生效

	方法二：网上还查到说可以设置myScrollView.smoothScrollTo(0,0);

*/

}
