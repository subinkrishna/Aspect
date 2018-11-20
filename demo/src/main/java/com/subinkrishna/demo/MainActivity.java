package com.subinkrishna.demo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.subinkrishna.aspect.AspectRatioImageView;
import com.subinkrishna.aspect.AspectRatioLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class MainActivity extends AppCompatActivity {

    // Log tag
    private static final String TAG = "MainActivity";

    Input[] inputsWithImage = {
            new Input("Being Human", "https://raw.githubusercontent.com/subinkrishna/Aspect/master/demo/images/1.jpg", 2000/1333f),
            new Input("Bagel", "https://raw.githubusercontent.com/subinkrishna/Aspect/master/demo/images/2.jpg", 1000/1500f),
            new Input("Frisco, CO", "https://raw.githubusercontent.com/subinkrishna/Aspect/master/demo/images/3.jpg", 1500/538f),
            new Input("Cat", "https://raw.githubusercontent.com/subinkrishna/Aspect/master/demo/images/4.jpg", 1000/1500f),
            new Input("Flamingo Garden", "https://raw.githubusercontent.com/subinkrishna/Aspect/master/demo/images/5.jpg", 800/800f),
            new Input("I'm broken!", "https://raw.githubusercontent.com/subinkrishna/Aspect/master/demo/images/broken.jpg", 2000/2000f)
    };

    int screenWidth = 0;
    int gridSpacing = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Resources res = getResources();
        screenWidth = res.getDisplayMetrics().widthPixels;
        gridSpacing = res.getDimensionPixelSize(R.dimen.grid_spacing);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view,
                                       RecyclerView parent, RecyclerView.State state) {
                outRect.top = gridSpacing;
                outRect.bottom = gridSpacing;
                outRect.left = gridSpacing;
                outRect.right = gridSpacing;
            }
        });

        adapter.setHasStableIds(true);
        recyclerView.setAdapter(adapter);
    }

    private RecyclerView.Adapter<ItemHolder> adapter = new RecyclerView.Adapter<ItemHolder>() {

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final Context context = parent.getContext();
            FrameLayout v = (FrameLayout) LayoutInflater.from(context)
                    .inflate(R.layout.simple_list_item, parent, false);
            return new ItemHolder(v);
        }

        @Override public void onBindViewHolder(final ItemHolder holder, int position) {
            final Input input = inputsWithImage[position % inputsWithImage.length];
            final FrameLayout root = (FrameLayout) holder.itemView;
            final Context context = root.getContext();
            root.setBackground(ContextCompat.getDrawable(context, R.drawable.placeholder));
            holder.textView.setText(input.label);
            holder.imageView.ratio(input.ratio).lock(AspectRatioLayout.WIDTH);
            final int targetW = screenWidth / 2;
            Picasso.get().load(input.url)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .resize(targetW, (int)(targetW / input.ratio))
                    .into(holder.imageView, new Callback() {
                        @Override public void onSuccess() {
                            root.setBackground(null);
                        }

                        @Override public void onError(Exception e) {
                            final Context context = root.getContext();
                            root.setBackground(ContextCompat.getDrawable(context, R.drawable.placeholder_broken));
                        }
                    });
        }

        @Override public int getItemCount() {
            return inputsWithImage.length * 4;
        }

        @Override public long getItemId(int position) {
            return position;
        }
    };

    static class ItemHolder extends RecyclerView.ViewHolder {
        TextView textView;
        AspectRatioImageView imageView;
        ItemHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
            imageView = (AspectRatioImageView) itemView.findViewById(R.id.image);
        }
    }

    static class Input {
        String url;
        String label;
        float ratio;
        int backgroundColor;

        Input (@NonNull String label, @NonNull String url, float ratio) {
            this.label = label;
            this.url = url;
            this.ratio = ratio;
            this.backgroundColor = 0xFFCCCCCC;
        }
    }
}
