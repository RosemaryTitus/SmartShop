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
import com.softsolutions.smartshop.pojo.Cart;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    Context context;
    List<Cart> ls;

    public CartAdapter(List<Cart> getDataAdapter, Context context) {

        super();
        this.ls = getDataAdapter;
        this.context = context;
    }

    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cust_layout_cart, parent, false);

        CartAdapter.ViewHolder viewHolder = new CartAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CartAdapter.ViewHolder holder, int position) {
        Cart obj_test = ls.get(position);


        holder.product.setText(obj_test.getProduct());
        holder.amount.setText(obj_test.getAmount());
        holder.pid.setText(obj_test.getPid());
        holder.number.setText(obj_test.getNumber());
        holder.date.setText(obj_test.getDate());
        holder.category.setText(obj_test.getCategory());
        holder.cartid.setText(obj_test.getCartid());

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


        public TextView product;
        public TextView cartid;
        public ImageView image;
        public TextView category;
        public TextView date;
        public TextView amount;
        public TextView number;
        public TextView pid;




        public ViewHolder(View itemView) {

            super(itemView);

            product = (TextView) itemView.findViewById(R.id.cart_cust_layout_product);
            amount = (TextView) itemView.findViewById(R.id.cart_cust_layout_amount);
            image = (ImageView) itemView.findViewById(R.id.cart_cust_layout_image);
            category = (TextView) itemView.findViewById(R.id.cart_cust_layout_category);
            date = (TextView) itemView.findViewById(R.id.cart_cust_layout_date);
            cartid = (TextView) itemView.findViewById(R.id.cart_cust_layout_cartid);
            pid = (TextView) itemView.findViewById(R.id.cart_cust_layout_pid);
            number = (TextView) itemView.findViewById(R.id.cart_cust_layout_product_number);

            image.getLayoutParams().height = 200;

            image.getLayoutParams().width = 200;

            image.setScaleType(ImageView.ScaleType.FIT_XY);


        }
    }
}