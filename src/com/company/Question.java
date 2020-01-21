package com.company;
import com.company.Question;
import java.util.List;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
public class Question {
    public String content;  //问题内容
    public List<BufferedImage> imgs;   //问题所涉及的图片
    //初始化
    Question(){
        imgs = new ArrayList<BufferedImage>();
        content = "";
    }
}
