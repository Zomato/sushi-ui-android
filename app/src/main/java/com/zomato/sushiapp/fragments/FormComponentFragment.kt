package com.zomato.sushiapp.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zomato.sushiapp.R
import com.zomato.sushilib.molecules.inputfields.SushiTextInputField
import kotlinx.android.synthetic.main.fragment_text_fields.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class FormComponentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_text_fields, container, false)

        rootView.textFieldRecipient.setEdgeDrawableClickListener(object : SushiTextInputField.EdgeDrawableClickListener {
            override fun onDrawableStartClicked() {

            }

            override fun onDrawableEndClicked() {
                rootView.textFieldRecipient.editText?.setText("")
            }

        })

        rootView.textFieldRecipient.setTextValidator { text ->
            "Must be at least 5 characters".takeIf {
                TextUtils.isEmpty(text) || TextUtils.getTrimmedLength(text) < 5
            }
        }

        return rootView
    }


}
