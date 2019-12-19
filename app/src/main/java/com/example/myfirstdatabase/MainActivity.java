package com.example.myfirstdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("sample/users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView text;

        Map<String, Object> user = new HashMap<>();
        user.put("email", "eth.and@gmail.com");
        user.put("card holder's name", "ethan ando");
        user.put("account number", "12345678");
        user.put("sort code","12-34-56");
      /*  FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").add(user);*/
        //text = findViewById(R.id.lb);
      mDocRef.set(user);
      mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
          @Override
          public void onSuccess(DocumentSnapshot documentSnapshot) {
              if (documentSnapshot.exists()){
                  String email = documentSnapshot.getString("email");
               //   text.setText(email);
              }
          }
      });



    }
}
