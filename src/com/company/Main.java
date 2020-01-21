package com.company;

import java.lang.*;
import javax.swing.*;
import com.spire.doc.Document;
import com.spire.doc.documents.DocumentObjectType;
import com.spire.doc.fields.DocPicture;
import com.spire.doc.interfaces.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.*;


public class Main {

    public static void main(String[] args) {
        //创建窗口
        JFrame window = new JFrame("题库");
        window.setSize(820,750);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //创建面板
        JPanel pan = new JPanel(null);
        //创建导航栏
        NavigationBar navbar = new NavigationBar(pan,20);
        //创建题目区域
        QuizAreaPan quizpan = new QuizAreaPan(pan);
        //创建其他的视图
        ButtonView btnview = new ButtonView(pan);
        // 设置窗口内容并显示
        window.setContentPane(pan);
        window.setVisible(true);

        //加载题目
        Chapter chapter = new Chapter();
        //添加监听事件
        navbar.tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                String path = e.getPath().toString();
                TreeNodeClicked(path);
            }
        });

    }
    //处理导航栏点击后的word导入
    public static void TreeNodeClicked(String path)
    {
        if(path.contains("章"))
        {
            int chap_num = Integer.parseInt(path.substring(path.indexOf("第")+1,path.indexOf("章")));
            String word_path = "C:\\Users\\hwl\\Desktop\\第"+chap_num+"章.doc";  //word文档所在路径
            Document document = new Document();
            try {
                document.loadFromFile(word_path);
            }
            catch (Exception e)
            {
                System.out.println(e);
                return;
            }
            //获得全部的文本
            String text=document.getText();

        }
        else return;

    }

}
