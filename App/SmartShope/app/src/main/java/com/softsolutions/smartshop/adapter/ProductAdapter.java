package com.softsolutions.smartshop.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.softsolutions.smartshop.R;
import com.softsolutions.smartshop.RoundedImageView;
import com.softsolutions.smartshop.pojo.Products;


import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context context;
    List<Products> ls;

    public ProductAdapter(List<Products> getDataAdapter, Context context) {

        super();
        this.ls = getDataAdapter;
        this.context = context;
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cust_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {
        Products obj_test = ls.get(position);

        holder.pid.setText(obj_test.getPid());
        holder.name.setText("Item "+obj_test.getName());
        holder.rate.setText("Rate "+obj_test.getRate());
        holder.category.setText("Category "+obj_test.getCategory());
        holder.unit.setText("Price per "+obj_test.getUnit());
        holder.rackno.setText("Rack no. "+obj_test.getRackno());

        byte[] decodedString = android.util.Base64.decode(obj_test.getImage(), android.util.Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        Bitmap decodedByte1 = RoundedImageView.getCroppedBitmap(decodedByte, 520);

        holder.image.setImageBitmap(decodedByte1);

    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView pid;
        public TextView name;
        public TextView rate;
        public TextView unit;
        public TextView rackno;
        public TextView category;
        public ImageView image;


        public ViewHolder(View itemView) {

            super(itemView);

            name = (TextView) itemView.findViewById(R.id.cust_layout_product_name);
            rate = (TextView) itemView.findViewById(R.id.cust_layout_product_rate);
            image = (ImageView) itemView.findViewById(R.id.cust_layout_image);
            category = (TextView) itemView.findViewById(R.id.cust_layout_product_category);
            unit = (TextView) itemView.findViewById(R.id.cust_layout_product_unit);
            rackno = (TextView) itemView.findViewById(R.id.cust_layout_product_rackno);
            pid = (TextView) itemView.findViewById(R.id.cust_layout_product_pid);

            image.getLayoutParams().height = 200;

            image.getLayoutParams().width = 200;

            image.setScaleType(ImageView.ScaleType.FIT_XY);


        }
    }
}