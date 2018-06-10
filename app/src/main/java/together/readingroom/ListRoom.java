package together.readingroom;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListRoom extends AppCompatActivity {

    private static final String TAG = "TAG";
    @BindView(R.id.rv_list_room)
    RecyclerView rvListRoom;
    List<Phong> list;
    DatabaseManager databaseManager;
    int time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_room);
        ButterKnife.bind(this);

        databaseManager = new DatabaseManager(this);
        list = new DatabaseManager(this).getListPhong();

        rvListRoom.setLayoutManager(new GridLayoutManager(this, 2));
        rvListRoom.setHasFixedSize(true);
        rvListRoom.setAdapter(new RoomAdapter(this, list));

        updatelist();
    }

    public void init(){
        databaseManager.capnhatDS(databaseManager.getListPhong());
        list = databaseManager.getListPhong();
        rvListRoom.setAdapter(new RoomAdapter(this, list));
    }

    public void updatelist(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                init();
                Log.d(TAG, "run: " + time++);
                handler.postDelayed(this, 10000);
            }
        });
    }

}
