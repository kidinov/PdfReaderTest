package org.kidinov.kura_test.pdf_reader_opened_file.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import org.kidinov.kura_test.common.Const;
import org.kidinov.kura_test.common.Presenter;
import org.kidinov.kura_test.pdf_reader_opened_file.domain.interactor.DefaultSubscriber;
import org.kidinov.kura_test.pdf_reader_opened_file.domain.interactor.GetFilePathCase;

import java.io.File;
import java.util.concurrent.TimeUnit;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class PdfReaderOpenedFilePresenter implements Presenter {
    private final PdfReaderOpenedFileView view;
    private final PdfReaderOpenedFileViewModel viewModel;
    private final GetFilePathCase getFilePathCase;
    private Subscription pageChangedListenerSubscription;
    private int currentPage = -1;
    private Subscription loadCompleteListenerSubscription;

    public PdfReaderOpenedFilePresenter(PdfReaderOpenedFileView view, PdfReaderOpenedFileViewModel viewModel,
                                        GetFilePathCase getFilePathCase) {
        this.view = view;
        this.viewModel = viewModel;
        this.getFilePathCase = getFilePathCase;
    }

    @Override
    public void create(Bundle savedInstanceState) {
        //Just to demonstrate how remote calls should be done
        showPreloaderView();
        getFilePathCase.execute(new GetPathSubscriber());

        if (savedInstanceState != null) {
            currentPage = savedInstanceState.getInt("page");
        }

        loadCompleteListenerSubscription = viewModel.getLoadCompleteListener()
                .delay(100, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(x -> {
                    if (currentPage != -1) {
                        view.goToPage(currentPage);
                    }
                    view.pageChanged();
                });


        pageChangedListenerSubscription = viewModel.getPageChangedListener()
                //In order to restore proper position
                .skipUntil(viewModel.getLoadCompleteListener())
                .subscribe(pair -> {
                    currentPage = pair.first;
                    viewModel.getPageNumber().set(String.format("%d/%d", pair.first, pair.second));
                    view.pageChanged();
                });
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void saveInstanceState(Bundle outState) {
        outState.putInt("page", currentPage);
    }

    @Override
    public void destroy() {
        getFilePathCase.unsubscribe();
        pageChangedListenerSubscription.unsubscribe();
        loadCompleteListenerSubscription.unsubscribe();
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

    /**
     * Show preloader view
     * Need to be implemented in real app
     */
    private void showPreloaderView() {
    }

    //


    /**
     * Show something to user in case of error
     * Need to be implemented in real app
     */
    private void showErrorMessage() {
    }

    private void showPdf(String path) {
        File file = new File(path);
        viewModel.getFileToShow().set(file);
        view.setFileTitle(file.getName());
        view.pageChanged();
    }

    /**
     * Hide preloader view
     * Need to be implemented in real app
     */
    private void hideViewLoading() {
    }

    private final class GetPathSubscriber extends DefaultSubscriber<String> {

        @Override
        public void onCompleted() {
            hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            hideViewLoading();
            showErrorMessage();
        }

        @Override
        public void onNext(String path) {
            showPdf(path);
        }
    }

}
