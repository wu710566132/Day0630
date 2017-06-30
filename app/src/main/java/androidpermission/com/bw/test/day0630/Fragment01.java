package androidpermission.com.bw.test.day0630;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

public class Fragment01 extends Fragment{

	private ImageView iv;
    private ViewPager vpager;
    List<News.LayoutsBean> layouts;
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragmnet_01, container, false);
		iv = (ImageView) view.findViewById(R.id.frag_iv);
        vpager = (ViewPager) view.findViewById(R.id.vpager);
		return view;
	}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getData();

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

                String result = response.body().string();
                Gson gson = new Gson();
                News news= gson.fromJson(result, News.class);
               layouts = news.getLayouts();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ViewPagerAdapter adapter=new ViewPagerAdapter(layouts,getActivity());

                        vpager.setAdapter(adapter);

                    }
                });

            }

        });


    }


}
