package carvellwakeman.shoppingapp.view;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import carvellwakeman.shoppingapp.utils.DiffUtilCallback;

import java.util.List;


public class CustomBaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {

    private int layout;
    private final List<T> elements;

    public CustomBaseAdapter(List<T> elements, int layout) {
        this.elements = elements;
        this.layout = layout;
    }

    @NonNull
    @Override
    public BaseViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, layout, parent, false);
        return new BaseViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        T object = elements.get(position);
        holder.bind(object);
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    public void updateElements(List<T> newList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtilCallback<>(newList, elements));

        elements.clear();
        elements.addAll(newList);

        diffResult.dispatchUpdatesTo(this);
    }

}
