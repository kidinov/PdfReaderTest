package org.kidinov.kura_test.pdf_reader_opened_file.presentation;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.util.Pair;

import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import java.io.File;

import rx.subjects.PublishSubject;

public class PdfReaderOpenedFileViewModel {
    private ObservableField<File> fileToShow = new ObservableField<>();
    private OnPageChangeListener onPageChangeListener;
    private OnLoadCompleteListener onLoadCompleteListener;
    private PublishSubject<Pair<Integer, Integer>> pageChangedListener = PublishSubject.create();
    private PublishSubject<Integer> loadCompleteListener = PublishSubject.create();
    private ObservableField<String> pageNumber = new ObservableField<>();
    private ObservableBoolean showPageNumber = new ObservableBoolean();


    public PdfReaderOpenedFileViewModel() {
        onPageChangeListener = (page, pageCount) -> pageChangedListener.onNext(new Pair<>(page, pageCount));

        onLoadCompleteListener = loadCompleteListener::onNext;
    }

    public ObservableField<File> getFileToShow() {
        return fileToShow;
    }

    public PublishSubject<Pair<Integer, Integer>> getPageChangedListener() {
        return pageChangedListener;
    }

    public ObservableField<String> getPageNumber() {
        return pageNumber;
    }

    public ObservableBoolean getShowPageNumber() {
        return showPageNumber;
    }

    public OnPageChangeListener getOnPageChangeListener() {
        return onPageChangeListener;
    }

    public OnLoadCompleteListener getOnLoadCompleteListener() {
        return onLoadCompleteListener;
    }

    public PublishSubject<Integer> getLoadCompleteListener() {
        return loadCompleteListener;
    }
}
