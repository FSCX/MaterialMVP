package com.fsc.newsnets.about.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fsc.newsnets.R;

/**关于fragment
 * Created by fsc on 2018/6/17.
 */

public class AboutFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle saveInstanceState ){
        View view = inflater.inflate(R.layout.fragment_about,null);
        return view;
    }
}
