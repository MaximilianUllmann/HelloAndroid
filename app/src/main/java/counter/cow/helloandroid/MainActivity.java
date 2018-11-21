package counter.cow.helloandroid;

import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import counter.cow.helloandroid.adapter.HerdAdapter;
import counter.cow.helloandroid.databinding.ActivityMainBinding;
import counter.cow.helloandroid.model.Cow;

public class MainActivity extends AppCompatActivity {


    private ArrayList<Cow> herd = new ArrayList<>();
    private HerdAdapter mHerdAdapter;
    private ActivityMainBinding binding;
    private MediaPlayer mediaPlayer;


    private Button addBt;
    private Button rejectBt;
    private Button resetBt;
    private EditText breedEt;
    private EditText idEt;
    private RecyclerView rvHerd;
    private TextView counterTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            herd = savedInstanceState.getParcelableArrayList("value");
        }
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        activateBreedEt();
        activateIdEt();
        activateCounterTv();
        activateAddbt();
        activateMediaPlayer();
        activateRejectBt();
        activateResetBt();
        activateHeader();
        activateTable();

    }

    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putParcelableArrayList("value", herd);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void activateAddbt() {
        addBt = findViewById(R.id.bt_add);
        addBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                if(breedInputValid() && idInputValid()) {
                    mediaPlayer.start();
                    Cow cow = new Cow(Integer.parseInt(breedEt.getText().toString()), Integer.parseInt(idEt.getText().toString()));
                    herd.add(0, cow);
                    mHerdAdapter.notifyItemInserted(0);
                    rvHerd.scrollToPosition(0);
                    counterTv.setText("Cows: " + String.valueOf(herd.size()));
                    idEt.setText(null);
                    breedEt.setText(null);

                }
            }
        });
    }

    private void activateRejectBt() {
        rejectBt = findViewById(R.id.bt_reject);
        rejectBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                breedEt.setText(null);
                idEt.setText(null);
            }
        });

    }

    private void activateResetBt(){
        resetBt = findViewById(R.id.resetBt);
        resetBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                herd.clear();
                counterTv.setText("Cows: " + String.valueOf(herd.size()));
            }
        });
    }

    private void activateBreedEt() {

        breedEt = (EditText) findViewById(R.id.et_breed);
    }

    private void activateIdEt() {

        idEt = findViewById(R.id.et_id);
    }

    private void activateCounterTv() {
        counterTv = findViewById(R.id.tvCounter);
    }


    private void activateHeader(){
        TextView breedHeader = findViewById(R.id.breed);
        breedHeader.setText("BREED");
        TextView idHeader = findViewById(R.id.id);
        idHeader.setText("ID");
    }

    private void activateMediaPlayer(){
        try {
            Uri mohh = Uri.parse("raw/mohh.mp3");
            mediaPlayer = MediaPlayer.create(this, R.raw.mohh);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void activateTable(){
        rvHerd = (RecyclerView) findViewById(R.id.recyclerView);

        mHerdAdapter = new HerdAdapter(herd);
        rvHerd.setAdapter(mHerdAdapter);
        rvHerd.setLayoutManager(new LinearLayoutManager(this));
    }

    private boolean breedInputValid(){
        boolean result = true;
        if(!TextUtils.isDigitsOnly(breedEt.getText())){
            result = false;
        }
        if(breedEt.getText().toString().trim().isEmpty()){
            result = false;
        }
        if(breedEt.getText() == null){
            result = false;
        }
        if(result == true && (Integer.parseInt(breedEt.getText().toString()) < 0 || Integer.parseInt(breedEt.getText().toString()) > 999)){
            result = false;
        }
        if(!result){
            breedEt.setError("BREED has to be a digit between 0 and 999");
        }
        return result;
    }

    private boolean idInputValid(){
        boolean result = true;
        if(!TextUtils.isDigitsOnly(idEt.getText())){
            result = false;
        }
        if(idEt.getText().toString().trim().isEmpty()){
            result = false;
        }
        if(idEt.getText() == null){
            result = false;
        }
        if(result == true && (Integer.parseInt(idEt.getText().toString()) < 0 || Integer.parseInt(idEt.getText().toString()) > 999)){
            result = false;
        }
        if(!result){
            idEt.setError("ID has to be a digit between 0 and 999");
        }
        return result;
    }

    public EditText getBreedEt() {
        return breedEt;
    }

    public EditText getIdEt() {
        return idEt;
    }

}
