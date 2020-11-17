package com.adith.noteapp.ui.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.adith.noteapp.R
import com.adith.noteapp.model.TodoData
import com.adith.noteapp.viewmodel.SharedViewModel
import com.adith.noteapp.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {
    private val todoViewModel: TodoViewModel by viewModels()
    private val shareViewModel: SharedViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_add, container, false)
        setHasOptionsMenu(true)


        view.spinner_priorities.onItemSelectedListener = shareViewModel.listener
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add){
            insertDataToDb()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() {
        val mTitle = edt_title.text.toString()
        val mPriority = spinner_priorities.selectedItem.toString()
        val mDescription = edt_description.text.toString()

        val validation = shareViewModel.verifyDataFromUSER(mTitle, mDescription)
        if (validation){
            val newData = TodoData(
                0,
                mTitle,
                shareViewModel.parsePriority(mPriority),
                mDescription

            )
            todoViewModel.insertData(newData)
            Toast.makeText(requireContext(),"Data Berhasil Dibuat", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        }
    }
}