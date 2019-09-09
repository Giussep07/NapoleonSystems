package com.giussep.ricardo.napoleonsystems.root;

import com.giussep.ricardo.napoleonsystems.dataSource.local.NapoleonDatabaseModule;
import com.giussep.ricardo.napoleonsystems.detail.DetailFragment;
import com.giussep.ricardo.napoleonsystems.detail.DetailModule;
import com.giussep.ricardo.napoleonsystems.favorites.FavoriteModule;
import com.giussep.ricardo.napoleonsystems.favorites.FavoritesFragment;
import com.giussep.ricardo.napoleonsystems.home.HomeFragment;
import com.giussep.ricardo.napoleonsystems.home.HomeModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class,
        HomeModule.class,
        DetailModule.class,
        FavoriteModule.class,
        NapoleonDatabaseModule.class})
public interface ApplicationComponent {

    void inject(HomeFragment homeFragment);

    void inject(DetailFragment detailFragment);

    void inject(FavoritesFragment favoritesFragment);
}
