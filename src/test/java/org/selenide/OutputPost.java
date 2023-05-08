package org.selenide;

public class OutputPost {
    private final String title;
    private final String text;

    public OutputPost(String title, String text){
        this.title = title;
        this.text = text;
    }

    public String getText(){
        return this.text;
    }

    public String getTitle(){
        return title;
    }
}
