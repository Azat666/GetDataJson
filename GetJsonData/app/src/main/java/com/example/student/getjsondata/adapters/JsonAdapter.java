package com.example.student.getjsondata.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.student.getjsondata.R;
import com.example.student.getjsondata.activtyis.MainActivity;
import com.example.student.getjsondata.fragments.JsonBigImageDialogFragment;
import com.example.student.getjsondata.models.JsonModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class JsonAdapter extends RecyclerView.Adapter<JsonAdapter.MyViewHolder> {
    private Context context;
    private List<JsonModel> list;

    public JsonAdapter(Context context, List<JsonModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public JsonAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.json_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(JsonAdapter.MyViewHolder holder, int position) {
        Picasso.get().load(list.get(position).getThumbnailUrl()).into(holder.imageId);
        holder.textTitle.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private  ImageView imageId;
        private TextView textTitle;

        MyViewHolder(View itemView) {
            super(itemView);
            imageId = itemView.findViewById(R.id.item_image);
            textTitle = itemView.findViewById(R.id.item_text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) context).setJsonModel(list.get(getAdapterPosition()));
                    JsonBigImageDialogFragment dialogFragment = new JsonBigImageDialogFragment();
                    dialogFragment.show(((MainActivity) context).getFragmentManager(), "aaa");
                }
            });
        }
    }
}
