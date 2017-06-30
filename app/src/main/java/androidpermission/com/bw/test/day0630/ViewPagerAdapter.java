package androidpermission.com.bw.test.day0630;

import java.util.List;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ViewPagerAdapter extends PagerAdapter{


	List<News.LayoutsBean> list;
	Context context;

	public ViewPagerAdapter(List<News.LayoutsBean> list,Context context) {
		this.list=list;
		this.context=context;

	}

	//返回要显示的页面个数
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	//如果当前位置的view跟instantiateItem(ViewGroup, int)返回的object的key相同 就返回true
	@Override
	public boolean isViewFromObject(View view, Object object) {
		// TODO Auto-generated method stub
		return view == object;
	}
	//1.返回当前指定位置的页面或者视图 2.把当前位置的视图添加到容器container中
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ImageView iv=new ImageView(context);
		Glide.with(context).load(list.get(position).getPicUrl()).into(iv);
		container.addView(iv);
		return iv;
	}
	//把当前位置页面显示的视图从容器里面移除
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
		//super.destroyItem(container, position, object);
	}

}
