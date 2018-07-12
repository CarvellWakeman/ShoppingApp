package carvellwakeman.shoppingapp.view;


import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import carvellwakeman.shoppingapp.BR;


public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder{

    protected ViewDataBinding binding;

    public BaseViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(T object) {
        binding.setVariable(BR.item, object);
        binding.executePendingBindings();
    }

}
