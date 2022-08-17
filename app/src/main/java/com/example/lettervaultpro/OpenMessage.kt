package com.example.lettervaultpro


import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lettervaultpro.databinding.FragmentOpenMessageBinding


/**
 * A simple [Fragment] subclass.
 * Use the [OpenMessage.newInstance] factory method to
 * create an instance of this fragment.
 */
class OpenMessage : Fragment() {

    private lateinit var binding: FragmentOpenMessageBinding

    private val args: OpenMessageArgs by navArgs()

    private val viewModel: OpenMessageViewModel by activityViewModels {
        OpenLetterViewModelFactory(
            (activity?.application as LetterApplication).database.letterDao(), Application()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id = args.letterId
        Log.d("ID from data", "$id")

        viewModel.onFetchLetter(id)

        viewModel.singleLetter.observe(this){ letter->
            if(letter.status){
                    (requireActivity() as AppCompatActivity).supportActionBar?.title = "Opened:${letter.selected_date}"
            }else{
                (requireActivity() as AppCompatActivity).supportActionBar?.title = "Opening:${letter.selected_date}"
            }
        }

        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOpenMessageBinding.inflate(inflater, container, false)
        return binding.root
    }


    @Deprecated("Deprecated in Java")
    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.clear() //remove all items
        requireActivity().menuInflater.inflate(R.menu.opened_message_menu, menu)

    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {

            R.id.delete -> {

                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Delete Letter?")
                builder.setPositiveButton(R.string.delete) { dialog, which ->
                    viewModel.singleLetter.observe(this){
                        viewModel.deleteLetter(it.id)
                    }
                    Toast.makeText(this.requireContext(), "Successfully Deleted", Toast.LENGTH_LONG)
                        .show()
                    findNavController().navigate(R.id.action_openMessage_to_letterFragment)
                }

                builder.setNegativeButton(R.string.cancel) { dialog, which ->

                }
                builder.show()

                true
            }
            R.id.share -> {
              viewModel.singleLetter.observe(this){
                  val sendIntent: Intent = Intent().apply {
                      action = Intent.ACTION_SEND
                      putExtra(Intent.EXTRA_TEXT, it.detail)
                      type = "text/plain"
                  }
                  startActivity(sendIntent)
                }

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModelMessage = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

    }


}