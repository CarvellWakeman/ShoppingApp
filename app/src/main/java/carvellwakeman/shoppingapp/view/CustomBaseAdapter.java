package carvellwakeman.shoppingapp.view;


import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import carvellwakeman.shoppingapp.data.IBaseEntity;
import carvellwakeman.shoppingapp.utils.DiffUtilCallback;

import java.util.ArrayList;
import java.util.List;


public abstract class CustomBaseAdapter<T extends IBaseEntity, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {

    protected int layout;

    protected final List<T> elements;
    protected List<T> displayElements;

    public CustomBaseAdapter(List<T> elements, int layout) {
        this.elements = elements;
        this.layout = layout;

        displayElements = new ArrayList<>(elements);
    }

    @NonNull
    @Override
    public abstract VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(displayElements.get(position));
    }

    @Override
    public int getItemCount() {
        return displayElements.size();
    }

    @Override
    public long getItemId(int position) {
        return displayElements.get(position).getId();
    }

    public void updateElements(List<T> newList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtilCallback<>(newList, elements));

        elements.clear();
        elements.addAll(newList);
        displayElements = new ArrayList<>(elements);

        diffResult.dispatchUpdatesTo(this);
    }

}
