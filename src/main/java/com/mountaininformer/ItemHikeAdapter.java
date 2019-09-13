package com.mountaininformer;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class ItemHikeAdapter extends RecyclerView.Adapter<ItemHikeAdapter.ExampleViewHolder>{
    public List<Hike> exampleList;
    public ItemHikeAdapter.OnItemClickListener onItemClickListener;
    public ItemHikeAdapter.OnItemLongClickListener onItemLongClickListener;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public interface OnItemLongClickListener{
        void onLongClick(int position);
    }

    public void setOnItemClickListener(ItemHikeAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(ItemHikeAdapter.OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageViewFoto;
        public TextView tvName;
        public TextView tvSurname;
        public TextView tvNumber;

        public ExampleViewHolder(@NonNull View itemView, final ItemHikeAdapter.OnItemClickListener onItemClickListener, final ItemHikeAdapter.OnItemLongClickListener onItemLongClickListener) {
            super(itemView);
//            tvDate = itemView.findViewById(R.id.textViewDate);
//            tvXCord = itemView.findViewById(R.id.textViewXCord);
//            tvYCord = itemView.findViewById(R.id.textViewYCord);
//            imageViewWinter = itemView.findViewById(R.id.imageViewWinter);
//            imageViewSummer = itemView.findViewById(R.id.imageViewSummer);
//            imageViewLocation = itemView.findViewById(R.id.imageViewLocation);
//            imageViewFoto = itemView.findViewById(R.id.imageViewFoto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            onItemClickListener.onItemClick(position);
                        }
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(onItemLongClickListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            onItemLongClickListener.onLongClick(position);
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
    }

    public ItemHikeAdapter(ArrayList<Hike> exampleItemList){
        exampleList = exampleItemList;
    }

    @NonNull
    @Override
    public ItemHikeAdapter.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.person_item,viewGroup,false);
        ItemHikeAdapter.ExampleViewHolder evh = new ItemHikeAdapter.ExampleViewHolder(v,onItemClickListener, onItemLongClickListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHikeAdapter.ExampleViewHolder exampleViewHolder, int i) {
        Hike currentItem = exampleList.get(i);

    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }

    public void deleteItem(int position) {

    }

    public void editItem(int position) {

    }
}
