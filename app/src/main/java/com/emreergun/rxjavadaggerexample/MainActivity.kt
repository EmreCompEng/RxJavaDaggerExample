package com.emreergun.rxjavadaggerexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.*
import io.reactivex.disposables.Disposable


//Log.i("RxJava","")

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Observable, Flowable , Single ,Maybe ,Completable
        //ObservableExample()
        ObservableAllMethodsExample()
        //SingleObserverExample()
        //MaybeObserverExample()
        //CompletableObserverExample()

    }

    private fun ObservableAllMethodsExample() {
        Observable.just("Hello")
                .doOnSubscribe { Log.i("RxJava","Subscribed") }
                .doOnNext { s -> Log.i("RxJava","Received: $s") }
                .doAfterNext { Log.i("RxJava","After Receiving") }
                .doOnError { e -> Log.i("RxJava","Error: $e") }
                .doOnComplete { Log.i("RxJava","Complete") }
                .doFinally { Log.i("RxJava","Do Finally!") }
                .doOnDispose { Log.i("RxJava","Do on Dispose!") }
                .subscribe { Log.i("RxJava","Subscribe") }
    }

    private fun CompletableObserverExample() {
        Log.i("RxJava","CompletableObserver")
        // Completable herhangi bir data iletmez.
        // İşlemin başarıyla sonuçlanıp sonuçlanmamasına göre onComplete yada onError metodu çalışır.
        // Completable için dinleyici olarak CompletableObserver kullanılır.
        // HTTP 204'ü döndüreceği ve hataların HTTP 301, HTTP 404, HTTP 500, vb .'den gelebileceği REST API'leri içindir.
        // Bu bilgilerle bir şeyler yapabiliriz.

        val completableObserver =object :CompletableObserver{
            override fun onSubscribe(d: Disposable) {
                Log.i("RxJava","onSubscribe")
            }

            override fun onComplete() {
                Log.i("RxJava","onComplete")
            }

            override fun onError(e: Throwable) {
                Log.i("RxJava","onError")
            }
        }

    }

    private fun MaybeObserverExample() {
        Log.i("RxJava","MaybeObserver")
        // Single gibi sadece bir değeri iletmek için kullanılır.
        // Singledan farkı değeri dönmek zorunda değildir.
        // Değer dönerse onSuccess metoduna düşer.
        // Şayet hata alırsa da diğer tiplerde olduğu gibi onError metoduna düşer.
        // Maybe ile kullanıcı veritabanımızda, herhangi bir kullanıcının olup olmadığını kontrol edebiliriz.
        // Kullanıcı varsa onSuccess metodu çalışacaktır.

        val maybeObservable=Maybe.just("This is Maybe")

        val maybeObserver = object :MaybeObserver<String>{
            override fun onSubscribe(d: Disposable) {
                Log.i("RxJava","onSubscribe")
            }

            override fun onSuccess(t: String) {
                Log.i("RxJava","onSuccess :$t")
            }

            override fun onError(e: Throwable) {
                Log.i("RxJava","onError")
            }

            override fun onComplete() {
                Log.i("RxJava","onComplete")
            }

        }

        maybeObservable
                .subscribe(maybeObserver)



    }
    private fun SingleObserverExample() {
        Log.i("RxJava","SingleObserver")

        // SingleObserver
        // Sadece bir değeri iletmek için kullanılır
        // O değeri başarıyla iletir yada hata mesajı ile döner.
        // onComplete metodu yoktur.

        val getSingleObservable=Single.just("This is A Single")

        val singleObserver =object :SingleObserver<String>{
            override fun onSubscribe(d: Disposable) {
                Log.i("RxJava","onSubscribe")
            }

            override fun onSuccess(t: String) {
                Log.i("RxJava","onSuccess : $t")
            }

            override fun onError(e: Throwable) {
                Log.i("RxJava","onError")
            }
        }
        // Subscription
        getSingleObservable
                .subscribe(singleObserver)


    }
    private fun ObservableExample() {
        Log.i("RxJava","Observable")



        // Observable : Veriyi dışa aktaran yapıdır
        val getObservable= Observable.just("X","Y","Z")

        // Observer : Observable tarafından dışa aktarılan veriyi dinleyen yapıdır
        val getObserver=object :Observer<String>{
            override fun onSubscribe(d: Disposable) {
                Log.i("RxJava","onSubscribe")
            }

            override fun onNext(t: String) {
                Log.i("RxJava","onNext :$t")
            }

            override fun onError(e: Throwable) {
                Log.i("RxJava","onError")

            }

            override fun onComplete() {
                Log.i("RxJava","onComplete")

            }

        }

        // Subscription
        getObservable
                .subscribe(getObserver)
        //RxJava: onSubscribe
        //RxJava: onNext :X
        //RxJava: onNext :Y
        //RxJava: onNext :Z
        //RxJava: onComplete



    }

}