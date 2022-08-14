package sg.edu.np.mad.test1mad;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {
    public static String readUsername;
    public static String readPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        EditText username = findViewById(R.id.username);

        EditText password = findViewById(R.id.password);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://madpractical6-fb5e7-default-rtdb.asia-southeast1.firebasedatabase.app");
        DatabaseReference myRef = database.getReference("Users");

        // Read from the database
        myRef.child("mad").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                readUsername = dataSnapshot.child("username").getValue().toString();
                readPassword = dataSnapshot.child("password").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        Button submitLoginDetails = findViewById(R.id.loginBtn);
        submitLoginDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String submitUsername = username.getText().toString();
                String submitPassword = password.getText().toString();

                if (readUsername.equals(submitUsername) && readPassword.equals(submitPassword)){
                    Intent intent2 = new Intent(LoginPage.this, ListActivity.class);
                    startActivity(intent2);
                }
            }
        });
    }
}