package com.example.asus.internship;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.asus.internship.controller.DetailRepos;
import com.example.asus.internship.model.Item;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>  {
    private List<Item> items;
    private Context context;
    public Adapter(Context applicationContext, List<Item> itemArrayList) {
        this.context = applicationContext;
        this.items = itemArrayList;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repos, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.title.setText(items.get(position).getName());
        viewHolder.owner.setText(items.get(position).getOwner().getLogin());

        Log.e("safaa", "description : "+items.get(position).getDescription());
        viewHolder.stars.setText(String.valueOf(items.get(position).getStargazersCount()));
        Glide.with(context)
                .asBitmap()
                .load(items.get(position).getOwner().getAvatar_url())
                .into(viewHolder.avatar);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, owner,stars;

        private ImageView avatar;


        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            //description = (TextView) view.findViewById(R.id.description);
           // description.setMovementMethod(new ScrollingMovementMethod());
            owner = (TextView) view.findViewById(R.id.owner);
            stars = (TextView) view.findViewById(R.id.stars);
            avatar = (ImageView) view.findViewById(R.id.avatar);

            //on item click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    desc(v);



                }

                private void desc(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Item clickedDataItem = items.get(pos);
                        Intent intent = new Intent(context, DetailRepos.class);
                        intent.putExtra("description", items.get(pos).getDescription());
                        intent.putExtra("title", items.get(pos).getName());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getName(), Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }
    }
}
