package data;

import android.app.Activity;
import android.content.Context;
import android.text.TextDirectionHeuristic;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.caloriestracker.FoodList;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

import model.*;

public class CustomListViewAdapter extends ArrayAdapter<Food> {

    private int layoutResources;
    private Activity activity;
    private ArrayList<Food> foodlist = new ArrayList<>();

    public CustomListViewAdapter(Activity act, int resource, ArrayList<Food> data) {
        super(act, resource, data);
        layoutResources = resource;
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


        return super.getView(position, convertView, parent);
    }

    public class ViewHolder {
        Food food;
        TextView foodName;
        TextView foodCalories;
        TextView foodDate;
    }
}
