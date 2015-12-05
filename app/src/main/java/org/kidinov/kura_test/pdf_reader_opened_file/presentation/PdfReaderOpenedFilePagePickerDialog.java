package org.kidinov.kura_test.pdf_reader_opened_file.presentation;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import org.kidinov.kura_test.R;

public class PdfReaderOpenedFilePagePickerDialog extends DialogFragment {
    private int pages;

    static PdfReaderOpenedFilePagePickerDialog newInstance(int pages) {
        PdfReaderOpenedFilePagePickerDialog f = new PdfReaderOpenedFilePagePickerDialog();

        Bundle args = new Bundle();
        args.putInt("pages", pages);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pages = getArguments().getInt("pages");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.go_to_page)
                .setItems(buildList(), (dialog, which) -> {
                    ((PdfReaderOpenedFileView) getActivity()).goToPage(which + 1);
                })
                .setNegativeButton(android.R.string.cancel, (dialog1, which1) -> {
                            dismiss();
                        }
                )
                .create();
    }

    private CharSequence[] buildList() {
        CharSequence[] charSequences = new CharSequence[pages];
        for (int i = 1; i <= pages; i++) {
            charSequences[i - 1] = String.valueOf(i);
        }
        return charSequences;
    }
}
