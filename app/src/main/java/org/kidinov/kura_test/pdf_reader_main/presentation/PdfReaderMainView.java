package org.kidinov.kura_test.pdf_reader_main.presentation;

import android.net.Uri;

public interface PdfReaderMainView {
    void startPdfFilePickerActivity(int code);

    void startPdfOpenedFileActivity(Uri data);

}
