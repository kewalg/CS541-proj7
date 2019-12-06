package data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.caloriestracker.FoodList;
import com.example.caloriestracker.R;

import java.util.ArrayList;

import model.Food;

public class CustomListViewAdapter extends ArrayAdapter<Food> {

    private int layoutResource;
    private Activity activity;
    private ArrayList<Food> foodlist = new ArrayList<>();

    public CustomListViewAdapter(Activity act, int resource, ArrayList<Food> data) {
        super(act, resource, data);
        layoutResource = resource;
        activity = act;
        foodlist = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return foodlist.size();
    }

    @Nullable
    @Override
    public Food getItem(int position) {
        return foodlist.get(position);
    }

    @Override
    public int getPosition(@Nullable Food item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        ViewHolder holder = null;

        if (row == null || (row.getTag() == null)) {
            LayoutInflater inflater = LayoutInflater.from(activity);
            row = inflater.inflate(layoutResource, null);
            holder = new ViewHolder();
            holder.foodName = (TextView) row.findViewById(R.id.tv_foodname_lv);
            holder.foodDate = (TextView) row.findViewById(R.id.tv_date_lv);
            holder.foodCalories = (TextView) row.findViewById(R.id.tv_cal_num_lv);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        holder.food = getItem(position);
        holder.foodName.setText(holder.food.getFoodName());
        holder.foodDate.setText(holder.food.getRecordDate());
        holder.foodCalories.setText(String.valueOf(holder.food.getCalories()));
        final ViewHolder finalHolder = holder;
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(activity, FoodList.class);
                Bundle mbundle = new Bundle();
                mbundle.putSerializable("userObj", finalHolder.food);
                i.putExtras(mbundle);
                activity.startActivity(i);

            }
        });


        return row;
    }

    public class ViewHolder {
        Food food;
        TextView foodName;
        TextView foodCalories;
        TextView foodDate;
    }
}
