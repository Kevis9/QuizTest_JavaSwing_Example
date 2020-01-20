package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;


public class QuizAreaPan {
    private JScrollPane scrollPan;
    private JTextArea textArea;
    Box vbox;
    QuizAreaPan(JPanel pan)
    {
        //Box垂直布局
        vbox = Box.createVerticalBox();
        //创建文本框
        textArea = new JTextArea();
        textArea.setEditable(false);    //设置为不可编辑
        textArea.setLineWrap(true);     //自动换行
        vbox.add(textArea);
        //创建scrollpan
        scrollPan = new JScrollPane(vbox,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        //设置scrollpan的大小
        scrollPan.setBounds(200,200,600,400);
        //添加到主面板中
        pan.add(scrollPan);
    }

    //设置题目
    public void setTextAreaText(String str)
    {
        this.textArea.setText(str);
    }

    //设置图片
    public void addImg(String imgPath)
    {
        ImageIcon img = new ImageIcon(imgPath);
        img.setImage(img.getImage().getScaledInstance(200,200, Image.SCALE_SMOOTH));//设置图片的大小和缩放模式
        JLabel label = new JLabel(img);
        label.setBounds(0,0,200,200);
        JButton btn = new JButton();
        this.vbox.add(label);
    }

}
