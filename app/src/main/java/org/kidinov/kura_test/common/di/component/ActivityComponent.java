package org.kidinov.kura_test.common.di.component;

import org.kidinov.kura_test.common.BaseActivity;
import org.kidinov.kura_test.common.di.PerActivity;
import org.kidinov.kura_test.common.di.module.ActivityModule;

import dagger.Component;

@PerActivity
@Component(
        dependencies = {
                ApplicationComponent.class
        },
        modules = {
                ActivityModule.class
        })
public interface ActivityComponent {
    void inject(BaseActivity activity);
}
