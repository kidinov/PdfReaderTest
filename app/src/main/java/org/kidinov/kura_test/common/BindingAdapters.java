package org.kidinov.kura_test.common;

import android.databinding.BindingAdapter;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import java.io.File;

public class BindingAdapters {

    @BindingAdapter({"bind:openFile", "bind:onPageChangeListener", "bind:onLoadCompleteListener"})
    public static void openFile(PDFView view, File file, OnPageChangeListener onPageChangeListener,
                                OnLoadCompleteListener onLoadCompleteListener) {
        if (file != null) {
            view.fromFile(file)
                    .onPageChange(onPageChangeListener)
                    .onLoad(onLoadCompleteListener)
                    .swipeVertical(true)
                    .load();
        }
    }

}
