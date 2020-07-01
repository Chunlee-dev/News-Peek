package com.chunleedev.newspeek.model;

public class ArticleModel {

    private final String name;
    private final String author;
    private final String imageUrl;
    private final String title;
    private final String date;
    private final String url;
    private final String contentPeek;


    public ArticleModel(String name, String author, String imageUrl, String title, String date, String url, String contentPeek) {
        this.name = name;
        this.author = author;
        this.imageUrl = imageUrl;
        this.title = title;
        this.date = date;
        this.url = url;
        this.contentPeek = contentPeek;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public String getContentPeek() {
        return contentPeek;
    }
}
