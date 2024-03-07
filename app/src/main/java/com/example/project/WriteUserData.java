package com.example.project;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WriteUserData {
    private DatabaseReference databaseReference;

    /**
     * Initialize the Realtime Database
     */
    public WriteUserData() {

        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    /**
     *
     * @param user
     * adds the user to the data base
     */
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
