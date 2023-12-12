package com.example.nedbal_navigation

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class HomeFragment : Fragment() {
    private var animeName: EditText? = null
    private var anime_result: TextView? = null
    private var character_result: TextView? = null
    private var quote_result: TextView? = null
    private var btnFetch: Button? = null
    private var btnSave: Button? = null
    private var mRandomRadio: RadioButton? = null
    private var mTitleRadio: RadioButton? = null
    private var mCharacterRadio: RadioButton? = null
    private var URL: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        mRandomRadio = view.findViewById(R.id.radioRandom)
        mTitleRadio = view.findViewById(R.id.radioTitle)
        mCharacterRadio = view.findViewById(R.id.radioCharacter)

        btnFetch = view.findViewById(R.id.btn_fetch)
        btnSave = view.findViewById(R.id.btn_save)

        animeName = view.findViewById(R.id.anime_name)

        anime_result = view.findViewById(R.id.anime_result)
        character_result = view.findViewById(R.id.character_result)
        quote_result = view.findViewById(R.id.quote_result)

        mRandomRadio?.setOnClickListener {
            mRandomRadio?.let { radioButton ->
                if (radioButton.isChecked) {
                    animeName?.visibility = View.INVISIBLE
                }
            }
        }

        mTitleRadio?.setOnClickListener {
            mTitleRadio?.let { radioButton ->
                if (radioButton.isChecked) {
                    animeName?.apply {
                        setText(Editable.Factory.getInstance().newEditable("Anime name"))
                        visibility = View.VISIBLE
                    }
                }
            }
        }

        mCharacterRadio?.setOnClickListener {
            mCharacterRadio?.let { radioButton ->
                if (radioButton.isChecked) {
                    animeName?.apply {
                        setText(Editable.Factory.getInstance().newEditable("Character name"))
                        visibility = View.VISIBLE
                    }
                }
            }
        }



        btnFetch?.setOnClickListener {
            var url: String? = null

            mRandomRadio?.let {
                if (it.isChecked) {
                    url = "https://animechan.xyz/api/random"
                }
            }

            mTitleRadio?.let {
                if (it.isChecked) {
                    val name = animeName?.text.toString().lowercase(Locale.getDefault())
                        .replace(" ".toRegex(), "%20")
                    url = "https://animechan.xyz/api/random/anime?title=$name"
                }
            }

            mCharacterRadio?.let {
                if (it.isChecked) {
                    val name = animeName?.text.toString().lowercase(Locale.getDefault())
                        .replace(" ".toRegex(), "%20")
                    url = "https://animechan.xyz/api/random/character?name=$name"
                }
            }

            url?.let {
                Log.e("URL", it)

                val requestQueue = Volley.newRequestQueue(activity)
                val objectRequest = JsonObjectRequest(
                    Request.Method.GET,
                    it, // Použití URL adresy uložené v proměnné 'url'
                    null,
                    { response ->
                        Log.e("rest response", response.toString())
                        try {
                            val anime = response.getString("anime")
                            val character = response.getString("character")
                            val quote = response.getString("quote")
                            anime_result?.text = anime
                            character_result?.text = character
                            quote_result?.text = quote
                        } catch (e: JSONException) {
                            throw RuntimeException(e)
                        }
                    }
                ) { error ->
                    Log.e("rest response", error.toString())
                    Toast.makeText(
                        activity, "Invalid Input $error",
                        Toast.LENGTH_LONG
                    ).show()
                }
                requestQueue.add(objectRequest)
            }
        }


        btnSave?.setOnClickListener(View.OnClickListener { view ->
            if (anime_result?.text.toString() == "name" && character_result?.text.toString() == "character" && quote_result?.text.toString() == "quote") {
                Toast.makeText(activity, "nelze ulozi default hodnoty!", Toast.LENGTH_SHORT).show()
            } else {
                save(view)
            }
        })

        return view
    }

    private fun save(v: View?) {
        val animeText = anime_result?.text.toString()
        val characterText = character_result?.text.toString()
        val quoteText = quote_result?.text.toString()
        val text = "Anime: $animeText\nCharakter: $characterText\nQuote: $quoteText\n\n "
        var fos: FileOutputStream? = null
        try {
            fos = activity?.openFileOutput(FILE_NAME, Context.MODE_APPEND)
            fos?.write(text.toByteArray())
            anime_result?.text = ""
            character_result?.text = ""
            quote_result?.text = ""
            Toast.makeText(
                activity, "Saved to " + activity?.filesDir + "/" + FILE_NAME,
                Toast.LENGTH_LONG
            ).show()
        } catch (e: IOException) {
            throw RuntimeException(e)
        } finally {
            fos?.close()
        }
    }

    companion object {
        private const val FILE_NAME = "historie.txt"
    }
}


