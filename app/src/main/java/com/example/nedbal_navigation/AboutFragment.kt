package com.example.nedbal_navigation

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class AboutFragment : Fragment() {
    private var api: TextView? = null
    private var icon: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)
        api = view.findViewById<TextView>(R.id.api)
        api?.movementMethod = LinkMovementMethod.getInstance()
        icon = view.findViewById<TextView>(R.id.icon)
        icon?.movementMethod = LinkMovementMethod.getInstance()
        return view
    }
}

