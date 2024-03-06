package com.example.project;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WriteUserData {
    private DatabaseReference databaseReference;
    public WriteUserData() {
        // Initialize the Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }
    public void addUser(User user) {
        String userId = databaseReference.child("users").push().getKey();

        // Set the generated ID for the user
        user.setId(userId);

        // Add the user to the "users" node in the Realtime Database
        databaseReference.child("users").child(userId).setValue(user)
                .addOnSuccessListener(aVoid -> {
                    // Data added successfully
                    // Handle success
                })
                .addOnFailureListener(e -> {
                    // Handle errors
                });
    }
}
