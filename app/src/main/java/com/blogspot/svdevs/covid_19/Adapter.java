package com.blogspot.svdevs.covid_19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends ArrayAdapter<Model> {

    private Context context;
    private List<Model> modelList;
    private List<Model> modelListFilterd;

    public Adapter(Context context, List<Model> model) {
        super(context, R.layout.list_item,model);

        this.context = context;
        this.modelList = model;
        this.modelListFilterd = model;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,null,true);
        TextView countryName = v.findViewById(R.id.tv_country);
        ImageView countryFlag = v.findViewById(R.id.flag);
        countryName.setText(modelListFilterd.get(position).getCountry());
        Glide.with(context).load(modelListFilterd.get(position).getFlag()).into(countryFlag);

        return v;
    }

    @Override
    public int getCount() {
        return modelListFilterd.size();
    }

    @Nullable
    @Override
    public Model getItem(int position) {
        return modelListFilterd.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if(constraint == null||constraint.length() == 0){
                    filterResults.count= modelList.size();
                    filterResults.values=modelList;
                }else {
                    List<Model> resultModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for (Model itemModel:modelList){
                        if(itemModel.getCountry().toLowerCase().contains(searchStr)){
                            resultModel.add(itemModel);
                        }
                        filterResults.count = resultModel.size();
                        filterResults.values = resultModel;
                    }
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                modelListFilterd = (List<Model>) results.values;
                TrackCountries.countryList = (List<Model>) results.values;
                notifyDataSetChanged();

            }
        };
        return  filter;
    }
}
