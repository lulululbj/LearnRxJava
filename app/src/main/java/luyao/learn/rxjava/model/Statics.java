package luyao.learn.rxjava.model;

import java.util.ArrayList;
import java.util.List;

import luyao.learn.rxjava.R;
import luyao.learn.rxjava.bean.ItemBean;
import luyao.learn.rxjava.ui.opeartor.OperatorCreateActivity;
import luyao.learn.rxjava.ui.opeartor.OperatorDoOnNextActivity;

/**
 * @author: luyao
 * @date： 2021/5/21 13:39
 */
public class Statics {

    public static List<ItemBean> provideOperators() {
        List<ItemBean> data = new ArrayList<>();
        data.add(new ItemBean("create", R.drawable.operator_create, OperatorCreateActivity.class));
        data.add(new ItemBean("doOnNext", R.drawable.operator_do, OperatorDoOnNextActivity.class));
        return data;
    }
}
