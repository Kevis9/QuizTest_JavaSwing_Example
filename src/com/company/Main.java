package com.company;

import java.awt.*;
import java.lang.*;
import javax.swing.*;
import com.company.NavigationBar;
import com.company.QuizAreaPan;
import com.company.ButtonView;
public class Main {

    public static void main(String[] args) {

        //创建窗口
        JFrame window = new JFrame("题库");
        window.setSize(800,800);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //创建面板
        JPanel pan = new JPanel(null);
        //创建导航栏
        NavigationBar.NavigationBar(pan,20);
        //创建题目区域
        QuizAreaPan quizpan = new QuizAreaPan(pan);
        quizpan.setTextAreaText("asdasdasdasdasdasdasd");
        quizpan.addImg("/Users/a123123/Desktop/sanli2.jpg");
        //创建其他的视图
        ButtonView btnview = new ButtonView(pan);
        // 设置窗口内容并显示
        window.setContentPane(pan);
        window.setVisible(true);

    }
}
