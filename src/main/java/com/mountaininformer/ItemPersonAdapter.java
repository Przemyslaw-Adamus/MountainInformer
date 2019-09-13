package com.mountaininformer;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

class ItemPersonAdapter extends RecyclerView.Adapter<ItemPersonAdapter.ExampleViewHolder>{
    public List<Person> exampleList;
    public ItemPersonAdapter.OnItemClickListener onItemClickListener;
    public ItemPersonAdapter.OnItemLongClickListener onItemLongClickListener;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public interface OnItemLongClickListener{
        void onLongClick(int position);
    }

    public void setOnItemClickListener(ItemPersonAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(ItemPersonAdapter.OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageViewFoto;
        public TextView tvName;
        public TextView tvSurname;
        public TextView tvNumber;

        public ExampleViewHolder(@NonNull View itemView, final ItemPersonAdapter.OnItemClickListener onItemClickListener,final ItemPersonAdapter.OnItemLongClickListener onItemLongClickListener) {
            super(itemView);
            tvName = itemView.findViewById(R.id.textViewName);
            tvSurname = itemView.findViewById(R.id.textViewSurname);
            tvNumber = itemView.findViewById(R.id.textViewNumber);
            imageViewFoto = itemView.findViewById(R.id.imageViewPerson);

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

    public ItemPersonAdapter(List<Person> exampleItemList){
        exampleList = exampleItemList;
    }

    @NonNull
    @Override
    public ItemPersonAdapter.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.person_item,viewGroup,false);
        ItemPersonAdapter.ExampleViewHolder evh = new ItemPersonAdapter.ExampleViewHolder(v,onItemClickListener,onItemLongClickListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemPersonAdapter.ExampleViewHolder exampleViewHolder, int i) {
        Person currentItem = exampleList.get(i);
        exampleViewHolder.tvName.setText(currentItem.getName());
        exampleViewHolder.tvSurname.setText(currentItem.getSurname());
        exampleViewHolder.tvNumber.setText("Tel:" + currentItem.getNumber());
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
