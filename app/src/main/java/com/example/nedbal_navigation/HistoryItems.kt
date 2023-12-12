package com.example.nedbal_navigation;

public class HistoryItems {
    private String manime;
    private String mcharacter;
    private String mquote;

    public HistoryItems(String anime, String character, String quote) {
        manime = anime;
        mcharacter = character;
        mquote = quote;
    }

    public String getMAnime() {
        return manime;
    }

    public String getMcharacter() {
        return mcharacter;
    }

    public String getMQuote() {
        return mquote;
    }


}