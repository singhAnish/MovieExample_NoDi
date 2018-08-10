package sampleproject.android.com.TestProject.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import sampleproject.android.com.TestProject.MyApp;
import sampleproject.android.com.TestProject.R;
import sampleproject.android.com.TestProject.model.MockyModelData;

public class MockyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<MockyModelData> mModel;

    public MockyAdapter(List<MockyModelData> model) {
        this.mModel = model;
    }

    @Override @NonNull
    public MockyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_place, parent, false);
        return new MockyAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder itemHolder, int position) {
        MyViewHolder holder = (MyViewHolder) itemHolder;
        final MockyModelData model = mModel.get(position);
        holder.placeName.setText(model.getPlace());

        Glide.with(MyApp.get().getContext()).load(model.getUrl())
                .thumbnail(0.05f)
                .placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background)
                .fallback(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(holder.placeImage);
    }

    @Override
    public int getItemCount() {
        return mModel.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView placeName;
        private final ImageView placeImage;

        private MyViewHolder(View view) {
            super(view);
            placeName = view.findViewById(R.id.placeName);
            placeImage = view.findViewById(R.id.placeImage);
        }
    }
}


