package com.giussep.ricardo.napoleonsystems.detail;

import com.giussep.ricardo.napoleonsystems.dataSource.local.Post.PostDataSource;
import com.giussep.ricardo.napoleonsystems.dataSource.local.User.UserDataSource;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class DetailModule {

    @Provides
    public DetailContract.Model provideModel(DetailContract.Repository repository) {
        return new DetailModel(repository);
    }

    @Provides
    public DetailContract.Presenter providePresenter(DetailContract.Model model) {
        return new DetailPresenter(model);
    }

    @Provides
    public DetailContract.Repository provideRepository(UserDataSource dataSource, Retrofit retrofit) {
        return new DetailRepository(dataSource, retrofit);
    }
}
