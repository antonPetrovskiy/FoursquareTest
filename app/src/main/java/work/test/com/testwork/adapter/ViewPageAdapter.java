package work.test.com.testwork.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import work.test.com.testwork.MapsActivity;
import work.test.com.testwork.R;
import work.test.com.testwork.entity.Venues;

public class ViewPageAdapter extends PagerAdapter {
    private MapsActivity activity;
    private LayoutInflater layoutInflater;
    public List<Venues> itemList;

    public ViewPageAdapter(MapsActivity activity, List<Venues> itemList){
        this.activity = activity;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.card, null);
        TextView textView1 = view.findViewById(R.id.textView3);
        TextView textView2 = view.findViewById(R.id.textView);
        TextView textView3 = view.findViewById(R.id.textView2);
        textView1.setText(itemList.get(position).getVenue().getName());
        textView2.setText("Координаты - "+itemList.get(position).getVenue().getLocation().getLat()+" "+itemList.get(position).getVenue().getLocation().getLat());
        textView3.setText("Адрес - "+itemList.get(position).getVenue().getLocation().getAddress());
        activity.setFocus();

        ViewPager vp = (ViewPager) container;
        vp.addView(view,0);

        return view;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View v = (View) object;
        vp.removeView(v);
    }
}
