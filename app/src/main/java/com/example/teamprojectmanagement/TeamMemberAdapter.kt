package com.example.teamprojectmanagement.ui.team

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.teamprojectmanagement.R

class TeamMemberAdapter(private val teamMembers: List<String>) : RecyclerView.Adapter<TeamMemberAdapter.TeamMemberViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamMemberViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_team_member, parent, false)
        return TeamMemberViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamMemberViewHolder, position: Int) {
        val teamMember = teamMembers[position]
        holder.teamMemberNameTextView.text = teamMember
    }

    override fun getItemCount(): Int {
        return teamMembers.size
    }

    class TeamMemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val teamMemberNameTextView: TextView = itemView.findViewById(R.id.teamMemberNameTextView)
    }
}
