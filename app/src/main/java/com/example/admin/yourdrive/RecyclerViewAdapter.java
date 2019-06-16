package com.example.admin.yourdrive;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.ContentValues.TAG;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

public List<Listitem> listitems;
public Context context;

    public RecyclerViewAdapter(List<Listitem> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow, parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder Holder, int position) {
    final Listitem listitem=listitems.get(position);
    Holder.tvcarmodel.setText(listitem.getCarmodel());
        Holder.tvcarlocation.setText(listitem.getCarlocation());
        Holder.tvcartripcost.setText(listitem.getCarTripcost());
        Picasso.with(context).load(listitem.getCarImageurl()).into(Holder.tvimageview);
         Holder.rLayout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Log.d(TAG,"onClick:clicked on"+ listitem.getCarmodel());
                 Intent intent=new Intent(context,CardescriptionActivity.class);
                 intent.putExtra("CarModel",listitem.getCarmodel());
                 intent.putExtra("CarImage",listitem.getCarImageurl());
                intent.putExtra("Cardesc",listitem.getCardesciption());
                 intent.putExtra("Carloc",listitem.getCarlocation());
                 intent.putExtra("Carpickup",listitem.getCarpickupdate());
                 intent.putExtra("Cardropoff",listitem.getCardropoffdate());
                 intent.putExtra("Cartripcost",listitem.getCarTripcost());
                 context.startActivity(intent);
                 //Toast.makeText(context,"You clicked"+listitem.getCarmodel(),Toast.LENGTH_LONG).show();
             }
         });
    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       public TextView tvcarmodel;
       public TextView tvcarlocation;
       public TextView tvcartripcost;
       public ImageView tvimageview;
       public RelativeLayout rLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            tvcarmodel=(TextView)itemView.findViewById(R.id.id_Carmodel);
            tvcarlocation=(TextView)itemView.findViewById(R.id.id_location);
            tvcartripcost=(TextView)itemView.findViewById(R.id.id_Tripcost);
            tvimageview=(ImageView)itemView.findViewById(R.id.id_Carimage);
            rLayout=(RelativeLayout)itemView.findViewById(R.id.rellayout);
        }

    }
}
