package com.example.greenvillage.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.greenvillage.R;
import com.example.greenvillage.models.Category;
import com.example.greenvillage.utils.Constants;

import java.util.List;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private List<Category> categories;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public CategoriesAdapter(List<Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    public interface OnItemClickListener {
        void onItemClick(Category category);
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Bind the category data to the views
        Category category = categories.get(position);
        holder.categoryName.setText(category.getNomination());
        // Concatenate the BASE_URL with the Image URL
        String imageUrl = Constants.BASE_URL_IMG + category.getImage();

        // Load the image using a library like Glide
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.category_placeholder)
                .error(R.drawable.error_placeholder)
                .centerInside()
                .override(100, 100)
                .into(holder.categoryImage);

        // Set click listener for the card
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the onItemClick method of the OnItemClickListener interface
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(category);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView categoryImage;
        public TextView categoryName;

        public ViewHolder(View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.category_image);
            categoryName = itemView.findViewById(R.id.category_name);
        }
    }
}
