package Controls;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import tdc.edu.vn.shoesshop.R;

/**
 * Created by kk on 29/03/2018.
 */

public class DateTimePicker extends LinearLayout {
    TextView tvTime;
    ImageButton btnSelect;
    private ViewGroup parentLayout;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private Date date;

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date time)
    {
        this.date = time;
        tvTime.setText(simpleDateFormat.format(date));
    }

    public DateTimePicker(Context context) {
        super(context);
        ini();
    }

    public DateTimePicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        ini();
    }

    public DateTimePicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ini();
    }

//    public DateTimePicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        ini();
//    }

    private void ini()
    {
        ViewGroup viewGroup = (ViewGroup) inflate(getContext(), R.layout.date_time_picker, this);
        parentLayout = (ViewGroup) viewGroup.getChildAt(0);
        tvTime = (TextView) findViewById(R.id.tvTime);
        btnSelect = (ImageButton) findViewById(R.id.btnSelectTime);
        btnSelect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int ngay = calendar.get(Calendar.DATE);
                int thang = calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);

                        setDate((Date) calendar.getTime());
                        tvTime.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },nam, thang, ngay);
                datePickerDialog.show();
            }
        });
    }
}
