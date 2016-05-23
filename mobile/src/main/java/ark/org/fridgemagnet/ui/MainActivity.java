/**
 * Created by ARK
 */
package ark.org.fridgemagnet.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import ark.org.fridgemagnet.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
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
        mAddItemEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyEvent != null) {
                    if (keyEvent.getAction() == KeyEvent.ACTION_DOWN)
                        return true;

                    if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_UP) {
                        String string = mAddItemEditText.getText().toString().replace(" ", "");
                        Log.wtf(TAG, string);
                        mItemAdapter.getDataSet().add(new Item(string));
                        mItemAdapter.notifyItemInserted(0);
                        mAddItemEditText.setText("");
                    }
                }
                return false;
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mItemAdapter);
    }

    /**
     * Interface - View.OnClickListener
     * Called when a view has been clicked.
     *
     * @param view The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        final int viewId = view.getId();
        switch (viewId){
            case R.id.edittext_additem :
                break;
        }
    }
}
