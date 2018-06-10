package together.readingroom;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailBookRoom extends AppCompatActivity {

    private static final String TAG = "TAG";
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.sw)
    Switch sw;
    @BindView(R.id.tv_stt)
    TextView tvStt;

    Phong phong;
    Date dateden, datedi;
    @BindView(R.id.btn_ngayden)
    Button btnNgayden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book_room);
        ButterKnife.bind(this);

        phong = (Phong) getIntent().getSerializableExtra("phong");
        dateden = new Date();
        datedi = new Date();

    }

    public String getFormatTime(Date date) {
        SimpleDateFormat simple = new SimpleDateFormat("MMM dd yyy, HH:mm");
        return simple.format(date);
    }

    @OnClick({R.id.btn_ngayden, R.id.btn_datphong, R.id.btn_huy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_ngayden:

                if (sw.isChecked()) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(dateden);
                    TimePickerDialog timePickerDialog = new TimePickerDialog(
                            this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int i, int i1) {
                            dateden.setHours(i);
                            dateden.setMinutes(i1);
                            btnNgayden.setText(getFormatTime(dateden));
                        }
                    }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                    timePickerDialog.show();
                } else {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(dateden);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(
                            this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            dateden.setYear(i - 1900);
                            dateden.setMonth(i1);
                            dateden.setDate(i2);
                            btnNgayden.setText(getFormatTime(dateden));
                        }
                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.show();
                }

                datedi.setTime(dateden.getTime() + 7200000);

                break;
            case R.id.btn_datphong:
                new DatabaseManager(DetailBookRoom.this).updatePhong(phong, getFormatTime(dateden), getFormatTime(datedi), Common.student.getMidnum());
                finish();
                break;
            case R.id.btn_huy:
                finish();
                break;
        }
    }
}
