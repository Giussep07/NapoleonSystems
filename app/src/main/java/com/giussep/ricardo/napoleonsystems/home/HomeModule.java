package com.giussep.ricardo.napoleonsystems.home;

import com.giussep.ricardo.napoleonsystems.dataSource.local.Post.PostLocalDataSource;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class HomeModule {

    @Provides
    public HomeContract.Presenter providePresenter(HomeContract.Model model) {
        return new HomePresenter(model);
    }

    @Provides
    public HomeContract.Model provideModel(HomeContract.Repository repository) {
        return new HomeModel(repository);
    }

    @Provides
    public HomeContract.Repository provideRepository(PostLocalDataSource dataSource, Retrofit retrofit) {
        return new HomeRepository(dataSource, retrofit);
    }
}
