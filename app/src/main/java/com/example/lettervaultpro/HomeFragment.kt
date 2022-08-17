package com.example.lettervaultpro

import android.app.Application

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.lettervaultpro.data.Letter
import com.example.lettervaultpro.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

//    lateinit var item: Letter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    private val viewModel: LetterViewModel by activityViewModels {
        LetterViewModelFactory(
            (activity?.application as LetterApplication).database.letterDao(), Application()
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        navController = Navigation.findNavController(view)

        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.datalist?.observe(viewLifecycleOwner) {
            if (it != null) {
                HomePageData(it)
            } else {
                binding.keyImg.visibility = View.INVISIBLE
                binding.msgSubject.text = getString(R.string.nofFound)
                binding.border.setOnClickListener { }
            }


        }

    }


    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.clear() //remove all items
        requireActivity().menuInflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.menu_add -> {

                findNavController().navigate(R.id.action_homeFragment_to_messageFragment)

                true
            }

            R.id.menu_list -> {
                findNavController().navigate(R.id.action_homeFragment_to_letterFragment)
                true
            }
            R.id.menu_settings -> {

                true
            }
            R.id.settings -> {
                findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }


    private fun HomePageData(msg: Letter) {
        binding.msgSubject.text = msg.subject
        binding.keyImg.setImageResource(R.drawable.ic_baseline_lock)
        binding.keyImg.visibility = View.VISIBLE
        if (msg.status) {
            binding.keyImg.setImageResource(R.drawable.ic_baseline_lock_open)
        } else {
            binding.keyImg.setImageResource(R.drawable.ic_baseline_lock)
        }

        binding.border.setOnClickListener {
            val action =
                HomeFragmentDirections.actionHomeFragmentToOpenMessage(viewModel.datalist!!.value!!.id)
            findNavController().navigate(action)

        }
    }


}