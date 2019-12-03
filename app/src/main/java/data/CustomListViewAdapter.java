package data;
import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import model.*;

public class CustomListViewAdapter extends ArrayAdapter<Food>{

    public CustomListViewAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }
}
