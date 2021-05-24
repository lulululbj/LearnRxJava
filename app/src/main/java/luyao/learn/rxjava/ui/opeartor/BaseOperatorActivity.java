package luyao.learn.rxjava.ui.opeartor;

/*
 * author: luyao
 * date:   2021/5/21 15:50
 */

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import luyao.learn.rxjava.R;
import luyao.learn.rxjava.bean.ItemBean;
import luyao.learn.rxjava.databinding.ActivityOperatorBinding;

public abstract class BaseOperatorActivity extends AppCompatActivity {

    public static final String BEAN = "item_bean";
    protected ActivityOperatorBinding binding;

    protected Observable observable;
    protected Observer observer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOperatorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bind();
        init();
    }

    abstract void init();

    private void bind() {
        bindOperation((ItemBean) getIntent().getSerializableExtra(BEAN));
        binding.subscribeBt.setOnClickListener(v -> {
            subscribe();
        });
    }

    protected void bindOperation(ItemBean itemBean) {
        if (itemBean == null) return;
        binding.operatorImg.setImageResource(itemBean.resId);
        binding.toolBar.setTitle(itemBean.name);
        binding.toolBar.setNavigationIcon(R.drawable.ic_arrow_back);
        binding.toolBar.setNavigationOnClickListener(v -> onBackPressed());
    }

    protected void bindText(String message) {
        String text = binding.statusTv.getText().toString();
        binding.statusTv.setText(String.format("%s\n%s", text, message));
    }

    protected void subscribe() {
        if (observable != null && observer != null)
            observable.subscribe(observer);
    }
}
