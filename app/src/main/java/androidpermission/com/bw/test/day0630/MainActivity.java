package androidpermission.com.bw.test.day0630;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private RadioButton rb1;
    private RadioButton rb2;
    private RadioGroup rg;
    private ViewPager view_pager;
    private ImageView iv1;
    private ImageView iv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton)  findViewById(R.id.rb2);
        rg = (RadioGroup) findViewById(R.id.rg);
        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        initView();
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);

    }

    private void initView() {
        view_pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = new Fragment01();
                        break;
                    case 1:
                        fragment = new Fragment02();
                        break;
                }

                return fragment;


            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        /**
         * ViewPager有一个特性 前后加载两页
         *
         */
       view_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                rg.check(rg.getChildAt(arg0).getId());
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub
            }
        });

        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);

        Fragment fragment = new Fragment02();


    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.rb1:
                view_pager.setCurrentItem(0,false);
            break;
            case R.id.rb2:
                view_pager.setCurrentItem(1,false);
                break;
            case R.id.iv1:
                yiChang();
                break;
        }



    }

    private void yiChang() {

        TextView textView=null;
        textView.setText("abcd");
    }

}



