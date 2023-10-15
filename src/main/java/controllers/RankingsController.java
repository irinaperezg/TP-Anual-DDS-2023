package controllers;

import io.javalin.http.Context;
import server.utils.ICrudViewsHandler;
public class RankingsController implements ICrudViewsHandler {

  public RankingsController( ) {
      // TODO
    }
    @Override
    public void index(Context context) {
      //context.render("rankings.hbs");
    }

    @Override
    public void show(Context context) {
      //TODO
    }

    public void create(Context context) {
      //TODO
    }

    @Override
    public void save(Context context) {
      //TODO
    }

    @Override
    public void edit(Context context) {
      //TODO
    }

    @Override
    public void update(Context context) {
      //TODO
    }

    @Override
    public void delete(Context context) {
      //TODO
    }
}
