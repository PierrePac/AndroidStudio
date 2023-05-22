package com.example.greenvillage.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenvillage.R;
import com.example.greenvillage.activities.ProductsListActivity;
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
        // Get the subcategory at the current position
        SubCategory subCategory = subCategories.get(position);
        // Set the subCategory name
        holder.sub_nomination.setText(subCategory.getNomination());

        // Set the click listener on the LinearLayout
        holder.sub_nomination.setOnClickListener(v -> {
            //Start the productListActivity and pass the slug as an extra
            Context context = holder.itemView.getContext();
            Intent intent = new Intent(context, ProductsListActivity.class);
            intent.putExtra("subcategory_slug", subCategory.getSlug());
            context.startActivity(intent);
        });
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

        private TextView sub_nomination;

        SubCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            sub_nomination = itemView.findViewById(R.id.sub_nomination);
        }

        void bind(SubCategory subCategory) {
            sub_nomination.setText(subCategory.getNomination());
        }
    }
}
