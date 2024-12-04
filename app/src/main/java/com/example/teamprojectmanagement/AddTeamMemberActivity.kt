package com.example.teamprojectmanagement.ui.team

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.teamprojectmanagement.R
import com.google.firebase.firestore.FirebaseFirestore

class AddTeamMemberActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var nameEditText: EditText
    private lateinit var roleEditText: EditText
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_team_member)

        db = FirebaseFirestore.getInstance()

        // Initialize views
        nameEditText = findViewById(R.id.nameEditText)
        roleEditText = findViewById(R.id.roleEditText)
        addButton = findViewById(R.id.addButton)

        addButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val role = roleEditText.text.toString()

            if (name.isNotEmpty() && role.isNotEmpty()) {
                val teamMember = hashMapOf(
                    "name" to name,
                    "role" to role
                )

                db.collection("team_members")
                    .add(teamMember)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Team member added", Toast.LENGTH_SHORT).show()
                        finish() // Close the activity and go back to TeamActivity
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error adding team member: $e", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
