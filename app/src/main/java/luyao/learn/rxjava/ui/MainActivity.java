package luyao.learn.rxjava.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import luyao.learn.rxjava.adapter.ItemAdapter;
import luyao.learn.rxjava.databinding.ActivityMainBinding;
import luyao.learn.rxjava.model.Statics;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initRecyclerView();
        initScroll();
        itemAdapter.submitList(Statics.provideOperators());
    }

    private void initRecyclerView() {
        if (itemAdapter == null)
            itemAdapter = new ItemAdapter(new ItemAdapter.ItemDiffCallback());
        binding.recyclerView.setAdapter(itemAdapter);
    }

    private void initScroll() {
        binding.sideBar.setOnTouchLetterChangeListener(letter -> {
            int position = itemAdapter.getLetterPosition(letter);
            if (position >= 0) {
                binding.recyclerView.smoothScrollToPosition(position);
                ((LinearLayoutManager) binding.recyclerView.getLayoutManager()).scrollToPositionWithOffset(position, 0);
            }
        });
    }
}