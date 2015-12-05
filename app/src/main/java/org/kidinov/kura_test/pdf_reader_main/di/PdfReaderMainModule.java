package org.kidinov.kura_test.pdf_reader_main.di;

import org.kidinov.kura_test.pdf_reader_main.presentation.PdfReaderMainPresenter;
import org.kidinov.kura_test.pdf_reader_main.presentation.PdfReaderMainView;
import org.kidinov.kura_test.pdf_reader_main.presentation.PdfReaderMainViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class PdfReaderMainModule {
    private PdfReaderMainView view;

    public PdfReaderMainModule(PdfReaderMainView view) {
        this.view = view;
    }

    @Provides
    @PerPdfReaderMainActivity
    public PdfReaderMainPresenter providePresenter(PdfReaderMainViewModel viewModel) {
        return new PdfReaderMainPresenter(view, viewModel);
    }

    @Provides
    @PerPdfReaderMainActivity
    public PdfReaderMainViewModel provideViewModel() {
        return new PdfReaderMainViewModel();
    }
}
