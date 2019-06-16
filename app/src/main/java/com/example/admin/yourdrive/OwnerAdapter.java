package com.example.admin.yourdrive;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.ContentValues.TAG;

public class OwnerAdapter extends RecyclerView.Adapter<OwnerAdapter.ViewHolder>  {
    public List<Ownercontactdetails> ownerlistitems;
    public Context context;

    public OwnerAdapter (List<Ownercontactdetails> ownerlistitems, Context context) {
        this.ownerlistitems = ownerlistitems;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ownerdetailslayout, parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder Holder, int position) {
        final Ownercontactdetails listitem=ownerlistitems.get(position);
        Holder.tvcarmodel.setText(listitem.getCarmodel());
        Holder.tvownercarlocation.setText(listitem.getOwnerlocation());
        Holder.tvownerphone.setText(listitem.getOwnercontactno());
        Picasso.with(context).load(listitem.getProfilepicurl()).into(Holder.tvownerimage);
        Holder.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0221636743"));
                context.startActivity(intent);
            }
        });
        Holder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.fromParts("sms","0221636743","null"));
                intent.putExtra("sms body","HI..");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ownerlistitems.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvcarmodel;
        public TextView tvownercarlocation;
        public TextView tvownerphone;
        public ImageView tvownerimage;
        public ImageView phone;
        public ImageView message;
        public RelativeLayout Layout;

        public ViewHolder(View itemView) {
            super(itemView);
            tvcarmodel=(TextView)itemView.findViewById(R.id.ownercarmodel);
            tvownercarlocation=(TextView)itemView.findViewById(R.id.owneraddress);
            tvownerphone=(TextView)itemView.findViewById(R.id.ownercontactno);
            tvownerimage=(ImageView)itemView.findViewById(R.id.ownerprofilepic);
            phone=(ImageView)itemView.findViewById(R.id.imgcontact);
            message=(ImageView)itemView.findViewById(R.id.imgmessage);
            Layout=(RelativeLayout)itemView.findViewById(R.id.rellayout2);
        }

    }
}
