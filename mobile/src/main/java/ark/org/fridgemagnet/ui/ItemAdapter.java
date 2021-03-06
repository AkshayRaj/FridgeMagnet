/**
 * Created by ARK
 */
package ark.org.fridgemagnet.ui;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ark.org.fridgemagnet.R;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private static final String TAG = ItemAdapter.class.getSimpleName();
    private static ItemAdapter sInstance;
    private Context mContext;
    private ArrayList<Item> mDataSet;

    public static ItemAdapter getInstance(){
        if(sInstance == null){
            sInstance = new ItemAdapter();
        }
        return sInstance;
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    private ItemAdapter() {
        mDataSet = new ArrayList<>();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView mCardView;
        public TextView mTextView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.cardview);
            mTextView = (TextView) itemView.findViewById(R.id.item_name);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cardview, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int position) {
        itemViewHolder.mTextView.setText(mDataSet.get(position).getName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void setDataSet(ArrayList<Item> dataset){
        mDataSet = dataset;
    }

    public ArrayList<Item> getDataSet(){
        return mDataSet;
    }
}
