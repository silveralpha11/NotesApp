package com.adith.noteapp.adapter

import android.view.View
import android.widget.Spinner
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.fragment.app.ListFragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.adith.noteapp.R
import com.adith.noteapp.data.Priority
import com.adith.noteapp.model.TodoData
import com.adith.noteapp.ui.list.ListFragmentDirections
import com.google.android.material.floatingactionbutton.FloatingActionButton

class  BindingAdapter {
    companion object{
        @BindingAdapter("android:navigateToAddFragment")
        @JvmStatic
        fun navigateToAddFragment(view: FloatingActionButton, navigate: Boolean){
            view.setOnClickListener{
                if (navigate) view.findNavController().navigate(R.id.action_listFragment_to_addFragment)

            }
        }

        @BindingAdapter("android:emptyDatabase")
        @JvmStatic
        fun emptyDatabase(view: View, emptyDatabase: MutableLiveData<Boolean>){
            when(emptyDatabase.value){
                true -> view.visibility = View.VISIBLE
                else -> view.visibility = View.INVISIBLE
            }
        }

        @BindingAdapter("android:parsePriorityColor")
        @JvmStatic
        fun parsePriorityColor(cardView: CardView, priority: Priority){
            when(priority){
                Priority.HIGH -> {cardView.setBackgroundColor(cardView.context.getColor(R.color.red))}
                Priority.MEDIUM -> {cardView.setBackgroundColor(cardView.context.getColor(R.color.yellow))}
                Priority.LOW -> {cardView.setBackgroundColor(cardView.context.getColor(R.color.green))}
            }
        }

        @BindingAdapter("android:parsePriorityToInt")
        @JvmStatic
        fun parsePriorityToInt(view: Spinner, priority: Priority){
            when(priority){
                Priority.HIGH -> {view.setSelection(0)}
                Priority.MEDIUM -> {view.setSelection(1)}
                Priority.LOW -> {view.setSelection(2)}
            }
        }

        @BindingAdapter("android:sendDataToUpdateFragment")
        @JvmStatic
        fun sendDataToUpdateFragment(view: CardView, currentItem: TodoData){
            view.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
                view.findNavController().navigate(action)
            }
        }
    }
}