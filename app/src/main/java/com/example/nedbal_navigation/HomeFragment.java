package com.example.nedbal_navigation;

import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;


public class HomeFragment extends Fragment {
    EditText animeName;
    TextView anime_result;
    TextView character_result;
    TextView quote_result;
    Button btnFetch, btnSave;

    private static final String FILE_NAME = "historie.txt";
    private RadioButton mRandomRadio, mTitleRadio, mCharacterRadio;

    private String URL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        //checkbox
        mRandomRadio = view.findViewById(R.id.radioRandom);
        mTitleRadio = view.findViewById(R.id.radioTitle);
        mCharacterRadio = view.findViewById(R.id.radioCharacter);

        //btn
        btnFetch = view.findViewById(R.id.btn_fetch);
        btnSave = view.findViewById(R.id.btn_save);

        //text input
        animeName = view.findViewById(R.id.anime_name);

        //text view result
        anime_result = view.findViewById(R.id.anime_result);
        character_result = view.findViewById(R.id.character_result);
        quote_result = view.findViewById(R.id.quote_result);

        mRandomRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mRandomRadio.isChecked()){
                    animeName.setVisibility(View.INVISIBLE);

                }
            }
        });

        mTitleRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTitleRadio.isChecked()){
                    animeName.setText("Anime name");
                    animeName.setVisibility(View.VISIBLE);

                }
            }
        });

        mCharacterRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCharacterRadio.isChecked()){
                    animeName.setText("Character name");
                    animeName.setVisibility(View.VISIBLE);
                }
            }
        });

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mRandomRadio.isChecked()){
                    URL = "https://animechan.vercel.app/api/random";
                }

                if(mTitleRadio.isChecked()){
                    URL ="https://animechan.vercel.app/api/random/anime?title=" + animeName.getText().toString().toLowerCase().replaceAll(" ","%20");
                    Log.e("URL", URL);
                }
                if(mCharacterRadio.isChecked()){
                    URL = "https://animechan.vercel.app/api/random/character?name=" + animeName.getText().toString().toLowerCase().replaceAll(" ", "%20");
                    Log.e("rest response", URL);
                }


                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

                JsonObjectRequest objectRequest = new JsonObjectRequest(
                        Request.Method.GET, //http metoda
                        URL, //url
                        null, //parametry ktere posilam
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.e("rest response", response.toString());

                                try {
                                    String anime = response.getString("anime");
                                    String character = response.getString("character");
                                    String quote = response.getString("quote");

                                    anime_result.setText(anime);
                                    character_result.setText(character);
                                    quote_result.setText(quote);

                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("rest response", error.toString());
                                Toast.makeText(getActivity(), "Invalid Input " + error,
                                Toast.LENGTH_LONG).show();
                            }
                        }

                );
                requestQueue.add(objectRequest);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(anime_result.getText().toString().equals("name")  && character_result.getText().toString().equals("character")
                        && quote_result.getText().toString().equals("quote")){
                    Toast.makeText(getActivity(), "nelze ulozi default hodnoty!", Toast.LENGTH_SHORT).show();
                }
            else {

                    save(view);
                }
            }
        });

        return view;
    }
    public void save(View v) {
        String animeText = anime_result.getText().toString();
        String characterText = character_result.getText().toString();
        String quoteText = quote_result.getText().toString();
        String text ="Anime: " + animeText + "\n" +"Charakter: "+ characterText + "\n"+"Quote: " + quoteText+"\n\n ";
        FileOutputStream fos = null;

        try {
            fos = getActivity().openFileOutput(FILE_NAME, MODE_APPEND);
            fos.write(text.getBytes());

            anime_result.setText("");
            character_result.setText("");
            quote_result.setText("");

            Toast.makeText(getActivity(), "Saved to " + getActivity().getFilesDir() + "/" + FILE_NAME,
                    Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

}

}

