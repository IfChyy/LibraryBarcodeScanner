package com.example.ifchyyy.librarybarcodescanner.dataclasses;


import com.google.gson.annotations.SerializedName;

/**
 * Book data class for storing each book info
 */
public class Book {

    private String bookContent;
    private String bookTitle, bookAuthor, bookDescription, bookDate;
    private String bookPreviewLink, bookPhoto;
    private Boolean read = false, lent = false;


    // GETTERS AND SETTERS
    public String getBookContent() {
        return bookContent;
    }

    public void setBookContent(String bookContent) {
        this.bookContent = bookContent;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }


    public String getBookPreviewLink() {
        return bookPreviewLink;
    }

    public void setBookPreviewLink(String bookPreviewLink) {
        this.bookPreviewLink = bookPreviewLink;
    }

    public String getBookPhoto() {
        return bookPhoto;
    }

    public void setBookPhoto(String bookPhoto) {
        this.bookPhoto = bookPhoto;
    }


    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Boolean getLent() {
        return lent;
    }

    public void setLent(Boolean lent) {
        this.lent = lent;
    }
}
