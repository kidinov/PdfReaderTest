package org.kidinov.kura_test.common.di.module;

import org.kidinov.kura_test.common.BaseActivity;

import dagger.Module;

@Module
public class ActivityModule {
    private final BaseActivity baseActivity;

    public ActivityModule(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

}
