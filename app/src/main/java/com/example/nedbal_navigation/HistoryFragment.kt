package com.example.nedbal_navigation

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader

class HistoryFragment : Fragment() {
    private var textview_line1: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        textview_line1 = view.findViewById<TextView>(R.id.textview_line1)
        textview_line1?.movementMethod = ScrollingMovementMethod()
        load(view)
        return view
    }

    private fun load(v: View?) {
        var fis: FileInputStream? = null
        try {
            fis = requireActivity().openFileInput(FILE_NAME)
            val isr = InputStreamReader(fis)
            val br = BufferedReader(isr)
            val sb = StringBuilder()
            var text: String?
            while (br.readLine().also { text = it } != null) {
                sb.append(text).append("\n")
            }
            textview_line1?.text = sb.toString()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            fis?.close()
        }
    }

    companion object {
        private const val FILE_NAME = "historie.txt"
    }
}

