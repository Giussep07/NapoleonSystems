package com.giussep.ricardo.napoleonsystems.favorites;

import com.giussep.ricardo.napoleonsystems.model.Post;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public interface FavoritesContract {

    interface Model {
        void setPresenter(Presenter presenter);

        void getFavoritesPosts();

        void setPostLeido(Post post);
    }

    interface View {

        void showDialogProgress();

        void hideDialogProgress();

        void showFavorites(List<Post> postList);

        void showErrorLoading(String message);
    }

    interface Presenter {

        void setView(View view);

        void getFavoritesPosts();

        void showFavorites(List<Post> postList);

        void showErrorLoading(String message);

        void setPostLeido(Post post);
    }

    interface Repository {
        Maybe<List<Post>> getPostsFavorites();

        Completable updatePost(Post post);
    }
}
