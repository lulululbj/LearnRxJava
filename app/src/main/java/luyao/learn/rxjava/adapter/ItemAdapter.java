package luyao.learn.rxjava.adapter;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import luyao.learn.rxjava.bean.ItemBean;
import luyao.learn.rxjava.databinding.ItemMainBinding;
import luyao.learn.rxjava.ui.opeartor.BaseOperatorActivity;

/*
 * author luyao
 * date 2021/5/21 13:13
 */
public class ItemAdapter extends ListAdapter<ItemBean, ItemAdapter.ViewHolder> {

    public ItemAdapter(ItemDiffCallback diffCallback) {
        super(diffCallback);
    }

    public int getLetterPosition(String letter) {
        for (int i = 0; i < getCurrentList().size(); i++) {
            if (getItem(i).name.toLowerCase().startsWith(letter.toLowerCase()))
                return i;
        }
        return -1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        holder.binding.itemName.setText(getItem(position).name);
        holder.binding.getRoot().setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), getItem(position).clazz);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(BaseOperatorActivity.BEAN,getItem(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ItemMainBinding binding;

        public ViewHolder(ItemMainBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static class ItemDiffCallback extends DiffUtil.ItemCallback<ItemBean> {
        @Override
        public boolean areItemsTheSame(@NonNull ItemBean oldItem, @NonNull ItemBean newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ItemBean oldItem, @NonNull ItemBean newItem) {
            return TextUtils.equals(oldItem.name, newItem.name);
        }
    }
}
