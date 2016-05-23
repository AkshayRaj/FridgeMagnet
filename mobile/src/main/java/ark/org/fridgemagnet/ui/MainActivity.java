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
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import ark.org.fridgemagnet.R;
import ark.org.fridgemagnet.jsonreaders.ItemReader;

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
                    String newItem = mAddItemEditText.getText().toString().replace(" ", "");
                    mItemAdapter.getDataSet().add(0,new Item(newItem));
                    ItemReader.init(getApplicationContext()).insertItem(0, newItem);
                    mItemAdapter.notifyItemInserted(0);
                    mAddItemEditText.setText("");
                    mLayoutManager.scrollToPosition(0);
                    return true;
                }
                return false;
            }
        });

    }
}
