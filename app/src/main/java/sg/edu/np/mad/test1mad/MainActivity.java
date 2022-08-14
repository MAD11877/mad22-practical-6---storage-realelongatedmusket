package sg.edu.np.mad.test1mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String TAG = "Main Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG, "On Create!");

        Intent receive = getIntent();
        String messageName = receive.getStringExtra("username");
        String messageDesc = receive.getStringExtra("description");
        boolean status = receive.getBooleanExtra("following", false);
        User user = new User(messageName, messageDesc, 0, status);

        DBHandler db = new DBHandler(this);
        TextView username = findViewById(R.id.profileName);
        Intent fromListActivity = getIntent();
        int rand = fromListActivity.getIntExtra("Random Number",0);
        username.setText(user.name + rand);
        TextView description = findViewById(R.id.description);
        description.setText(user.description);
        Button followBtn = findViewById(R.id.followBtn);
        Button messageBtn = findViewById(R.id.messageBtn);

        if (user.followed){
            followBtn.setText("Unfollow");
        }
        followBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Button Pressed!");
                if (user.followed){
                    followBtn.setText("Follow");
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
                    user.followed = false;
                }
                else {
                    followBtn.setText("Unfollow");
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
                    user.followed = true;
                }
                db.updateUser(user);
            }
        });
        messageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TAG, "Msg Button Pressed");
                Intent mainToMessage = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(mainToMessage);
            }
        });

    }
}