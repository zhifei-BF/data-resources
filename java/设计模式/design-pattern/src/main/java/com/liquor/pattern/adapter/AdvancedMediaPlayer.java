package com.liquor.pattern.adapter;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：9:35
 * Description：更高级的媒体播放器
 *
 * @author Liquor
 * @version 1.0.0
 */
public interface AdvancedMediaPlayer {

    public void playVlc(String fileName);

    public void playMp4(String fileName);
}
