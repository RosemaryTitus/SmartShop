package com.softsolutions.smartshop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.softsolutions.smartshop.R;
import com.softsolutions.smartshop.pojo.Feedback;

import java.util.List;


public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.ViewHolder> {

    Context context;
    List<Feedback> ls;

    public FeedbackAdapter(List<Feedback> getDataAdapter, Context context) {

        super();
        this.ls = getDataAdapter;
        this.context = context;
    }

    @Override
    public FeedbackAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cust_layout_feedback, parent, false);

        FeedbackAdapter.ViewHolder viewHolder = new FeedbackAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FeedbackAdapter.ViewHolder holder, int position) {
        Feedback obj_test = ls.get(position);

        holder.name.setText(obj_test.getName());
        holder.email.setText(obj_test.getEmail());
        holder.phone.setText(obj_test.getPhone());
        holder.feedback.setText(obj_test.getFeedback());
        holder.date.setText(obj_test.getDate());
        holder.rating.setRating(Float.parseFloat(obj_test.getRating()));

    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        public TextView name;
        public TextView email;
        public TextView phone;
        public TextView date;
        public TextView feedback;
        public RatingBar rating;




        public ViewHolder(View itemView) {

            super(itemView);

            name = (TextView) itemView.findViewById(R.id.feedback_cust_layout_name);
            email =  (TextView)itemView.findViewById(R.id.feedback_cust_layout_email);
            phone = (TextView)itemView.findViewById(R.id.feedback_cust_layout_phone);
            feedback =  (TextView)itemView.findViewById(R.id.feedback_cust_layout_feedback);
            date =  (TextView)itemView.findViewById(R.id.feedback_cust_layout_Date);
            rating = (RatingBar) itemView.findViewById(R.id.feedback_cust_layout_rating);


        }
    }

}
