package org.kidinov.kura_test.common;

import android.app.Application;

import org.kidinov.kura_test.common.di.component.ApplicationComponent;
import org.kidinov.kura_test.common.di.component.DaggerApplicationComponent;
import org.kidinov.kura_test.common.di.module.ApplicationModule;

public class App extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        applicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
