package work.test.com.testwork;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import work.test.com.testwork.api.ApiService;
import work.test.com.testwork.api.RetroClient;
import work.test.com.testwork.entity.MyResponse;
import work.test.com.testwork.entity.Venues;

public class MapsModel {

    public List<Venues> venues = new LinkedList<>();

    public void loadVenues(LoadVenuesCallback callback) {
        LoadVenuesTask loadVenuesTask = new LoadVenuesTask(callback);
        loadVenuesTask.execute();
    }

    interface LoadVenuesCallback {
        void onLoad(List<Venues> venues);
    }

    class LoadVenuesTask extends AsyncTask<Void, Void, List<Venues>> {

        private final LoadVenuesCallback callback;

        LoadVenuesTask(LoadVenuesCallback callback) {
            this.callback = callback;
        }

        @Override
        protected List<Venues> doInBackground(Void... params) {
            String clientId = "ZZRAHSCQ2NTKRJAZ30MHSDCJBSHZ1BH123YDXUDBQRTZH4DU";
            String clientSecret = "3HKZULQLMAJJ1HGQYT22KDXUGYXHLDRN0K32HBEPVYZY11RU";
            final String v = "20180323";
            String count = "20";
            String ll = "50.4185,30.5510";
            ApiService api = RetroClient.getApiService();
            Call<MyResponse> call = api.getVenues(clientId,clientSecret,v,ll,count);

            call.enqueue(new Callback<MyResponse>() {
                @Override
                public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                    if (response.isSuccessful()) {
                        venues = response.body().getGroup().getGroups().get(0).getItems();
                        Log.i("GETSA" , venues.get(0).getVenue().getName()+"   OK");
                        Log.i("GETSA" , venues.get(1).getVenue().getName()+"   OK");
                        Log.i("GETSA" , venues.get(2).getVenue().getName()+"   OK");
                        Log.i("GETSA" , venues.get(3).getVenue().getName()+"   OK");
                        Log.i("GETSA" , venues.get(4).getVenue().getName()+"   OK");
                        Log.i("GETSA" , venues.get(5).getVenue().getName()+"   OK");
                        Log.i("GETSA" , venues.get(6).getVenue().getName()+"   OK");
                        Log.i("GETSA" , venues.get(7).getVenue().getName()+"   OK");
                        Log.i("GETSA" , venues.get(8).getVenue().getName()+"   OK");
                        Log.i("GETSA" , venues.get(9).getVenue().getName()+"   OK");
                        callback.onLoad(venues);
                    } else {
                        JSONObject jObjError = null;
                        try {
                            jObjError = new JSONObject(response.errorBody().string());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Log.i("GETSA" , jObjError.toString()+"1");

                    }
                }

                @Override
                public void onFailure(Call<MyResponse> call, Throwable t) {
                    Log.i("GETSA" , t.getMessage()+" ");
                }
            });

            return venues;
        }

        @Override
        protected void onPostExecute(List<Venues> users) {
            if (callback != null) {
                callback.onLoad(users);
            }
        }
    }
}
