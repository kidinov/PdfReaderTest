package org.kidinov.kura_test.pdf_reader_main.presentation;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import org.kidinov.kura_test.R;
import org.kidinov.kura_test.common.BaseActivity;
import org.kidinov.kura_test.common.Presenter;
import org.kidinov.kura_test.databinding.PdfReaderMainActivityBinding;
import org.kidinov.kura_test.pdf_reader_main.di.DaggerPdfReaderMainComponent;
import org.kidinov.kura_test.pdf_reader_main.di.PdfReaderMainComponent;
import org.kidinov.kura_test.pdf_reader_main.di.PdfReaderMainModule;

import javax.inject.Inject;

/**
 * Idea of current architecture inspired by Robert C. Martin from his "The Clean Architecture"
 * https://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html
 * Some ideas is borrowed from here as well
 * http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/
 * <p>
 * Also added usage of Android Bindings (and MVP switched to MVP-VM in presenter layer)
 * in order to make code easy testable with unit tests
 * <p>
 * Also used "Package by feature" approach
 */
public class PdfReaderMainActivity extends BaseActivity implements PdfReaderMainView {
    @Inject
    PdfReaderMainViewModel viewModel;
    @Inject
    PdfReaderMainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PdfReaderMainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.pdf_reader_main_activity);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void setupComponent() {
        PdfReaderMainComponent component = DaggerPdfReaderMainComponent.builder()
                .applicationComponent(getApplicationComponent())
                .pdfReaderMainModule(new PdfReaderMainModule(this))
                .build();

        component.inject(this);
    }

    @Override
    protected Presenter getPresenter() {
        return presenter;
    }

}
