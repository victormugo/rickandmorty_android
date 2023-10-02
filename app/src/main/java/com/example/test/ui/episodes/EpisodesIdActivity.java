package com.example.test.ui.episodes;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.test.R;
import com.example.test.adapters.RecyclerCharactersListAdapter;
import com.example.test.api.APIClient;
import com.example.test.api.episodesApi;
import com.example.test.databinding.ActivityEpisodesBinding;
import com.example.test.models.episodes.VoEpisodes;
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
public class EpisodesIdActivity extends AppCompatActivity {

    private ActivityEpisodesBinding binding;
    public episodesApi episodesApi;

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
        binding = ActivityEpisodesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Recuperar información del identificador seleccionado
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Integer episodeId = bundle.getInt("episodeId");

            // Hacer petición del episodio seleccionado
            getEpisodeId(episodeId);
        }

        clickButtons();
    }

    public void getEpisodeId(Integer episodeId) {

        episodesApi = APIClient.getClient().create(episodesApi.class);

        EpisodesIdActivity.this.runOnUiThread(() -> {
            dialog = Dialogs.showDialogProgress(getResources().getString(R.string.loading), EpisodesIdActivity.this).build();
            if (dialog != null) dialog.show();
        });

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {

            Call<VoEpisodes> call = episodesApi.episodesById(episodeId);
            call.enqueue(new Callback<VoEpisodes>() {
                @Override
                public void onResponse(@NonNull Call<VoEpisodes> call, @NonNull Response<VoEpisodes> response) {

                    handler.post(() -> {

                        EpisodesIdActivity.this.runOnUiThread(() -> {

                            switch (response.code()) {

                                case HttpURLConnection.HTTP_OK:

                                    binding.backEpisodeId.toolbarBackSimpleTitle.setText(response.body().getName());

                                    // Petición al servidor para obtener listado de personajes
                                    /*binding.episodesName.setText(response.body().getName());
                                    binding.episodesAirDate.setText(String.format(getApplicationContext().getString(R.string.episodesAirCode), response.body().getAir_date()));
                                    binding.episodesEpisode.setText(String.format(getApplicationContext().getString(R.string.episodesEpisodeCode), response.body().getEpisode()));

                                    binding.episodesCreated.setText(response.body().getCreated());


                                    // Utilizar un linearLayout
                                    layoutManager = new LinearLayoutManager(getApplicationContext());
                                    binding.episodesCharactersList.setHasFixedSize(false);
                                    binding.episodesCharactersList.setLayoutManager(layoutManager);

                                    // Especificar un adaptador
                                    adapter = new RecyclerCharactersListAdapter(EpisodesIdActivity.this, getApplicationContext(), response.body().);
                                    binding.emptyEpisodesList.emptyText.setText(getResources().getString(R.string.message_empty_list));
                                    binding.episodesCharactersList.setEmptyView(binding.emptyEpisodesList.listEmpty);
                                    binding.episodesCharactersList.setAdapter(adapter);

                                    // binding.refre.setRefreshing(false);*/

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
                public void onFailure(@NonNull Call<VoEpisodes> call, @NonNull Throwable t) {
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
        binding.backEpisodeId.toolbarBackSimpleIcon.setOnClickListener(v -> finish());
    }
}