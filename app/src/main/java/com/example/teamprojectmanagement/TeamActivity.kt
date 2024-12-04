package com.example.teamprojectmanagement.ui.team

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teamprojectmanagement.R
import com.google.firebase.firestore.FirebaseFirestore

class TeamActivity : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()

    private lateinit var teamRecyclerView: RecyclerView
    private lateinit var addTeamMemberButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        // Initialize RecyclerView
        teamRecyclerView = findViewById(R.id.teamRecyclerView)
        teamRecyclerView.layoutManager = LinearLayoutManager(this)

        addTeamMemberButton = findViewById(R.id.addTeamMemberButton)
        addTeamMemberButton.setOnClickListener {
            // Navigate to AddTeamMemberActivity when the button is clicked
            val intent = Intent(this, AddTeamMemberActivity::class.java)
            startActivity(intent)
        }

        val teamMembers = mutableListOf<String>()

        db.collection("team_members")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val name = document.getString("name")
                    teamMembers.add(name ?: "Unknown")
                }

                val adapter = TeamMemberAdapter(teamMembers)
                teamRecyclerView.adapter = adapter
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error getting team members: $e", Toast.LENGTH_SHORT).show()
            }
    }
}
