package com.subinkrishna.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.subinkrishna.aspect.AspectRatioFrameLayout;

public class MainActivity extends AppCompatActivity {

    Input[] inputs = {
            new Input("4:3",  4/3f,   0xFFFF8888),
            new Input("1:1",  1f,     0xFFFF00FF),
            new Input("16:9", 16/9f,  0xFFFFFF00),
            new Input("3:2",  3/2f,   0xFF00FFFF),
            new Input("2:3",  2/3f,   0xFF00FF00),
            new Input("2:1",  2f,     0xFFFF0000),
            new Input("4:3",  4/3f,   0xFFFF8888),
            new Input("1:1",  1f,     0xFFFF00FF),
            new Input("16:9", 16/9f,  0xFFFFFF00),
            new Input("3:2",  3/2f,   0xFF00FFFF),
            new Input("2:3",  2/3f,   0xFF00FF00),
            new Input("2:1",  2f,     0xFFFF0000)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    private RecyclerView.Adapter<ItemHolder> adapter = new RecyclerView.Adapter<ItemHolder>() {
        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final Context context = parent.getContext();
            final AspectRatioFrameLayout v = (AspectRatioFrameLayout) LayoutInflater.from(context)
                    .inflate(R.layout.listitem_1, parent, false);
            return new ItemHolder(v);
        }

        @Override public void onBindViewHolder(ItemHolder holder, int position) {
            final AspectRatioFrameLayout v = (AspectRatioFrameLayout) holder.itemView;
            final Input input = inputs[position];
            v.ratio(input.ratio);
            v.setBackgroundColor(input.backgroundColor);
            holder.textView.setText(input.label);
        }

        @Override public int getItemCount() {
            return inputs.length;
        }
    };

    static class ItemHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ItemHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }

    static class Input {
        String label;
        float ratio;
        int backgroundColor;
        Input (@NonNull String label, float ratio, @ColorInt int backgroundColor) {
            this.label = label;
            this.ratio = ratio;
            this.backgroundColor = backgroundColor;
        }
    }
}
