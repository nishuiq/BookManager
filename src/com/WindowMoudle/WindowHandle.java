package com.WindowMoudle;

import com.DAO.BookCRUD;

public class WindowHandle {
    BookCRUD bookCRUD;
    public WindowHandle() {
        bookCRUD = new BookCRUD();
    }

    public String[][] SearchAll(Window window) {
        return bookCRUD.searchAll();
    }
    public String[] searchColumnAll() {
        return bookCRUD.searchColumnAll();
    }
    public String[][] fuzzySearch(Window window) {
        return bookCRUD.fuzzySearch(window.getTitle());
    }
    public boolean insert(Window window) {
        return bookCRUD.insert(window.getTitle(), window.getCnt());
    }
    public boolean review(Window window, String changeTitle) {
        return bookCRUD.review(window.getTitle(), window.getCnt(), changeTitle);
    }
    public boolean borrow(Window window) {
        return bookCRUD.borrow(window.getTitle());
    }
    public int getBookCnt(Window window) {
        return bookCRUD.getBookCnt(window.getTitle());
    }
    public boolean remove(Window window) {
        return bookCRUD.remove(window.getTitle());
    }
}
