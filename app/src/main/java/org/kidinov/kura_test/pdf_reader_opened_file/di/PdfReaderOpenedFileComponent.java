package org.kidinov.kura_test.pdf_reader_opened_file.di;

import org.kidinov.kura_test.common.di.component.ApplicationComponent;
import org.kidinov.kura_test.pdf_reader_opened_file.presentation.PdfReaderOpenedFileActivity;

import dagger.Component;

@PerPdfReaderOpenedFileActivity
@Component(
        dependencies = {
                ApplicationComponent.class
        },
        modules = {
                PdfReaderOpenedFileModule.class
        })
public interface PdfReaderOpenedFileComponent {
    void inject(PdfReaderOpenedFileActivity activity);
}
