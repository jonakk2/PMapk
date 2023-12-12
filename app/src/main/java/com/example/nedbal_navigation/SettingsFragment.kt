package com.example.nedbal_navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class SettingsFragment : Fragment() {
    private var btnDelete: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        btnDelete = view.findViewById<Button>(R.id.btn_deleteHistory)
        btnDelete?.setOnClickListener {
            Toast.makeText(
                activity, "Deleted" + activity?.filesDir + "/" + FILE_NAME,
                Toast.LENGTH_LONG
            ).show()
            activity?.deleteFile(FILE_NAME)
        }
        return view
    }

    companion object {
        private const val FILE_NAME = "historie.txt"
    }
}


