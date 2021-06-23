package com.shop.cosm.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;


    private String filename;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;


    public Image() {
    }

    public Image(User user) {
        this.author = user;

    }



    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public User getAuthor() {
        return author;
    }
public void setAuthor(User author) {
        this.author = author;
    }
  public List<Image> DeleteByFilename(String filename){
        return null;
  };

//    public void setText(String text) {
//        this.text = text;
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getTag() {
//        return tag;
//    }
//
//    public void setTag(String tag) {
//        this.tag = tag;
//    }

    public String getFilename() {
        return filename;
    }

    public String setFilename(String filename) {
        this.filename = filename;
        return filename;
    }
}
