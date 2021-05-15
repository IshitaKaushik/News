package com.example.news;

 public class News {
     private String title;
     private String url;
     private String imageurl;
     private String author;

     public News(String title,String url,String imageurl,String author){
         this.title=title;
         this.url=url;
         this.imageurl=imageurl;
         this.author=author;
     }
     public String getTitle(){
         return title;
     }

     public String getAuthor() {
         return author;
     }

     public String getImageurl() {
         return imageurl;
     }

     public String getUrl() {
         return url;
     }
 }
