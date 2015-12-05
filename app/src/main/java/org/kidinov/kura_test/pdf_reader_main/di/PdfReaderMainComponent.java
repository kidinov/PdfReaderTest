package org.kidinov.kura_test.pdf_reader_main.di;

import org.kidinov.kura_test.common.di.component.ApplicationComponent;
import org.kidinov.kura_test.pdf_reader_main.presentation.PdfReaderMainActivity;

import dagger.Component;

@PerPdfReaderMainActivity
@Component(
        dependencies = {
                ApplicationComponent.class
        },
        modules = {
                PdfReaderMainModule.class
        })
public interface PdfReaderMainComponent {
    void inject(PdfReaderMainActivity activity);
}
