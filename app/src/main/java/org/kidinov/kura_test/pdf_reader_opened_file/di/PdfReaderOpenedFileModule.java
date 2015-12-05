package org.kidinov.kura_test.pdf_reader_opened_file.di;

import android.content.Context;
import android.net.Uri;

import org.kidinov.kura_test.pdf_reader_opened_file.domain.interactor.GetFilePathCase;
import org.kidinov.kura_test.pdf_reader_opened_file.presentation.PdfReaderOpenedFilePresenter;
import org.kidinov.kura_test.pdf_reader_opened_file.presentation.PdfReaderOpenedFileView;
import org.kidinov.kura_test.pdf_reader_opened_file.presentation.PdfReaderOpenedFileViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class PdfReaderOpenedFileModule {
    private final Context context;
    private PdfReaderOpenedFileView view;
    private final Uri fileUri;

    public PdfReaderOpenedFileModule(Context context, PdfReaderOpenedFileView view, Uri fileUri) {
        this.context = context;
        this.view = view;
        this.fileUri = fileUri;
    }

    @Provides
    @PerPdfReaderOpenedFileActivity
    public PdfReaderOpenedFilePresenter providePresenter(PdfReaderOpenedFileViewModel viewModel,
                                                         GetFilePathCase getFilePathCase) {
        return new PdfReaderOpenedFilePresenter(view, viewModel, getFilePathCase);
    }

    @Provides
    @PerPdfReaderOpenedFileActivity
    public PdfReaderOpenedFileViewModel provideViewModel() {
        return new PdfReaderOpenedFileViewModel();
    }

    @Provides
    @PerPdfReaderOpenedFileActivity
    public GetFilePathCase provideUseCase() {
        return new GetFilePathCase(context, fileUri);
    }
}
