package luyao.learn.rxjava.ui.opeartor;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/*
 * author: luyao
 * date:   2021/5/24 11:14
 */
public class OperatorDoOnNextActivity extends BaseOperatorActivity {

    @Override
    protected void init() {
        /*
         * doOnNext() 在 onNext() 被调用的时候执行，
         * 时间顺序发生在 onNext() 之前
         */
        observable = Observable.create(emitter -> {
            emitter.onNext("1");
            emitter.onNext("2");
            emitter.onComplete();
        }).doOnNext(o -> bindText("doOnNext: " + o.toString()));

        observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                bindText("onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                bindText("onNext: " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                bindText("onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                bindText("onComplete");
            }
        };
    }
}
