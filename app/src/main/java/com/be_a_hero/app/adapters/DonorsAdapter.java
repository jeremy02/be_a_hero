package com.be_a_hero.app.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.be_a_hero.app.R;
import com.be_a_hero.app.databinding.ItemDonorsHeaderBinding;
import com.be_a_hero.app.databinding.ItemDonorsRowBinding;
import com.be_a_hero.app.models.UsersListItem;

import java.util.List;
import java.util.Objects;

public class DonorsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<UsersListItem> filtered_items;

    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position, UsersListItem obj);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    class HeaderViewHolder extends ViewHolder {
        ItemDonorsHeaderBinding binding;
        HeaderViewHolder(final ItemDonorsHeaderBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }

    class RowViewHolder extends ViewHolder {
        ItemDonorsRowBinding binding;
        RowViewHolder(final ItemDonorsRowBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public DonorsAdapter(Context ctx, List<UsersListItem> items) {
        this.ctx = ctx;
        filtered_items = items;
    }

    public void setUsersListItemList(List<UsersListItem> items) {
        // then update the items
        filtered_items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case UsersListItem.TYPE_HEADER:
                ItemDonorsHeaderBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_donors_header, parent, false);
                viewHolder = new DonorsAdapter.HeaderViewHolder(binding);
                break;
            case UsersListItem.TYPE_ROW:
                ItemDonorsRowBinding rowBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_donors_row, parent, false);
                viewHolder = new DonorsAdapter.RowViewHolder(rowBinding);
                break;
        }

        return Objects.requireNonNull(viewHolder);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,
                                 int position) {

        switch (holder.getItemViewType()) {

            case UsersListItem.TYPE_HEADER:
//                GeneralItem generalItem
//                        = (GeneralItem) consolidatedList.get(position);
//                GeneralViewHolder generalViewHolder
//                        = (GeneralViewHolder) viewHolder;

                // Populate general item data here

                break;

            case UsersListItem.TYPE_ROW:
//                DateItem dateItem
//                        = (DateItem) consolidatedList.get(position);
//                DateViewHolder dateViewHolder
//                        = (DateViewHolder) viewHolder;

                // Populate date item data here

                break;
        }

        // Here you apply the animation when the view is bound
        setAnimation(holder.itemView, position);
    }

    /**
     * Here is the key method to apply the animation
     */
    private int lastPosition = -1;
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(ctx, R.anim.slide_in_bottom);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (filtered_items != null) {
            return filtered_items.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return filtered_items.get(position).getType();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public UsersListItem getItem(int position) {
        return filtered_items.get(position);
    }
}