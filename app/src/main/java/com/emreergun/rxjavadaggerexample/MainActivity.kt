package com.emreergun.rxjavadaggerexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.*
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.observables.GroupedObservable
import io.reactivex.schedulers.Schedulers
import okio.Timeout
import java.util.*
import java.util.concurrent.TimeUnit


//Log.i("RxJava","")

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //---Observable, Flowable , Single ,Maybe ,Completable---
        //ObservableExample()               //
        //ObservableAllMethodsExample()     //
        //SingleObserverExample()           //
        //MaybeObserverExample()            // Tek veri geri iletir
        //CompletableObserverExample()      // Herhangi bir veri dönmez

        //---RXJava Scheduler Türleri---
        // Schedulers, RxJava’da işlemlerin gerçekleştiği yada sonuçlarının yayınlandığı küçük tasklar oluşturur.
        // Bu tasklar birbiriyle eş zamanlı yada sıralı çalışarak büyük asenkron yapıları meydana getirir.
        // Kullanım senaryonuza göre en uygun Schedulers’ı seçerek minimum kaynak ile maksimum verimi alabilirsiniz.

        //Observable değişkeninizde Schedulers’ları observeOn ya da subscribeOn ile kullanabilirsiniz.
        // TODO Schedulers yapıları örnekleme

        // ---RxJava Operatorler--- Dönüştürmeler
        //SwitchMapOperator()  // Sadece Son Değeri getirir
        //FlatMapOperator()    // İşlemleri Karışık yapar
        //ContactMapOperator() // İşlemleri Sıralı yapar
        //GroupByOperator()    // Gruplama
        //ScanOperator()       // Birleştirme

        // ---RxJava Operatorler--- Filtreleme
        //DistinctOperator()        // Verilerin hespi uniq dir tekrarlı veri olmaz
        //ElementAtOperator()       // Verilen indexteki elemanı getirir
        //FilterOperator()          // Filtreleme
        //IgnoreElementsOperator()  // Herhangi bir veri dönmez sadece işlem tamamlanınca haber verir
        //SampleOperator()          // Parçalayarak alır
        //SkipOperator()            // Verilerde atlama yapılır
        //SkipLastOperator()          // Sondan atlama yapar
        //TakeOpearator()             // ilk verilen kadar eleman getirir
        //TakeLastOperator()            // Sondan n elamanı getirir


    }

    private fun TakeLastOperator() {
        Log.i("RxJava", "IgnoreElementsOperator")
        // Take operatöründen farklı olarak ilk değil son n elemanı iletir.
        // Diğerlerini görmezden gelir. takeLast(n) şeklinde kullanılır.

        //---Çıktı---
        // IgnoreElementsOperator
        // onSubscribe
        // onNext :7
        // onNext :8
        // onNext :9
        // onComplete

        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .takeLast(3)
                .subscribe(object : Observer<Int> {
                    override fun onSubscribe(d: Disposable) {
                        Log.i("RxJava", "onSubscribe")
                    }

                    override fun onNext(t: Int) {
                        Log.i("RxJava", "onNext :$t")
                    }

                    override fun onError(e: Throwable) {
                        Log.i("RxJava", "onError")
                    }

                    override fun onComplete() {
                        Log.i("RxJava", "onComplete")
                    }

                })


    }

    private fun TakeOpearator() {
        Log.i("RxJava", "IgnoreElementsOperator")
        // Skip operatörünün tam tersi gibi çalışır.
        // Skip ilk n sayıdaki elemanı görmezden gelirken,take ise sadece ilk n sayıdaki elemanı iletmektedir.
        // take(n) şeklinde kullanılır.
        // Aşağıdaki örnekte Observable’ın yaydığı 1,….,8 sayılarından
        // take(4) operatörü ile sadece ilk 4 eleman Observer’a iletilmiştir.

        //---Çıktı---
        // IgnoreElementsOperator
        // onSubscribe
        // onNext :1
        // onNext :2
        // onNext :3
        // onNext :4
        // onComplete

        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .take(4)
                .subscribe(object : Observer<Int> {
                    override fun onSubscribe(d: Disposable) {
                        Log.i("RxJava", "onSubscribe")
                    }

                    override fun onNext(t: Int) {
                        Log.i("RxJava", "onNext :$t")
                    }

                    override fun onError(e: Throwable) {
                        Log.i("RxJava", "onError")
                    }

                    override fun onComplete() {
                        Log.i("RxJava", "onComplete")
                    }

                })


    }

    private fun SkipLastOperator() {
        Log.i("RxJava", "IgnoreElementsOperator")

        // Skiplast operatörü,skip operatöründen farklı olarak son n sayıdaki elemanın iletilmesini engeller.
        // skipLast(n) şeklinde kullanılır.
        // Aşağıdaki örnekte Observable’ın yaydığı son 3 eleman Observer’a iletilmemiştir.

        //---Çıktı--
        // IgnoreElementsOperator
        // onSubscribe
        // onNext :1
        // onNext :2
        // onNext :3
        // onNext :4
        // onNext :5
        // onNext :6
        // onComplete

        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .skipLast(3)
                .subscribe(object : Observer<Int> {
                    override fun onSubscribe(d: Disposable) {
                        Log.i("RxJava", "onSubscribe")
                    }

                    override fun onNext(t: Int) {
                        Log.i("RxJava", "onNext :$t")
                    }

                    override fun onError(e: Throwable) {
                        Log.i("RxJava", "onError")
                    }

                    override fun onComplete() {
                        Log.i("RxJava", "onComplete")
                    }

                })


    }

    private fun SkipOperator() {
        Log.i("RxJava", "IgnoreElementsOperator")

        // Skip operatörü Observable’ın yaydığı ilk n elemanı yok sayar ve sonrasındaki elemanları iletir.
        // skip(n) şeklinde kullanılır.
        // Aşağıdaki örnekte skip(3) değeri ile ilk 3 eleman Observer’a iletilmemiştir.

        //---Çıktı---
        // IgnoreElementsOperator
        // onSubscribe
        // onNext :4
        // onNext :5
        // onNext :6
        // onNext :7
        // onNext :8
        // onComplete

        Observable.just(1, 2, 3, 4, 5, 6, 7, 8)
                .skip(3)
                .subscribe(object : Observer<Int> {
                    override fun onSubscribe(d: Disposable) {
                        Log.i("RxJava", "onSubscribe")
                    }

                    override fun onNext(t: Int) {
                        Log.i("RxJava", "onNext :$t")
                    }

                    override fun onError(e: Throwable) {
                        Log.i("RxJava", "onError")
                    }

                    override fun onComplete() {
                        Log.i("RxJava", "onComplete")
                    }

                })


    }

    private fun SampleOperator() {
        Log.i("RxJava", "IgnoreElementsOperator")
        // Sample operatörü seçilen belli bir periyot sonunda, Observable’ın o periyot içinde yaydığı son elemanı alıp onu iletir.
        // Örneğin aşağıdaki örnekte Observable her saniye değerini Long cinsinden yaymaktadır.
        // Sample, içinde belirtilen 3 saniye periyodu ile her 3 saniye sonunda,Observable tarafından yayınlanan en son elemanı Observer’a iletiyor.

        // ---Çıktı---
        // IgnoreElementsOperator
        // onSubscribe
        // onNext :3
        // onNext :5
        // onNext :9
        // onNext :12
        // onNext :15
        // onNext :17
        // onNext :20
        // ...

        val timeObservable = Observable.interval(0, 1, TimeUnit.SECONDS)

        timeObservable
                .sample(3, TimeUnit.SECONDS)
                .subscribe(object : Observer<Long> {
                    override fun onSubscribe(d: Disposable) {
                        Log.i("RxJava", "onSubscribe")
                    }

                    override fun onNext(t: Long) {
                        Log.i("RxJava", "onNext :$t")
                    }

                    override fun onError(e: Throwable) {
                        Log.i("RxJava", "onError")
                    }

                    override fun onComplete() {
                        Log.i("RxJava", "onComplete")
                    }

                })


    }

    private fun IgnoreElementsOperator() {
        Log.i("RxJava", "IgnoreElementsOperator")
        // IgnoreElements operatörü herhangi bir veri yaymaz.
        // Sadece işlemi tamamlandığını ya da hata alıp almadığını Observer’a iletir.
        // Observable’ın yaydığı verilerle ilgilinmiyorsanız bu operatörü kullanabilirsiniz.

        //---Çıktı---
        // IgnoreElementsOperator
        // onSubscribe
        // onComplete

        Observable.just(1, 2, 3, 4, 5, 6, 7, 8)
                .ignoreElements()
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {
                        Log.i("RxJava", "onSubscribe")
                    }

                    override fun onComplete() {
                        Log.i("RxJava", "onComplete")
                    }

                    override fun onError(e: Throwable) {
                        Log.i("RxJava", "onError")
                    }

                })
    }

    private fun FilterOperator() {
        Log.i("RxJava", "FilterOperator")
        // Filter operatörü Observable tarafından yayılan veriyi bir filtreye tabi tutar ve bu filtreyi geçen veriler dinleyici olan Observer’a iletilir.
        // Aşağıdaki örnekte Observable tarafından yayılan her sayı filter fonksiyonuna düşmekte ve burda t >3 kontrolü yapılmaktadır.
        // 3'ten büyük olan değerler koşulu sağladığından sırayla Observer onNext metoduna düşecektir.

        //---Çıktı---
        // FilterOperator
        // onSubscribe
        // onNext :4
        // onNext :5
        // onNext :6
        // onComplete

        Observable.just(1, 2, 3, 4, 5, 6)
                .filter { t -> t > 3 }
                .subscribe(object : Observer<Int> {
                    override fun onSubscribe(d: Disposable) {
                        Log.i("RxJava", "onSubscribe")
                    }

                    override fun onNext(t: Int) {
                        Log.i("RxJava", "onNext :$t")
                    }

                    override fun onError(e: Throwable) {
                        Log.i("RxJava", "onError")
                    }

                    override fun onComplete() {
                        Log.i("RxJava", "onComplete")
                    }

                })
    }

    private fun ElementAtOperator() {
        Log.i("RxJava", "ElementAtOperator")
        // ElementAt operatörü bize Observable tarafından yayılan verinin istediğimiz elemanına ulaşmamızı sağlar.
        // elementAt(index) içinde seçilen index indisine sahip eleman dinleyici olan Observer’a gönderilir ve onSucces metoduna düşer.
        // Eğer o indexte eleman yoksa önce onSubscribe sonra onComplete motodu çalışır,herhangi bir veri gelmez.

        // ElementAtOperator
        // onSubscribe
        // onSuccess :2

        Observable.just(1, 2, 3, 4, 5, 6)
                .elementAt(1)
                .subscribe(object : MaybeObserver<Int> {
                    override fun onSubscribe(d: Disposable) {
                        Log.i("RxJava", "onSubscribe")
                    }

                    override fun onSuccess(t: Int) {
                        Log.i("RxJava", "onSuccess :$t")
                    }

                    override fun onError(e: Throwable) {
                        Log.i("RxJava", "onError")
                    }

                    override fun onComplete() {
                        Log.i("RxJava", "onComplete")
                    }

                })

    }

    private fun DistinctOperator() {
        Log.i("RxJava", "DistinctOperator")
        // Distinct operatörü Observable tarafından yayılan veri içindeki tekrarlanan elemanları engeller.
        // Herhangi bir objenin tekrarlanıp tekrarlanmadığını kontrol ederken objenin equals metodu kullanır.
        // Aynı itemin birinci seferden sonra dinleyici olan Observer’a gönderilmez.
        // Aşağıdaki örnekte bir sayı dizisi tekrarlanan elemanlardan oluşmaktadır.
        // Distinct metodu bu tekrarlanan elemanları sadece bir kez yayınlayacaktır.

        //---Çıktı---
        // DistinctOperator
        // onSubscribe
        // onNext :10
        // onNext :20
        // onNext :30
        // onNext :40
        // onNext :50
        // onNext :15
        // onComplete

        Observable.just(10, 20, 30, 10, 20, 30, 40, 50, 10, 15)
                .distinct()
                .subscribe(object : Observer<Int> {
                    override fun onSubscribe(d: Disposable) {
                        Log.i("RxJava", "onSubscribe")
                    }

                    override fun onNext(t: Int) {
                        Log.i("RxJava", "onNext :$t")
                    }

                    override fun onError(e: Throwable) {
                        Log.i("RxJava", "onError")
                    }

                    override fun onComplete() {
                        Log.i("RxJava", "onComplete")
                    }
                })

    }

    private fun ScanOperator() {
        // Scan operatörü sıralı olarak tüm verileri tek tek işleme alır.
        // İşlemden çıkan her sonucu bir sonraki işlemin birinci değişkeni olarak kullanır.
        // Bu durumda ikinci değişken de Observable’dan gelen sıradaki veri olur.
        //Aşağıdaki örnekte J,A,V,A harfleri sırasıyla scan fonksiyonu içine düşüyor.
        // Scan içinde t1 bir önceki işlemin sonucu, t2 ise Observable’dan gelen sıradaki veri.
        // İlk başta t1 boş ve t2 “J” olmak üzere string birleştirme işlemi yaplıyor ve bu değer dönüyor.
        // İkinci işlemde t1 bir önceki işlemden dönen “J” iken t2 ise Observable’dan gelen sıradaki veri olan “A” ,
        // yine string birleştirme işlemi yapılarak “JA” dönüyor.İşlem bu şekilde son veri de gelene kadar devam ediyor.

        Log.i("RxJava", "ScanOperator")
        Observable.just("K", "O", "T", "L", "İ", "N")
                .scan { t1, t2 -> t1 + t2 }
                .subscribe(object : Observer<String> {
                    override fun onSubscribe(d: Disposable) {
                        Log.i("RxJava", "onSubscribe")
                    }

                    override fun onNext(t: String) {
                        Log.i("RxJava", "onNext $t")
                    }

                    override fun onError(e: Throwable) {
                        Log.i("RxJava", "onError")
                    }

                    override fun onComplete() {
                        Log.i("RxJava", "onComplete")
                    }

                })

    }

    private fun GroupByOperator() {
        Log.i("RxJava", "GroupBy")
        // GroupBy operatörü ile veriyi çeşitli koşullara göre gruplara ayırabilirsiniz.
        // Gruplara ayrılan veriler GroupedObservable<Key,Value> türünde tutulur ve dinleyici olan Observer’a iletilir.

        // Aşağıdaki örnekte 1,…,9 arası sayılar sırasıyla groupBy fonksiyonuna düşüyor.
        // Burada bir sayıları bir koşulla ayırıyoruz.
        // Eğer t değeri 2 ile tam bölünüyor ise EVEN_NUMBER_KEY stringini dönüyoruz,değil ise ODD_NUMBER_KEY stringini dönüyoruz.
        // Aslında burada çift sayılar EVEN_NUMBER_KEY, tek sayıları ise ODD_NUMBER_KEY stringi ile işaretledik gibi düşünebiliriz.
        // Burada string dönmek yerine true ya da false da dönebilirdik.
        // Döndüğümüz bu keyler sahip oldukları değerler ile birlikte dinleyici olan Observer’ın onNext metoduna düşüyor.
        // Burada istediğimiz grubu key kontrolü yaparak alabiliriz.
        // Aşağıda groupedObservable.key == EVEN_NUMBER_KEY kontrolü yaparak çift sayıları aldık.

        // ---Çktı---
        //  GroupBy
        //  onSubscribe
        //  Group onSubscribe
        //  Group onNext :2
        //  Group onNext :4
        //  Group onNext :6
        //  Group onNext :8
        //  Group onComplete
        //  onComplete

        val EVEN_NUMBER_KEY = "even number"
        val ODD_NUMBER_KEY = "odd number"

        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .groupBy { t ->
                    if (t % 2 == 0) { // cift sayı ise
                        EVEN_NUMBER_KEY
                    } else {
                        ODD_NUMBER_KEY
                    }
                }
                .subscribe(object : Observer<GroupedObservable<String, Int>> {
                    override fun onSubscribe(d: Disposable) {
                        Log.i("RxJava", "onSubscribe")
                    }

                    override fun onNext(t: GroupedObservable<String, Int>) {
                        if (t.key == EVEN_NUMBER_KEY) {
                            t.subscribe(object : Observer<Int> {
                                override fun onSubscribe(d: Disposable) {
                                    Log.i("RxJava", "Group onSubscribe")
                                }

                                override fun onNext(t: Int) {
                                    Log.i("RxJava", "Group onNext :$t ")
                                }

                                override fun onError(e: Throwable) {
                                    Log.i("RxJava", "Group onError")
                                }

                                override fun onComplete() {
                                    Log.i("RxJava", "Group onComplete")
                                }

                            })
                        }
                    }

                    override fun onError(e: Throwable) {
                        Log.i("RxJava", "onError")
                    }

                    override fun onComplete() {
                        Log.i("RxJava", "onComplete")
                    }
                })


    }

    private fun ContactMapOperator() {
        // ConcatMap operatörü flatMap operatörü ile birebir aynı denilebilir.
        // Tek farkı flatMap operatörü veriyi yayınlama işlemini sıralı yapmazken concatMap’ın sıralı yapmasıdır.

        Log.i("RxJava", "contactMap")

        fun getModifiedObservable(integer: Int): Observable<Int> {
            return Observable.create(ObservableOnSubscribe<Int> { emitter ->
                emitter.onNext(integer * 2)
                emitter.onComplete()
            }).subscribeOn(Schedulers.io())
        }

        // ---Çıktı--- Sıralı bir şekilde işlemleri yaptı
        // contactMap
        // onSubscribe
        // onNext: 2
        // onNext: 4
        // onNext: 6
        // onNext: 8
        // onNext: 10
        // onNext: 12
        // onComplete


        Observable.just(1, 2, 3, 4, 5, 6)
                .concatMap { t -> getModifiedObservable(t) }
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<Int> {
                    override fun onComplete() {
                        Log.i("RxJava", "onComplete")
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.i("RxJava", "onSubscribe")
                    }

                    override fun onNext(t: Int) {
                        Log.i("RxJava", "onNext: $t")
                    }

                    override fun onError(e: Throwable) {
                        Log.i("RxJava", "onError")
                    }


                })
    }

    private fun SwitchMapOperator() {
        // SwitchMap operatörü de flatMap gibi Observable bir değer döndürür.
        // **Flatmap’tan farkı ise her değeri değil de sadece son değeri dinleyici olan Observer’a iletmesidir.
        // Aşağıdaki örnekte yine 1,…,6 arası Integer değerleri sırasıyla switchMap içindeki apply metoduna düşecektir.
        // Her yeni değer geldiğinde bir öncekini yok sayacak
        // ve sonuncu değer için işlemi yapıp dinleyici olan Observer’a gönderecektir.


        Log.i("RxJava", "switchMap")
        fun getModifiedObservable(integer: Int): Observable<Int> {
            return Observable.create(ObservableOnSubscribe<Int> { emitter ->
                emitter.onNext(integer * 2)
                emitter.onComplete()
            }).subscribeOn(Schedulers.io())
        }

        //  --çıktı--
        //  onSubscribe
        //  onNext: 12
        //  onComplete

        Observable.just(1, 2, 3, 4, 5, 6)
                .switchMap { t -> getModifiedObservable(t) }
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<Int> {
                    override fun onComplete() {
                        Log.i("RxJava", "onComplete")
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.i("RxJava", "onSubscribe")
                    }

                    override fun onNext(t: Int) {
                        Log.i("RxJava", "onNext: $t")
                    }

                    override fun onError(e: Throwable) {
                    }
                })


    }

    private fun FlatMapOperator() {
        // Bu operatör de aslında “Map” operatörü gibi çalışır, veriyi istediğiniz forma dönüştürebilirsiniz.
        // Map ile farkı ise veriyi dönerken Observable bir değer olarak dönme zorunluluğu olmasıdır.
        // Yani aslında birden çok Observable işlemini tek bir değere indirger.
        // Flatmap’ın bir diğer özelliği ise veriyi yayma işlemini sıralı yapmamasıdır.
        // Aşağıdaki örnekte yine 1,..,6 arası sayılar sırası ile flatmap operatörü içindeki t değişkenine düşmektedir.

        // Daha sonra fonksiyona giren her t getModifiedObservable motoduna gönderilir.
        // Burda da işlemden geçen t Integer değeri, Observable<Int> olarak döner.
        // Son olarak bu Observable<Int> değerleri dinleyici olan Observer’a gönderilir.
        // Flatmap işlemleri sırasıyla yapmaz karışık yapar

        // Çıktı işlemler karışık yapıldı
        // onSubscribe
        //  onNext: 4
        //  onNext: 2
        //  onNext: 6
        //  onNext: 8
        //  onNext: 10
        //  onNext: 12
        //  onComplete

        fun getModifiedObservable(integer: Int): Observable<Int> {
            return Observable.create(ObservableOnSubscribe<Int> { emitter ->
                emitter.onNext(integer * 2)
                emitter.onComplete()
            }).subscribeOn(Schedulers.io())
        }

        Observable.just(1, 2, 3, 4, 5, 6)
                .flatMap { t -> getModifiedObservable(t) }
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<Int> {
                    override fun onComplete() {
                        Log.i("RxJava", "onComplete")
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.i("RxJava", "onSubscribe")
                    }

                    override fun onNext(t: Int) {
                        Log.i("RxJava", "onNext: $t")
                    }

                    override fun onError(e: Throwable) {
                    }
                })
    }

    private fun ObservableAllMethodsExample() {
        Observable.just("Hello")
                .doOnSubscribe { Log.i("RxJava", "Subscribed") }
                .doOnNext { s -> Log.i("RxJava", "Received: $s") }
                .doAfterNext { Log.i("RxJava", "After Receiving") }
                .doOnError { e -> Log.i("RxJava", "Error: $e") }
                .doOnComplete { Log.i("RxJava", "Complete") }
                .doFinally { Log.i("RxJava", "Do Finally!") }
                .doOnDispose { Log.i("RxJava", "Do on Dispose!") }
                .subscribe { Log.i("RxJava", "Subscribe") }
    }

    private fun CompletableObserverExample() {
        Log.i("RxJava", "CompletableObserver")
        // Completable herhangi bir data iletmez.
        // İşlemin başarıyla sonuçlanıp sonuçlanmamasına göre onComplete yada onError metodu çalışır.
        // Completable için dinleyici olarak CompletableObserver kullanılır.
        // HTTP 204'ü döndüreceği ve hataların HTTP 301, HTTP 404, HTTP 500, vb .'den gelebileceği REST API'leri içindir.
        // Bu bilgilerle bir şeyler yapabiliriz.

        val completableObserver = object : CompletableObserver {
            override fun onSubscribe(d: Disposable) {
                Log.i("RxJava", "onSubscribe")
            }

            override fun onComplete() {
                Log.i("RxJava", "onComplete")
            }

            override fun onError(e: Throwable) {
                Log.i("RxJava", "onError")
            }
        }

    }

    private fun MaybeObserverExample() {
        Log.i("RxJava", "MaybeObserver")
        // Single gibi sadece bir değeri iletmek için kullanılır.
        // Singledan farkı değeri dönmek zorunda değildir.
        // Değer dönerse onSuccess metoduna düşer.
        // Şayet hata alırsa da diğer tiplerde olduğu gibi onError metoduna düşer.
        // Maybe ile kullanıcı veritabanımızda, herhangi bir kullanıcının olup olmadığını kontrol edebiliriz.
        // Kullanıcı varsa onSuccess metodu çalışacaktır.

        val maybeObservable = Maybe.just("This is Maybe")

        val maybeObserver = object : MaybeObserver<String> {
            override fun onSubscribe(d: Disposable) {
                Log.i("RxJava", "onSubscribe")
            }

            override fun onSuccess(t: String) {
                Log.i("RxJava", "onSuccess :$t")
            }

            override fun onError(e: Throwable) {
                Log.i("RxJava", "onError")
            }

            override fun onComplete() {
                Log.i("RxJava", "onComplete")
            }

        }

        maybeObservable
                .subscribe(maybeObserver)


    }

    private fun SingleObserverExample() {
        Log.i("RxJava", "SingleObserver")

        // SingleObserver
        // Sadece bir değeri iletmek için kullanılır
        // O değeri başarıyla iletir yada hata mesajı ile döner.
        // onComplete metodu yoktur.

        val getSingleObservable = Single.just("This is A Single")

        val singleObserver = object : SingleObserver<String> {
            override fun onSubscribe(d: Disposable) {
                Log.i("RxJava", "onSubscribe")
            }

            override fun onSuccess(t: String) {
                Log.i("RxJava", "onSuccess : $t")
            }

            override fun onError(e: Throwable) {
                Log.i("RxJava", "onError")
            }
        }
        // Subscription
        getSingleObservable
                .subscribe(singleObserver)


    }

    private fun ObservableExample() {
        Log.i("RxJava", "Observable")


        // Observable : Veriyi dışa aktaran yapıdır
        val getObservable = Observable.just("X", "Y", "Z")

        // Observer : Observable tarafından dışa aktarılan veriyi dinleyen yapıdır
        val getObserver = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.i("RxJava", "onSubscribe")
            }

            override fun onNext(t: String) {
                Log.i("RxJava", "onNext :$t")
            }

            override fun onError(e: Throwable) {
                Log.i("RxJava", "onError")

            }

            override fun onComplete() {
                Log.i("RxJava", "onComplete")

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


