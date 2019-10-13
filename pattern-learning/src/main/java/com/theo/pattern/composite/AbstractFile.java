package com.theo.pattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangsuixin
 * @date 2019/10/10 17:18
 * @description 模拟杀毒软件架构
 */
public interface AbstractFile {
    /**
     * 杀毒
     */
    void killVirus();
}


class ImageFile implements AbstractFile{
    private String name;

    public ImageFile(String name) {
        this.name = name;
    }

    @Override
    public void killVirus() {
        System.out.println("---图片文件：" + name + ",进行杀毒");
    }
}

class TextFile implements AbstractFile{
    private String name;

    public TextFile(String name) {
        this.name = name;
    }

    @Override
    public void killVirus() {
        System.out.println("---文本文件：" + name + ",进行杀毒");
    }
}

class VideoFile implements AbstractFile{
    private String name;

    public VideoFile(String name) {
        this.name = name;
    }

    @Override
    public void killVirus() {
        System.out.println("---视频文件：" + name + ",进行杀毒");
    }
}

class Folder implements AbstractFile {
    private String name;

    public Folder(String name) {
        this.name = name;
    }

    private List<AbstractFile> child = new ArrayList<>();

    @Override
    public void killVirus() {
        System.out.println("---文件夹：" + name + ", 进行查杀");

        for (AbstractFile file : child) {
            file.killVirus();
        }
    }

    public void add(AbstractFile file) {
        child.add(file);
    }

    void remove(AbstractFile file){
        child.remove(file);

    }

    AbstractFile getChild(int index){
        return child.get(index);
    }
}