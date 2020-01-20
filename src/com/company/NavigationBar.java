//导航栏的视图
package com.company;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class NavigationBar {
    public static void NavigationBar(JPanel pan,int chapter_num)
    {
        //创建根节点
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("题库");
        //创建二级节点--对应章节
        for(int i=1;i<=chapter_num;i++)
        {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode("第"+i+"章");
            rootNode.add(node);
        }
        //树组件
        JTree tree = new JTree(rootNode);
        //设置根节点句柄
        tree.setShowsRootHandles(false);
        //设置不可编辑
        tree.setEditable(false);
        //设置节点选中监听器
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                System.out.println("当前被选中的节点: " + e.getPath());
            }
        });
        //创建滚动面板
        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setBounds(0,0,200,800);
        //添加到面板
        pan.add(scrollPane);
    }
}
