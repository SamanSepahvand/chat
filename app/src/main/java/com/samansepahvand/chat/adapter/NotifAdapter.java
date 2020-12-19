package com.samansepahvand.chat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samansepahvand.chat.R;
import com.samansepahvand.chat.model.ModelNotification;

import java.util.List;

public class NotifAdapter  extends RecyclerView.Adapter<NotifAdapter.ViewHolder> {


    List<ModelNotification> models;

    Context context;


    public NotifAdapter(List<ModelNotification> models, Context context) {
        this.models = models;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_data_notification,parent,false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ModelNotification model=models.get(position);

//        holder.txtNotifId.setText(model.getId());



        if (model!=null){
            holder.txtNotifTitle.setText(model.getNotifTitle());
            holder.txtNotifBody.setText(model.getNotifBody());
        }else{
            Toast.makeText(context, "Empty Notification Repository!!", Toast.LENGTH_SHORT).show();

        }




    }

    @Override
    public int getItemCount() {
        return models.size();
    }
    public class ViewHolder  extends RecyclerView.ViewHolder{
        TextView txtNotifTitle,txtNotifBody,txtNotifId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {

            txtNotifBody=itemView.findViewById(R.id.txt_notif_body);
            txtNotifTitle=itemView.findViewById(R.id.txt_notif_title);
            txtNotifId=itemView.findViewById(R.id.txt_notif_id);

        }
    }
}
