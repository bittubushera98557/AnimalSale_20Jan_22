package com.example.lenovo.emptypro.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lenovo.emptypro.R
import com.google.firebase.database.FirebaseDatabase

class P2PChatView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p2_pchat_view)



            var loggeInRef = FirebaseDatabase.getInstance().getReference().child("/Users")
            val userId = loggeInRef.push().key
            loggeInRef.child("" + userId).child("name").setValue("strName")
            loggeInRef.child("" + userId).child("email").setValue("strEmail")
            loggeInRef.child("" + userId).child("mobile").setValue("strMob")
            loggeInRef.child("" + userId).child("password").setValue("strPsd")
            loggeInRef.child("" + userId).child("userType").setValue("User")
            loggeInRef.child("" + userId).child("status").setValue("active")
    }
}
