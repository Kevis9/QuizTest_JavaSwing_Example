package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
//import java.util.concurrent.Flow;


public class QuizAreaPan {
    public JScrollPane scrollPan;
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
    public void addImg(BufferedImage bimg)
    {
        JLabel label = new JLabel();
        ImageIcon img = new ImageIcon(bimg);
        img.setImage(img.getImage().getScaledInstance(300,200, Image.SCALE_SMOOTH));//设置图片的大小和缩放模式
        label.setIcon(img);
        label.setBounds(0,0,300,200);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        this.vbox.add(Box.createVerticalStrut(20)); //创建垂直间隙
        this.vbox.add(label);
        //将label添加监听器，点击后在内部窗口显示
        label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //点击之后创建一个窗口
                JFrame jf = new JFrame("图片");
                jf.setSize(bimg.getWidth(),bimg.getHeight());
                jf.setLocationRelativeTo(null);
                jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                ImageIcon img = new ImageIcon(bimg);
                JLabel img_label = new JLabel(img);
                JScrollPane jScrollPane = new JScrollPane(img_label,
                        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS
                        );
                jf.setContentPane(jScrollPane);
                jf.setVisible(true);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                return;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                return;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                return;
            }
            @Override
            public void mouseExited(MouseEvent e) {
                return;
            }

        });
    }
    public void addImg(String str)
    {
        ImageIcon img = new ImageIcon(str);
        img.setImage(img.getImage().getScaledInstance(200,200, Image.SCALE_SMOOTH));//设置图片的大小和缩放模式
        JLabel label = new JLabel(img);
        label.setBounds(0,0,200,200);
        this.vbox.add(label);
    }

}
