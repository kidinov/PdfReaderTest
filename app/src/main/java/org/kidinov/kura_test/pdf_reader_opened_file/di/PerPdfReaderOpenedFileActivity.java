package org.kidinov.kura_test.pdf_reader_opened_file.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Scope
@Retention(RUNTIME)
public @interface PerPdfReaderOpenedFileActivity {
}
