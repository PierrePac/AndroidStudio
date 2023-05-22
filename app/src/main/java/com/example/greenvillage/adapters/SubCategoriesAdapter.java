package com.example.greenvillage.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenvillage.R;
import com.example.greenvillage.models.SubCategory;

import java.util.List;

public class SubCategoriesAdapter extends RecyclerView.Adapter<SubCategoriesAdapter.SubCategoryViewHolder> {

    private List<SubCategory> subCategories;
    private Context context;

    public SubCategoriesAdapter(List<SubCategory> subCategories, Context context) {
        this.subCategories = subCategories;
        this.context = context;
    }

    @NonNull
    @Override
    public SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sub_category, parent, false);
        return new SubCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryViewHolder holder, int position) {
        SubCategory subCategory = subCategories.get(position);
        holder.bind(subCategory);
    }

    @Override
    public int getItemCount() {
        return subCategories.size();
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
        notifyDataSetChanged();
    }

    class SubCategoryViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNomination;

        SubCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNomination = itemView.findViewById(R.id.sub_nomination);
        }

        void bind(SubCategory subCategory) {
            tvNomination.setText(subCategory.getNomination());
        }
    }
}
