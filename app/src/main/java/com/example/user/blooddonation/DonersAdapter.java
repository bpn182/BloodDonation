package com.example.user.blooddonation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class DonersAdapter extends RecyclerView.Adapter<DonersAdapter.MyViewHolder> {
    private List<Doners> donerList;
    private ArrayList<Doners> filter;
    private ArrayList<Doners> newList;


    public void setFilter(ArrayList<Doners> filter) {
        this.filter = filter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name, phone,group, district;
        private String mItem;
        private TextView mTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            group = itemView.findViewById(R.id.group);
            district = itemView.findViewById(R.id.district);

            itemView.setOnClickListener(this);


        }
        public void setItem(String item) {
            mItem = item;
            mTextView.setText(item);
        }

        @Override
        public void onClick(View view) {
            /*Log.d(TAG, "onClick " + getPosition() + " " + mItem);
            Toast.makeText(view.getContext(), "Item is clicked", Toast.LENGTH_SHORT).show()*/;
            Context context = view.getContext();
            String phoneValue = phone.getText().toString();
            Toast.makeText(view.getContext(), phoneValue, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneValue, null));
            context.startActivity(intent);

        }

    }

    public DonersAdapter(List<Doners> donerList){
        this.donerList = donerList;

    }

    @NonNull
    @Override
    public DonersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.doners_list, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Doners doners = donerList.get(i);
        myViewHolder.name.setText(doners.getName());
        myViewHolder.group.setText(doners.getGroup());
        myViewHolder.phone.setText(doners.getPhone());
        myViewHolder.district.setText(doners.getDistrict());

    }

    @Override
    public int getItemCount() {
        return donerList.size();
    }

    public void setFilters(ArrayList<Doners> newList){
        donerList = new ArrayList<>();
        donerList.addAll(newList);
        notifyDataSetChanged();
    }

}
