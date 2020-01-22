package com.company;

import java.util.List;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
public class Question {
    public String content;  //问题内容
    public List<BufferedImage> imgs;   //问题所涉及的图片
    public int img_num;                 //问题涉及图片的数量
    public String ans;      //问题的答案
    //初始化
    Question(){
        imgs = new ArrayList<BufferedImage>();
        content = "";
    }
}
