package com.adith.noteapp.ui.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.adith.noteapp.R
import com.adith.noteapp.databinding.FragmentUpdateBinding
import com.adith.noteapp.model.TodoData
import com.adith.noteapp.viewmodel.SharedViewModel
import com.adith.noteapp.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_update.*

class UpdateFragment : Fragment() {

    private val args: UpdateFragmentArgs by navArgs<UpdateFragmentArgs>()
    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mToDoViewModel: TodoViewModel by viewModels()
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        binding.args = args
        binding.spinnerPrioritiesUpdate.onItemSelectedListener = mSharedViewModel.listener
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> updateItem()
            R.id.menu_delete -> confirmItemRemoval()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmItemRemoval() {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete '${args.CurrentItem.title}'")
            .setMessage("Apakah Yakin Ingin Menghapus '${args.CurrentItem.title}'")
            .setPositiveButton("Yes") { _, _ ->
                mToDoViewModel.deleteData(args.CurrentItem)
                Toast.makeText(requireContext(), "Berhasil Dihapus", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }
            .setNegativeButton("No") { _, _ -> }
            .create()
            .show()
    }

    private fun updateItem() {
        val mTitle = edt_title_update.text.toString()
        val mPriority = spinner_priorities_update.selectedItem.toString()
        val mDescription = edt_description_update.text.toString()

        val validation = mSharedViewModel.verifyDataFromUSER(mTitle, mDescription)
        if (validation) {
            val newData = TodoData(
                args.CurrentItem.id,
                mTitle,
                mSharedViewModel.parsePriority(mPriority),
                mDescription

            )
            mToDoViewModel.updateData(newData)
            Toast.makeText(requireContext(), "Data Berhasil Di Update", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}