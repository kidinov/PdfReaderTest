package org.kidinov.kura_test.pdf_reader_opened_file.presentation;

import org.kidinov.kura_test.pdf_reader_main.presentation.PdfReaderMainView;

public interface PdfReaderOpenedFileView extends PdfReaderMainView {
    void setFileTitle(String title);

    void goToPage(int pageNum);

    void pageChanged();
}
