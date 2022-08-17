package com.example.lettervaultpro

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.example.lettervaultpro.adapter.LetterAdapter
import com.example.lettervaultpro.data.Letter
import com.example.lettervaultpro.databinding.FragmentLetterBinding
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


// TODO: Rename parameter arguments, choose names that match

/**
 * A simple [Fragment] subclass.
 * Use the [LetterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class LetterFragment : Fragment() {

    private lateinit var binding: FragmentLetterBinding

    private val viewModel: LetterViewModel by activityViewModels {

        LetterViewModelFactory(
            (activity?.application as LetterApplication).database.letterDao(), Application()
        )
    }

    lateinit var letterAdapter: LetterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLetterBinding.inflate(inflater, container, false)

        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.clear() //remove all items
        requireActivity().menuInflater.inflate(R.menu.letter_menu, menu)
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector", "NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        letterAdapter = LetterAdapter { letter: Letter ->

            val action = LetterFragmentDirections.actionLetterFragmentToOpenMessage(letter.id)
            Log.d("ID selected", letter.id.toString())
            findNavController().navigate(
                action
            )
        }

        binding.recyclerView.adapter = letterAdapter
        lifecycleScope.launch {
            viewModel.letterList.collectLatest {
                letterAdapter.submitData(it)

            }
        }

        binding.recyclerView.setHasFixedSize(true)
        binding.letterFragment = this

    }

    fun onFabClick() {
        findNavController().navigate(R.id.action_letterFragment_to_messageFragment)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_filter -> {
                true
            }
            R.id.all -> {
                letterAdapter = LetterAdapter { letter: Letter ->

                    val action =
                        LetterFragmentDirections.actionLetterFragmentToOpenMessage(letter.id)
                    Log.d("ID selected", letter.id.toString())
                    findNavController().navigate(
                        action
                    )
                }

                binding.recyclerView.adapter = letterAdapter
                lifecycleScope.launch {
                    viewModel.letterList.collectLatest {
                        letterAdapter.submitData(it)

                    }
                }
                true
            }
            R.id.opened -> {

                letterAdapter = LetterAdapter { letter: Letter ->

                    val action =
                        LetterFragmentDirections.actionLetterFragmentToOpenMessage(letter.id)
                    Log.d("ID selected", letter.id.toString())
                    findNavController().navigate(
                        action
                    )
                }
                binding.recyclerView.adapter = letterAdapter

                lifecycleScope.launch {
                    viewModel.openedList.collectLatest {
                        letterAdapter.submitData(it)
                    }
                }
                true
            }
            R.id.future -> {
                letterAdapter = LetterAdapter { letter: Letter ->

                    val action =
                        LetterFragmentDirections.actionLetterFragmentToOpenMessage(letter.id)
                    Log.d("ID selected", letter.id.toString())
                    findNavController().navigate(
                        action
                    )
                }
                binding.recyclerView.adapter = letterAdapter
                lifecycleScope.launch {
                    viewModel.futureList.collectLatest {
                        letterAdapter.submitData(it)
                    }

                }

                true
            }
            R.id.settings -> {
                findNavController().navigate(R.id.action_letterFragment_to_settingsFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}





