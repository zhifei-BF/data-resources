package com.liquor.pattern.adapter;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：9:35
 * Description：
 *
 * @author Liquor
 * @version 1.0.0
 */
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        //什么也不做
    }
}
