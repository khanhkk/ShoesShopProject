package tdc.edu.vn.shoesshop.Khanh;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    private final Fragment[] fragments = new Fragment[getCount()];

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fragments[position] = fragment;
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //fragments[position] = null;
        super.destroyItem(container, position, object);
    }

    // return fragment by position
    public Fragment findFragment(int position) {
        return fragments[position];
    }

    @Override
    public Fragment getItem(int position) {
        //Log.d("aaa", position+"");
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    // Force a refresh of the page when a different fragment is displayed
//    @Override
//    public int getItemPosition(Object object) {
//        // this method will be called for every fragment in the ViewPager
//        if (object instanceof SomePermanantCachedFragment) {
//            return POSITION_UNCHANGED; // don't force a reload
//        } else {
//            // POSITION_NONE means something like: this fragment is no longer valid
//            // triggering the ViewPager to re-build the instance of this fragment.
//            return POSITION_NONE;
//        }
//    }

//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
//        ContentFragment cf = (ContentFragment) object;
//        mFragMan.beginTransaction().remove(cf).commit();
//        mFrags.add(position, ContentFragment.newInstance(cf.getmParam1()));
//        mFrags.remove(position+1);
//    }

    @Override
    public int getCount() {
        return 3;
    }
}
