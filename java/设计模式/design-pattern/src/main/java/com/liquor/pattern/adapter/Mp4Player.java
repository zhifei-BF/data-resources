package com.liquor.pattern.adapter;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：9:37
 * Description：
 *
 * @author Liquor
 * @version 1.0.0
 */
public class Mp4Player implements AdvancedMediaPlayer {

    @Override
    public void playVlc(String fileName) {
        //什么也不做
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: " + fileName);
    }
}
