package luyao.learn.rxjava.ui.opeartor;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/*
 * author: luyao
 * date: 2021/5/21 15:05
 */
public class OperatorCreateActivity extends BaseOperatorActivity {

    Observable<String> observable;
    Observer<String> observer;

    @Override
    protected void init() {
        observable = Observable.create(emitter -> {
            /*
             * onError 之后调用 onComplete/onNext，不会再响应
             * onComplete 之后调用 onError，会 crash
             */
            emitter.onNext("1");
            emitter.onNext("2");
            emitter.onNext("3");
//            emitter.onError(new Throwable("error"));
//            emitter.onComplete();
            emitter.onNext("4");
            throw new Exception("error");
        });

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

    @Override
    protected void subscribe() {
        observable.subscribe(observer);
    }
}
