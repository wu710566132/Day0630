package androidpermission.com.bw.test.day0630;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

public class Fragment02 extends Fragment implements View.OnClickListener {

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			layouts= (List<News.LayoutsBean>) msg.obj;
			lv.setAdapter(adapter);
		}
	};


	private ListView poplist;
	private PopupWindow pw;
	private SpringView sp;
	private List<News.LayoutsBean> layouts;
	private ListView lv;
	private Myadapter adapter;
	private Button bt;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragmnet_02,container,false);
		getData();
		sp = (SpringView) view.findViewById(R.id.sp);
		lv = (ListView) view.findViewById(R.id.lv);
		bt = (Button) view.findViewById(R.id.daohang);
		return view;
	}


	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		adapter = new Myadapter();
		bt.setOnClickListener(this);
		sp.setHeader(new DefaultHeader(getActivity()));
		sp.setFooter(new DefaultFooter(getActivity()));
		sp.setType(SpringView.Type.FOLLOW);
		sp.setListener(new SpringView.OnFreshListener() {
			@Override
			public void onRefresh() {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						sp.onFinishFreshAndLoad();
					}
				},2000);
			}
			@Override
			public void onLoadmore() {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						sp.onFinishFreshAndLoad();
					}
				},2000);
			}
		});

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

				Toast.makeText(getActivity(), layouts.get(i).getName(), Toast.LENGTH_LONG).show();

			}
		});


	}



	private void getData() {

		//创建okHttpClient对象
		OkHttpClient mOkHttpClient = new OkHttpClient();
		//创建一个Request
		final Request request = new Request.Builder()
				.url("http://h5test.newaircloud.com/api/getLayouts?sid=xkycs&cid=10024&date=")
				.build();
		//new call
		Call call = mOkHttpClient.newCall(request);
		//请求加入调度
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Request request, IOException e) {
			}

			@Override
			public void onResponse(final Response response) throws IOException {
				String string = response.body().string();
				Gson gson = new Gson();
				System.out.println("string = " + string);
				News bean = gson.fromJson(string, News.class);
				List<News.LayoutsBean> layouts = bean.getLayouts();

				Message msg = new Message();
				msg.obj = layouts;
				handler.sendMessage(msg);


			}

		});
	}

	@Override
	public void onClick(View view) {
		pop();
		pw.showAsDropDown(view);

	}

	class Myadapter extends BaseAdapter{

		@Override
		public int getCount() {
			return layouts != null ? layouts.size() : 0;
		}

		@Override
		public Object getItem(int position) {
			return layouts.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			A a;
			if (convertView == null){

				convertView = View.inflate(getActivity(),R.layout.lv_item,null);

				a = new A();

				a.tv = (TextView) convertView.findViewById(R.id.tv1);
				convertView.setTag(a);

			}else {
				a = (A) convertView.getTag();
			}
			a.tv.setText(layouts.get(position).getName());

			return convertView;
		}



		class A{

			TextView tv;
		}




	}

	private void pop() {

		View view = View.inflate(getActivity(), R.layout.pop, null);

		poplist = (ListView) view.findViewById(R.id.pop);
		pw = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		poplist.setAdapter(adapter);
		pw.setOutsideTouchable(true);
		pw.setFocusable(true);
		pw.setBackgroundDrawable(new ColorDrawable(Color.GREEN));
	}

}










