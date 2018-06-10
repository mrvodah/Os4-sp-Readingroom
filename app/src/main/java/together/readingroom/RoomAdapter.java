package together.readingroom;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by VietVan on 10/06/2018.
 */

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    Context context;
    List<Phong> list;

    public RoomAdapter(Context context, List<Phong> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, null);

        return new RoomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, final int position) {
        holder.setData(list.get(position));

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(position).getTt() == 0){

                    Intent intent = new Intent(context, DetailBookRoom.class);
                    intent.putExtra("phong", list.get(position));
                    context.startActivity(intent);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder{

        TextView ten, thoigianden, thoigiandi,stt;
        CardView cv;
        View v;

        public RoomViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            stt = v.findViewById(R.id.tv_stt);
            ten = v.findViewById(R.id.tv_tenkhach);
            thoigianden = v.findViewById(R.id.tv_thoigianden);
            thoigiandi = v.findViewById(R.id.tv_thoigiandi);
            cv = v.findViewById(R.id.cardview);
        }

        // Set giá trị cho trường
        public void setData(Phong phong){
            stt.setText(phong.getmID() + "");

            if(phong.getTt() == 1){
                ten.setText(new DatabaseManager(context).getStudentwmIDNum(phong.getMidnum()).getmName());
                thoigianden.setText(phong.getTimebegin());
                thoigiandi.setText(phong.getTimeend());
            }
            else{
                thoigianden.setText("");
                thoigiandi.setText("");
            }

            if(phong.getTt() == 1) {
                SimpleDateFormat simple = new SimpleDateFormat("MMM dd yyy, HH:mm");
                try {
                    Date date = simple.parse(phong.getTimeend());
                    if(date.getTime() - System.currentTimeMillis() >= 1800000){
                        cv.setCardBackgroundColor(Color.parseColor("#C01734"));
                    }
                    else{
                        cv.setCardBackgroundColor(Color.parseColor("#d7c70d"));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            else{
                ten.setText("<Empty>");
                cv.setCardBackgroundColor(Color.parseColor("#58B526"));
            }

        }

    }

}
