package sg.edu.np.mad.test1mad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.content.Context;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    private String TAG = "List Activity";
    private RecyclerView recyclerView;
    Random rand = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        DBHandler db = new DBHandler(this);
        for (int i = 0; i < 20; i++){
            Random randomNo = new Random();

            int userId = Math.abs(randomNo.nextInt()/1000);
            String userName = "Name" + userId;
            String userDescription = "Description " + Math.abs(randomNo.nextInt()/1000);
            boolean userIsFollowing = randomNo.nextInt(2)==1;
            User newUser = new User(userName, userDescription, userId, userIsFollowing);
            Log.v(TAG, newUser.getUserName());
            db.insertUser(newUser);
        }
        ArrayList<User> userArrayList = db.getUser();
        RecyclerView recyclerView = findViewById(R.id.userRecycler);
        Adapter myAdapter = new Adapter(userArrayList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);

//        ImageView profileImage = findViewById(R.id.profileImage);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Profile");
//        builder.setMessage("MADness");
//        builder.setCancelable(true);
//        builder.setPositiveButton("Close", new
//                DialogInterface.OnClickListener(){
//                    public void onClick(DialogInterface dialog, int id){
//
//                    }
//                });
//        builder.setNegativeButton("View", new
//                DialogInterface.OnClickListener(){
//                    public void onClick(DialogInterface dialog, int id){
//                        Random num = new Random();
//                        int rand = num.nextInt();
//                        Intent launchMainActivity = new Intent(ListActivity.this, MainActivity.class);
//                        launchMainActivity.putExtra("Random Number", rand);
//                        startActivity(launchMainActivity);
//                    }
//                });
//
//        profileImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.v(TAG, "Image Pressed!");
//                AlertDialog alert = builder.create();
//                alert.show();
//            }
//        });
    }

//    public ArrayList<User> CreateUserList(int num){
//        ArrayList<User> userList = new ArrayList<>();
//        for (int i = 0; i < num; i++){
//            String randNameDesc = String.valueOf(rand.nextInt(999999));
//            boolean randFollowed = rand.nextBoolean();
//            User newUser = new User(randNameDesc, randNameDesc, i, randFollowed);
//            userList.add(newUser);
//        }
//        return userList;
//    }
//
//    public void SetAdapter(ArrayList<User> userList){
//        RecyclerView recyclerView = findViewById(R.id.userRecycler);
//        Adapter myAdapter = new Adapter(userList);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(myAdapter);
//    }
}