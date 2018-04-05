package Controls;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import tdc.edu.vn.shoesshop.R;

/**
 * Created by kk on 05/04/2018.
 */

public class TabarControl extends LinearLayout {
    private ImageView button1, button2, button3, button4;
    private ViewGroup group;
    TabarFunctions tabarFunctions;

    public void setImageButton1(int image)
    {
        button1.setImageResource(image);
    }

    public void setImageButton2(int image)
    {
        button2.setImageResource(image);
    }

    public void setImageButton3(int image)
    {
        button3.setImageResource(image);
    }

    public void setImageButton4(int image)
    {
        button4.setImageResource(image);
    }

    public TabarControl(Context context) {
        super(context);
        init();
    }

    public TabarControl(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TabarControl(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

//    public Tabar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        init();
//    }


    private OnClickListener itemClicked = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int child = group.getChildCount();
            for(int i = 0; i < child; i++)
            {
                if(tabarFunctions == null)
                    return;
                switch(v.getId())
                {
                    case R.id.ibButton1:
                        tabarFunctions.onButton1Clicked();
                        break;
                    case R.id.ibButton2:
                        tabarFunctions.onButton2Clicked();
                        break;
                    case R.id.ibButton3:
                        tabarFunctions.onButton3Clicked();
                        break;
                    case R.id.ibButton4:
                        tabarFunctions.onButton4Clicked();
                        break;
                }

                View btn = group.getChildAt(i);
                if(btn.getId() == v.getId())
                {
                    btn.setSelected(true);
                    btn.setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimaryDark));
                }
                else
                {
                    btn.setSelected(false);
                    btn.setBackgroundColor(getContext().getResources().getColor(R.color.none));
                }
            }
        }
    };

    public interface TabarFunctions{
        void onButton1Clicked();
        void onButton2Clicked();
        void onButton3Clicked();
        void onButton4Clicked();
    }

    public void setTabarFunctions(TabarFunctions tabarFunctions) {
        this.tabarFunctions = tabarFunctions;
    }

    private void init()
    {
        ViewGroup viewGroup = (ViewGroup) inflate(getContext(), R.layout.tabar_control, this);
        group = (ViewGroup) viewGroup.getChildAt(0);

        button1 = (ImageView) group.findViewById(R.id.ibButton1);
        button1.setOnClickListener(itemClicked);

        button2 = (ImageView) group.findViewById(R.id.ibButton2);
        button2.setOnClickListener(itemClicked);

        button3 = (ImageView) group.findViewById(R.id.ibButton3);
        button3.setOnClickListener(itemClicked);

        button4 = (ImageView) group.findViewById(R.id.ibButton4);
        button4.setOnClickListener(itemClicked);
    }
}
