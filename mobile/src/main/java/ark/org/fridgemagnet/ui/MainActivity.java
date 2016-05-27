/**
 * Created by ARK
 */
package ark.org.fridgemagnet.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;

import ark.org.fridgemagnet.R;
import ark.org.fridgemagnet.jsonreaders.SwipeableRecyclerViewTouchListener;
import ark.org.fridgemagnet.jsonreaders.Utils;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Activity mActivity;
    private EditText mAddItemEditText;
    private RecyclerView mRecyclerView;
    private ItemAdapter mItemAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate()");
        setContentView(R.layout.main_activity);
        mActivity = this;
        mItemAdapter = ItemAdapter.getInstance();
        ArrayList<Item> items = Utils.getItems(getApplicationContext().getFileStreamPath(Utils.ITEMS_JSON_FILE));
        if(items != null) {
            Log.wtf(TAG, "items is not null");
            mItemAdapter.setDataSet(items);
        }
        mAddItemEditText = (EditText) findViewById(R.id.edittext_additem);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mItemAdapter);
        mAddItemEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    String newItem = mAddItemEditText.getText().toString();
                    mItemAdapter.getDataSet().add(0,new Item(newItem));
                    mItemAdapter.notifyItemInserted(0);
                    mAddItemEditText.setText("");
                    mLayoutManager.scrollToPosition(0);
                    return true;
                }
                return false;
            }
        });

        SwipeableRecyclerViewTouchListener swipeableRecyclerViewTouchListener =
                new SwipeableRecyclerViewTouchListener(mRecyclerView,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {
                            /**
                             * Called to determine whether the given position can be swiped to the left.
                             *
                             * @param position
                             */
                            @Override
                            public boolean canSwipeLeft(int position) {
                                return true;
                            }

                            /**
                             * Called to determine whether the given position can be swiped to the right.
                             *
                             * @param position
                             */
                            @Override
                            public boolean canSwipeRight(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    mItemAdapter.getDataSet().remove(position);
                                    mItemAdapter.notifyItemRemoved(position);
                                }
                                mItemAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    mItemAdapter.getDataSet().remove(position);
                                    mItemAdapter.notifyItemRemoved(position);
                                }
                                mItemAdapter.notifyDataSetChanged();
                            }
                        });
        mRecyclerView.addOnItemTouchListener(swipeableRecyclerViewTouchListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.wtf(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.wtf(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.wtf(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.wtf(TAG, "onStop()");
        persistItems();
    }

    @Override
    protected void onDestroy() {
        Log.wtf(TAG, "onDestroy()");
        super.onDestroy();
    }

    /**
     * Items are persisted in a json file on disk
     */
    private void persistItems(){
        ArrayList<Item> dataset = mItemAdapter.getDataSet();
        JSONArray jsonArray = new JSONArray();
        for(Item item : dataset){
            jsonArray.put(item.toJson());
        }
        Utils.writeToFile(getApplicationContext(), Utils.ITEMS_JSON_FILE, jsonArray.toString());
    }
}
