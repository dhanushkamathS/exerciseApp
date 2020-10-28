package com.example.fitnessproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    private Button startButton;
    private Button randomButton;

    private  int sets ;

     ArrayList<ExeDatatype> source;

    // Layout Manager
    RecyclerView.LayoutManager RecyclerViewLayoutManager;

    // adapter class object
    ExeAdaptor adapter;

    // Linear Layout Manager
    LinearLayoutManager HorizontalLayout;


    int RecyclerViewItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
        randomButton = findViewById(R.id.Randombutton);


        // recycle view setter
     recycleSetter();

     // button to change exercise
     randomButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             recycleSetter();
         }
     });



        // start takes to second activity
        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ActivityTwo.class);
                intent.putExtra("key",(ArrayList<ExeDatatype>) source);
                startActivity(intent);

            }
        });


    }


    public void AddItemsToRecyclerViewArrayList()
    {    source = new ArrayList<>();
        // Adding items to ArrayLis
        ArrayList<ExeDatatype> tempHolder = new ArrayList<>();
        tempHolder.add(new ExeDatatype("Crunch",R.drawable.crunch,"180000",true));
        tempHolder.add(new ExeDatatype("Curls",R.drawable.curls,"15",false));
        tempHolder.add(new ExeDatatype("Dumbbell push up",R.drawable.dumbell_push,"90000",true));
        tempHolder.add(new ExeDatatype("Inclined push up ",R.drawable.inclined_push,"15",false));
        tempHolder.add(new ExeDatatype("One leg stand",R.drawable.one_leg_stand,"60000",true));
        tempHolder.add(new ExeDatatype("push up",R.drawable.pushup,"20",false));
        tempHolder.add(new ExeDatatype("sit up",R.drawable.situp,"120000",true));
        tempHolder.add(new ExeDatatype("Streching",R.drawable.strech,"120000",true));
        tempHolder.add(new ExeDatatype("V-hold",R.drawable.vhold,"120000",true));


        Random rand = new Random();

        // create a temporary list for storing
        // selected element
        for (int i = 0; i < 5; i++) {

            // take a raundom index between 0 to size
            // of given List
            int randomIndex = rand.nextInt(tempHolder.size());

            // add element in temporary list
            source.add(tempHolder.get(randomIndex));

            // Remove selected element from orginal list
            tempHolder.remove(randomIndex);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public  void recycleSetter(){
        // Set LayoutManager on Recycler View
       // recyclerView.setLayoutManager(RecyclerViewLayoutManager);
        // Adding items to RecyclerView.
        AddItemsToRecyclerViewArrayList();



        // calling constructor of adapter
        // with source list as a parameter
        adapter = new ExeAdaptor(source);

        // Set Horizontal Layout Manager
        // for Recycler view
        HorizontalLayout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
       recyclerView.setLayoutManager(HorizontalLayout);

        // Set adapter on recycler view
        recyclerView.setAdapter(adapter);
    }
}
