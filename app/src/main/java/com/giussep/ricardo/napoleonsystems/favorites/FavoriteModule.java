package com.giussep.ricardo.napoleonsystems.favorites;

import com.giussep.ricardo.napoleonsystems.dataSource.local.Post.PostLocalDataSource;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class FavoriteModule {

    @Provides
    public FavoritesContract.Presenter providePresenter(FavoritesContract.Model model) {
        return new FavoritesPresenter(model);
    }

    @Provides
    public FavoritesContract.Model provideModel(FavoritesContract.Repository repository) {
        return new FavoritesModel(repository);
    }

    @Provides
    public FavoritesContract.Repository provideRepository(PostLocalDataSource dataSource) {
        return new FavoritesRepository(dataSource);
    }
}
