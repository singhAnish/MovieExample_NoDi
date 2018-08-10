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
import sampleproject.android.com.TestProject.model.MainActivityModelData;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<MainActivityModelData> mModel;

    public MainAdapter(List<MainActivityModelData> model) {
        this.mModel = model;
    }

    @Override @NonNull
    public MainAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_place, parent, false);
        return new MainAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder itemHolder, int position) {
        MyViewHolder holder = (MyViewHolder) itemHolder;
        final MainActivityModelData model = mModel.get(position);
        holder.placeName.setText(model.getPlace());

        Glide.with(MyApp.get().getContext()).load(model.getUrl())
                .thumbnail(0.05f)//it will download 5% quality of image followed by original image(Progressive image for better user experience)
                .placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background)
                .fallback(R.drawable.ic_launcher_background)// In case when something goes wrong
                .diskCacheStrategy(DiskCacheStrategy.ALL)//It will cache the Image
                .crossFade().into(holder.placeImage);
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


