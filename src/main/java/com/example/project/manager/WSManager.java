package com.example.project.manager;

import android.content.Context;
import android.util.Log;

import com.example.project.R;
import com.example.project.model.FavorteMovieModel;
import com.example.project.model.MemberModel;
import com.example.project.model.MovieModel;
import com.example.project.model.TypeMovieModel;
import com.example.project.task.WSTask;


public class WSManager {
    private static WSManager wsManager;
    private Context context;

    public interface WSManagerListener {
        void onComplete(Object response);

        void onError(String err);
    }

    public WSManager(Context context) {
        this.context = context;
    }

    public static WSManager getWsManager(Context context) {
        if (wsManager == null)
            wsManager = new WSManager(context);
        return wsManager;
    }

    public void doRegister(Object object, final WSManagerListener listener) {
        if (!(object instanceof MemberModel)) {
            return;
        }
        MemberModel userModel = (MemberModel) object;
        userModel.toJSONString();
        WSTask task = new WSTask(this.context,new WSTask.WSTaskListener() {
            @Override
            public void onComplete(String response) {
                MemberModel userModel = new MemberModel(response);
                listener.onComplete(userModel);
            }

            @Override
            public void onError(String err) {
                listener.onError(err);
            }
        });

        Log.d("data ", userModel.toJSONString());
        task.execute(context.getString(R.string.register_url), userModel.toJSONString());
    }
    public void doLogin(Object object, final WSManagerListener listener) {
        if (!(object instanceof MemberModel)) {
            return;
        }
        MemberModel userModel = (MemberModel) object;
        userModel.toJSONString();
        WSTask task = new WSTask(this.context,new WSTask.WSTaskListener() {
            @Override
            public void onComplete(String response) {
                listener.onComplete(response);
            }

            @Override
            public void onError(String err) {
                listener.onError(err);
            }
        });
        Log.d("data ", userModel.toJSONString());
        task.execute(context.getString(R.string.do_login), userModel.toJSONString());
    }
    public void doAllMovie(Object object, final WSManagerListener listener) {
        if (!(object instanceof MovieModel)) {
            return;
        }
        MovieModel movieModel = (MovieModel) object;
        movieModel.toJSONString();
        WSTask task = new WSTask(this.context,new WSTask.WSTaskListener() {
            @Override
            public void onComplete(String response) {
                listener.onComplete(response);
            }

            @Override
            public void onError(String err) {
                listener.onError(err);
            }
        });
        Log.d("data ", movieModel.toJSONString());
        task.execute(context.getString(R.string.get_allMovie), movieModel.toJSONString());
    }
    public void doAllTypeMovie(Object object, final WSManagerListener listener) {
        if (!(object instanceof TypeMovieModel)) {
            return;
        }
        TypeMovieModel typeMovieModel = (TypeMovieModel) object;
        typeMovieModel.toJSONString();
        WSTask task = new WSTask(this.context,new WSTask.WSTaskListener() {
            @Override
            public void onComplete(String response) {
                listener.onComplete(response);

            }

            @Override
            public void onError(String err) {
                listener.onError(err);
            }
        });
        Log.d("data ", typeMovieModel.toJSONString());
        task.execute(context.getString(R.string.get_allTypeMovie), typeMovieModel.toJSONString());
    }
    public void doAddMyMovie(Object object, final WSManagerListener listener) {
        if (!(object instanceof FavorteMovieModel)) {
            return;
        }
        FavorteMovieModel favorteMovieModel  = (FavorteMovieModel) object;
        favorteMovieModel.toJSONString();
        WSTask task = new WSTask(this.context,new WSTask.WSTaskListener() {
            @Override
            public void onComplete(String response) {

                listener.onComplete(response);
            }

            @Override
            public void onError(String err) {
                listener.onError(err);
            }
        });

        Log.d("data ", favorteMovieModel.toJSONString());
        task.execute(context.getString(R.string.get_addMyMovie), favorteMovieModel.toJSONString());
    }
    public void doAllListMyMovie(Object object, final WSManagerListener listener) {
        if (!(object instanceof FavorteMovieModel)) {
            return;
        }
        FavorteMovieModel favorteMovieModel = (FavorteMovieModel) object;
        favorteMovieModel.toJSONString();
        WSTask task = new WSTask(this.context,new WSTask.WSTaskListener() {
            @Override
            public void onComplete(String response) {
                listener.onComplete(response);
            }

            @Override
            public void onError(String err) {
                listener.onError(err);
            }
        });
        Log.d("data ", favorteMovieModel.toJSONString());
        task.execute(context.getString(R.string.get_listMyMovie), favorteMovieModel.toJSONString());
    }
    public void doFaorteRemoves(Object object, final WSManagerListener listener) {
        if (!(object instanceof FavorteMovieModel)) {
            return;
        }
        FavorteMovieModel favorteMovieModel = (FavorteMovieModel) object;
        favorteMovieModel.toJSONString();
        WSTask task = new WSTask(this.context,new WSTask.WSTaskListener() {
            @Override
            public void onComplete(String response) {
                listener.onComplete(response);

            }

            @Override
            public void onError(String err) {
                listener.onError(err);
            }
        });
        Log.d("data ", favorteMovieModel.toJSONString());
        task.execute(context.getString(R.string.get_FavorteRemove), favorteMovieModel.toJSONString());
    }
}
