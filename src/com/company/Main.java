package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import com.spire.doc.Document;
import com.spire.doc.Section;
import com.spire.doc.documents.DocumentObjectType;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.fields.DocPicture;
import com.spire.doc.interfaces.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import com.spire.doc.interfaces.ICompositeObject;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.*;


public class Main {

    static Chapter chapter; //当前章节
    static int que_number;  //当前题号
    static QuizAreaPan quizpan; //当前面板
    public static void main(String[] args) throws IOException {
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
        quizpan = new QuizAreaPan(pan);
        //创建其他的视图
        ButtonView btnview = new ButtonView(pan);
        // 设置窗口内容并显示
        window.setContentPane(pan);
        window.setVisible(true);
        //添加监听事件
        //导航栏章节点击
        navbar.tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                String path = e.getPath().toString();
                TreeNodeClicked(path);
                //进行问题的显示
                if(chapter==null)
                    return;
                else{
                    //刷新quizarea
                    pan.remove(quizpan.scrollPan);
                    quizpan = new QuizAreaPan(pan);
                    //显示题号对应的题目
                    quizpan.setTextAreaText(chapter.ques.get(que_number).content);
                    //将题目对应的图片加上去
                    for(int i=0;i<chapter.ques.get(que_number).img_num;i++)
                    {
                        quizpan.addImg(chapter.ques.get(que_number).imgs.get(i));
                    }

                }
            }
        });
        //上一题
        btnview.btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //判断chapter是否为空
                if(chapter==null)
                    return;
                //判断题号是否为0
                if(que_number==0)
                    return;
                que_number--;
                //进行面板刷新
                pan.remove(quizpan.scrollPan);
                quizpan = new QuizAreaPan(pan);
                //显示题号对应的题目
                quizpan.setTextAreaText(chapter.ques.get(que_number).content);
                //将题目对应的图片加上去
                for(int i=0;i<chapter.ques.get(que_number).img_num;i++)
                {
                    quizpan.addImg(chapter.ques.get(que_number).imgs.get(i));
                }
            }
        });
        //下一题
        btnview.btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //判断chapter是否为空
                if(chapter==null)
                    return;
                //判断题号是否小于章节题目数
                if(que_number>=chapter.ques.size()-1)
                    return;
                que_number++;
                //进行面板刷新
                pan.remove(quizpan.scrollPan);
                quizpan = new QuizAreaPan(pan);
                //显示题号对应的题目

                quizpan.setTextAreaText(chapter.ques.get(que_number).content);
//                quizpan.setTextAreaText("hahahahahhaha");
                //将题目对应的图片加上去

                for(int i=0;i<chapter.ques.get(que_number).img_num;i++)
                {
                    quizpan.addImg(chapter.ques.get(que_number).imgs.get(i));
                }
            }
        });


    }
    //处理导航栏点击后章节的word导入
    public static void TreeNodeClicked(String path)
    {
        if(path.contains("章"))
        {
            int chap_num = Integer.parseInt(path.substring(path.indexOf("第")+1,path.indexOf("章")));
            String word_path = "C:\\Users\\hwl\\Desktop\\第"+chap_num+"章.doc";  //word文档所在路径
            Document document = new Document();
            try{
                document.loadFromFile(word_path);
            }
            catch (Exception e)
            {
                System.out.println(e);
                return;
            }

            //当前章节
            chapter = new Chapter();
            que_number = 0;     //当前题号为0

            //获取word文档中的图片
            Queue<ICompositeObject> nodes = new LinkedList<ICompositeObject>();
            nodes.add(document);
            //用来存取图片
            List<BufferedImage> images = new ArrayList<BufferedImage>();
            while (nodes.size() > 0) {
                ICompositeObject node = nodes.poll();
                for (int i = 0; i < node.getChildObjects().getCount(); i++) {
                    IDocumentObject child = node.getChildObjects().get(i);
                    if (child instanceof ICompositeObject) {
                        nodes.add((ICompositeObject) child);
                        if (child.getDocumentObjectType() == DocumentObjectType.Picture) {
                            DocPicture picture = (DocPicture) child;
                            images.add(picture.getImage());
                        }
                    }
                }
            }
            int index=0;    //每个问题对应图片的起始索引
            //获取word中的文本
            //获取第一个section
            Section section = document.getSections().get(0);
            for(int i=0;i<section.getParagraphs().getCount();i++)
            {
                Paragraph paragraph = section.getParagraphs().get(i);
                if(paragraph.getText()=="")
                    continue;
                else{
                    //装载问题文本到问题中
                    Question que =  new Question();
                    que.content = paragraph.getText();
                    que.img_num = Integer.parseInt(que.content.substring(que.content.indexOf("<图片数")+4,que.content.indexOf("图片数>")));
                    for(int j=0;j<que.img_num;j++)
                    {
                        que.imgs.add(images.get(index));
                        index++;
                    }
                    //将问题添加到当前章节中
                    chapter.ques.add(que);
                }
            }
        }//if
        else return;
    }
}

