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
public class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }

    private void loadFromDisk(String fileName) {
        System.out.println("Loading " + fileName);
    }
}
