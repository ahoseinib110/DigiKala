package org.maktab.digikala.view.activity;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import org.maktab.digikala.R;
import org.maktab.digikala.data.model.Orderby;
import org.maktab.digikala.view.fragment.HomePageFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        AddFragments(fragmentManager,R.id.fragmentLatestContainer,  Orderby.DATE);
        AddFragments(fragmentManager,R.id.fragmentMostRatedContainer,  Orderby.MOST_RATED);
        AddFragments(fragmentManager,R.id.fragmentMostViewedContainer,  Orderby.MOST_VIEWED);
    }

    private void AddFragments(FragmentManager fragmentManager, @IdRes int containerViewId, Orderby orderby) {
        fragmentManager.beginTransaction()
                .replace(containerViewId , HomePageFragment.newInstance(orderby))
                .commit();
    }
}