package org.kidinov.kura_test.pdf_reader_main.presentation;

import android.view.View;

import org.kidinov.kura_test.pdf_reader_main.di.PerPdfReaderMainActivity;

import rx.subjects.PublishSubject;

@PerPdfReaderMainActivity
public class PdfReaderMainViewModel {
    private PublishSubject<View> onChoosePdfClickListener = PublishSubject.create();

    public void onChoosePdfClick(View view) {
        onChoosePdfClickListener.onNext(view);
    }

    public PublishSubject<View> getOnChoosePdfClickListener() {
        return onChoosePdfClickListener;
    }

}
