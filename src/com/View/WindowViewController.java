package com.View;

import com.DAO.HistoryUserCRUD;
import com.WindowMoudle.Window;
import com.WindowMoudle.WindowHandle;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowViewController implements ActionListener {
    WindowHandle windowHandle;
    WindowView windowView;
    Window window;
    HistoryUserCRUD historyUserCRUD;

    private String []tableHead;
    private String [][]content;

    public WindowViewController() {
        window = new Window();
        windowHandle = new WindowHandle();
        historyUserCRUD = new HistoryUserCRUD();
    }
    public void setWindowView(WindowView win) {
        windowView = win;
        windowView.table.getSelectionModel().addListSelectionListener((e -> {
            int row = windowView.table.getSelectedRow();
            if (row == -1) return;

            // 动态设置数据
            for (int i = 0; i < tableHead.length; i ++) {
                if (tableHead[i].equals("title") || tableHead[i].equals("book")) window.setTitle(content[row][i]);
                if (tableHead[i].equals("store_cnt")) window.setCnt(content[row][i]);
            }

            if (window.getIsManager()) {
                windowView.ReviewContent.setText(window.getTitle());
                windowView.ReviewNumber.setText(window.getCnt());
                windowView.DelContent.setText(window.getTitle());
            }
            windowView.GiveBack.setText(window.getTitle());
        }));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("搜索")) {
            window.setTitle(windowView.SearchContent.getText().trim());
            content = windowHandle.fuzzySearch(window);
            tableHead = windowHandle.searchColumnAll();
        }
        else if (e.getActionCommand().equals("借还")) {
            if (windowView.GiveBack.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "请输入书名！", "提示信息", JOptionPane.WARNING_MESSAGE);
                return;
            }

            window.setTitle(windowView.GiveBack.getText());
            boolean state = historyUserCRUD.match(window.getUsername(), window.getTitle());
            if (state) {  // history表中有记录，则归还书籍
                int cnt = windowHandle.getBookCnt(window) + 1;
                window.setCnt(cnt + "");
                // 当book表修改成功后，
                if (windowHandle.review(window, "") && historyUserCRUD.review(window.getUsername(), window.getTitle())) {
                    JOptionPane.showMessageDialog(null, "归还成功！", "提示信息", JOptionPane.PLAIN_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "失败", "提示信息", JOptionPane.WARNING_MESSAGE);
                }
            }
            else {  // 借书
                if (windowHandle.borrow(window) && historyUserCRUD.insert(window.getUsername(), window.getTitle())) {
                    JOptionPane.showMessageDialog(null, "借书成功！", "提示信息", JOptionPane.PLAIN_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "借书失败", "提示信息", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            if (window.getIsManager()) content = historyUserCRUD.searchAll();
            else content = historyUserCRUD.searchAll(window.getUsername());
            tableHead = historyUserCRUD.searchColumnAll();

        }
        else if (e.getActionCommand().equals("历史")) {
            if (window.getIsManager()) content = historyUserCRUD.searchAll();
            else content = historyUserCRUD.searchAll(window.getUsername());
            tableHead = historyUserCRUD.searchColumnAll();
        }
        else {
            if (e.getActionCommand().equals("添加")) {
                window.setTitle(windowView.AddContent.getText().trim());
                window.setCnt(windowView.AddNumber.getText().trim());
                boolean state = windowHandle.insert(window);
                if (state) {
                    JOptionPane.showMessageDialog(null, "添加成功！", "提示信息", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "添加失败！请检查书名是否已存在或输入的数量非法。", "提示信息", JOptionPane.WARNING_MESSAGE);
                }
            } else if (e.getActionCommand().equals("修改")) {  //TODO:可修改书名和数量啦
                window.setTitle(windowView.ReviewContent.getText().trim());
                window.setCnt(windowView.ReviewNumber.getText().trim());

                String changeTitle = JOptionPane.showInputDialog(null, "输入修改后的书名(留空书名将维持不变)", "修改书名", JOptionPane.PLAIN_MESSAGE);
                boolean state = windowHandle.review(window, changeTitle);


                if (state) {
                    JOptionPane.showMessageDialog(null, "修改成功！", "提示信息", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "修改失败！请检查书名是否已存在！或数量设置错误(>=0)", "提示信息", JOptionPane.WARNING_MESSAGE);
                }
            } else if (e.getActionCommand().equals("删除")) {
                window.setTitle(windowView.DelContent.getText().trim());
                boolean state = windowHandle.remove(window);
                if (state) {
                    JOptionPane.showMessageDialog(null, "删除成功！", "提示信息", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "删除失败！请检查书名是否已存在", "提示信息", JOptionPane.WARNING_MESSAGE);
                }
            }
            content = windowHandle.SearchAll(window);
            tableHead = windowHandle.searchColumnAll();
        }
        windowView.dataModel.setDataVector(content, tableHead);
        windowView.table.setModel(windowView.dataModel);

        // 较好的列宽以看清内容
        TableColumn column = windowView.table.getColumnModel().getColumn(1);
        column.setPreferredWidth(150);
        column = windowView.table.getColumnModel().getColumn(0);
        column.setPreferredWidth(50);
        column.setMaxWidth(50);
    }
}