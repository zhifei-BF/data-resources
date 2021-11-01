package com.liquor.pattern.proxy;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：14:32
 * Description：
 *
 * @author Liquor
 * @version 1.0.0
 */
public class ProxyImage implements Image {

    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
