package com.company;

import javax.swing.*;
public class ButtonView {
    public JButton btn1;   //微课
    public JButton btn2;   //确认本题的选择
    public JButton btn3;   //查看答案
    public JButton btn4;   //上一题
    public JButton btn5;   //下一题
    ButtonView(JPanel pan)
    {
        btn1 = new JButton("微课视频");
        btn2 = new JButton("确认本题选择");
        btn3 = new JButton("查看本题答案");
        btn4 = new JButton("上一题");
        btn5 = new JButton("下一题");

        //label
        JLabel label = new JLabel("查看微课视频: ");
        //选择框
        // 创建两个单选按钮
        JRadioButton radioBtn01 = new JRadioButton();
        JRadioButton radioBtn02 = new JRadioButton();
        // 创建按钮组，把两个单选按钮添加到该组
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(radioBtn01);
        btnGroup.add(radioBtn02);

        //开始布局
        label.setBounds(600,100,120,20);
        btn1.setBounds(700,100,80,20);
        radioBtn01.setBounds(500,620,30,20);
        radioBtn02.setBounds(535,620,30,20);
        btn2.setBounds(565,620,100,20);
        btn3.setBounds(675,620,100,20);
        btn4.setBounds(565,650,100,40);
        btn5.setBounds(675,650,100,40);
        pan.add(label);
        pan.add(btn1);
        pan.add(radioBtn01);
        pan.add(radioBtn02);
        pan.add(btn2);
        pan.add(btn3);
        pan.add(btn4);
        pan.add(btn5);
    }
}
