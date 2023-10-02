package com.example.test.ui.locations;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.test.R;
import com.example.test.api.APIClient;

import com.example.test.api.locationsApi;
import com.example.test.databinding.ActivityLocationsBinding;
import com.example.test.models.locations.VoLocations;
import com.example.test.utils.Dialogs;

import java.net.HttpURLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Victor on 20/11/15
 */
public class LocationIdActivity extends AppCompatActivity {

    private ActivityLocationsBinding binding;
    public locationsApi locationsApi;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private MaterialDialog dialog = null;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLocationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Recuperar información del identificador seleccionado
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Integer locationId = bundle.getInt("locationId");

            // Hacer petición del episodio seleccionado
            getLocationId(locationId);
        }

        clickButtons();
    }

    public void getLocationId(Integer locationId) {

        locationsApi = APIClient.getClient().create(locationsApi.class);

        LocationIdActivity.this.runOnUiThread(() -> {
            dialog = Dialogs.showDialogProgress(getResources().getString(R.string.loading), LocationIdActivity.this).build();
            if (dialog != null) dialog.show();
        });

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {

            Call<VoLocations> call = locationsApi.locationsById(locationId);
            call.enqueue(new Callback<VoLocations>() {
                @Override
                public void onResponse(@NonNull Call<VoLocations> call, @NonNull Response<VoLocations> response) {

                    handler.post(() -> {

                        LocationIdActivity.this.runOnUiThread(() -> {

                            switch (response.code()) {

                                case HttpURLConnection.HTTP_OK:

                                    binding.backLocationId.toolbarBackSimpleTitle.setText(response.body().getName());

                                    binding.locationsName.setText(response.body().getName());
                                    binding.locationsType.setText(String.format(getApplicationContext().getString(R.string.locationsType), response.body().getType()));
                                    binding.locationsDimension.setText(String.format(getApplicationContext().getString(R.string.locationDimension), response.body().getDimension()));

                                    binding.locationsCreated.setText(response.body().getCreated());

                                    /*
                                    // Utilizar un linearLayout
                                    layoutManager = new LinearLayoutManager(getApplicationContext());
                                    binding.episodesList.setHasFixedSize(false);
                                    binding.episodesList.setLayoutManager(layoutManager);

                                    // Especificar un adaptador
                                    adapter = new RecyclerEpisodesListAdapter(EpisodesIdActivity.this, getApplicationContext(), response.body().);
                                    binding.emptyEpisodesList.emptyText.setText(getResources().getString(R.string.message_empty_list));
                                    binding.episodesList.setEmptyView(binding.emptyEpisodesList.listEmpty);
                                    binding.episodesList.setAdapter(adapter);

                                    binding.episodesListRefreshLayout.setRefreshing(false);

                                    */
                                    break;

                                case HttpURLConnection.HTTP_UNAUTHORIZED:
                                    break;

                                case HttpURLConnection.HTTP_FORBIDDEN:
                                    break;

                                default:
                                    // Mostrar mensaje de error
                            }

                            if (dialog != null) dialog.dismiss();

                        });

                    });
                }

                @Override
                public void onFailure(@NonNull Call<VoLocations> call, @NonNull Throwable t) {
                    call.cancel();
                    if (dialog != null) dialog.dismiss();
                }

            });
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void clickButtons() {
        // Botón volver atrás
        binding.backLocationId.toolbarBackSimpleIcon.setOnClickListener(v -> finish());
    }
}