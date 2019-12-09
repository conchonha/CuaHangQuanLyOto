package com.example.quanlyoto_doan.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.quanlyoto_doan.R;
import com.squareup.picasso.Picasso;

public class Banner_Adapter extends PagerAdapter {
    private View view;
    private Context context;

    private String arrayPicture[];

    public Banner_Adapter(Context context, String[] arrayPicture) {
        this.context = context;
        this.arrayPicture = arrayPicture;
    }

    @Override
    public int getCount() {
        return arrayPicture.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        view=View.inflate(context, R.layout.bannerlayout,null);
        ImageView imageView=view.findViewById(R.id.imageviewbanner);
        Picasso.with(context).load(arrayPicture[position]).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
      //  super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
