package com.example.myfirstdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> list = new ArrayList<String>();
        Log.d("My Tag", "HELLO");
        final EditText cardHoldersNametxt;
        cardHoldersNametxt = findViewById(R.id.cardHoldersName);
        final EditText accountNumbertxt;
        accountNumbertxt = findViewById(R.id.accountNumber);
        final EditText sortCodetxt;
        sortCodetxt = findViewById(R.id.sortCode);
        final Map<String, Object> user = new HashMap<>();
        final Button submitbtn;
        final TextView name1;
        final TextView name2;
        final TextView name3;
        final TextView name4;
        final TextView name5;
        name1 = findViewById(R.id.name1);
        name2 = findViewById(R.id.name2);
        name3 = findViewById(R.id.name3);
        name4 = findViewById(R.id.name4);
        name5 = findViewById(R.id.name5);
        submitbtn = findViewById(R.id.submitbtn);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference mDocRef = FirebaseFirestore.getInstance().document("users/"+cardHoldersNametxt.getText().toString());
                user.put("card holder's name", cardHoldersNametxt.getText().toString());
                user.put("account number", accountNumbertxt.getText().toString());
                user.put("sort code",sortCodetxt.getText().toString());
                mDocRef.set(user);
            }
        });
        CollectionReference mColRef = FirebaseFirestore.getInstance().collection("users");
        mColRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<DocumentSnapshot> docs = task.getResult().getDocuments();
                name1.setText("Card Holder - "+docs.get(0).get("card holder's name").toString() +"   Account Number - "+ docs.get(0).get("account number").toString()+"   Sort Code - "+docs.get(0).get("sort code").toString());
                name2.setText("Card Holder - "+docs.get(1).get("card holder's name").toString() +"   Account Number - "+ docs.get(1).get("account number").toString()+"   Sort Code - "+docs.get(1).get("sort code").toString());
            }
        });

    }
}
