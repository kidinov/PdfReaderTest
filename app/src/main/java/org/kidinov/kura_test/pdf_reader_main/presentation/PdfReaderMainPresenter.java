package org.kidinov.kura_test.pdf_reader_main.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import org.kidinov.kura_test.common.Const;
import org.kidinov.kura_test.common.Presenter;
import org.kidinov.kura_test.pdf_reader_main.di.PerPdfReaderMainActivity;

import rx.Subscription;
import timber.log.Timber;

@PerPdfReaderMainActivity
public class PdfReaderMainPresenter implements Presenter {
    private final PdfReaderMainViewModel viewModel;
    private final PdfReaderMainView view;
    private Subscription choosePdfClickSubscription;

    public PdfReaderMainPresenter(PdfReaderMainView view, PdfReaderMainViewModel viewModel) {
        this.viewModel = viewModel;
        this.view = view;
    }

    @Override
    public void create(Bundle savedInstanceState) {
        choosePdfClickSubscription = viewModel.getOnChoosePdfClickListener()
                .subscribe(x -> view.startPdfFilePickerActivity(Const.OPEN_FILE_CODE),
                        e -> Timber.e(e, ""));
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void saveInstanceState(Bundle outState) {
    }

    @Override
    public void destroy() {
        choosePdfClickSubscription.unsubscribe();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Const.OPEN_FILE_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    view.startPdfOpenedFileActivity(data.getData());
                }
                break;
        }
    }

}
