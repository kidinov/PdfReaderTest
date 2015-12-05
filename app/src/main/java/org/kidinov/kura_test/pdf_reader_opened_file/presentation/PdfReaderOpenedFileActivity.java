package org.kidinov.kura_test.pdf_reader_opened_file.presentation;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;

import com.h6ah4i.android.widget.verticalseekbar.VerticalSeekBar;
import com.joanzapata.pdfview.PDFView;

import org.kidinov.kura_test.R;
import org.kidinov.kura_test.common.BaseActivity;
import org.kidinov.kura_test.common.Const;
import org.kidinov.kura_test.common.DisplayUtils;
import org.kidinov.kura_test.common.Presenter;
import org.kidinov.kura_test.databinding.PdfReaderOpenedFileActivityBinding;
import org.kidinov.kura_test.pdf_reader_opened_file.di.DaggerPdfReaderOpenedFileComponent;
import org.kidinov.kura_test.pdf_reader_opened_file.di.PdfReaderOpenedFileComponent;
import org.kidinov.kura_test.pdf_reader_opened_file.di.PdfReaderOpenedFileModule;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;

public class PdfReaderOpenedFileActivity extends BaseActivity implements PdfReaderOpenedFileView {
    @Inject
    PdfReaderOpenedFileViewModel viewModel;

    @Inject
    PdfReaderOpenedFilePresenter presenter;

    //We should avoid direct usage of view, instead of it we should manipulate with ViewModel
    //Here it was done only in order to avoid extra complexity
    private Toolbar toolbar;
    private PDFView pdfView;
    private VerticalSeekBar seekBar;
    private Subscription hidePageDelayedSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PdfReaderOpenedFileActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.pdf_reader_opened_file_activity);
        binding.setViewModel(viewModel);

        initViews();

        setupSeekBar();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        pdfView = (PDFView) findViewById(R.id.pdf_view);
        seekBar = (VerticalSeekBar) findViewById(R.id.seek_bar);
    }

    private void drawProgress(SeekBar seekBar, int progress) {
        String dynamicText = String.valueOf(progress + 1);
        seekBar.setThumb(writeOnDrawable(dynamicText));
    }

    private void setupSeekBar() {
        drawProgress(seekBar, 0);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                drawProgress(seekBar, progress);
                if (fromUser) {
                    goToPage(progress + 1);
                    viewModel.getShowPageNumber().set(true);
                    if (hidePageDelayedSubscription != null) {
                        hidePageDelayedSubscription.unsubscribe();
                        hidePageDelayedSubscription = null;
                    }
                    hidePageDelayedSubscription = Observable.just(null)
                            .delay(1000, TimeUnit.MILLISECONDS)
                            .subscribe(x -> viewModel.getShowPageNumber().set(false));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private BitmapDrawable writeOnDrawable(String text) {
        int size = DisplayUtils.dpToPx(this, 30);

        Bitmap bm = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#99000000"));
        canvas.drawCircle(size / 2, size / 2, size / 2, paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(size / 2);
        int xPos = (size / 2) + 15;
        int yPos = (int) ((size / 2) - ((paint.descent() + paint.ascent()) / 2));
        canvas.rotate(-90, xPos, yPos);
        canvas.drawText(text, xPos, yPos, paint);

        return new BitmapDrawable(getResources(), bm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pdf_reader_opened_file_menu, menu);
        DrawableCompat.setTint(menu.getItem(0).getIcon(), Color.WHITE);
        DrawableCompat.setTint(menu.getItem(1).getIcon(), Color.WHITE);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.go_to_page:
                showDialogWithPages();
                return true;
            case R.id.open_file:
                startPdfFilePickerActivity(Const.OPEN_FILE_CODE);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialogWithPages() {
        PdfReaderOpenedFilePagePickerDialog.newInstance(pdfView.getPageCount())
                .show(getSupportFragmentManager(), "PdfReaderOpenedFilePagePickerDialog");
    }

    @Override
    protected void setupComponent() {
        PdfReaderOpenedFileComponent component = DaggerPdfReaderOpenedFileComponent.builder()
                .applicationComponent(getApplicationComponent())
                .pdfReaderOpenedFileModule(new PdfReaderOpenedFileModule(this, this, getIntent().getData()))
                .build();

        component.inject(this);
    }

    @Override
    protected Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void setFileTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void goToPage(int pageNum) {
        pdfView.jumpTo(pageNum);
    }

    @Override
    public void pageChanged() {
        seekBar.setMax(pdfView.getPageCount());
        seekBar.setProgress(pdfView.getCurrentPage());
    }

}
