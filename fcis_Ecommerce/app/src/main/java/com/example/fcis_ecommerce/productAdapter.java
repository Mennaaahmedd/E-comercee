package com.example.fcis_ecommerce;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
public class productAdapter extends RecyclerView.Adapter<productAdapter.MyViewHolder> {
String data1 [];

    int images [] = {R.drawable.pinkshirt,R.drawable.purple,R.drawable.blackshirt,R.drawable.yellowshirt,R.drawable.greenshirt};
Context context ;
private  ViewProduct recycle_click ;
    public productAdapter(String[] data1,  Context context) {
        this.data1 = data1;
        //this.img = img;
        this.context = context;
       // this.recycle_click = recycle_click;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(context);
        View v = inflate.inflate(R.layout.single_product,parent,false);
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.product_name.setText(data1[position]);
        holder.product_image.setImageResource(images[position]);


    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView product_name ;
        ImageView product_image ;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            context=itemView.getContext();
            product_name = itemView.findViewById(R.id.proname_txt);
            product_image = itemView.findViewById(R.id.pro_image);
           itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //recycle_click.onItemClick(getAdapterPosition());
                    Intent intent=new Intent(itemView.getContext(),product_details.class);
                    int pos =getAdapterPosition();
                    String name=data1[pos];
                    int image=images[pos];
                    intent.putExtra("h1",image);
                    intent.putExtra("h2",name);
                    itemView.getContext().startActivity(intent);
                }
            });

        }
    }
}
