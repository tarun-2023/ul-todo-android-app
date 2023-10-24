package com.example.ul_todo_android_app.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ul_todo_android_app.R
import com.example.ul_todo_android_app.adapters.GoodHabitsAdapter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

data class GoodHabit(
    val name: String,
    val description: String
)

val goodHabits = listOf(
    GoodHabit(
        "Morning Meditation",
        "Spend 10 minutes in the morning meditating to start your day with a clear mind and reduced stress."
    ),
    GoodHabit(
        "Regular Exercise",
        "Incorporate at least 30 minutes of physical activity into your daily routine to boost your energy and overall health."
    ),
    GoodHabit(
        "Healthy Eating",
        "Focus on consuming a balanced diet with plenty of fruits, vegetables, and whole grains to nourish your body."
    ),
    GoodHabit(
        "Daily Reading",
        "Dedicate time each day to read a book or informative articles to expand your knowledge and stimulate your mind."
    ),
    GoodHabit(
        "Gratitude Journaling",
        "Reflect on and write down things you're thankful for each day to promote positivity and mindfulness."
    ),
    GoodHabit(
        "Hydrate Well",
        "Drink an adequate amount of water throughout the day to stay hydrated and support bodily functions."
    ),
    GoodHabit(
        "Prioritize Sleep",
        "Aim for 7-8 hours of quality sleep each night to enhance cognitive function and overall well-being."
    ),
    GoodHabit(
        "Mindfulness Meditation",
        "Practice mindfulness meditation to increase self-awareness and reduce anxiety."
    ),
    GoodHabit(
        "Regular Stretching",
        "Incorporate stretching exercises to maintain flexibility and prevent muscle stiffness."
    ),
    GoodHabit(
        "Effective Time Management",
        "Plan your day and prioritize tasks to make the most of your time and reduce stress."
    ),
    GoodHabit(
        "Practice Gratitude",
        "Express gratitude to others and yourself regularly to foster positive relationships and self-esteem."
    ),
    GoodHabit(
        "Limit Screen Time",
        "Set boundaries on screen time to reduce eye strain and promote face-to-face interactions."
    ),
    GoodHabit(
        "Healthy Snacking",
        "Choose nutritious snacks like fruits, nuts, or yogurt to maintain energy levels throughout the day."
    ),
    GoodHabit(
        "Random Acts of Kindness",
        "Perform small acts of kindness for others to spread positivity and build strong connections."
    ),
    GoodHabit(
        "Learn Something New",
        "Dedicate time to acquire new skills or knowledge to continuously grow and adapt."
    )
)


class GoodHabitsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_good_habits, container, false)

        val goodHabitsRecyclerView: RecyclerView? = view.findViewById(R.id.goodHabitsRecyclerView)
        goodHabitsRecyclerView?.layoutManager = GridLayoutManager(requireContext(), 2)

        val adapter = GoodHabitsAdapter(requireContext(), goodHabits)

        // Set the click listener for the adapter
        adapter.setOnItemClickListener(object : GoodHabitsAdapter.OnItemClickListener {
            override fun onItemClick(habit: GoodHabit) {
                showHabitDetailsDialog(habit)
            }
        })

        goodHabitsRecyclerView?.adapter = adapter

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GoodHabitsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    @SuppressLint("MissingInflatedId")
    private fun showHabitDetailsDialog(habit: GoodHabit) {
        val dialogBuilder = AlertDialog.Builder(requireActivity())
        val dialogView = layoutInflater.inflate(R.layout.activity_habit_dialog, null)
        dialogBuilder.setView(dialogView)

        // Initialize dialog views
        val dialogTitle = dialogView.findViewById<TextView>(R.id.dialogTitle)
        val dialogDescription = dialogView.findViewById<TextView>(R.id.dialogDescription)

        // Set habit details in the dialog
        dialogTitle.text = habit.name
        dialogDescription.text = habit.description

        // Create and show the dialog
        val dialog = dialogBuilder.create()
        dialog.show()
    }
}
