package luyao.learn.rxjava.bean;

import java.io.Serializable;

import androidx.annotation.DrawableRes;

/**
 * @author: luyao
 * @date： 2021/5/21 13:13
 */
public class ItemBean implements Serializable {
    public String name;
    public Class clazz;
    public int resId;

    public ItemBean(String name, @DrawableRes int resId, Class clazz) {
        this.name = name;
        this.clazz = clazz;
        this.resId = resId;
    }
}
