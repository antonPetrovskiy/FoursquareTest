package work.test.com.testwork;

import java.util.List;

import work.test.com.testwork.entity.Venues;

public class MapsPresenter {
    private MapsActivity view;
    private final MapsModel model;

    public MapsPresenter(MapsModel model) {
        this.model = model;
    }

    public void attachView(MapsActivity mapsActivity) {
        view = mapsActivity;
    }

    public void detachView() {
        view = null;
    }



    public void loadVenues() {
        model.loadVenues(new MapsModel.LoadVenuesCallback() {
            @Override
            public void onLoad(List<Venues> venues) {
                view.showVenues(venues);
            }
        });
    }



}
