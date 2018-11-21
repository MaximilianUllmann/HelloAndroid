package counter.cow.helloandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import counter.cow.helloandroid.R;
import counter.cow.helloandroid.model.Cow;

public class HerdAdapter extends RecyclerView.Adapter<HerdAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView breed;
        public TextView id;


        public ViewHolder(View itemView) {
            super(itemView);
            breed = (TextView) itemView.findViewById(R.id.breed);
            id = (TextView) itemView.findViewById(R.id.id);
        }
    }

    public List<Cow> herd;


    public HerdAdapter(List<Cow> herd) {
        this.herd = herd;
    }

    @Override
    public HerdAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View herdView = inflater.inflate(R.layout.cow_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(herdView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(HerdAdapter.ViewHolder viewHolder, int position) {
        Cow cow = herd.get(position);


        TextView breed_tv = viewHolder.breed;
        breed_tv.setText(String.valueOf(cow.getBreed()));

        TextView id_tv = viewHolder.id;
        id_tv.setText(String.valueOf(cow.getId()));
    }

    @Override
    public int getItemCount() {
        return herd.size();
    }


}
