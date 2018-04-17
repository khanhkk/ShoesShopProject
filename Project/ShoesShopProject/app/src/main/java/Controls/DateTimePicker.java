package Controls;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import tdc.edu.vn.shoesshop.R;

/**
 * Created by kk on 29/03/2018.
 */

public class DateTimePicker extends LinearLayout implements  DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    TextView tvTime;
    ImageButton btnSelect;
    private ViewGroup parentLayout;
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    int ngay, thang, nam, gio, phut;
    int fNgay, fThang, fNam, fGio, fPhut;

    private Date date;

//    public Time getTime() {
//        return time;
//    }
//
//    public void setTime(Time time) {
//        this.time = time;
//    }

    //private Time time;

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
                ngay = calendar.get(Calendar.DATE);
                thang = calendar.get(Calendar.MONTH);
                nam = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), DateTimePicker.this , nam, thang, ngay);/*new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);

                        setDate((Date) calendar.getTime());
                        tvTime.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },nam, thang, ngay);8*/
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        fNam = i;
        fThang = i1;
        fNgay = i2;

        Calendar c = Calendar.getInstance();
        gio = c.get(Calendar.HOUR_OF_DAY);
        phut = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), DateTimePicker.this, gio - 1 , phut, android.text.format.DateFormat.is24HourFormat(getContext()));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        fGio = i;
        fPhut =  i1;

        final Calendar calendar = Calendar.getInstance();
        calendar.set(fNam, fThang, fNgay, fGio, fPhut);
        setDate(calendar.getTime());
        tvTime.setText(simpleDateFormat.format(calendar.getTime()));
        return;
    }
}
