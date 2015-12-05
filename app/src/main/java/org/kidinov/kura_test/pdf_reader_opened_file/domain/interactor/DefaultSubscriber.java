package org.kidinov.kura_test.pdf_reader_opened_file.domain.interactor;

import timber.log.Timber;

/**
 * Default subscriber base class to be used whenever you want default error handling.
 */
public class DefaultSubscriber<T> extends rx.Subscriber<T> {
    @Override
    public void onCompleted() {
    }


    @Override
    public void onError(Throwable e) {
        Timber.e(e, "");
    }


    @Override
    public void onNext(T t) {
    }
}
