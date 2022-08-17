package com.example.lettervaultpro

import android.annotation.SuppressLint
import android.app.Application
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.lettervaultpro.databinding.FragmentMessageBinding
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.properties.Delegates


/**
 * A simple [Fragment] subclass.
 * Use the [MessageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MessageFragment : Fragment() {


    private val currentTime: String =
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:ma"))

    var selectedHour by Delegates.notNull<Int>()
    var selectedMinute by Delegates.notNull<Int>()


    private lateinit var binding: FragmentMessageBinding

    private val viewModel: LetterViewModel by activityViewModels {
        LetterViewModelFactory(
            (activity?.application as LetterApplication).database.letterDao(),
            application = Application()
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appBar = (requireActivity() as AppCompatActivity).supportActionBar

        appBar?.apply {
            title = "Created $currentTime"
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_sharp_close)
        }

        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMessageBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

    }


    @Deprecated("Deprecated in Java")
    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.clear() //remove all items
        requireActivity().menuInflater.inflate(R.menu.message_menu, menu)

    }

    @SuppressLint("SimpleDateFormat", "DefaultLocale")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {

            R.id.selected_time -> {
                val cal = Calendar.getInstance()
                val hours = cal.get(Calendar.HOUR_OF_DAY)
                val min = cal.get(Calendar.MINUTE)

                val timePicker = TimePickerDialog(requireContext(), { view, hourOfDay, minute ->
                    selectedMinute = minute
                    selectedHour = hourOfDay

                }, hours, min, true)
                timePicker.show()

                true
            }
            R.id.finish -> {
                addNewItem()
                findNavController().navigate(R.id.action_messageFragment_to_homeFragment)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addNewItem() {
        viewModel.addNewItem(
            selectedHour, selectedMinute,
            binding.subject.text.toString(),
            binding.messageTxt.text.toString(),
        )

    }

}






