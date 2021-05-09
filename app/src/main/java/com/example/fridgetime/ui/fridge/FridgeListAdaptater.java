package com.example.fridgetime.ui.fridge;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fridgetime.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FridgeListAdaptater extends RecyclerView.Adapter<FridgeListAdaptater.FridgeListViewHolder> {
    private ItemClickListener mClickListener;
    private final Map<String, Integer> data;

    FridgeListAdaptater(Map<String, Integer> _data) {
        this.data = _data;
    }


    @NotNull
    @Override
    public FridgeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fridge_list_element, parent, false);
        return new FridgeListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FridgeListViewHolder holder, int position) {
        //Récupère les ingredients depuis la map par sa position
        List<String> ingredientList= new ArrayList<>(data.keySet());
        String ingredient = ingredientList.get(position);
        int quantities = data.get(ingredient);
        holder.ingredientText.setText(ingredient);
        holder.quantitiesText.setText(quantities + " g");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class FridgeListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView ingredientText;
        TextView quantitiesText;

        public FridgeListViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientText = itemView.findViewById(R.id.fridgeList_item);
            quantitiesText = itemView.findViewById(R.id.fridgeList_quantities);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) mClickListener.onItemClick(v, getAdapterPosition());
        }
    }
}
